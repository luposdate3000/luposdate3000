package lupos.simulator_core

public abstract class Entity : ISimulationLifeCycle {

    internal lateinit var simulation: Simulation

    private var isTerminated = false

    public abstract fun onEvent(source: Entity, data: Any)

    internal fun processIncomingEvent(event: Event) {
        if (isTerminated) {
            return
        }

        if (event.data is ITimer) {
            event.data.onExpire()
        } else {
            onEvent(event.source, event.data)
        }
    }

    protected fun scheduleEvent(destination: Entity, data: Any, delay: Long) {
        require(!isTerminated)
        val sim = simulation
        sim.addEvent(delay, this, destination, data)
    }

    public fun setTimer(time: Long, callback: ITimer) {
        scheduleEvent(this, callback, time)
    }

    protected fun terminate() {
        onShutDown()
        isTerminated = true
    }
}
