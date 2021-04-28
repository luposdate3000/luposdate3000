abstract class Entity() {

    private val deferredEvents: EventPriorityQueue = EventPriorityQueue()
    var currentState = State.RUNNABLE
        private set
    private class BusyEndIdentifier

    enum class State {
        RUNNABLE,
        BUSY,
        TERMINATED,
    }

    abstract fun startUpEntity()
    abstract fun processEvent(event: Event)
    abstract fun shutDownEntity()

    fun addIncomingEvent(event: Event) {
        if(isBusyEndEvent(event)) {
            currentState = State.RUNNABLE
        }
        deferredEvents.enqueue(event)
    }

    private fun isBusyEndEvent(event: Event)
        = event.data != null && event.data is BusyEndIdentifier


    fun processDeferredEvents() {
        var ev: Event
        while (deferredEvents.hasNext() && currentState == State.RUNNABLE) {
            ev = deferredEvents.dequeue()
            processEvent(ev)
        }
    }

    protected fun sendEvent(destination: Entity, delay: Long, data: Any?) {
        require(currentState == State.RUNNABLE)
        val event = Event(delay, this, destination, data)
        Simulation.addEvent(event)
    }

    protected fun beBusy(busyDuration: Long) {
        require(currentState == State.RUNNABLE)
        currentState = State.BUSY
        val event = Event(busyDuration, this, this, BusyEndIdentifier())
        Simulation.addEvent(event)
    }

    protected fun terminate() {
        currentState = State.TERMINATED
        deferredEvents.clear()
    }

}
