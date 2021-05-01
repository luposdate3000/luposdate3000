data class Event(
    val sequenceNumber: Int,
    val occurrenceTime: Long,
    val source: Entity,
    val destination: Entity,
    val data: Any?,
    )
