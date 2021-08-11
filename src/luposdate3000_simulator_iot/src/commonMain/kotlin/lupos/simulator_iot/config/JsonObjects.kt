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

@file:Suppress("UnusedImport")

package lupos.simulator_iot.config
import lupos.parser.JsonParserArray
import lupos.parser.JsonParserObject
public data class JsonObjects(
    var database: JsonParserObject = JsonParserObject(mutableMapOf()), // custom properties interpreted directly by the database
    var deterministic: Boolean = true,
    var logging: Boolean = true,
    val linkType: MutableList<LinkType> = mutableListOf(),
    val sensorType: MutableList<SensorType> = mutableListOf(),
    val deviceType: MutableList<DeviceType> = mutableListOf(),
    val fixedDevice: MutableList<FixedDevice> = mutableListOf(),
    val fixedLink: MutableList<FixedLink> = mutableListOf(),
    val rootRouter: String = "",
    val randomMeshNetwork: MutableList<RandomMeshNetwork> = mutableListOf(),
    val randomStarNetwork: MutableList<RandomStarNetwork> = mutableListOf(),
    val querySender: MutableList<QuerySender> = mutableListOf(),
) {
    public constructor(data: JsonParserObject) : this(
        database = data.getOrDefault("database", JsonParserObject(mutableMapOf())) as JsonParserObject,
        deterministic = data.getOrDefault("deterministic", true) as Boolean,
        logging = data.getOrDefault("logging", true) as Boolean,
        linkType = (data.getOrDefault("linkType", JsonParserArray(mutableListOf<Any>()))as JsonParserArray).map { LinkType(it as JsonParserObject) },
        sensorType = (data.getOrDefault("sensorType", JsonParserArray(mutableListOf<Any>()))as JsonParserArray).map { SensorType(it as JsonParserObject) },
        deviceType = (data.getOrDefault("deviceType", JsonParserArray(mutableListOf<Any>()))as JsonParserArray).map { DeviceType(it as JsonParserObject) },
        fixedDevice = (data.getOrDefault("fixedDevice", JsonParserArray(mutableListOf<Any>()))as JsonParserArray).map { FixedDevice(it as JsonParserObject) },
        fixedLink = (data.getOrDefault("fixedLink", JsonParserArray(mutableListOf<Any>()))as JsonParserArray).map { FixedLink(it as JsonParserObject) },
        rootRouter = data.getOrDefault("rootRouter", "") as String,
        randomMeshNetwork = (data.getOrDefault("randomMeshNetwork", JsonParserArray(mutableListOf<Any>()))as JsonParserArray).map { RandomMeshNetwork(it as JsonParserObject) },
        randomStarNetwork = (data.getOrDefault("randomStarNetwork", JsonParserArray(mutableListOf<Any>()))as JsonParserArray).map { RandomStarNetwork(it as JsonParserObject) },
        querySender = (data.getOrDefault("querySender", JsonParserArray(mutableListOf<Any>()))as JsonParserArray).map { QuerySender(it as JsonParserObject) },
    )
}

public data class LinkType(
    val name: String = "",
    var rangeInMeters: Int = 0,
    val dataRateInKbps: Int = 0,
) {
    public constructor(data: JsonParserObject) : this(
        name = data.getOrDefault("name", "") as String,
        rangeInMeters = data.getOrDefault("rangeInMeters", 0) as Int,
        dataRateInKbps = data.getOrDefault("dataRateInKbps", 0) as Int,
    )
}

public data class SensorType(
    val name: String = "",
    val area: Int = 0,
    val dataSink: String = "",
    val rateInSec: Int = 0,
    var maxSamples: Int = -1,
) {
    public constructor(data: JsonParserObject) : this(
        name = data.getOrDefault("name", "") as String,
        area = data.getOrDefault("area", 0) as Int,
        dataSink = data.getOrDefault("dataSink", "") as String,
        rateInSec = data.getOrDefault("rateInSec", 0) as Int,
        maxSamples = data.getOrDefault("maxSamples", -1) as Int,
    )
}

public data class DeviceType(
    val name: String = "",
    var database: Boolean = false,
    val parkingSensor: String = "",
    val performance: Double = 100.0,
    val supportedLinkTypes: List<String> = mutableListOf(),
) {
    public constructor(data: JsonParserObject) : this(
        name = data.getOrDefault("name", "") as String,
        database = data.getOrDefault("database", false) as Boolean,
        parkingSensor = data.getOrDefault("parkingSensor", "") as String,
        performance = data.getOrDefault("performance", 100.0) as Double,
        supportedLinkTypes = data.getOrDefault("supportedLinkTypes", mutableListOf<String>())as List<String>,
    )
}

public data class FixedDevice(
    val name: String = "",
    val deviceType: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
) {
    public constructor(data: JsonParserObject) : this(
        name = data.getOrDefault("name", "") as String,
        deviceType = data.getOrDefault("deviceType", "") as String,
        latitude = data.getOrDefault("latitude", 0.0) as Double,
        longitude = data.getOrDefault("longitude", 0.0) as Double,
    )
}

public data class FixedLink(
    val fixedDeviceA: String = "",
    val fixedDeviceB: String = "",
    val dataRateInKbps: Int = 0,
) {
    public constructor(data: JsonParserObject) : this(
        fixedDeviceA = data.getOrDefault("fixedDeviceA", "") as String,
        fixedDeviceB = data.getOrDefault("fixedDeviceB", "") as String,
        dataRateInKbps = data.getOrDefault("dataRateInKbps", 0) as Int,
    )
}

public data class RandomStarNetwork(
    val networkPrefix: String = "",
    val starRoot: String = "",
    val dataSink: String = "",
    val linkType: String = "",
    val deviceType: String = "",
    var number: Int = 0
) {
    public constructor(data: JsonParserObject) : this(
        networkPrefix = data.getOrDefault("networkPrefix", "") as String,
        starRoot = data.getOrDefault("starRoot", "") as String,
        dataSink = data.getOrDefault("dataSink", "") as String,
        linkType = data.getOrDefault("linkType", "") as String,
        deviceType = data.getOrDefault("deviceType", "") as String,
        number = data.getOrDefault("number", 0) as Int,
    )
}

public data class RandomMeshNetwork(
    val networkPrefix: String = "",
    val originLatitude: Double = 0.0,
    val originLongitude: Double = 0.0,
    val deviceType: String = "",
    val linkType: String = "",
    val signalCoverageEast: Int = 0,
    val signalCoverageSouth: Int = 0
) {
    public constructor(data: JsonParserObject) : this(
        networkPrefix = data.getOrDefault("networkPrefix", "")as String,
        originLatitude = data.getOrDefault("originLatitude", 0.0)as Double,
        originLongitude = data.getOrDefault("originLongitude", 0.0)as Double,
        deviceType = data.getOrDefault("deviceType", "")as String,
        linkType = data.getOrDefault("linkType", "")as String,
        signalCoverageEast = data.getOrDefault("signalCoverageEast", 0)as Int,
        signalCoverageSouth = data.getOrDefault("signalCoverageSouth", 0)as Int,
    )
}

public data class QuerySender(
    val name: String = "",
    val sendRateInSeconds: Int = 0,
    var maxNumberOfQueries: Int = 0,
    val sendStartClockInSec: Int = 0,
    var query: String = "",
) {
    public constructor(data: JsonParserObject) : this(
        name = data.getOrDefault("name", "")as String,
        sendRateInSeconds = data.getOrDefault("sendRateInSeconds", 0) as Int,
        maxNumberOfQueries = data.getOrDefault("maxNumberOfQueries", 0) as Int,
        sendStartClockInSec = data.getOrDefault("sendStartClockInSec", 0) as Int,
        query = data.getOrDefault("query", "") as String,
    )
}
