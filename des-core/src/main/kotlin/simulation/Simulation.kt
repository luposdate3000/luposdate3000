package simulation
import java.util.*

object Simulation {

    private var entities: MutableList<Entity> = ArrayList<Entity>()
    private var futureEvents: EventPriorityQueue = EventPriorityQueue()
    var clock = 0.0
        private set

    fun initialize(entities: ArrayList<Entity>) {
        resetVariables()
        this.entities = entities
    }

    fun runSimulation(): Double {
        prepareRun()
        val simClock = run()
        finishRun()
        return simClock
    }

    private fun prepareRun() {
        startUpAllEntities()
    }

    private fun startUpAllEntities() {
        for (ent: Entity in entities) {
            ent.startUpEntity()
        }
    }

    private fun run(): Double {
        var finished = false
        while (!finished) {
            finished = runClockTick()
        }
        return clock
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
            Event.BUSY_END -> {
                event.sourceEntity.stopBeingBusy()
            }
        }
    }

    private fun increaseSimulationClock(event: Event) {
        clock = event.time
    }

    fun sendEvent(src: Entity, dest: Entity, delay: Double, type: EventType, data: Any?) {
        val event = Event(Event.SEND_EVENT, calcEventOccurringTime(delay), src, dest, type, data)
        futureEvents.enqueue(event)
    }

    fun setEntityBusy(src: Entity, delay: Double) {
        val event = Event(Event.BUSY_END, calcEventOccurringTime(delay), src, src, null, null)
        futureEvents.enqueue(event)
    }

    private fun calcEventOccurringTime(delay: Double) = clock + delay

    private fun finishRun() {
        shutDownAllEntities()
        resetVariables()
    }

    private fun shutDownAllEntities() {
        for (ent: Entity in entities) {
            ent.shutDownEntity()
        }
    }

    private fun resetVariables() {
        entities.clear()
        clock = 0.0
        futureEvents.clear()
    }


}
