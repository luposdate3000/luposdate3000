package lupos.simulator_core

public class Simulation(private val entities: List<Entity>) {

    private var futureEvents: EventPriorityQueue = EventPriorityQueue()

    private var steadyClock: Long = Long.MAX_VALUE

    public var maxClock: Long = Long.MAX_VALUE
        private set

    private var callback: ISimulationLifeCycle? = null

    public var currentClock: Long = 0
        private set

    public var addedEventCounter: Int = 0
        private set

    public var processedEventCounter: Int = 0
        private set

    public fun start() {
        startUp()
        run()
        shutDown()
    }

    public fun stop() {
        maxClock = currentClock
    }

    public fun steadyStateReachedAt(time: Long) {
        steadyClock = time
    }

    public fun setMaximalTime(time: Long) {
        maxClock = time
    }

    public fun setLifeCycleCallback(callback: ISimulationLifeCycle) {
        this.callback = callback
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
        if (!futureEvents.hasNext())
            return true

        if (isSteadyStateReached())
            transferToSteadyState()

        if (isMaxClockReached())
            return true

        processEvent()
        return false
    }

    private fun processEvent() {
        processedEventCounter++
        val nextEvent = futureEvents.dequeue()
        currentClock = nextEvent.occurrenceTime
        val entity = nextEvent.destination
        entity.processIncomingEvent(nextEvent)
    }

    private fun transferToSteadyState() {
        currentClock = steadyClock
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

    internal fun addEvent(occurrenceTime: Long, src: Entity, dest: Entity, data: Any) {
        addedEventCounter++
        val updatedOccurringTime = currentClock + occurrenceTime
        val ev = Event(addedEventCounter, updatedOccurringTime, src, dest, data)
        futureEvents.enqueue(ev)
    }

    private fun startUp() {
        callback?.onStartUp()
        startUpAllEntities()
    }

    private fun shutDown() {
        shutDownAllEntities()
        callback?.onShutDown()
    }

    private fun shutDownAllEntities() {
        for (ent: Entity in entities)
            ent.onShutDown()
    }

    public fun numberOfEntities(): Int = entities.size
}
