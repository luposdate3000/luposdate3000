abstract class Entity {

    private val deferredEvents: EventPriorityQueue = EventPriorityQueue()
    var isTerminated = false
        private set

    abstract fun onStartUp()
    abstract fun onEvent(event: Event)
    abstract fun onShutDown()

    fun addIncomingEvent(event: Event) {
        deferredEvents.enqueue(event)
    }

    private fun isTimerExpiredEvent(event: Event)
        = event.data != null && event.data is ITimerExpired

    fun processDeferredEvents() {
        var ev: Event
        while (deferredEvents.hasNext() && !isTerminated) {
            ev = deferredEvents.dequeue()
            if(isTimerExpiredEvent(ev))
                (ev.data as ITimerExpired).onExpire()
            else
                onEvent(ev)
        }
    }

    protected fun scheduleEvent(destination: Entity, delay: Long, data: Any) {
        require(!isTerminated)
        Simulation.addEvent(delay, this, destination, data)
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
        deferredEvents.clear()
    }

}
