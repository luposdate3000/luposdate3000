package lupos.simulator_iot.iot.net

import lupos.simulator_iot.iot.Device

internal class StarNetwork(internal val root: Device) {
    internal var networkPrefix: String = ""
    internal var children: MutableList<Device> = arrayListOf()
}
