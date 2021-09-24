/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package lupos.simulator_iot.config
import lupos.parser.JsonParser
import lupos.parser.JsonParserObject
import lupos.parser.JsonParserString
import lupos.shared.SanityCheck
import lupos.shared.inline.File
import lupos.simulator_core.Entity
import lupos.simulator_db.IApplicationStack_Actuator
import lupos.simulator_db.IApplication_Factory
import lupos.simulator_db.IPackage_Database
import lupos.simulator_iot.LoggerMeasure
import lupos.simulator_iot.LoggerStdout
import lupos.simulator_iot.ReflectionHelper
import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.applications.ApplicationStack_AllShortestPath
import lupos.simulator_iot.applications.ApplicationStack_CatchSelfMessages
import lupos.simulator_iot.applications.ApplicationStack_Logger
import lupos.simulator_iot.applications.ApplicationStack_MergeMessages
import lupos.simulator_iot.applications.ApplicationStack_MultipleChilds
import lupos.simulator_iot.applications.ApplicationStack_RPL
import lupos.simulator_iot.applications.ApplicationStack_RPL_Fast
import lupos.simulator_iot.applications.ApplicationStack_Sequence
import lupos.simulator_iot.applications.Application_QuerySender
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.geo.GeoLocation
import lupos.simulator_iot.models.net.DeviceLinker
import lupos.simulator_iot.models.net.LinkManager
import lupos.visualize.distributed.database.VisualisationNetwork
import kotlin.math.round

public class Configuration(private val simRun: SimulationRun) {
    public companion object {
        public val defaultOutputDirectory: String = "simulator_output/"
    }
    private val factories = mutableMapOf<String, IApplication_Factory>()
private val features=mutableListOf<IApplicationStack_Feature>()

    public var devices: MutableList<Device> = mutableListOf()
    private var namedAddresses: MutableMap<String, Int> = mutableMapOf()
    public var outputDirectory: String = defaultOutputDirectory
    public var jsonObjects: JsonObjects = JsonObjects()
        private set
    public var json: JsonParserObject? = null

    private var rootRouterAddress: Int = -1

    private var deviceNames: MutableList<String> = mutableListOf()

    internal var linker = DeviceLinker()
        private set

    public fun addQuerySender(
        startClockInSec: Int,
        sendRateInSec: Int,
        maxNumberOfQueries: Int,
        query: String,
        receiver: Int = rootRouterAddress
    ) {
        val sender = Application_QuerySender(startClockInSec, sendRateInSec, maxNumberOfQueries, query, receiver)
        val device = getDeviceByAddress(receiver)
        device.applicationStack.addChildApplication(sender)
    }
    public fun addQuerySender(
        startClockInSec: Int,
        sendRateInSec: Int,
        maxNumberOfQueries: Int,
        queryPck: IPackage_Database,
        receiver: Int = rootRouterAddress
    ) {
        val sender = Application_QuerySender(startClockInSec, sendRateInSec, maxNumberOfQueries, queryPck, receiver)
        val device = getDeviceByAddress(receiver)
        device.applicationStack.addChildApplication(sender)
    }

    internal fun parse(json: JsonParserObject, fileName: String, autocorrect: Boolean = true) {
        this.json = json
        jsonObjects = JsonObjects(json)
        outputDirectory = json.getOrDefault("outputDirectory", defaultOutputDirectory) + "/"
        if (outputDirectory == "") {
            outputDirectory = defaultOutputDirectory
            json.set("outputDirectory", defaultOutputDirectory)
        }
        val jsonLoggers = json.getOrEmptyObject("logging")
        if (jsonLoggers.getOrEmptyObject("stdout").getOrDefault("enabled", false)) {
            simRun.logger.loggers.add(LoggerStdout(simRun))
        }
        if (jsonLoggers.getOrEmptyObject("visualize").getOrDefault("enabled", false)) {
            simRun.logger.loggers.add(VisualisationNetwork(outputDirectory, { simRun.sim.clock }))
        }
        if (jsonLoggers.getOrEmptyObject("measure").getOrDefault("enabled", false)) {
            simRun.logger.loggers.add(LoggerMeasure(simRun))
        }
// load all link types --->>>
        linker.setLinkTypes(
            json.getOrEmptyObject("linkType").iterator().asSequence().map {
                val v = it.second
                v as JsonParserObject
                LinkType(
                    it.first,
                    v.getOrDefault("rangeInMeters", 0),
                    v.getOrDefault("dataRateInKbps", 0),
                )
            }.toList<LinkType>().toTypedArray()
        )
// load all link types <<<---
        for ((name, fixedDevice) in json.getOrEmptyObject("fixedDevice")) {
            fixedDevice as JsonParserObject
            val location = GeoLocation(fixedDevice.getOrDefault("latitude", 0.0), fixedDevice.getOrDefault("longitude", 0.0))
            val nameID = addDeviceName(name)
            val created = createDevice(fixedDevice.getOrDefault("deviceType", ""), location, nameID, fixedDevice)
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/config/Configuration.kt:127"/*SOURCE_FILE_END*/ },
                { namedAddresses[name] == null },
                { "name $name must be unique" }
            )
            namedAddresses[name] = created.address
        }
        val rootRouterName = json.getOrDefault("rootRouter", "")
        if (rootRouterName.isNotEmpty()) {
            val device = getDeviceByName(rootRouterName)
            device.applicationStack.setRoot()
            rootRouterAddress = device.address
        }
// assign all static links --->>>
        for (l in json.getOrEmptyArray("fixedLink")) {
            l as JsonParserObject
            val a = getDeviceByName(l.getOrDefault("fixedDeviceA", ""))
            val b = getDeviceByName(l.getOrDefault("fixedDeviceB", ""))
            linker.link(a, b, l.getOrDefault("dataRateInKbps", 0))
        }
// assign all static links <<<---
        for (network in jsonObjects.randomMeshNetwork) {
            createRandomMeshNetwork(network)
        }
        for (network in jsonObjects.randomStarNetwork) {
            createRandomStarNetwork(network)
        }
// assign all dynamic links --->>>
        linker.createAvailableLinks(devices)
// assign all dynamic links <<<---
        if (autocorrect) {
            File(fileName).withOutputStream { out ->
                out.println(JsonParser().jsonToString(json, false))
            }
        }
    }

    internal fun parse(fileName: String, autocorrect: Boolean = true) {
        val fileStr = File(fileName).readAsString()
        val json = JsonParser().stringToJson(fileStr) as JsonParserObject
        parse(json, fileName, autocorrect)
    }

    public fun getEntities(): MutableList<Entity> {
        val entities: MutableList<Entity> = mutableListOf()
        entities.addAll(devices)
        return entities
    }

    private fun createRandomMeshNetwork(network: RandomMeshNetwork) {
        val origin = createMeshOriginDevice(network)
        val nameID = addDeviceName("${network.networkPrefix}_member")
        val linkType = linker.getLinkByName(network.linkType)
        var column = createSouthernDevices(origin, linkType, network, network.deviceType, nameID)
        var restCoverageEast = network.signalCoverageEast - linkType.rangeInMeters
        var predecessor = origin
        while (restCoverageEast > 0) {
            val distance = getRandomDistance(linkType.rangeInMeters)
            val location = GeoLocation.createEasternLocation(predecessor.location, distance)
            predecessor = createDevice(network.deviceType, location, nameID, null)
            column = createSouthernDevices(predecessor, linkType, network, network.deviceType, nameID)
            restCoverageEast -= distance
        }
    }

    private fun createSouthernDevices(origin: Device, linkType: LinkType, network: RandomMeshNetwork, deviceTypeName: String, nameID: Int): MutableList<Device> {
        val column = mutableListOf<Device>()
        var restCoverageSouth = network.signalCoverageSouth - linkType.rangeInMeters
        column.add(origin)
        var predecessor = origin
        while (restCoverageSouth > 0) {
            val distance = getRandomDistance(linkType.rangeInMeters)
            val location = GeoLocation.createSouthernLocation(predecessor.location, distance)
            predecessor = createDevice(deviceTypeName, location, nameID, null)
            column.add(predecessor)
            restCoverageSouth -= distance
        }

        return column
    }

    private fun getRandomDistance(maxDistance: Int): Int {
        val density = 0.7
        val percentage = round(maxDistance * density).toInt()
        return simRun.randGenerator.getInt(percentage, maxDistance)
    }

    private fun createMeshOriginDevice(network: RandomMeshNetwork): Device {
        val location = GeoLocation(network.originLatitude, network.originLongitude)
        val nameID = addDeviceName("${network.networkPrefix}_origin")
        return createDevice(network.deviceType, location, nameID, null)
    }

    internal fun getDeviceByName(name: String): Device {
        val index = namedAddresses[name]!!
        return devices[index]
    }

    internal fun getDeviceName(nameIndex: Int) = deviceNames[nameIndex]
    internal fun getRootDevice(): Device = devices[rootRouterAddress]

    private fun createRandomStarNetwork(network: RandomStarNetwork) {
        val root = getDeviceByName(network.starRoot)
        val childNameID = addDeviceName("${network.networkPrefix}_child")
        val linkType = linker.getLinkByName(network.linkType)
        for (i in 1..network.number) {
            val location = GeoLocation.getRandomLocationInRadius(root.location, linkType.rangeInMeters, simRun.randGenerator.random)
            val leaf = createDevice(network.deviceType, location, childNameID, null)
            linker.linkIfPossible(root, leaf)
            leaf.isStarNetworkChild = true
        }
    }

    private fun addDeviceName(name: String): Int {
        deviceNames.add(name)
        return deviceNames.size - 1
    }

    private fun createDevice(deviceTypeName: String, location: GeoLocation, nameIndex: Int, jsonFixed: JsonParserObject?): Device {
        val ownAddress = devices.size
        val applications = mutableListOf<IApplicationStack_Actuator>()

        val deviceTypes = json!!.getOrEmptyObject("deviceType")
        val deviceType = deviceTypes.getOrEmptyObject(deviceTypeName)

        val jsonApplicationsEffective = json!!.getOrEmptyObject("applications").cloneJson()
        jsonApplicationsEffective.mergeWith(deviceType.getOrEmptyObject("applications").cloneJson())
        if (jsonFixed != null) {
            jsonApplicationsEffective.mergeWith(jsonFixed.getOrEmptyObject("applications").cloneJson())
        }
        val linkTypes = linker.getSortedLinkTypeIndices(deviceType.getOrEmptyArray("supportedLinkTypes").map { (it as JsonParserString).value }.toMutableList())
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/config/Configuration.kt:258"/*SOURCE_FILE_END*/ },
            { deviceType.getOrDefault("performance", 100.0) > 0.0 },
            { "The performance level of a device can not be 0.0 %" },
        )
        for ((applicationName, applicationJson) in jsonApplicationsEffective) {
            var factory = factories[applicationName]
            if (factory == null) {
                factory = ReflectionHelper.createApplicationFactory(applicationName)
factory.registerFeatures(features)
                factories[applicationName] = factory
            }
            applications.addAll(
                factory.create(
                    applicationJson,
                    ownAddress,
                    simRun.logger,
                    outputDirectory,
                    simRun.randGenerator
                )
            )
        }
        val applicationStack = ApplicationStack_Sequence(
            ownAddress,
            ApplicationStack_MergeMessages(
                ApplicationStack_CatchSelfMessages(
                    ownAddress,
                    ApplicationStack_MultipleChilds(applications.map { it -> ApplicationStack_Logger(ownAddress, simRun.logger, it) }.toTypedArray()),
                )
            )
        )
        val jsonRouting = json!!.getOrEmptyObject("routing")
        val router = when (jsonRouting.getOrDefault("protocol", "RPL")) {
            "AllShortestPath" -> ApplicationStack_AllShortestPath(
                applicationStack,
                simRun.logger,
                simRun.config,
            )
            "RPL_Fast" -> ApplicationStack_RPL_Fast(
                applicationStack,
                simRun.logger,
                simRun.config,
                jsonRouting.getOrDefault("compatibilityMode", false),
            )
            "RPL" -> ApplicationStack_RPL(
                applicationStack,
                simRun.logger,
                simRun.config,
            )
            else -> TODO("unknown routing.protocol '${jsonRouting.getOrDefault("protocol", "RPL")}'")
        }
        val device = Device(
            simRun,
            location,
            ownAddress,
            deviceType.getOrDefault("performance", 100.0),
            LinkManager(linkTypes),
            nameIndex,
            json!!.getOrDefault("deterministic", true),
            router,
            namedAddresses,
        )
        simRun.logger.addDevice(ownAddress, location.longitude, location.latitude)
        devices.add(device)
        return device
    }

    internal fun getNumberOfDevices(): Int {
        return devices.size
    }

    internal fun getDeviceByAddress(address: Int): Device {
        return devices[address]
    }
}
