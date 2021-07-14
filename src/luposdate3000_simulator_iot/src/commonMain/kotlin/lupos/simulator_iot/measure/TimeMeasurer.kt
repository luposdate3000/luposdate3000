package lupos.simulator_iot.measure
import kotlinx.datetime.Instant
import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.utils.TimeUtils

public class TimeMeasurer(private val simRun: SimulationRun) {

    private lateinit var startUpTimeStamp: Instant
    private lateinit var shutDownTimeStamp: Instant
    private lateinit var realShutDownTimeStamp: Instant
    private var initStartTimeStamp: Instant = TimeUtils.stamp()

    internal fun onStartUp() {
        startUpTimeStamp = TimeUtils.stamp()
    }

    internal fun onShutDown() {
        shutDownTimeStamp = getSimulationTime()
        realShutDownTimeStamp = TimeUtils.stamp()
    }

    private fun getSimulationTime(): Instant = TimeUtils.addMillis(startUpTimeStamp, simRun.getCurrentSimulationClock())

    internal fun getTimeString(time: Instant): String {
        return TimeUtils.toISOString(time)
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
        TimeUtils.differenceInSeconds(initStartTimeStamp, startUpTimeStamp)

    internal fun getSimulationDuration(): Double =
        TimeUtils.differenceInSeconds(startUpTimeStamp, shutDownTimeStamp)

    internal fun getRealSimulationDuration(): Double =
        TimeUtils.differenceInSeconds(startUpTimeStamp, realShutDownTimeStamp)

    internal fun getRealShutDownTimeString(): String {
        return getTimeString(realShutDownTimeStamp)
    }

}
