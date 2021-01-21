package simulation

class Event(
    val occurrenceTime: Double,
    val source: Entity,
    val destination: Entity,
    val type: Int,
    val data: Any?,
    ): Comparable<Event?> {


    override fun compareTo(other: Event?) = when {
            other == null -> { 1 }
            occurrenceTime < other.occurrenceTime -> { -1 }
            occurrenceTime > other.occurrenceTime -> { 1 }
            else -> { 1 }
        }
}
