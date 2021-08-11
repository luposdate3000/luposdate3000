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
public class JsonObjects {
    public var database: JsonParserObject
    public var deterministic: Boolean
    public var logging: Boolean
    public val linkType: MutableList<LinkType>
    public val sensorType: MutableList<SensorType>
    public val deviceType: MutableList<DeviceType>
    public val fixedDevice: MutableList<FixedDevice>
    public val fixedLink: MutableList<FixedLink>
    public val rootRouter: String
    public val randomMeshNetwork: MutableList<RandomMeshNetwork>
    public val randomStarNetwork: MutableList<RandomStarNetwork>
    public val querySender: MutableList<QuerySender>
    public val json: JsonParserObject

    public constructor() : this(JsonParserObject(mutableMapOf()))
    public constructor(data: JsonParserObject) {
        json = data
        database = data.getOrEmptyObject("database")
        deterministic = data.getOrDefault("deterministic", true)
        logging = data.getOrDefault("logging", true)
        linkType = data.getOrEmptyArray("linkType").map { LinkType(it as JsonParserObject) }
        sensorType = data.getOrEmptyArray("sensorType").map { SensorType(it as JsonParserObject) }
        deviceType = data.getOrEmptyArray("deviceType").map { DeviceType(it as JsonParserObject) }
        fixedDevice = data.getOrEmptyArray("fixedDevice").map { FixedDevice(it as JsonParserObject) }
        fixedLink = data.getOrEmptyArray("fixedLink").map { FixedLink(it as JsonParserObject) }
        rootRouter = data.getOrDefault("rootRouter", "")
        randomMeshNetwork = data.getOrEmptyArray("randomMeshNetwork").map { RandomMeshNetwork(it as JsonParserObject) }
        randomStarNetwork = data.getOrEmptyArray("randomStarNetwork").map { RandomStarNetwork(it as JsonParserObject) }
        querySender = data.getOrEmptyArray("querySender").map { QuerySender(it as JsonParserObject) }
    }
}

public class LinkType {
    public val name: String
    public var rangeInMeters: Int
    public val dataRateInKbps: Int
    public constructor(data: JsonParserObject) {
        name = data.getOrDefault("name", "")
        rangeInMeters = data.getOrDefault("rangeInMeters", 0)
        dataRateInKbps = data.getOrDefault("dataRateInKbps", 0)
    }
}

public class SensorType {
    public val name: String
    public val area: Int
    public val dataSink: String
    public val rateInSec: Int
    public var maxSamples: Int

    public constructor(data: JsonParserObject) {
        name = data.getOrDefault("name", "")
        area = data.getOrDefault("area", 0)
        dataSink = data.getOrDefault("dataSink", "")
        rateInSec = data.getOrDefault("rateInSec", 0)
        maxSamples = data.getOrDefault("maxSamples", -1)
    }
}

public class DeviceType {
    public val name: String
    public var database: Boolean = false
    public val parkingSensor: String
    public val performance: Double
    public val supportedLinkTypes: List<String>
    public constructor(data: JsonParserObject) {
        name = data.getOrDefault("name", "")
        database = data.getOrDefault("database", false)
        parkingSensor = data.getOrDefault("parkingSensor", "")
        performance = data.getOrDefault("performance", 100.0)
        supportedLinkTypes = data.getOrEmptyArray("supportedLinkTypes").map { (it as JsonParserString).value }.toMutableList()
    }
}

public class FixedDevice {
    public val name: String
    public val deviceType: String
    public val latitude: Double
    public val longitude: Double

    public constructor(data: JsonParserObject) {
        name = data.getOrDefault("name", "")
        deviceType = data.getOrDefault("deviceType", "")
        latitude = data.getOrDefault("latitude", 0.0)
        longitude = data.getOrDefault("longitude", 0.0)
    }
}

public class FixedLink {
    public val fixedDeviceA: String
    public val fixedDeviceB: String
    public val dataRateInKbps: Int
    public constructor(data: JsonParserObject) {
        fixedDeviceA = data.getOrDefault("fixedDeviceA", "")
        fixedDeviceB = data.getOrDefault("fixedDeviceB", "")
        dataRateInKbps = data.getOrDefault("dataRateInKbps", 0)
    }
}

public class RandomStarNetwork {
    public val networkPrefix: String
    public val starRoot: String
    public val dataSink: String
    public val linkType: String
    public val deviceType: String
    public var number: Int
    public constructor(data: JsonParserObject) {
        networkPrefix = data.getOrDefault("networkPrefix", "")
        starRoot = data.getOrDefault("starRoot", "")
        dataSink = data.getOrDefault("dataSink", "")
        linkType = data.getOrDefault("linkType", "")
        deviceType = data.getOrDefault("deviceType", "")
        number = data.getOrDefault("number", 0)
    }
}

public class RandomMeshNetwork {
    public val networkPrefix: String
    public val originLatitude: Double
    public val originLongitude: Double
    public val deviceType: String
    public val linkType: String
    public val signalCoverageEast: Int
    public val signalCoverageSouth: Int
    public constructor(data: JsonParserObject) {
        networkPrefix = data.getOrDefault("networkPrefix", "")
        originLatitude = data.getOrDefault("originLatitude", 0.0)
        originLongitude = data.getOrDefault("originLongitude", 0.0)
        deviceType = data.getOrDefault("deviceType", "")
        linkType = data.getOrDefault("linkType", "")
        signalCoverageEast = data.getOrDefault("signalCoverageEast", 0)
        signalCoverageSouth = data.getOrDefault("signalCoverageSouth", 0)
    }
}

public class QuerySender {
    public val name: String
    public val sendRateInSeconds: Int
    public var maxNumberOfQueries: Int
    public val sendStartClockInSec: Int
    public var query: String
    public constructor(data: JsonParserObject) {
        name = data.getOrDefault("name", "")
        sendRateInSeconds = data.getOrDefault("sendRateInSeconds", 0)
        maxNumberOfQueries = data.getOrDefault("maxNumberOfQueries", 0)
        sendStartClockInSec = data.getOrDefault("sendStartClockInSec", 0)
        query = data.getOrDefault("query", "")
    }
}
