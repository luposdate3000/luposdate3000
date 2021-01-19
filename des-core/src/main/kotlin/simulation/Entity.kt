package simulation


abstract class Entity(val name: String) : Cloneable {

    private val deferredEvents: EventPriorityQueue = EventPriorityQueue()
    private var currentState = RUNNABLE
    private var busyDuration = 0.0

    private companion object {
        const val RUNNABLE = 1
        const val BUSY = 2
        const val TERMINATED = 2
    }

    abstract fun startUpEntity()
    abstract fun processEvent(ev: Event)
    abstract fun shutDownEntity()

    fun addIncomingEvent(event: Event) {
        deferredEvents.enqueue(event)
    }

    fun processDeferredEvents() {
        var ev: Event
        while (deferredEvents.hasNext() && currentState == RUNNABLE) {
            ev = deferredEvents.dequeue()
            processEvent(ev)
        }
    }

    protected fun sendEvent(destination: Entity, delay: Double, type: EventType, data: Any? = null) {
        val updatedDelay = addBusyDuration(delay)
        Simulation.sendEvent(this, destination, updatedDelay, type, data)
    }

    protected fun beBusy(duration: Double) {
        currentState = BUSY
        busyDuration = duration
        Simulation.setEntityBusy(this, duration)
    }

    private fun addBusyDuration(delay: Double)
            = if(currentState == BUSY) delay + busyDuration else delay

    fun stopBeingBusy() {
        currentState = RUNNABLE
        busyDuration = 0.0
    }

    protected fun terminate() {
        currentState = TERMINATED
        deferredEvents.clear()
    }

}
