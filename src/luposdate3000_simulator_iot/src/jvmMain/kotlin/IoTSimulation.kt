package lupos.simulator_iot

import kotlinx.datetime.*
import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration

public fun main() {
}

public class IoTSimulation {

    /*
    * Lasse erstmal die Simulation laufen wie im
    * test, und schaue was dabei herauskommt.
    *
    * Idee: 3 Configurationen mit verschiedenen Datensenken
    *
    *
    * */
    public fun run() {
        val configFileName = ""
        Configuration.parse(configFileName)
        val sim = Simulation(Configuration.devices, callback = Logger())

        sim.startSimulation()
    }

    public fun getDateTimeString(simClock: Long) {
        val now: Instant = Clock.System.now()
        val timeZone = TimeZone.currentSystemDefault()
        val simTime = now.plus(2, DateTimeUnit.MILLISECOND, timeZone)

        val time = simTime.toString()
    }
}
