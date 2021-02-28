package simulation
import java.util.*

object Simulation {

    private var entities: MutableList<Entity> = ArrayList<Entity>()
    private var futureEvents: EventPriorityQueue = EventPriorityQueue()
    var clock: Long = 0
        private set
    var maxClock: Long = Long.MAX_VALUE
        private set

    fun initialize(entities: MutableList<Entity>, maxClock: Long = Long.MAX_VALUE) {
        resetVariables()
        this.entities = entities
        this.maxClock = maxClock
    }

    fun runSimulation(): Long {
        startUpAllEntities()
        val simClock = run()
        finishRun()
        return simClock
    }

    private fun startUpAllEntities() {
        for (ent: Entity in entities) {
            ent.startUpEntity()
        }
    }

    private fun run(): Long {
        var finished = false
        while (!finished) {
            finished = runClockTick()
        }
        return clock
    }

    private fun runClockTick(): Boolean {
        runAllEntities()
        if (futureEvents.hasNext() && isInTime()) {
            dealWithFirstFutureEvents()
            return false
        }
        return true
    }

    private fun isInTime(): Boolean {
        val nextEvent = futureEvents.peek()
        return nextEvent.occurrenceTime <= maxClock
    }

    private fun runAllEntities() {
        for (entity in entities) {
            entity.processDeferredEvents()
        }
    }

    private fun dealWithFirstFutureEvents() {
        val first = processNextFutureEvent()
        processTimeEqualEvents(first.occurrenceTime)
    }

    private fun processNextFutureEvent(): Event {
        val nextEvent = futureEvents.dequeue()
        clock = nextEvent.occurrenceTime
        nextEvent.destination.addIncomingEvent(nextEvent)
        return nextEvent
    }

    private fun processTimeEqualEvents(time: Long) {
        var nextEvent: Event
        while (futureEvents.hasNext()) {
            nextEvent = futureEvents.peek()
            if (time == nextEvent.occurrenceTime) {
                processNextFutureEvent()
            }
            else {
                break
            }
        }
    }

    fun addEvent(event: Event) {
        val eventOccurringTime = clock + event.occurrenceTime
        val timeUpdatedEvent = Event(eventOccurringTime, event.source, event.destination, event.type, event.data)
        futureEvents.enqueue(timeUpdatedEvent)
    }

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
        clock = 0
        futureEvents.clear()
    }

}
