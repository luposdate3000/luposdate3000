package lupos.simulator_iot

public data class NetworkPackage(
    val sourceAddress: Int,
    val destinationAddress: Int,
    val payload: Any
)
