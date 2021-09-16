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
import lupos.parser.JsonParserArray
import lupos.parser.JsonParserObject
import lupos.parser.JsonParserString
import lupos.shared.SanityCheck
import lupos.shared.inline.File
import lupos.simulator_core.Entity
import lupos.simulator_db.IApplicationStack_Actuator
import lupos.simulator_db.IPackage_Database
import lupos.simulator_db.dummyImpl.Application_DatabaseDummy
import lupos.simulator_db.luposdate3000.Application_Luposdate3000
import lupos.simulator_iot.LoggerMeasure
import lupos.simulator_iot.LoggerStdout
import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.applications.ApplicationStack_Logger
import lupos.simulator_iot.applications.Application_ParkingSensor
import lupos.simulator_iot.applications.Application_QuerySender
import lupos.simulator_iot.applications.Application_ReceiveParkingSample
import lupos.simulator_iot.applications.Application_ReceiveQueryResponse
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.geo.GeoLocation
import lupos.simulator_iot.models.net.DeviceLinker
import lupos.visualize.distributed.database.VisualisationNetwork
import kotlin.math.round

public class Configuration(private val simRun: SimulationRun) {
    public companion object {
        public val defaultOutputDirectory: String = "simulator_output/"
    }
    public var devices: MutableList<Device> = mutableListOf()
    private var namedAddresses: MutableMap<String, Int> = mutableMapOf()
    public var outputDirectory: String = defaultOutputDirectory
    public var jsonObjects: JsonObjects = JsonObjects()
        private set
    public var json: JsonParserObject? = null

    internal var dbDeviceAddressesStore: IntArray = intArrayOf()
    internal var dbDeviceAddressesQuery: IntArray = intArrayOf()
    private val dbDeviceAddressesStoreList = mutableListOf<Int>()
    private val dbDeviceAddressesQueryList = mutableListOf<Int>()

    public fun addQuerySender(
        startClockInSec: Int,
        sendRateInSec: Int,
        maxNumberOfQueries: Int,
        query: String,
        receiver: Int = rootRouterAddress
    ) {
        val sender = Application_QuerySender(startClockInSec, sendRateInSec, maxNumberOfQueries, query, receiver)
        val device = getDeviceByAddress(receiver)
        device.allApplications.addChild(sender)
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
        device.allApplications.addChild(sender)
    }

    private var rootRouterAddress: Int = -1

    internal var numberOfDatabases = 0
        private set

    internal var numberOfSensors = 0
        private set

    private var deviceNames: MutableList<String> = mutableListOf()

    internal var linker = DeviceLinker()
        private set

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
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/config/Configuration.kt:132"/*SOURCE_FILE_END*/ },
                { namedAddresses[name] == null },
                { "name $name must be unique" }
            )
            namedAddresses[name] = created.address
        }
        val rootRouterName = json.getOrDefault("rootRouter", "")
        if (rootRouterName.isNotEmpty()) {
            val device = getDeviceByName(rootRouterName)
            device.userApplication.setRoot()
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
        dbDeviceAddressesStore = dbDeviceAddressesStoreList.toIntArray()
        dbDeviceAddressesQuery = dbDeviceAddressesQueryList.toIntArray()
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
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/config/Configuration.kt:265"/*SOURCE_FILE_END*/ },
            { deviceType.getOrDefault("performance", 100.0) > 0.0 },
            { "The performance level of a device can not be 0.0 %" },
        )
        var databaseStore = false
        var databaseQuery = false
        var hasSensor = false
        for ((applicationName, applicationJson) in jsonApplicationsEffective) {
            when (applicationName) {
                "DummyDatabase" -> {
                    applicationJson as JsonParserObject
                    numberOfDatabases++
                    databaseStore = true
                    databaseQuery = true
                    dbDeviceAddressesStoreList.add(ownAddress)
                    dbDeviceAddressesQueryList.add(ownAddress)
                    applications.add(
                        Application_DatabaseDummy(
                            applicationJson,
                            simRun.logger,
                            ownAddress,
                            "$outputDirectory/db_states/device$ownAddress",
                            dbDeviceAddressesStoreList,
                            dbDeviceAddressesQueryList,
                        )
                    )
                }
                "Luposdate3000" -> {
                    applicationJson as JsonParserObject
                    numberOfDatabases++
                    databaseQuery = applicationJson.getOrDefault("databaseQuery", true)
                    databaseStore = applicationJson.getOrDefault("databaseStore", true) || !databaseQuery // at least one must be true
                    if (databaseStore) {
                        dbDeviceAddressesStoreList.add(ownAddress)
                    }
                    if (databaseQuery) {
                        dbDeviceAddressesQueryList.add(ownAddress)
                    }
                    applications.add(
                        Application_Luposdate3000(
                            applicationJson,
                            simRun.logger,
                            ownAddress,
                            "$outputDirectory/db_states/device$ownAddress",
                            dbDeviceAddressesStoreList,
                            dbDeviceAddressesQueryList,
                        )
                    )
                }
                "QueryResponseReceiver" -> {
                    applications.add(Application_ReceiveQueryResponse(outputDirectory + "/"))
                }
                "ParkingSampleReceiver" -> {
                    applications.add(Application_ReceiveParkingSample(ownAddress))
                }
                "QuerySender" -> {
                    applicationJson as JsonParserArray
                    for (it in applicationJson) {
                        it as JsonParserObject
                        applications.add(
                            Application_QuerySender(
                                it.getOrDefault("sendStartClockInSec", 0),
                                it.getOrDefault("sendRateInSec", 1),
                                it.getOrDefault("maxNumberOfQueries", 1),
                                it.getOrDefault("query", ""),
                                ownAddress,
                            )
                        )
                    }
                }
                "ParkingSensor" -> {
                    applicationJson as JsonParserObject
                    hasSensor = true
                    numberOfSensors++
                    applications.add(
                        Application_ParkingSensor(
                            applicationJson.getOrDefault("sendStartClockInSec", 0),
                            applicationJson.getOrDefault("rateInSec", 0),
                            applicationJson.getOrDefault("maxSamples", -1),
                            applicationJson.getOrDefault("dataSink", ""),
                            ownAddress,
                            simRun.randGenerator,
                            applicationJson.getOrDefault("area", 0),
                        )
                    )
                }
                else -> TODO("unknown application '$applicationName'")
            }
        }
        val device = Device(
            simRun,
            location,
            ownAddress,
            deviceType.getOrDefault("performance", 100.0),
            linkTypes,
            nameIndex,
            json!!.getOrDefault("deterministic", true),
            applications.map { it -> ApplicationStack_Logger(ownAddress, simRun.logger, it) }.toTypedArray(),
            namedAddresses,
        )
        simRun.logger.addDevice(ownAddress, location.longitude, location.latitude, databaseStore, databaseQuery, hasSensor)
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
