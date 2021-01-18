package simulation
import java.util.*

object Simulation {

    private var entities: MutableList<Entity> = ArrayList<Entity>()
    private var futureEvents: EventPriorityQueue = EventPriorityQueue()
    var clock = 0.0
        private set

    fun addEntityToSimulation(entity: Entity) {
        entities.add(entity)
    }

    fun runSimulation(){
        prepareRun()
        run()
        finishRun()
    }

    private fun prepareRun() {
        startUpAllEntities()
    }

    private fun startUpAllEntities() {
        for (ent: Entity in entities) {
            ent.startUpEntity()
        }
    }

    private fun run() {
        var finished = false
        while (!finished) {
            finished = runClockTick()
        }
    }

    private fun runClockTick(): Boolean {
        runAllEntities()
        if (futureEvents.hasNext()) {
            dealWithFirstFutureEvents()
            return false
        }
        return true
    }

    private fun runAllEntities() {
        for (entity in entities) {
            entity.processDeferredEvents()
        }
    }

    private fun dealWithFirstFutureEvents() {
        val first = processNextFutureEvent()
        processTimeEqualEvents(first.time)
    }

    private fun processNextFutureEvent(): Event {
        val next = futureEvents.dequeue()
        processEvent(next)
        return next
    }

    private fun processTimeEqualEvents(time: Double) {
        var nextEvent: Event
        while (futureEvents.hasNext()) {
            nextEvent = futureEvents.peek()
            if (time == nextEvent.time) {
                processNextFutureEvent()
            }
            else {
                break
            }
        }
    }

    private fun processEvent(event: Event) {
        increaseSimulationClock(event)
        when (event.internalEventType) {
            Event.SEND_EVENT -> {
                event.destinationEntity.addIncomingEvent(event)
            }
        }
    }

    private fun increaseSimulationClock(event: Event) {
        clock = event.time
    }

    fun sendEvent(src: Entity, dest: Entity, delay: Double, type: EventType, data: Any?) {
        val newTime = clock + delay
        val e = Event(Event.SEND_EVENT, newTime, src, dest, type, data)
        futureEvents.enqueue(e)
    }

    private fun finishRun() {
        shutDownAllEntities()
    }

    private fun shutDownAllEntities() {
        for (ent: Entity in entities) {
            ent.shutDownEntity()
        }
    }

}
