abstract class Entity {

    private val deferredEvents: EventPriorityQueue = EventPriorityQueue()
    var currentState = State.RUNNABLE
        private set
    private class BusyEndIdentifier

    enum class State {
        RUNNABLE,
        BUSY,
        TERMINATED,
    }

    abstract fun onStartUp()
    abstract fun onEvent(event: Event)
    abstract fun onShutDown()

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
            onEvent(ev)
        }
    }

    protected fun sendEvent(destination: Entity, delay: Long, data: Any?) {
        require(currentState == State.RUNNABLE)
        Simulation.addEvent(delay, this, destination, data)
    }

    protected fun beBusy(busyDuration: Long) {
        require(currentState == State.RUNNABLE)
        currentState = State.BUSY
        Simulation.addEvent(busyDuration, this, this, BusyEndIdentifier())
    }

    protected fun terminate() {
        currentState = State.TERMINATED
        deferredEvents.clear()
    }

}
