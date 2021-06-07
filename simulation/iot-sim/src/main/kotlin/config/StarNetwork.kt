package config

import Device

class StarNetwork(val root: Device, val dataSink: Device) {
    var networkPrefix = ""
    var children: MutableList<Device> = arrayListOf()
}