abstract class Entity : ISimulationLifeCycle {

    internal lateinit var simulation: Simulation

    private var isTerminated = false

    abstract fun onEvent(event: Event)

    private fun isTimerExpiredEvent(event: Event)
        = event.data != null && event.data is ITimerExpired

    internal fun processIncomingEvent(event: Event) {
        if(isTerminated)
            return

        if(isTimerExpiredEvent(event))
            (event.data as ITimerExpired).onExpire()
        else
            onEvent(event)
    }

    protected fun scheduleEvent(destination: Entity, delay: Long, data: Any) {
        require(!isTerminated)
        simulation.addEvent(delay, this, destination, data)
    }

    fun setTimer(time: Long, callback: ITimerExpired) {
        scheduleEvent(this, time, callback)
    }

    interface ITimerExpired {
        fun onExpire()
    }

    protected fun terminate() {
        onShutDown()
        isTerminated = true
    }

    fun getCurrentSimulationTime() = simulation.currentClock

}
