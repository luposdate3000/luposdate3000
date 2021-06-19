package lupos.simulator_iot.net

import lupos.simulator_iot.Device

public class StarNetwork(public val root: Device, public val dataSink: Device) {
    public var networkPrefix: String = ""
    public var children: MutableList<Device> = arrayListOf()
}
