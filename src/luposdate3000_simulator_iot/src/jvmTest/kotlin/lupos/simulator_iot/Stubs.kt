package lupos.simulator_iot

import lupos.simulator_iot.geo.GeoLocation

internal object Stubs {

    internal fun createEmptyDevice(address: Int): Device {
        val loc = GeoLocation.getRandom()
        return Device(loc, address, null, null, 100.0, IntArray(0), -1)
    }

    internal fun createEmptyDevice(address: Int, linkTypes: IntArray): Device {
        val loc = GeoLocation.getRandom()
        return Device(loc, address, null, null, 100.0, linkTypes, -1)
    }
}
