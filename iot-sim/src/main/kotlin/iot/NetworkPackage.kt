package iot

data class NetworkPackage(
    val sender: Device,
    val receiver : Device,
    val data: Any)