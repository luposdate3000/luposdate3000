package lupos.simulator_iot.config

import lupos.simulator_iot.Device

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
