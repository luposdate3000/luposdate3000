package layer1.net


data class Connection(
    val bandwidth: Double,
    val latency: Double,
    val protocol: String,
    var distance: Double,
)
