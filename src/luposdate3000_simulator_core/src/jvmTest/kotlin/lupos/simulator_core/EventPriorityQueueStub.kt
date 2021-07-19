package lupos.simulator_core

internal class EventPriorityQueueStub {

    fun getEventPriorityQueue(): PriorityQueue<Event> {
        return PriorityQueue(compareBy<Event> { it.occurrenceTime }.thenBy { it.eventNumber })
    }
}
