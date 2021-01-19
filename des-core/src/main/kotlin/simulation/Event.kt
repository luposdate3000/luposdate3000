package simulation

class Event(
    val internalEventType: Int,
    val time: Double,
    val sourceEntity: Entity,
    val destinationEntity: Entity,
    val eventType: EventType?,
    val data: Any?,
    ) : Cloneable, Comparable<Event?> {

    companion object {
        const val SEND_EVENT = 1
        const val BUSY_END = 2
    }

    override fun compareTo(other: Event?) = when {
            other == null -> { 1 }
            time < other.time -> { -1 }
            time > other.time -> { 1 }
            else -> { 1 }
        }

    public override fun clone(): Event {
        return Event(internalEventType, time, sourceEntity, destinationEntity, eventType, data)
    }
}
