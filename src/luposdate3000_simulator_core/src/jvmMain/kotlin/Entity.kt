package lupos.simulator_core

public abstract class Entity : ISimulationLifeCycle {

    internal lateinit var simulation: Simulation

    private var isTerminated = false

    public abstract fun onEvent(source: Entity, data: Any)

    internal fun processIncomingEvent(event: Event) {
        if (isTerminated)
            return

        if (event.data is ITimer)
            event.data.onExpire()
        else
            onEvent(event.source, event.data)
    }

    protected fun scheduleEvent(destination: Entity, data: Any, delay: Int) {
        require(!isTerminated)
        simulation.addEvent(delay.toLong(), this, destination, data)
    }

    public fun setTimer(time: Int, callback: ITimer) {
        scheduleEvent(this, callback, time)
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
