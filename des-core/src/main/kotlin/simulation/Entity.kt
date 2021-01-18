package simulation


abstract class Entity(val name: String) : Cloneable {

    private val deferredEvents: EventPriorityQueue = EventPriorityQueue()

    fun addIncomingEvent(event: Event) {
        deferredEvents.enqueue(event)
    }

    abstract fun startUpEntity()
    abstract fun processEvent(ev: Event)
    abstract fun shutDownEntity()
    protected abstract fun getEventSendingDelay(destination: Entity, type: EventType, data: Any? = null): Double

    fun processDeferredEvents() {
        var ev: Event
        while (deferredEvents.hasNext()) {
            ev = deferredEvents.dequeue()
            processEvent(ev)
        }
    }

    protected fun sendEvent(destination: Entity, type: EventType, data: Any? = null) {
        val delay = getEventSendingDelay(destination, type, data)
        Simulation.sendEvent(this, destination, delay, type, data)
    }



//    private fun getNetworkDelay(srcEntity: Entity, dstEntity: Entity): Double {
///*        return if (NetworkTopology.isNetworkEnabled()) {
//            NetworkTopology.getDelay(src, dst)
//        } else 0.0*/return 0.0
//    }


}
