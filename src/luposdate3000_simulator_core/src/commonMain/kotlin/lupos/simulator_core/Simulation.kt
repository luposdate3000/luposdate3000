package lupos.simulator_core

public class Simulation(
    private val entities: List<Entity>,
    internal var maxClock: Long = Long.MAX_VALUE,
    private var steadyClock: Long = Long.MAX_VALUE,
    private var callback: ISimulationLifeCycle
) {

    private var futureEvents: EventPriorityQueue = EventPriorityQueue()

    init {
        callback.simulation = this
    }

    private var clock: Long = 0

    internal var addedEventCounter: Int = 0
        private set

    public fun getCurrentClock(): Long = clock

    public fun startSimulation() {
        startUp()
        run()
        shutDown()
    }

    public fun endSimulation() {
        maxClock = clock
    }

    private fun startUpAllEntities() {
        for (entity: Entity in entities) {
            entity.simulation = this
            entity.onStartUp()
        }
    }

    private fun run() {
        var isFinished = false
        while (!isFinished)
            isFinished = runNextTimeStep()
    }

    private fun runNextTimeStep(): Boolean {
        if (!futureEvents.hasNext()) {
            return true
        }

        if (isSteadyStateReached()) {
            transferToSteadyState()
        }

        if (isMaxClockReached()) {
            return true
        }

        processEvent()
        return false
    }

    private fun processEvent() {
        val nextEvent = futureEvents.dequeue()
        clock = nextEvent.occurrenceTime
        val entity = nextEvent.destination
        entity.processIncomingEvent(nextEvent)
    }

    private fun transferToSteadyState() {
        clock = steadyClock
        notifyAboutSteadyState()
    }

    private fun getTimeOfNextTimeStep() = futureEvents.peek().occurrenceTime

    private fun isSteadyStateReached() = getTimeOfNextTimeStep() > steadyClock

    private fun isMaxClockReached() = getTimeOfNextTimeStep() > maxClock

    private fun notifyAboutSteadyState() {
        for (entity in entities)
            entity.onSteadyState()
        callback.onSteadyState()
    }

    internal fun addEvent(delay: Long, src: Entity, dest: Entity, data: Any) {
        require(delay >= 0) { "Clock cannot go backwards." }
        addedEventCounter++
        val occurringTime = clock + delay
        val ev = Event(addedEventCounter, occurringTime, src, dest, data)
        futureEvents.enqueue(ev)
    }

    private fun startUp() {
        callback.onStartUp()
        startUpAllEntities()
    }

    private fun shutDown() {
        shutDownAllEntities()
        callback.onShutDown()
    }

    private fun shutDownAllEntities() {
        for (ent: Entity in entities)
            ent.onShutDown()
    }

    internal fun numberOfEntities(): Int = entities.size
}
