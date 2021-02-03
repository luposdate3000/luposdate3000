package net


data class Connection(
    val bandwidth: Double,
    val latency: Double,
    val protocol: String,
    var distance: Double,
)
