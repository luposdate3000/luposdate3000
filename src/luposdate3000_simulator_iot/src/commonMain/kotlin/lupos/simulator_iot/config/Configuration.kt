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

import lupos.parser.IJsonParserValue
import lupos.parser.JsonParser
import lupos.parser.JsonParserArray
import lupos.parser.JsonParserObject
import lupos.parser.JsonParserString
import lupos.shared.SanityCheck
import lupos.shared.inline.File
import lupos.simulator_core.Entity
import lupos.simulator_iot.IPackage_Database
import lupos.simulator_iot.ReflectionHelper
import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.applications.ApplicationStack_AllShortestPath
import lupos.simulator_iot.applications.ApplicationStack_CatchSelfMessages
import lupos.simulator_iot.applications.ApplicationStack_Logger
import lupos.simulator_iot.applications.ApplicationStack_MergeMessages
import lupos.simulator_iot.applications.ApplicationStack_MulticastNone
import lupos.simulator_iot.applications.ApplicationStack_MultipleChilds
import lupos.simulator_iot.applications.ApplicationStack_RPL
import lupos.simulator_iot.applications.ApplicationStack_RPL_Fast
import lupos.simulator_iot.applications.ApplicationStack_Sequence
import lupos.simulator_iot.applications.Application_QuerySender
import lupos.simulator_iot.applications.IApplicationFeature
import lupos.simulator_iot.applications.IApplicationStack_Actuator
import lupos.simulator_iot.applications.IApplication_Factory
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.geo.GeoLocation
import lupos.simulator_iot.models.net.DeviceLinker
import lupos.simulator_iot.models.net.LinkManager
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin
import kotlin.math.sqrt

public class Configuration(private val simRun: SimulationRun) {
    public companion object {
        public val defaultOutputDirectory: String = "simulator_output/"
    }

    private val factories = mutableMapOf<String, IApplication_Factory>()
    public val features: MutableList<IApplicationFeature> = mutableListOf<IApplicationFeature>()

    public var devices: MutableList<Device> = mutableListOf()
    private var namedAddresses: MutableMap<String, Int> = mutableMapOf()
    public var outputDirectory: String = defaultOutputDirectory
    public var json: JsonParserObject? = null

    private var rootRouterAddress: Int = -1

    internal var linker = DeviceLinker()
        private set

    public fun getAllDevicesForFeature(feature: Int): List<Device> = devices.filter { hasFeature(it, feature) }
    public fun featureIdForName(name: String): Int {
        for (i in 0 until features.size) {
            if (features[i].getName() == name) {
                return i
            }
        }
        TODO()
    }

    public fun featureIdForName2(name: String): Int {
        for (i in 0 until features.size) {
            if (features[i].getName() == name) {
                return i
            }
        }
        return -1
    }

    public fun hasFeature(device: Device, feature: Int): Boolean {
        if (feature < 0) {
            return false
        }
        val f = features[feature]
        for (app in device.applicationStack.getAllChildApplications()) {
            if (f.hasFeature(app)) {
                return true
            }
        }
        return false
    }

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
        outputDirectory = json.getOrDefault("outputDirectory", defaultOutputDirectory) + "/"
        if (outputDirectory == "") {
            outputDirectory = defaultOutputDirectory
            json.set("outputDirectory", defaultOutputDirectory)
        }
        val jsonLoggers = json.getOrEmptyObject("logging")
        for ((loggerName, loggerJson) in jsonLoggers) {
            loggerJson as JsonParserObject
            val enabled = loggerJson.getOrDefault("enabled", false)
            if (enabled) {
                val log = ReflectionHelper.createLogger(loggerName)
                log.initialize(simRun)
                simRun.logger.loggers.add(log)
            }
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
            val created = createDevice(
                fixedDevice.getOrDefault("deviceType", ""),
                fixedDevice,
                JsonParserObject(mutableMapOf()),
            )
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/config/Configuration.kt:165"/*SOURCE_FILE_END*/ },
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
        createPattern(
            json.getOrEmptyArray("patterns"),
            null,
            JsonParserObject(mutableMapOf()),
            null,
        )
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

    private fun getRandomDistance(maxDistance: Int): Int {
        val density = 0.7
        val percentage = round(maxDistance * density).toInt()
        return simRun.randGenerator.getInt(percentage, maxDistance)
    }

    public fun getDeviceByName(name: String): Device {
        val index = namedAddresses[name]!!
        return devices[index]
    }

    public fun getRootDevice(): Device = devices[rootRouterAddress]

    private fun createDevice(deviceTypeName: String, jsonDeviceParam: JsonParserObject, valuesPassThrough: JsonParserObject): Device {
        val ownAddress = devices.size
// device json-->>
        val deviceTypes2 = json!!.getOrEmptyObject("deviceType")
        val deviceType2 = deviceTypes2.getOrEmptyObject(deviceTypeName)
        val jsonDevice = valuesPassThrough.cloneJson()
        jsonDevice.mergeWith(deviceType2.cloneJson())
        jsonDevice.mergeWith(jsonDeviceParam.cloneJson())
// device json<<--
        val location = GeoLocation(jsonDevice.getOrDefault("latitude", 0.0), jsonDevice.getOrDefault("longitude", 0.0))
// applications-->>
        val applications = mutableListOf<IApplicationStack_Actuator>()
        val jsonApplicationsEffective = json!!.getOrEmptyObject("applications").cloneJson()
        jsonApplicationsEffective.mergeWith(jsonDevice.getOrEmptyObject("applications").cloneJson())
        for ((applicationName, applicationJsonTmp) in jsonApplicationsEffective) {
            var applicationJson: IJsonParserValue = jsonDevice.cloneJson()
            if (applicationJsonTmp is JsonParserObject) {
                applicationJson as JsonParserObject
                applicationJson.mergeWith(applicationJsonTmp.cloneJson())
            } else {
                applicationJson = applicationJsonTmp
            }
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
// applications<<--
        val jsonRouting = json!!.getOrEmptyObject("routing")
        val multicastLayer = when (jsonRouting.getOrDefault("multicast", "None")) {
            "None" -> ApplicationStack_MulticastNone(applicationStack)
            "Simple" -> ApplicationStack_MulticastSimple(applicationStack)
            else -> TODO("unknown multicast implementation '${jsonRouting.getOrDefault("multicast", "None")}'")
        }
        val router = when (jsonRouting.getOrDefault("protocol", "RPL")) {
            "AllShortestPath" -> ApplicationStack_AllShortestPath(
                multicastLayer,
                simRun.logger,
                simRun.config,
            )
            "RPL_Fast" -> ApplicationStack_RPL_Fast(
                multicastLayer,
                simRun.logger,
                simRun.config,
                jsonRouting.getOrDefault("compatibilityMode", false),
            )
            "RPL" -> ApplicationStack_RPL(
                multicastLayer,
                simRun.logger,
                simRun.config,
            )
            else -> TODO("unknown routing.protocol '${jsonRouting.getOrDefault("protocol", "RPL")}'")
        }
        val linkTypes = linker.getSortedLinkTypeIndices(jsonDevice.getOrEmptyArray("supportedLinkTypes").map { (it as JsonParserString).value }.toMutableList())
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/config/Configuration.kt:300"/*SOURCE_FILE_END*/ },
            { jsonDevice.getOrDefault("performance", 100.0) > 0.0 },
            { "The performance level of a device can not be 0.0 %" },
        )
        val device = Device(
            simRun,
            location,
            ownAddress,
            jsonDevice.getOrDefault("performance", 100.0),
            LinkManager(linkTypes),
            json!!.getOrDefault("deterministic", true),
            router,
            namedAddresses,
        )
        devices.add(device)
        simRun.logger.addDevice(ownAddress, location.longitude, location.latitude)
        createPattern(jsonDevice.getOrEmptyArray("patterns"), location, valuesPassThrough, device)
        return device
    }

    public fun getNumberOfDevices(): Int {
        return devices.size
    }

    internal fun getDeviceByAddress(address: Int): Device {
        return devices[address]
    }

    private fun createPattern(
        patterns: JsonParserArray,
        location: GeoLocation?,
        valuesPassThrough: JsonParserObject,
        parent: Device?
    ) {
        for (rand in patterns) {
            rand as JsonParserObject
            val posLong = if (location != null) {
                location.longitude
            } else {
                rand.getOrDefault("longitude", 0.0)
            }
            val posLat = if (location != null) {
                location.latitude
            } else {
                rand.getOrDefault("latitude", 0.0)
            }
            val type = rand.getOrDefault("type", "random_fill")
            when (type) {
                "random_fill" -> {
                    val radius = rand.getOrDefault("radius", 0.1)
                    val count = when (rand.getOrDefault("mode", "count")) {
                        "count" -> rand.getOrDefault("count", 1)
                        "density" -> (2 * PI * radius * radius / rand.getOrDefault("density", 0.01)).toInt() + 1
                        else -> TODO()
                    }
                    val deviceTypeName = rand.getOrDefault("deviceType", "")
                    for (i in 0 until count) {
                        val p = randomCoords(radius)
                        rand["latitude"] = posLat + p.first
                        rand["longitude"] = posLong + p.second
                        val name = rand.getOrDefault("provideCounterAs", "")
                        val values = valuesPassThrough.cloneJson()
                        if (name != "") {
                            values[name] = i
                        }
                        createDevice(deviceTypeName, rand, values)
                    }
                }
                "ring" -> {
                    val radius = rand.getOrDefault("radius", 0.1)
                    val count = rand.getOrDefault("count", 1)
                    val deviceTypeName = rand.getOrDefault("deviceType", "")
                    var firstDevice: Device? = parent
                    var lastDevice: Device? = parent
                    for (i in 0 until count) {
                        val alpha = 2 * PI * i.toDouble() / count.toDouble()
                        rand["latitude"] = posLat + sin(alpha) * radius
                        rand["longitude"] = posLong + cos(alpha) * radius
                        val name = rand.getOrDefault("provideCounterAs", "")
                        val values = valuesPassThrough.cloneJson()
                        if (name != "") {
                            values[name] = i
                        }
                        val d = createDevice(deviceTypeName, rand, values)
                        if (firstDevice == null) {
                            firstDevice = d
                        } else {
                            linker.link(d, lastDevice!!, rand.getOrDefault("dataRateInKbps", 1000))
                        }
                        lastDevice = d
                    }
                    linker.link(firstDevice!!, lastDevice!!, rand.getOrDefault("dataRateInKbps", 1000))
                }
                "full" -> {
                    val radius = rand.getOrDefault("radius", 0.1)
                    val count = rand.getOrDefault("count", 1)
                    val deviceTypeName = rand.getOrDefault("deviceType", "")
                    var localDevices = mutableListOf<Device>()
                    if (parent != null) {
                        localDevices.add(parent)
                    }
                    for (i in 0 until count) {
                        val alpha = 2 * PI * i.toDouble() / count.toDouble()
                        rand["latitude"] = posLat + sin(alpha) * radius
                        rand["longitude"] = posLong + cos(alpha) * radius
                        val name = rand.getOrDefault("provideCounterAs", "")
                        val values = valuesPassThrough.cloneJson()
                        if (name != "") {
                            values[name] = i
                        }
                        val d = createDevice(deviceTypeName, rand, values)
                        localDevices.add(d)
                    }
                    for (i in 0 until localDevices.size) {
                        for (j in i + 1 until localDevices.size) {
                            linker.link(localDevices[i], localDevices[j], rand.getOrDefault("dataRateInKbps", 1000))
                        }
                    }
                }
                "uniform" -> {
                    fun sunflower(n: Double, alpha: Double, action: (Int, Double, Double) -> Unit) {
                        val b = round(alpha * sqrt(n))
                        val phi = (sqrt(5.0) + 1.0) / 2.0
                        for (i in 1 until 1 + n.toInt()) {
                            val k = i.toDouble()
                            val r = if (k> n - b) {
                                1.0
                            } else {
                                sqrt(k - 1.0 / 2.0) / sqrt(n - (b + 1.0) / 2.0)
                            }
                            val theta = 2 * PI * k / (phi * phi)
                            action(i, r * cos(theta), r * sin(theta))
                        }
                    }
                    val radius = rand.getOrDefault("radius", 0.1)
                    val count = when (rand.getOrDefault("mode", "count")) {
                        "count" -> rand.getOrDefault("count", 1)
                        "density" -> (2 * PI * radius * radius / rand.getOrDefault("density", 0.01)).toInt() + 1
                        else -> TODO()
                    }
                    val deviceTypeName = rand.getOrDefault("deviceType", "")
                    sunflower(count.toDouble(), 1.0) { i, x, y ->
                        rand["latitude"] = posLat + x * radius
                        rand["longitude"] = posLong + y * radius
                        val name = rand.getOrDefault("provideCounterAs", "")
                        val values = valuesPassThrough.cloneJson()
                        if (name != "") {
                            values[name] = i
                        }
                        createDevice(deviceTypeName, rand, values)
                    }
                }
                else -> TODO()
            }
        }
    }

    private fun randomCoords(r: Double): Pair<Double, Double> {
        var a = simRun.randGenerator.getDouble(0.0, 1.0)
        var b = simRun.randGenerator.getDouble(0.0, 1.0)
        if (b < a) {
            var c = b
            b = a
            a = c
        }
        return b * r * cos(2 * PI * a / b) to b * r * sin(2 * PI * a / b)
    }
}
