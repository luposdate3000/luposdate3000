package layer1.sim


import java.lang.IllegalArgumentException
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap


object Simulation {

    /** The id of CloudSimShutdown entity.  */
    private var shutdownId = -1

    /** The Constant NOT_FOUND.  */
    private val NOT_FOUND = -1

    /** The trace flag.  */
    private var traceFlag = false

    /** The calendar.  */
    private var calendar: Calendar? = null

    /** The termination time.  */
    private var terminateAt = -1.0

    /** The minimal time between events. Events within shorter periods after the last event are discarded.  */
    var minTimeBetweenEvents = 0.1
        private set


    private var entities: MutableList<Entity>? = null

    private var future: PendingEvents? = null

    private var deferred: CurrentEvents? = null

    /** The simulation clock.  */
    var clock = 0.0
        private set

    /** Flag for checking if the simulation is running.  */
    var isSimulationRunning = false
        private set

    /** The entities by name.  */
    private var entitiesByName: MutableMap<String, Entity>? = null

    /** The predicates used in entity wait methods  */
    private var waitPredicates: MutableMap<Int, Predicate>? = null

    private var isShutDownForcedExternally = false

    /** A standard predicate that matches any event.  */
    val SIM_ANY = PredicateAny()


    private fun initCommonVariable(_calendar: Calendar?, _traceFlag: Boolean) {
        initialize()
        // NOTE: the order for the below 3 lines are important
        traceFlag = _traceFlag

        // Set the current Wall clock time as the starting time of
        // simulation
        if (_calendar == null) {
            calendar = Calendar.getInstance()
        } else {
            calendar = _calendar
        }

        // creates a CloudSimShutdown object
        val shutdown = ShutdownEntity("CloudSimShutdown")
        shutdownId = shutdown.id
    }

    /**
     * Initialises CloudSim parameters. This method should be called before creating any entities.
     *
     *
     * Inside this method, it will create the following CloudSim entities:
     *
     *  * CloudInformationService.
     *  * CloudSimShutdown
     *
     *
     *
     *
     * @param numUser the number of User Entities created. This parameters indicates that
     * [gridsim.CloudSimShutdown] first waits for all user entities's
     * END_OF_SIMULATION signal before issuing terminate signal to other entities
     * @param cal starting time for this simulation. If it is <tt>null</tt>, then the time will be
     * taken from <tt>Calendar.getInstance()</tt>
     * @param traceFlag <tt>true</tt> if CloudSim trace need to be written
     * @see gridsim.CloudSimShutdown
     *
     * @see CloudInformationService.CloudInformationService
     *
     * @pre numUser >= 0
     * @post $none
     */
    fun init(cal: Calendar?, traceFlag: Boolean) {
        try {
            initCommonVariable(cal, traceFlag)


        } catch (s: IllegalArgumentException) {
            println("CloudSim.init(): The simulation has been terminated due to an unexpected error")
            println(s.message)
        } catch (e: Exception) {
            println("CloudSim.init(): The simulation has been terminated due to an unexpected error")
            println(e.message)
        }
    }


    /**
     * Initialise the simulation for stand alone simulations. This function should be called at the
     * start of the simulation.
     */
    private fun initialize() {
        printMessage("Initialising...")
        entities = ArrayList<Entity>()
        entitiesByName = LinkedHashMap<String, Entity>()
        future = PendingEvents()
        deferred = CurrentEvents()
        waitPredicates = HashMap()
        clock = 0.0
        isSimulationRunning = false
    }


    /**
     * Starts the execution of CloudSim simulation. It waits for complete execution of all entities,
     * i.e. until all entities threads reach non-RUNNABLE state or there are no more events in the
     * future event queue.
     *
     *
     * **Note**: This method should be called after all the entities have been setup and added.
     */
    fun startSimulation(): Double {
        printMessage("Starting the Simulation")

            val clock = run()

            // reset all static variables
            shutdownId = -1
            calendar = null
            traceFlag = false
            return clock

    }


    /**
     * Add a new entity to the simulation. This is present for compatibility with existing
     * simulations since entities are automatically added to the simulation upon instantiation.
     */
    fun addEntityToSimulation(entity: Entity) {
        val evt: Event
        if (isSimulationRunning) {
            evt = Event(Event.CREATE, clock, 1, 0, 0, entity)
            future!!.addEvent(evt)
        }
        if (entity.id == -1) { // Only add once!
            entity.id = entities!!.size
            entities!!.add(entity)
            entitiesByName!![entity.name] = entity
        }
    }


    private fun runAllRunnableEntities() {
        for (i in 0 until entities!!.size) {
            if (entities!![i].currentState == Entity.RUNNABLE) {
                entities!![i].run()
            }
        }
    }

    private fun dealWithPendingEvents() {
        val toRemove: MutableList<Event> = ArrayList<Event>()
        val first = processFirstFutureEvent()
        val fit = future!!.getIterator()

        // Check if next events are at same time...
        var trymore = fit.hasNext()
        while (trymore) {
            val next: Event = fit.next()
            if (next.timeOfOccurrence == first.timeOfOccurrence) {
                processEvent(next)
                toRemove.add(next)
                trymore = fit.hasNext()
            } else {
                trymore = false
            }
        }
        future!!.removeAll(toRemove)
    }

    private fun processFirstFutureEvent() : Event {
        val fit: Iterator<Event> = future!!.getIterator()
        val first: Event = fit.next()
        processEvent(first)
        future!!.remove(first)
        return first
    }


    /**
     * Internal method used to run one tick of the simulation. This method should **not** be
     * called in simulations.
     *
     * @return true, if successful otherwise
     */
    private fun runClockTick(): Boolean {
        runAllRunnableEntities()
        val isQueueEmpty: Boolean
        if (hasPendingEvents()) {
            isQueueEmpty = false
            dealWithPendingEvents()
        } else {
            isQueueEmpty = true
            isSimulationRunning = false
        }
        return isQueueEmpty
    }

    private fun hasPendingEvents() = future!!.getSize() > 0

    /**
     * Used to hold an entity for some time.
     */
    fun pause(src: Int, delay: Double) {
        if (!isSimulationRunning) {
            return
        }
        val event = Event(Event.HOLD_DONE, clock + delay, src)
        future!!.addEvent(event)
        entities!![src].currentState = Entity.HOLDING
    }

    /**
     * Used to send an event from one entity to another.
     */
    fun sendEvent(src: Int, dest: Int, delay: Double, tag: Int, data: Any?) {

        val e = Event(Event.SEND, clock + delay, src, dest, tag, data)
        future!!.addEvent(e)
    }


    /**
     * Sets an entity's state to be waiting. The predicate used to wait for an event is now passed
     * to Sim_system. Only events that satisfy the predicate will be passed to the entity. This is
     * done to avoid unnecessary context switches.
     */
    fun wait(src: Int, p: Predicate) {
        entities!![src].currentState = Entity.WAITING
        if (p !== SIM_ANY) {
            // If a predicate has been used store it in order to check it
            waitPredicates!![src] = p
        }
    }

    /**
     * Checks if events for a specific entity are present in the deferred event queue.
     */
    fun waiting(entityId: Int, p: Predicate): Int {
        var count = 0
        val iterator: Iterator<Event> = deferred!!.getIterator()
        while (iterator.hasNext()) {
            val event = iterator.next()
            if ((event.destinationEntityId == entityId) && (p.match(event))) {
                count++
            }
        }
        return count
    }

    /**
     * Selects an event matching a predicate.
     */
    fun select(src: Int, p: Predicate): Event? {
        var ev: Event? = null
        val iterator: MutableIterator<Event> = deferred!!.getIterator()
        while (iterator.hasNext()) {
            ev = iterator.next()
            if (ev.destinationEntityId == src && p.match(ev)) {
                iterator.remove()
                break
            }
        }
        return ev
    }

    /**
     * Find first deferred event matching a predicate.
     */
    fun findFirstDeferred(src: Int, p: Predicate): Event? {
        var ev: Event? = null
        val iterator: Iterator<Event> = deferred!!.getIterator()
        while (iterator.hasNext()) {
            ev = iterator.next()
            if (ev.destinationEntityId == src && p.match(ev)) {
                break
            }
        }
        return ev
    }

    /**
     * Removes an event from the event queue.
     */
    fun cancel(src: Int, p: Predicate): Event? {
        var ev: Event? = null
        val iter: MutableIterator<Event> = future!!.getIterator()
        while (iter.hasNext()) {
            ev = iter.next()
            if (ev.sourceEntityId == src && p.match(ev)) {
                iter.remove()
                break
            }
        }
        return ev
    }

    /**
     * Removes all events that match a given predicate from the future event queue returns true if
     * at least one event has been cancelled; false otherwise
     */
    fun cancelAll(src: Int, p: Predicate): Boolean {
        var ev: Event? = null
        val previousSize: Int = future!!.getSize()
        val iter: MutableIterator<Event> = future!!.getIterator()
        while (iter.hasNext()) {
            ev = iter.next()
            if (ev.sourceEntityId == src && p.match(ev)) {
                iter.remove()
            }
        }
        return previousSize < future!!.getSize()
    }

    private fun sendEventToWaitingEntity(event: Event, entity: Entity) {

        val p = waitPredicates!![entity.id]
        if ((p == null) || (event.tag == 9999) || (p.match(event))) {
            entity.eventBuffer = event.clone()
            entity.currentState = Entity.RUNNABLE
            waitPredicates!!.remove(entity.id)
        } else {
            deferred!!.addEvent(event)
        }
    }

    private fun processEvent(event: Event) {

        val destinationEntity: Entity
        // Update the system's clock
        clock = event.timeOfOccurrence
        when (event.eventType) {
            Event.CREATE -> {
                val newEntity: Entity = event.eventData as Entity
                newEntity.startUpEntity()
            }
            Event.SEND -> {
                // Check for matching wait



                    destinationEntity = entities!![event.destinationEntityId]
                    if (destinationEntity.currentState == Entity.WAITING) {
                        sendEventToWaitingEntity(event, destinationEntity)
                    } else {
                        deferred!!.addEvent(event)
                    }

            }
            Event.HOLD_DONE -> {

                    entities!![event.sourceEntityId].currentState = Entity.RUNNABLE

            }

        }
    }


    private fun startUpAllEntities() {
        for (ent: Entity in entities!!) {
            ent.startUpEntity()
        }
    }

    private fun startRun() {
        if (!isSimulationRunning) {
            isSimulationRunning = true
            startUpAllEntities()
        }
    }

    private fun shutDownAllEntities() {
        for (ent: Entity in entities!!) {
            ent.shutDownEntity()
        }
    }


    /**
     * Start the simulation running. This should be called after all the entities have been setup
     * and added, and their ports linked.
     *
     * @return the double last clock value
     */
    fun run(): Double {
        startRun()
        while (true) {
            if (runClockTick() || isShutDownForcedExternally) {
                break
            }


        }
        val lastClockValue = clock
        finishRun()
        return lastClockValue
    }


    private fun finishRun() {
        finishEntities()
        shutDownAllEntities()
        resetAllVariables()
    }

    private fun finishEntities() {
        // Allow all entities to exit their body method
        if (!isShutDownForcedExternally) {
            for (ent: Entity in entities!!) {
                if (ent.currentState != Entity.FINISHED) {
                    ent.run()
                }
            }
        }
    }

    private fun resetAllVariables() {
        entities = null
        entitiesByName = null
        future = null
        deferred = null
        clock = 0.0
        isSimulationRunning = false
        waitPredicates = null
        isShutDownForcedExternally = false
    }


    fun forceShutDown() {
        isShutDownForcedExternally = true
    }


    private fun printMessage(message: String) {
        println(message)
    }
}
