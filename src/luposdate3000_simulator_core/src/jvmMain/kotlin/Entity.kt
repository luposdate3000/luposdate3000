package lupos.simulator_core

public abstract class Entity : ISimulationLifeCycle {

    override lateinit var simulation: ISimulation

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
        val sim = simulation as Simulation
        sim.addEvent(delay.toLong(), this, destination, data)
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
}
