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
import lupos.shared.inline.File
import lupos.simulator_core.Entity
import lupos.simulator_db.ApplicationLayerMergeMessages
import lupos.simulator_db.ApplicationLayerMultipleChilds
import lupos.simulator_db.DatabaseState
import lupos.simulator_db.dummyImpl.DatabaseSystemDummy
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.geo.GeoLocation
import lupos.simulator_iot.models.net.DeviceLinker
import lupos.simulator_iot.models.net.MeshNetwork
import lupos.simulator_iot.models.net.StarNetwork
import lupos.simulator_iot.models.sensor.ParkingSensor
import lupos.simulator_iot.queryproc.ApplicationLayerReceiveQueryResonse
import lupos.simulator_iot.queryproc.DatabaseAdapter
import lupos.simulator_iot.utils.FilePaths
import kotlin.math.round

public class Configuration(private val simRun: SimulationRun) {
    public var devices: MutableList<Device> = mutableListOf()

    private var namedAddresses: MutableMap<String, Int> = mutableMapOf()

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
        private set
    internal var dbDeviceAddressesQuery: IntArray = intArrayOf()
        private set

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
        linker.sortedLinkTypes = json!!.getOrEmptyObject("linkType").iterator().asSequence().map {
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
            val created = createDevice(fixedDevice.getOrDefault("deviceType", ""), location, nameID)
            require(namedAddresses[name] == null) { "name $name must be unique" }
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
        dbDeviceAddressesStore = devices.filter { it.hasDatabaseStore }.map { it.address }.toIntArray()
        dbDeviceAddressesQuery = devices.filter { it.hasDatabaseQuery }.map { it.address }.toIntArray()
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
            predecessor = createDevice(network.deviceType, location, nameID)
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
            predecessor = createDevice(deviceTypeName, location, nameID)
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
        return createDevice(network.deviceType, location, nameID)
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
            val leaf = createDevice(network.deviceType, location, childNameID)
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

    private fun createDevice(deviceTypeName: String, location: GeoLocation, nameIndex: Int): Device {
        val deviceTypes = json!!.getOrEmptyObject("deviceType")
        val deviceType = deviceTypes.getOrEmptyObject(deviceTypeName)
        val linkTypes = linker.getSortedLinkTypeIndices(deviceType.getOrEmptyArray("supportedLinkTypes").map { (it as JsonParserString).value }.toMutableList())
        require(deviceType.getOrDefault("performance", 100.0) != 0.0) { "The performance level of a device can not be 0.0 %" }
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
        val databaseStore = deviceType.getOrDefault("databaseStore", deviceType.getOrDefault("database", false))
        val databaseQuery = deviceType.getOrDefault("databaseQuery", deviceType.getOrDefault("database", false))
        if (databaseStore || databaseQuery) {
            numberOfDatabases++
            val initialState = {
                object : DatabaseState(
                    visualisationNetwork = device.simRun.visualisationNetwork,
                    ownAddress = device.address,
                    allAddressesStore = device.simRun.config.dbDeviceAddressesStore,
                    allAddressesQuery = device.simRun.config.dbDeviceAddressesQuery,
                    absolutePathToDataDirectory = "${FilePaths.dbStates}/device${device.address}",
                ) {}
            }
            val adapter = DatabaseAdapter(device)
            val mergeMessages = ApplicationLayerMergeMessages(adapter)
            val multiChilds = ApplicationLayerMultipleChilds(mergeMessages)

            val db = when (device.simRun.config.jsonObjects.database.getOrDefault("type", "Dummy")) {
                "Dummy" -> {
                    DatabaseSystemDummy(device.simRun.config.jsonObjects.database, multiChilds, initialState)
                    ApplicationLayerReceiveQueryResonse(multiChilds)
                }
                "Luposdate3000" -> {
                    DatabaseHandle(multiChilds, device.simRun.config.jsonObjects.database, initialState)
                    ApplicationLayerReceiveQueryResonse(multiChilds)
                }
                else -> TODO()
            }

            device.userApplication = adapter
            device.hasDatabaseStore = databaseStore
            device.hasDatabaseQuery = databaseQuery
        }

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
