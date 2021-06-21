package lupos.simulator_iot

import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration

public fun main() {

    //IoTSimulation().simulate("upwardRouteForwarding.json")
    IoTSimulation().simulate("$jvmResource\\multipleDatabases.json")
}

public class IoTSimulation {



    public fun simulate(configFileName: String) {
        Configuration.parse(configFileName)
        val sim = Simulation(
            entities =  Configuration.getEntities(),
            callback = IoTSimLifeCycle)
        sim.startSimulation()
    }
}
