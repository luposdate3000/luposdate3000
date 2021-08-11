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
import lupos.parser.JsonParserObject
import lupos.parser.JsonParserString
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
        database = data.getOrEmptyObject("database"),
        deterministic = data.getOrDefault("deterministic", true),
        logging = data.getOrDefault("logging", true),
        linkType = data.getOrEmptyArray("linkType").map { LinkType(it as JsonParserObject) },
        sensorType = data.getOrEmptyArray("sensorType").map { SensorType(it as JsonParserObject) },
        deviceType = data.getOrEmptyArray("deviceType").map { DeviceType(it as JsonParserObject) },
        fixedDevice = data.getOrEmptyArray("fixedDevice").map { FixedDevice(it as JsonParserObject) },
        fixedLink = data.getOrEmptyArray("fixedLink").map { FixedLink(it as JsonParserObject) },
        rootRouter = data.getOrDefault("rootRouter", ""),
        randomMeshNetwork = data.getOrEmptyArray("randomMeshNetwork").map { RandomMeshNetwork(it as JsonParserObject) },
        randomStarNetwork = data.getOrEmptyArray("randomStarNetwork").map { RandomStarNetwork(it as JsonParserObject) },
        querySender = data.getOrEmptyArray("querySender").map { QuerySender(it as JsonParserObject) },
    )
}

public data class LinkType(
    val name: String = "",
    var rangeInMeters: Int = 0,
    val dataRateInKbps: Int = 0,
) {
    public constructor(data: JsonParserObject) : this(
        name = data.getOrDefault("name", ""),
        rangeInMeters = data.getOrDefault("rangeInMeters", 0),
        dataRateInKbps = data.getOrDefault("dataRateInKbps", 0),
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
        name = data.getOrDefault("name", ""),
        area = data.getOrDefault("area", 0),
        dataSink = data.getOrDefault("dataSink", ""),
        rateInSec = data.getOrDefault("rateInSec", 0),
        maxSamples = data.getOrDefault("maxSamples", -1),
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
        name = data.getOrDefault("name", ""),
        database = data.getOrDefault("database", false),
        parkingSensor = data.getOrDefault("parkingSensor", ""),
        performance = data.getOrDefault("performance", 100.0),
        supportedLinkTypes = data.getOrEmptyArray("supportedLinkTypes").map { (it as JsonParserString).value }.toMutableList(),
    )
}

public data class FixedDevice(
    val name: String = "",
    val deviceType: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
) {
    public constructor(data: JsonParserObject) : this(
        name = data.getOrDefault("name", ""),
        deviceType = data.getOrDefault("deviceType", ""),
        latitude = data.getOrDefault("latitude", 0.0),
        longitude = data.getOrDefault("longitude", 0.0),
    )
}

public data class FixedLink(
    val fixedDeviceA: String = "",
    val fixedDeviceB: String = "",
    val dataRateInKbps: Int = 0,
) {
    public constructor(data: JsonParserObject) : this(
        fixedDeviceA = data.getOrDefault("fixedDeviceA", ""),
        fixedDeviceB = data.getOrDefault("fixedDeviceB", ""),
        dataRateInKbps = data.getOrDefault("dataRateInKbps", 0),
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
        networkPrefix = data.getOrDefault("networkPrefix", ""),
        starRoot = data.getOrDefault("starRoot", ""),
        dataSink = data.getOrDefault("dataSink", ""),
        linkType = data.getOrDefault("linkType", ""),
        deviceType = data.getOrDefault("deviceType", ""),
        number = data.getOrDefault("number", 0),
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
        networkPrefix = data.getOrDefault("networkPrefix", ""),
        originLatitude = data.getOrDefault("originLatitude", 0.0),
        originLongitude = data.getOrDefault("originLongitude", 0.0),
        deviceType = data.getOrDefault("deviceType", ""),
        linkType = data.getOrDefault("linkType", ""),
        signalCoverageEast = data.getOrDefault("signalCoverageEast", 0),
        signalCoverageSouth = data.getOrDefault("signalCoverageSouth", 0),
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
        name = data.getOrDefault("name", ""),
        sendRateInSeconds = data.getOrDefault("sendRateInSeconds", 0),
        maxNumberOfQueries = data.getOrDefault("maxNumberOfQueries", 0),
        sendStartClockInSec = data.getOrDefault("sendStartClockInSec", 0),
        query = data.getOrDefault("query", ""),
    )
}
