package lupos.simulator_core

public abstract class Entity : ISimulationLifeCycle {

    internal lateinit var simulation: Simulation

    private var isTerminated = false

    public abstract fun onEvent(event: Event)

    internal fun processIncomingEvent(event: Event) {
        if (isTerminated)
            return

        if (event.data is ITimer)
            event.data.onExpire()
        else
            onEvent(event)
    }

    protected fun scheduleEvent(destination: Entity, delay: Long, data: Any) {
        require(!isTerminated)
        simulation.addEvent(delay, this, destination, data)
    }

    public fun setTimer(time: Long, callback: ITimer) {
        scheduleEvent(this, time, callback)
    }

    public interface ITimer {
        public fun onExpire()
    }

    protected fun terminate() {
        onShutDown()
        isTerminated = true
    }

    public fun getCurrentSimulationTime(): Long = simulation.currentClock
}
