package lupos.iot_sim.config

import lupos.iot_sim.Device

public class StarNetwork(public val root: Device, public val dataSink: Device) {
    public var networkPrefix: String = ""
    public var children: MutableList<Device> = arrayListOf()
}
