package lupos.simulator_iot

import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.geo.GeoLocation

internal object Stubs {

    internal fun createEmptyDevice(address: Int): Device {
        val loc = GeoLocation.getRandom(Configuration.randomGenerator.random)
        return Device(loc, address, null, null, 100.0, intArrayOf(), -1, true)
    }

    internal fun createEmptyDevice(address: Int, linkTypes: IntArray): Device {
        val loc = GeoLocation.getRandom(Configuration.randomGenerator.random)
        return Device(loc, address, null, null, 100.0, linkTypes, -1, true)
    }
}
