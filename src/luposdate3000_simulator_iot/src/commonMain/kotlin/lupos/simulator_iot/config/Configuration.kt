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
import lupos.simulator_db.ApplicationLayerCatchSelfMessages
import lupos.simulator_db.ApplicationLayerLogger
import lupos.simulator_db.ApplicationLayerMergeMessages
import lupos.simulator_db.ApplicationLayerMultipleChilds
import lupos.simulator_db.ApplicationLayerSequence
import lupos.simulator_db.DatabaseState
import lupos.simulator_db.IUserApplication
import lupos.simulator_db.dummyImpl.DatabaseSystemDummy
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_iot.LoggerMeasure
import lupos.simulator_iot.LoggerStdout
import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.geo.GeoLocation
import lupos.simulator_iot.models.net.DeviceLinker
import lupos.simulator_iot.models.net.MeshNetwork
import lupos.simulator_iot.models.net.StarNetwork
import lupos.simulator_iot.models.sensor.ParkingSensor
import lupos.simulator_iot.queryproc.ApplicationLayerReceiveParkingSample
import lupos.simulator_iot.queryproc.ApplicationLayerReceiveQueryResonse
import lupos.simulator_iot.queryproc.DatabaseAdapter
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
    internal var randStarNetworks: MutableMap<String, StarNetwork> = mutableMapOf()
        private set

    internal var randMeshNetworks: MutableMap<String, MeshNetwork> = mutableMapOf()
        private set

    public var querySenders: MutableList<lupos.simulator_iot.queryproc.QuerySender> = mutableListOf()
        private set

    internal var dbDeviceAddressesStore: IntArray = intArrayOf()
    internal var dbDeviceAddressesQuery: IntArray = intArrayOf()
    private var dbDeviceAddressesStoreList = mutableListOf<Int>()
    private var dbDeviceAddressesQueryList = mutableListOf<Int>()

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
        linker.sortedLinkTypes = json.getOrEmptyObject("linkType").iterator().asSequence().map {
            val v = it.second
            v as JsonParserObject
            LinkType(
                it.first,
                v.getOrDefault("rangeInMeters", 0),
                v.getOrDefault("dataRateInKbps", 0),
            )
        }.toList<LinkType>().toTypedArray()
        for ((name, fixedDevice) in json.getOrEmptyObject("fixedDevice")) {
            fixedDevice as JsonParserObject
            val location = GeoLocation(fixedDevice.getOrDefault("latitude", 0.0), fixedDevice.getOrDefault("longitude", 0.0))
            val nameID = addDeviceName(name)
            val created = createDevice(fixedDevice.getOrDefault("deviceType", ""), location, nameID, fixedDevice)
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/config/Configuration.kt:118"/*SOURCE_FILE_END*/ },
                { namedAddresses[name] == null },
                { "name $name must be unique" }
            )
            namedAddresses[name] = created.address
        }
        val rootRouterName = json.getOrDefault("rootRouter", "")
        if (rootRouterName.isNotEmpty()) {
            val device = getDeviceByName(rootRouterName)
            device.router.isRoot = true
            rootRouterAddress = device.address
        }
        for (sender in jsonObjects.querySender) {
            val receiverDevice = getDeviceByName(rootRouterName)
            val querySender = lupos.simulator_iot.queryproc.QuerySender(
                simRun = simRun,
                name = sender.name,
                sendRateInSec = sender.sendRateInSeconds,
                maxNumberOfQueries = sender.maxNumberOfQueries,
                startClockInSec = sender.sendStartClockInSec,
                receiver = receiverDevice,
                query = sender.query
            )
            querySenders.add(querySender)
        }
        for (l in jsonObjects.fixedLink) {
            val a = getDeviceByName(l.fixedDeviceA)
            val b = getDeviceByName(l.fixedDeviceB)
            linker.link(a, b, l.dataRateInKbps)
        }
        for (network in jsonObjects.randomMeshNetwork) {
            createRandomMeshNetwork(network)
        }
        for (network in jsonObjects.randomStarNetwork) {
            createRandomStarNetwork(network)
        }
        linker.createAvailableLinks(devices)
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
        entities.addAll(querySenders)
        return entities
    }

    private fun createRandomMeshNetwork(network: RandomMeshNetwork) {
        val origin = createMeshOriginDevice(network)
        val meshNetwork = MeshNetwork()
        meshNetwork.networkPrefix = network.networkPrefix
        val nameID = addDeviceName("${network.networkPrefix}_member")
        val linkType = linker.getLinkByName(network.linkType)

        var column = createSouthernDevices(origin, linkType, network, network.deviceType, nameID)
        meshNetwork.mesh[0] = column
        var restCoverageEast = network.signalCoverageEast - linkType.rangeInMeters
        var predecessor = origin
        while (restCoverageEast > 0) {
            val distance = getRandomDistance(linkType.rangeInMeters)
            val location = GeoLocation.createEasternLocation(predecessor.location, distance)
            predecessor = createDevice(network.deviceType, location, nameID, null)
            column = createSouthernDevices(predecessor, linkType, network, network.deviceType, nameID)
            meshNetwork.mesh.add(column)

            restCoverageEast -= distance
        }

        randMeshNetworks[network.networkPrefix] = meshNetwork
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
        val starNetwork = StarNetwork(root)
        starNetwork.networkPrefix = network.networkPrefix
        val childNameID = addDeviceName("${starNetwork.networkPrefix}_child")
        val linkType = linker.getLinkByName(network.linkType)
        for (i in 1..network.number) {
            val location = GeoLocation.getRandomLocationInRadius(root.location, linkType.rangeInMeters, simRun.randGenerator.random)
            val leaf = createDevice(network.deviceType, location, childNameID, null)
            linker.linkIfPossible(root, leaf)
            leaf.isStarNetworkChild = true
            starNetwork.children.add(leaf)
        }
        randStarNetworks[network.networkPrefix] = starNetwork
    }

    private fun addDeviceName(name: String): Int {
        deviceNames.add(name)
        return deviceNames.size - 1
    }

    private fun createDevice(deviceTypeName: String, location: GeoLocation, nameIndex: Int, jsonFixed: JsonParserObject?): Device {
        val applications = mutableListOf<IUserApplication>()

        val deviceTypes = json!!.getOrEmptyObject("deviceType")
        val deviceType = deviceTypes.getOrEmptyObject(deviceTypeName)

        val jsonApplicationsEffective = json!!.getOrEmptyObject("applications").cloneJson()
        jsonApplicationsEffective.mergeWith(deviceType.getOrEmptyObject("applications").cloneJson())
        if (jsonFixed != null) {
            jsonApplicationsEffective.mergeWith(jsonFixed.getOrEmptyObject("applications").cloneJson())
        }

        val linkTypes = linker.getSortedLinkTypeIndices(deviceType.getOrEmptyArray("supportedLinkTypes").map { (it as JsonParserString).value }.toMutableList())
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/config/Configuration.kt:272"/*SOURCE_FILE_END*/ },
            { deviceType.getOrDefault("performance", 100.0) > 0.0 },
            { "The performance level of a device can not be 0.0 %" },
        )
        val device = Device(simRun, location, devices.size, null, deviceType.getOrDefault("performance", 100.0), linkTypes, nameIndex, jsonObjects.deterministic)
        val sensorTypeName = deviceType.getOrDefault("parkingSensor", "")
        if (sensorTypeName.isNotEmpty()) {
            numberOfSensors++
            val sensorTypes = json!!.getOrEmptyObject("sensorType")
            val sensorType = sensorTypes.getOrEmptyObject(sensorTypeName)
            device.sensor = ParkingSensor(
                device,
                sensorType.getOrDefault("rateInSec", 0),
                sensorType.getOrDefault("maxSamples", -1),
                sensorType.getOrDefault("dataSink", ""),
                sensorType.getOrDefault("area", 0),
            )
        }
        var databaseStore = false
        var databaseQuery = false
        for ((applicationName, applicationJson) in jsonApplicationsEffective) {
            applicationJson as JsonParserObject
            when (applicationName) {
                "DummyDatabase" -> {
                    databaseStore = true
                    databaseQuery = true
                    val initialState = {
                        object : DatabaseState(
                            logger = simRun.logger,
                            ownAddress = device.address,
                            allAddressesStore = dbDeviceAddressesStore,
                            allAddressesQuery = dbDeviceAddressesQuery,
                            absolutePathToDataDirectory = "$outputDirectory/db_states/device${device.address}",
                        ) {
                            init {
                                File(absolutePathToDataDirectory).mkdirs()
                            }
                        }
                    }
                    applications.add(DatabaseSystemDummy(applicationJson, initialState))
                }
                "QueryResponseReceiver" -> applications.add(ApplicationLayerReceiveQueryResonse(outputDirectory + "/"))
                "ParkingSampleReceiver" -> applications.add(ApplicationLayerReceiveParkingSample(device.address))
                "Luposdate3000" -> {
                    databaseStore = applicationJson.getOrDefault("databaseStore", true)
                    databaseQuery = applicationJson.getOrDefault("databaseQuery", true)
                    if (databaseStore || databaseQuery) {
                        numberOfDatabases++
                        val initialState = {
                            object : DatabaseState(
                                logger = simRun.logger,
                                ownAddress = device.address,
                                allAddressesStore = dbDeviceAddressesStore,
                                allAddressesQuery = dbDeviceAddressesQuery,
                                absolutePathToDataDirectory = "$outputDirectory/db_states/device${device.address}",
                            ) {
                                init {
                                    File(absolutePathToDataDirectory).mkdirs()
                                }
                            }
                        }
                        applications.add(DatabaseHandle(applicationJson, initialState))
                        if (databaseStore) {
                            dbDeviceAddressesStoreList.add(device.address)
                        }
                        if (databaseQuery) {
                            dbDeviceAddressesQueryList.add(device.address)
                        }
                    }
                }
                else -> TODO("unknown application '$applicationName'")
            }
        }
        simRun.logger.addDevice(device.address, location.longitude, location.latitude, databaseStore, databaseQuery, sensorTypeName.isNotEmpty())
        device.userApplication = DatabaseAdapter(
            device,
            ApplicationLayerSequence(
                device.address,
                ApplicationLayerMergeMessages(
                    ApplicationLayerCatchSelfMessages(
                        device.address,
                        ApplicationLayerMultipleChilds(
                            applications.map { it -> ApplicationLayerLogger(device.address, simRun.logger, it) }.toTypedArray()
                        )
                    )
                )
            )
        )
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
