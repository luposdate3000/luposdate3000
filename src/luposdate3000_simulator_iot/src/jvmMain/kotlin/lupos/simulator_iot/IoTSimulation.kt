package lupos.simulator_iot

import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration

public fun main() {

    //IoTSimulation().simulate("${FilePaths.jvmResource}\\Exception_2Sensors1Database.json")
    //IoTSimulation().simulate("${FilePaths.jvmResource}\\anotherException_2Sensor1Database.json")
    //IoTSimulation().simulate("${FilePaths.jvmResource}\\Exception_2DBwith1Sensor.json")
    IoTSimulation().simulate("${FilePaths.jvmResource}\\star.json")


}

public class IoTSimulation {



    public fun simulate(configFileName: String) {
        Configuration.parse(configFileName)
        val sim = Simulation(
            entities =  Configuration.getEntities(),
            callback = Logger)
        sim.startSimulation()
    }
}
