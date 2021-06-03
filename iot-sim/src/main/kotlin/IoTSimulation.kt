import kotlinx.datetime.*

class IoTSimulation {


    fun getDateTimeString(simClock: Long) {
        val now: Instant = Clock.System.now()
        val timeZone = TimeZone.currentSystemDefault()
        val simTime = now.plus(2, DateTimeUnit.MILLISECOND, timeZone)


        val time = simTime.toString()
    }

}