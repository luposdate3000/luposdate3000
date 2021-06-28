package lupos.simulator_iot.net

import lupos.simulator_iot.Device

internal class StarNetwork(internal val root: Device) {
    internal var networkPrefix: String = ""
    internal var children: MutableList<Device> = arrayListOf()
}
