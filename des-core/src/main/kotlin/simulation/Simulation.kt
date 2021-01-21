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
        processTimeEqualEvents(first.occurrenceTime)
    }

    private fun processNextFutureEvent(): Event {
        val next = futureEvents.dequeue()
        increaseSimulationClock(next)
        next.destination.addIncomingEvent(next)
        return next
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

    private fun increaseSimulationClock(event: Event) {
        clock = event.occurrenceTime
    }

    fun sendEvent(event: Event) {
        val newTime = calcEventOccurringTime(event.occurrenceTime)
        val timeUpdatedEvent = Event(newTime, event.source, event.destination, event.type, event.data)
        futureEvents.enqueue(timeUpdatedEvent)
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
