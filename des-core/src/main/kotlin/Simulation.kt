import java.util.*

object Simulation {

    private var entities: List<Entity> = ArrayList<Entity>()
    private var futureEvents: EventPriorityQueue = EventPriorityQueue()
    var clock: Long = 0
        private set

    private val maxClockDefault: Long = Long.MAX_VALUE

    var eventCounter = 0
        private set

    var maxClock: Long = maxClockDefault
        private set

    var callback: Callback? = null

    fun start(entities: List<Entity>, callback: Callback?, maxClock: Long = maxClockDefault): Long {
        startUp(entities, callback, maxClock)
        val simClock = run()
        shutDown()
        return simClock
    }

    fun stop() {
        maxClock = clock - 1
    }

    private fun initialize(entities: List<Entity>,callback: Callback?, maxClock: Long) {
        resetVariables()
        this.entities = entities
        this.maxClock = maxClock
        this.callback = callback
    }

    private fun startUpAllEntities() {
        for (ent: Entity in entities) {
            ent.onStartUp()
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

    fun addEvent(occurrenceTime: Long, src: Entity, dest: Entity, data: Any?) {
        eventCounter++
        val updatedOccurringTime = clock + occurrenceTime
        val ev = Event(eventCounter, updatedOccurringTime, src, dest, data)
        futureEvents.enqueue(ev)
    }

    private fun startUp(entities: List<Entity>, callback: Callback?, maxClock: Long) {
        initialize(entities, callback, maxClock)
        callback?.onStartUp()
        startUpAllEntities()
    }

    private fun shutDown() {
        shutDownAllEntities()
        callback?.onShutDown()
        resetVariables()
    }

    private fun shutDownAllEntities() {
        for (ent: Entity in entities) {
            ent.onShutDown()
        }
    }

    private fun resetVariables() {
        entities = ArrayList()
        clock = 0
        maxClock = maxClockDefault
        futureEvents = EventPriorityQueue()
        eventCounter = 0
        callback = null
    }


    fun numberOfEntities() = entities.size

    interface Callback {
        fun onStartUp()
        fun onShutDown()

    }




}
