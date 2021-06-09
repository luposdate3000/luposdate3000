package lupos.simulator_core

public abstract class Entity : ISimulationLifeCycle {

    internal lateinit var simulation: Simulation

    private var isTerminated = false

    public abstract fun onEvent(event: Event)

    private fun isTimerExpiredEvent(event: Event) =
        event.data != null && event.data is ITimerExpired

    internal fun processIncomingEvent(event: Event) {
        if (isTerminated)
            return

        if (isTimerExpiredEvent(event))
            (event.data as ITimerExpired).onExpire()
        else
            onEvent(event)
    }

    protected fun scheduleEvent(destination: Entity, delay: Long, data: Any) {
        require(!isTerminated)
        simulation.addEvent(delay, this, destination, data)
    }

    public fun setTimer(time: Long, callback: ITimerExpired) {
        scheduleEvent(this, time, callback)
    }

    public interface ITimerExpired {
        public fun onExpire()
    }

    protected fun terminate() {
        onShutDown()
        isTerminated = true
    }

    public fun getCurrentSimulationTime(): Long = simulation.currentClock
}
