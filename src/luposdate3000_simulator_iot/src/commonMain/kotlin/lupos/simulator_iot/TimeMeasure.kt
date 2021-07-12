package lupos.simulator_iot
import lupos.simulator_core.Simulation
import kotlinx.datetime.Instant

internal class TimeMeasure(private val sim: Simulation) {

    private lateinit var startUpTimeStamp: Instant
    private lateinit var shutDownTimeStamp: Instant
    private lateinit var realShutDownTimeStamp: Instant
    private var initStartTimeStamp: Instant = Time.stamp()


    internal fun onStartUp() {
        startUpTimeStamp = Time.stamp()
    }

    internal fun onShutDown() {
        shutDownTimeStamp = getSimulationTime()
        realShutDownTimeStamp = Time.stamp()
    }

    private fun getSimulationTime(): Instant = Time.addMillis(startUpTimeStamp, sim.clock)

    internal fun getTimeString(time: Instant): String {
        return Time.toISOString(time)
    }

    internal fun getSimulationTimeString(): String {
        return getTimeString(getSimulationTime())
    }

    internal fun getShutDownTimeString(): String {
        return getTimeString(shutDownTimeStamp)
    }

    internal fun getStartUpTimeString(): String {
        return getTimeString(startUpTimeStamp)
    }

    internal fun getInitDuration(): Double =
        Time.differenceInSeconds(initStartTimeStamp, startUpTimeStamp)

    internal fun getSimulationDuration(): Double =
        Time.differenceInSeconds(startUpTimeStamp, shutDownTimeStamp)

    internal fun getRealSimulationDuration(): Double =
        Time.differenceInSeconds(startUpTimeStamp, realShutDownTimeStamp)

}
