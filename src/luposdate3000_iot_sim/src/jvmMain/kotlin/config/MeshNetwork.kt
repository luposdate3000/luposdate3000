package lupos.iot_sim.config

import lupos.iot_sim.Device

public class MeshNetwork {
    public var networkPrefix: String = ""
    public var mesh: MutableList<MutableList<Device>> = arrayListOf(arrayListOf())

    public fun numOfDevices(): Int {
        var counter = 0
        for (list in mesh) {
            counter += list.size
        }
        return counter
    }
}
