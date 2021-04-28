import java.util.*

object Simulation {

    private var entities: List<Entity> = ArrayList<Entity>()
    private var futureEvents: EventPriorityQueue = EventPriorityQueue()
    var clock: Long = 0
        private set

    private const val maxClockDefault: Long = Long.MAX_VALUE

    var eventCounter = 0
        private set

    var maxClock: Long = maxClockDefault
        private set

    fun start(entities: List<Entity>, maxClock: Long = maxClockDefault): Long {
        initialize(entities, maxClock)
        logSimulationStart()
        startUpAllEntities()
        val simClock = run()
        logSimulationEnd()
        finishRun()
        return simClock
    }

    fun stop() {
        maxClock = clock - 1
    }

    private fun initialize(entities: List<Entity>, maxClock: Long) {
        resetVariables()
        Simulation.entities = entities
        Simulation.maxClock = maxClock
    }

    fun log(content: String) {
        println(content)
    }

    private fun logSimulationStart() {
        log("")
        log("")
        log("================================================")
        log("Simulation has started")
        log("Number of entities: ${entities.size}")
        log("")
    }

    private fun logSimulationEnd() {
        log("")
        log("Number of processed events: $eventCounter")
        log("Simulation clock: $clock")
        log("Simulation completed")
        log("================================================")
        log("")
        log("")
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
        val timeUpdatedEvent = Event(eventOccurringTime, event.source, event.destination, event.data)
        futureEvents.enqueue(timeUpdatedEvent)
        eventCounter++
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
        entities = ArrayList()
        clock = 0
        maxClock = maxClockDefault
        futureEvents = EventPriorityQueue()
        eventCounter = 0
    }

}
