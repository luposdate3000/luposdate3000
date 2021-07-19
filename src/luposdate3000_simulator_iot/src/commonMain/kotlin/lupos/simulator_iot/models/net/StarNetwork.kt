package lupos.simulator_iot.models.net

import lupos.simulator_iot.models.Device

internal class StarNetwork(internal val root: Device) {
    internal var networkPrefix: String = ""
    internal var children: MutableList<Device> = arrayListOf()
}
