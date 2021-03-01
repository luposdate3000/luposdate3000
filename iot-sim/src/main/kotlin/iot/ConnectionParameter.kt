package iot

import config.NetworkProtocol


data class ConnectionParameter(
    val dataRateInKbps: Int,
    val maxDistanceInMeters: Double,
    val protocolName: String,
    var distance: Double,
)
