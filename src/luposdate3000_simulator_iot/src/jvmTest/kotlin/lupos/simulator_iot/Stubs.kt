package lupos.simulator_iot

import lupos.simulator_iot.geo.GeoLocation

object Stubs {

    fun createEmptyDevice(address: Int): Device {
        val loc = GeoLocation.getRandom()
        return Device( loc, address, null, null, IntArray(0))
    }

    fun createEmptyDevice(address: Int, linkTypes: IntArray): Device {
        val loc = GeoLocation.getRandom()
        return Device( loc, address, null, null, linkTypes)
    }
}
