package simulation

class Event(
    val internalTag: Int,
    val occurrenceTime: Double,
    val source: Entity,
    val destination: Entity,
    val type: EventType?,
    val data: Any?,
    ) : Cloneable, Comparable<Event?> {

    companion object {
        const val SEND_EVENT = 1
        const val BUSY_END = 2
    }

    override fun compareTo(other: Event?) = when {
            other == null -> { 1 }
            occurrenceTime < other.occurrenceTime -> { -1 }
            occurrenceTime > other.occurrenceTime -> { 1 }
            else -> { 1 }
        }

    public override fun clone(): Event {
        return Event(internalTag, occurrenceTime, source, destination, type, data)
    }
}
