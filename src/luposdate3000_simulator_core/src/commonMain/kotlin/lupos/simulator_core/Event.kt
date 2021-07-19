package lupos.simulator_core

public data class Event(
    val eventNumber: Int,
    val occurrenceTime: Long,
    val source: Entity,
    val destination: Entity,
    val data: Any
)
