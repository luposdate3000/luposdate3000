package lupos.simulator_core

internal class EventPriorityQueue {

    private val comparator = compareBy<Event> { it.occurrenceTime }.thenBy { it.eventNumber }

    private val queue = mutableListOf<Event>()

    fun enqueue(newEvent: Event) {
        var insertionIndex = queue.binarySearch(newEvent, comparator)
        if (insertionIndex < 0) {
            insertionIndex = insertionIndex.inv()
        }
        queue.add(insertionIndex, newEvent)
    }

    fun dequeue(): Event =
        queue.removeAt(0)

    fun peek(): Event =
        queue[0]

    fun hasNext() = queue.isNotEmpty()
}
