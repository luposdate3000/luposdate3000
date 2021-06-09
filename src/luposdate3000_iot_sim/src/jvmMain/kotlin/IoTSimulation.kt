package lupos.iot_sim

import kotlinx.datetime.*
import lupos.des_core.Simulation
import lupos.iot_sim.config.Configuration

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
        val sim = Simulation(Configuration.devices)
        sim.setLifeCycleCallback(Logger(sim))
        sim.start()
    }

    public fun getDateTimeString(simClock: Long) {
        val now: Instant = Clock.System.now()
        val timeZone = TimeZone.currentSystemDefault()
        val simTime = now.plus(2, DateTimeUnit.MILLISECOND, timeZone)

        val time = simTime.toString()
    }
}
