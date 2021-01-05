package layer1.net

import layer1.config.Device

data class Connection(
    val source: Device,
    val destination: Device,
    val bandwidth: Double,
    val latency: Double,
    val protocol: String,
    var distance: Double,
)
