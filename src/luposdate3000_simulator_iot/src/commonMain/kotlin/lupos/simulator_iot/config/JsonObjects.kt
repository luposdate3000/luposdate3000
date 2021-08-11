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

import kotlinx.serialization.Serializable

@Serializable
public data class JsonObjects(
    var database: MutableMap<String, String> = mutableMapOf(), // custom properties interpreted directly by the database
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
)

@Serializable
public data class LinkType(
    val name: String = "",
    var rangeInMeters: Int = 0,
    val dataRateInKbps: Int = 0,
)

@Serializable
public data class SensorType(
    val name: String = "",
    val area: Int = 0,
    val dataSink: String = "",
    val rateInSec: Int = 0,
    var maxSamples: Int = -1,
)

@Serializable
public data class DeviceType(
    val name: String = "",
    var database: Boolean = false,
    val parkingSensor: String = "",
    val performance: Double = 100.0,
    val supportedLinkTypes: List<String> = mutableListOf(),
)

@Serializable
public data class FixedDevice(
    val name: String = "",
    val deviceType: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)

@Serializable
public data class FixedLink(
    val fixedDeviceA: String = "",
    val fixedDeviceB: String = "",
    val dataRateInKbps: Int = 0,
)

@Serializable
public data class RandomStarNetwork(
    val networkPrefix: String = "",
    val starRoot: String = "",
    val dataSink: String = "",
    val linkType: String = "",
    val deviceType: String = "",
    var number: Int = 0
)

@Serializable
public data class RandomMeshNetwork(
    val networkPrefix: String = "",
    val originLatitude: Double = 0.0,
    val originLongitude: Double = 0.0,
    val deviceType: String = "",
    val linkType: String = "",
    val signalCoverageEast: Int = 0,
    val signalCoverageSouth: Int = 0
)

@Serializable
public data class QuerySender(
    val name: String = "",
    val sendRateInSeconds: Int = 0,
    var maxNumberOfQueries: Int = 0,
    val sendStartClockInSec: Int = 0,
    var query: String = "",
)
