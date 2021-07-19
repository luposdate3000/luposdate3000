package lupos.simulator_core

public class Simulation(private val entities: List<Entity>) {

    private var futureEvents: PriorityQueue<Event> = PriorityQueue(compareBy<Event> { it.occurrenceTime }.thenBy { it.eventNumber })

    public var maxClock: Long = Long.MAX_VALUE

    public var steadyClock: Long = Long.MAX_VALUE

    public var callback: ISimulationLifeCycle? = null

    public var clock: Long = 0
        private set

    internal var addedEventCounter: Int = 0
        private set

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

    public fun run() {
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
        callback?.onSteadyState()
    }

    internal fun addEvent(delay: Long, src: Entity, dest: Entity, data: Any) {
        require(delay >= 0) { "Clock cannot go backwards." }
        addedEventCounter++
        val occurringTime = clock + delay
        val ev = Event(addedEventCounter, occurringTime, src, dest, data)
        futureEvents.enqueue(ev)
    }

    public fun startUp() {
        callback?.onStartUp()
        startUpAllEntities()
    }

    public fun shutDown() {
        shutDownAllEntities()
        callback?.onShutDown()
    }

    private fun shutDownAllEntities() {
        for (ent: Entity in entities)
            ent.onShutDown()
    }

    internal fun numberOfEntities(): Int = entities.size
}
