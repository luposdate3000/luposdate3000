package lupos.iot_sim

public data class NetworkPackage(
    val sourceAddress: Int,
    val destinationAddress: Int,
    val payload: Any
)
