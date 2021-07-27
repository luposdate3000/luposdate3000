package lupos.simulator_iot.unit

import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.geo.GeoLocation

internal object Stubs {

    internal fun createEmptyDevice(address: Int, simRun: SimulationRun = SimulationRun()): Device {
        val loc = GeoLocation.getRandom(simRun.randGenerator.random)
        return Device(simRun, loc, address, null, null, 100.0, intArrayOf(), -1, true)
    }

    internal fun createEmptyDevice(address: Int, linkTypes: IntArray, simRun: SimulationRun = SimulationRun()): Device {
        val loc = GeoLocation.getRandom(simRun.randGenerator.random)
        return Device(simRun, loc, address, null, null, 100.0, linkTypes, -1, true)
    }
}
