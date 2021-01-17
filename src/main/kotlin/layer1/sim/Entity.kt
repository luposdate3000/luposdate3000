package layer1.sim


abstract class Entity(val name: String) : Cloneable {

    var id: Int = -1

    var eventBuffer: Event? = null

    var currentState: Int = RUNNABLE

    companion object {
        const val RUNNABLE = 0
        const val WAITING = 1
        const val HOLDING = 2
        const val FINISHED = 3
    }

    init {
        Simulation.addEntityToSimulation(this)
    }


    fun pause(delay: Double) {
        Simulation.pause(id, delay)
    }

    /**
     * Count how many events matching a predicate are waiting in the entity's deferred queue.
     *
     * @param p The event selection predicate
     * @return The count of matching events
     */
    fun numEventsWaiting(p: Predicate): Int {
        return Simulation.waiting(id, p)
    }

    /**
     * Count how many events are waiting in the entity's deferred queue.
     *
     * @return The count of events
     */
    fun numEventsWaiting(): Int {
        return Simulation.waiting(id, Simulation.SIM_ANY)
    }

    /**
     * Extract the first event matching a predicate waiting in the entity's deferred queue.
     *
     * @param p The event selection predicate
     * @return the simulation event
     */
    fun selectEvent(p: Predicate): Event? {
        return if (!Simulation.isSimulationRunning) {
            null
        } else Simulation.select(id, p)
    }

    /**
     * Cancel the first event matching a predicate waiting in the entity's future queue.
     *
     * @param p The event selection predicate
     * @return The number of events cancelled (0 or 1)
     */
    fun cancelEvent(p: Predicate): Event? {
        return if (!Simulation.isSimulationRunning) {
            null
        } else Simulation.cancel(id, p)
    }

    /**
     * Get the first event matching a predicate from the deferred queue, or if none match, wait for
     * a matching event to arrive.
     *
     * @param p The predicate to match
     * @return the simulation event
     */
    fun getNextEvent(p: Predicate): Event? {
        if (!Simulation.isSimulationRunning) {
            return null
        }
        return if (numEventsWaiting(p) > 0) {
            selectEvent(p)
        } else null
    }

    /**
     * Wait for an event matching a specific predicate. This method does not check the entity's
     * deferred queue.
     *
     * @param p The predicate to match
     */
    fun waitForEvent(p: Predicate) {
        if (!Simulation.isSimulationRunning) {
            return
        }
        Simulation.wait(id, p)
        currentState = WAITING
    }




    abstract fun startUpEntity()
    abstract fun processEvent(ev: Event)
    abstract fun shutDownEntity()

    fun run() {
        var ev: Event? = determineFirstEventToProcess()
        while (ev != null) {
            processEvent(ev)
            if (currentState != RUNNABLE) {
                break
            }
            ev = getNextEvent()
        }
        eventBuffer = null
    }

    private fun determineFirstEventToProcess() : Event?
       =  if (eventBuffer != null) eventBuffer else getNextEvent()

    fun getNextEvent() : Event? {
        return getNextEvent(Simulation.SIM_ANY)
    }



    /**
     * Sends an event/message to another entity by <tt>delaying</tt> the simulation time from the
     * current time, with a tag representing the event type.
     *
     * @param destinationEntityId the id number of the destination entity
     * @param delay how long from the current simulation time the event should be sent. If delay is
     * a negative number, then it will be changed to 0
     * @param cloudSimTag an user-defined number representing the type of an event/message
     * @pre entityID > 0
     * @pre delay >= 0.0
     * @post $none
     */
    protected fun send(destinationEntityId: Int, delay: Double, cloudSimTag: Int, data: Any? = null) {
        var calculatedDelay = delay

        if (destinationEntityId != id) { // does not delay self messages
            calculatedDelay += getNetworkDelay(id, destinationEntityId)
        }
        //TODO Networkdelay hat in dieser Klasse nichts zu suchen -> Muss von außen gesetzt werden

        Simulation.sendEvent(id, destinationEntityId, calculatedDelay, cloudSimTag, data)
    }



    /**
     * Gets the network delay associated to the sent of a message from a given source to a given
     * destination.
     *
     * @param src source of the message
     * @param dst destination of the message
     * @return delay to send a message from src to dst
     * @pre src >= 0
     * @pre dst >= 0
     */
    private fun getNetworkDelay(src: Int, dst: Int): Double {
/*        return if (NetworkTopology.isNetworkEnabled()) {
            NetworkTopology.getDelay(src, dst)
        } else 0.0*/return 0.0
    }


}
