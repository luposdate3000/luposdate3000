package lupos.simulator_iot.models.net

import lupos.simulator_iot.models.Device

internal class MeshNetwork {
    internal var networkPrefix: String = ""
    internal var mesh: MutableList<MutableList<Device>> = arrayListOf(arrayListOf())

    internal fun numOfDevices(): Int {
        var counter = 0
        for (list in mesh) {
            counter += list.size
        }
        return counter
    }
}
