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
public class JsonObjects {
    public var deterministic: Boolean
    public val fixedLink: MutableList<FixedLink>
    public val randomMeshNetwork: MutableList<RandomMeshNetwork>
    public val randomStarNetwork: MutableList<RandomStarNetwork>
    public val json: JsonParserObject

    public constructor() : this(JsonParserObject(mutableMapOf()))
    public constructor(data: JsonParserObject) {
        json = data
        deterministic = data.getOrDefault("deterministic", true)
        fixedLink = data.getOrEmptyArray("fixedLink").map { FixedLink(it as JsonParserObject) }
        randomMeshNetwork = data.getOrEmptyArray("randomMeshNetwork").map { RandomMeshNetwork(it as JsonParserObject) }
        randomStarNetwork = data.getOrEmptyArray("randomStarNetwork").map { RandomStarNetwork(it as JsonParserObject) }
    }
}

public class LinkType(
    public val name: String = "",
    public var rangeInMeters: Int = 0,
    public val dataRateInKbps: Int = 0,
)

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
