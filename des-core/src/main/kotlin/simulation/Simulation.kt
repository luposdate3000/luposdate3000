package simulation
import java.util.*

object Simulation {

    private var entities: MutableList<Entity> = ArrayList<Entity>()
    private var futureEvents: EventPriorityQueue = EventPriorityQueue()
    var clock = 0.0
        private set
    var maxClock = Double.MAX_VALUE
        private set

    fun initialize(entities: MutableList<Entity>, maxClock: Double = Double.MAX_VALUE) {
        resetVariables()
        this.entities = entities
        this.maxClock = maxClock
    }

    fun runSimulation(): Double {
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

    private fun run(): Double {
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

    private fun processTimeEqualEvents(time: Double) {
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
        clock = 0.0
        futureEvents.clear()
    }

}
