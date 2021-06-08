import config.Configuration
import kotlinx.datetime.*

fun main() {

}

class IoTSimulation {






 /*
 * Lasse erstmal die Simulation laufen wie im
 * test, und schaue was dabei herauskommt.
 *
 * Idee: 3 Configurationen mit verschiedenen Datensenken
 *
 *
 * */
    fun run() {
     val configFileName = ""
     Configuration.parse(configFileName)
     val sim = Simulation(Configuration.devices)
     sim.setLifeCycleCallback(Logger(sim))
     sim.start()
    }

    fun getDateTimeString(simClock: Long) {
        val now: Instant = Clock.System.now()
        val timeZone = TimeZone.currentSystemDefault()
        val simTime = now.plus(2, DateTimeUnit.MILLISECOND, timeZone)


        val time = simTime.toString()
    }

}