package layer1.sim


class Event : Cloneable, Comparable<Event?> {

    val eventType: Int
    val timeOfOccurrence: Double

    /** time that the event was removed from the queue for service  */
    private var endWaitingTime = 0.0

    var sourceEntityId: Int
    var destinationEntityId: Int
    /**
     * Get the user-defined tag of this event.
     *
     * @return The tag
     */
    /** the user defined type of the event  */
    val tag: Int

    val eventData: Any?
    var serialNumber: Long = -1

    // ------------------- PACKAGE LEVEL METHODS --------------------------
    internal constructor(evtype: Int, time: Double, src: Int, dest: Int, tag: Int, edata: Any?) {
        eventType = evtype
        this.timeOfOccurrence = time
        sourceEntityId = src
        destinationEntityId = dest
        this.tag = tag
        eventData = edata
    }

    internal constructor(evtype: Int, time: Double, src: Int) {
        eventType = evtype
        this.timeOfOccurrence = time
        sourceEntityId = src
        destinationEntityId = -1
        tag = -1
        eventData = null
    }



/*    override fun toString(): String {
        return ("Event tag = " + tag + " source = " + CloudSim.getEntity(sourceEntityId).getName() + " destination = "
                + CloudSim.getEntity(destinationEntityId).getName())
    }*/


    override fun compareTo(other: Event?): Int {
        return when {
            other == null -> { 1 }
            timeOfOccurrence < other.timeOfOccurrence -> { -1 }
            timeOfOccurrence > other.timeOfOccurrence -> { 1 }
            serialNumber < other.serialNumber -> { -1 }
            serialNumber > other.serialNumber -> { 1 }
            else -> { 1 }
        }
    }


    public override fun clone(): Event {
        return Event(eventType, timeOfOccurrence, sourceEntityId, destinationEntityId, tag, eventData)
    }

    companion object {
        const val SEND = 1
        const val HOLD_DONE = 2
        const val CREATE = 3
    }


}
