package simulation

class Event(
    val occurrenceTime: Long,
    val source: Entity,
    val destination: Entity,
    val type: Int,
    val data: Any?,
    )
