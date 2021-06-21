package lupos.simulator_iot

import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration

public fun main() {

    //IoTSimulation().simulate("upwardRouteForwarding.json")
    IoTSimulation().simulate("nullPointerException.json")
}

public class IoTSimulation {



    public fun simulate(configFileName: String) {
        Configuration.parse(configFileName)
        val a = Configuration.getNamedDevice("A")
        val f = Configuration.getNamedDevice("F")
        f.sensor!!.setDataSink(a.address)
        val sim = Simulation(
            entities =  Configuration.getEntities(),
            callback = IoTSimLifeCycle)
        sim.startSimulation()
    }
}
