package lupos.simulator_iot

import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.net.routing.RPLRouter

public fun main() {

    IoTSimulation().simulate("upwardRouteForwarding.json")
}

public class IoTSimulation {



    public fun simulate(configFileName: String) {
        Configuration.parse(configFileName)
        val a = Configuration.getNamedDevice("A")
        val f = Configuration.getNamedDevice("F")
        f.sensor!!.setDataSink(a.address)
        val sim = Simulation(
            entities =  Configuration.getEntities(),
            maxClock = 1000 * 1000,
            callback = Logger)
        sim.startSimulation()
    }
}
