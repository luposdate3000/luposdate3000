package simulation

class Event(
    val occurrenceTime: Long,
    val source: Entity,
    val destination: Entity,
    val data: Any?,
    )
