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
import lupos.shared.inline.File
import lupos.simulator_core.Entity
import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.geo.GeoLocation
import lupos.simulator_iot.models.net.DeviceLinker
import lupos.simulator_iot.models.net.MeshNetwork
import lupos.simulator_iot.models.net.StarNetwork
import lupos.simulator_iot.models.sensor.ParkingSensor
import lupos.simulator_iot.queryproc.DatabaseAdapter
import kotlin.math.round

public class Configuration(private val simRun: SimulationRun) {
    public var devices: MutableList<Device> = mutableListOf()

    private var namedAddresses: MutableMap<String, Int> = mutableMapOf()

    public var jsonObjects: JsonObjects = JsonObjects()
        private set

    internal var randStarNetworks: MutableMap<String, StarNetwork> = mutableMapOf()
        private set

    internal var randMeshNetworks: MutableMap<String, MeshNetwork> = mutableMapOf()
        private set

    public var querySenders: MutableList<lupos.simulator_iot.queryproc.QuerySender> = mutableListOf()
        private set

    internal var dbDeviceAddresses: IntArray = intArrayOf()
        private set

    private var rootRouterAddress: Int = -1

    internal var numberOfDatabases = 0
        private set

    internal var numberOfSensors = 0
        private set

    private var deviceNames: MutableList<String> = mutableListOf()

    internal var linker = DeviceLinker()
        private set

    private var hadParse = false

    internal fun parse(jsonObjects: JsonObjects) {
        require(!hadParse)
        hadParse = true
        initVariables(jsonObjects)
        createFixedDevices()
        setRootDevice()
        createQuerySenders()
        createFixedLinks()
        createRandomMeshNetworks()
        createRandomStarNetworks()
        linker.createAvailableLinks(devices)
        createDbDeviceAddresses()
    }

    internal fun parse(fileName: String) {
        val fileStr = File(fileName).readAsString()
        val json = JsonParser().stringToJson(fileStr) as JsonParserObject
        parse(json)
    }
    internal fun parse(json: JsonParserObject) {
        parse(JsonObjects(json))
    }

    private fun initVariables(jsonObjects: JsonObjects) {
        this.jsonObjects = jsonObjects
        linker.sortedLinkTypes = jsonObjects.linkType.toTypedArray()
    }

    public fun getEntities(): MutableList<Entity> {
        val entities: MutableList<Entity> = mutableListOf()
        entities.addAll(devices)
        entities.addAll(querySenders)
        return entities
    }

    private fun createRandomMeshNetworks() {
        for (network in jsonObjects.randomMeshNetwork)
            createRandomMeshNetwork(network)
    }

    private fun createRandomStarNetworks() {
        for (network in jsonObjects.randomStarNetwork)
            createRandomStarNetwork(network)
    }

    private fun createQuerySenders() {
        for (sender in jsonObjects.querySender)
            createQuerySender(sender)
    }

    private fun createRandomMeshNetwork(network: RandomMeshNetwork) {
        val origin = createMeshOriginDevice(network)
        val meshNetwork = MeshNetwork()
        meshNetwork.networkPrefix = network.networkPrefix
        val nameID = addDeviceName("${network.networkPrefix}_member")
        val linkType = getLinkTypeByName(network.linkType)
        val deviceType = getDeviceTypeByName(network.deviceType)

        var column = createSouthernDevices(origin, linkType, network, deviceType, nameID)
        meshNetwork.mesh[0] = column
        var restCoverageEast = network.signalCoverageEast - linkType.rangeInMeters
        var predecessor = origin
        while (restCoverageEast > 0) {
            val distance = getRandomDistance(linkType.rangeInMeters)
            val location = GeoLocation.createEasternLocation(predecessor.location, distance)
            predecessor = createDevice(deviceType, location, nameID)
            column = createSouthernDevices(predecessor, linkType, network, deviceType, nameID)
            meshNetwork.mesh.add(column)

            restCoverageEast -= distance
        }

        randMeshNetworks[network.networkPrefix] = meshNetwork
    }

    private fun createSouthernDevices(origin: Device, linkType: LinkType, network: RandomMeshNetwork, deviceType: DeviceType, nameID: Int): MutableList<Device> {
        val column = mutableListOf<Device>()
        var restCoverageSouth = network.signalCoverageSouth - linkType.rangeInMeters
        column.add(origin)
        var predecessor = origin
        while (restCoverageSouth > 0) {
            val distance = getRandomDistance(linkType.rangeInMeters)
            val location = GeoLocation.createSouthernLocation(predecessor.location, distance)
            predecessor = createDevice(deviceType, location, nameID)
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
        val deviceType = getDeviceTypeByName(network.deviceType)
        val location = GeoLocation(network.originLatitude, network.originLongitude)
        val nameID = addDeviceName("${network.networkPrefix}_origin")
        return createDevice(deviceType, location, nameID)
    }

    internal fun getDeviceByName(name: String): Device {
        val index = namedAddresses.getValue(name)
        return devices[index]
    }

    internal fun getDeviceName(nameIndex: Int) =
        deviceNames[nameIndex]

    internal fun getRootDevice(): Device = devices[rootRouterAddress]

    private fun createRandomStarNetwork(network: RandomStarNetwork) {
        val root = getDeviceByName(network.starRoot)
        val starNetwork = StarNetwork(root)
        starNetwork.networkPrefix = network.networkPrefix
        val childNameID = addDeviceName("${starNetwork.networkPrefix}_child")
        val deviceType = getDeviceTypeByName(network.deviceType)
        val linkType = getLinkTypeByName(network.linkType)
        for (i in 1..network.number) {
            val location = GeoLocation.getRandomLocationInRadius(root.location, linkType.rangeInMeters, simRun.randGenerator.random)
            val leaf = createDevice(deviceType, location, childNameID)
            linker.linkIfPossible(root, leaf)
            leaf.isStarNetworkChild = true
            starNetwork.children.add(leaf)
        }
        randStarNetworks[network.networkPrefix] = starNetwork
    }

    private fun setRootDevice() {
        val name = jsonObjects.rootRouter
        if (name.isNotEmpty()) {
            val device = getDeviceByName(name)
            device.router.isRoot = true
            rootRouterAddress = device.address
        }
    }

    private fun addDeviceName(name: String): Int {
        deviceNames.add(name)
        return deviceNames.size - 1
    }

    private fun createFixedDevices() {
        for (fixedDevice in jsonObjects.fixedDevice)
            createFixedLocatedDevice(fixedDevice)
    }

    private fun createFixedLinks() {
        for (l in jsonObjects.fixedLink) {
            val a = getDeviceByName(l.fixedDeviceA)
            val b = getDeviceByName(l.fixedDeviceB)
            linker.link(a, b, l.dataRateInKbps)
        }
    }

    private fun createFixedLocatedDevice(fixedDevice: FixedDevice) {
        val deviceType = getDeviceTypeByName(fixedDevice.deviceType)
        val location = GeoLocation(fixedDevice.latitude, fixedDevice.longitude)
        val nameID = addDeviceName(fixedDevice.name)
        val created = createDevice(deviceType, location, nameID)
        require(!namedAddresses.containsKey(fixedDevice.name)) { "name ${fixedDevice.name} must be unique" }
        namedAddresses[fixedDevice.name] = created.address
    }

    private fun createQuerySender(querySenderJson: QuerySender) {
        val receiverDevice = getDeviceByName(jsonObjects.rootRouter)
        val querySender = lupos.simulator_iot.queryproc.QuerySender(
            simRun = simRun,
            name = querySenderJson.name,
            sendRateInSec = querySenderJson.sendRateInSeconds,
            maxNumberOfQueries = querySenderJson.maxNumberOfQueries,
            startClockInSec = querySenderJson.sendStartClockInSec,
            receiver = receiverDevice,
            query = querySenderJson.query
        )
        querySenders.add(querySender)
    }

    private fun createDevice(deviceType: DeviceType, location: GeoLocation, nameIndex: Int): Device {
        val linkTypes = linker.getSortedLinkTypeIndices(deviceType.supportedLinkTypes)
        require(deviceType.performance != 0.0) { "The performance level of a device can not be 0.0 %" }
        val device = Device(simRun, location, devices.size, null, null, deviceType.performance, linkTypes, nameIndex, jsonObjects.deterministic)
        val parkingSensor = getParkingSensor(deviceType, device)
        device.sensor = parkingSensor
        val database = getDatabase(deviceType, device)
        device.database = database
        devices.add(device)
        return device
    }

    private fun getDeviceTypeByName(typeName: String): DeviceType {
        val deviceType = jsonObjects.deviceType.find { typeName == it.name }
        requireNotNull(deviceType) { "device type name $typeName does not exist" }
        return deviceType
    }

    private fun getLinkTypeByName(name: String): LinkType {
        val element = jsonObjects.linkType.find { name == it.name }
        requireNotNull(element) { "link type $name does not exist" }
        return element
    }

    private fun getSensorTypeByName(name: String): SensorType {
        val element = jsonObjects.sensorType.find { name == it.name }
        requireNotNull(element) { "sensor type $name does not exist" }
        return element
    }

    private fun getDatabase(deviceType: DeviceType, device: Device): DatabaseAdapter? {
        if (deviceType.database) {
            numberOfDatabases++
            return DatabaseAdapter(device)
        }
        return null
    }

    private fun getParkingSensor(deviceType: DeviceType, device: Device): ParkingSensor? {
        if (deviceType.parkingSensor.isNotEmpty()) {
            numberOfSensors++
            val sensorType = getSensorTypeByName(deviceType.parkingSensor)
            return ParkingSensor(device, sensorType.rateInSec, sensorType.maxSamples, sensorType.dataSink, sensorType.area)
        }
        return null
    }

    private fun createDbDeviceAddresses() {
        val addresses = devices.filter { it.hasDatabase() }.map { it.address }
        dbDeviceAddresses = IntArray(addresses.size) { addresses[it] }
    }

    internal fun getNumberOfDevices(): Int {
        return devices.size
    }

    internal fun getDeviceByAddress(address: Int): Device {
        return devices[address]
    }
}
