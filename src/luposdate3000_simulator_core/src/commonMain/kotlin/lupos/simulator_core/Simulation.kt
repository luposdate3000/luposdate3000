package lupos.simulator_core
import lupos.visualize.distributed.database.VisualisationNetwork

public class Simulation(private val entities: List<Entity>) {

    public val visualisationNetwork: VisualisationNetwork = VisualisationNetwork()

    private var futureEvents: EventPriorityQueue = EventPriorityQueue()

    public var maxClock: Double = Double.MAX_VALUE

    public var steadyClock: Double = Double.MAX_VALUE

    public var callback: ISimulationLifeCycle? = null

    public var clock: Double = 0.0
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

    internal fun addEvent(delay: Double, src: Entity, dest: Entity, data: Any) {
        require(delay >= 0) { "Clock cannot go backwards." }
        addedEventCounter++
        val occurringTime: Double = clock + delay
        val ev = Event(addedEventCounter, occurringTime, src, dest, data)
        futureEvents.enqueue(ev)
    }

    public fun startUp() {
        callback?.onStartUp()
        startUpAllEntities()
    }

    public fun shutDown() {
        shutDownAllEntities()
//        println(visualisationNetwork.toString())
//        visualisationNetwork.toImage()
        callback?.onShutDown()
    }

    private fun shutDownAllEntities() {
        for (ent: Entity in entities)
            ent.onShutDown()
    }

    internal fun numberOfEntities(): Int = entities.size
}
