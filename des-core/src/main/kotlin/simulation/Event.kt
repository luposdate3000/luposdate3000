package simulation

class Event(
    val occurrenceTime: Double,
    val source: Entity,
    val destination: Entity,
    val type: Int,
    val data: Any?,
    )
