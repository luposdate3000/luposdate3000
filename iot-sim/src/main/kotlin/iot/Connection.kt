package iot

import config.NetworkProtocol


data class Connection(
    val dataRateInKbps: Int,
    val maxDistanceInMeters: Double,
    val protocolName: String,
    var distance: Double,
    var destination: Device
)
