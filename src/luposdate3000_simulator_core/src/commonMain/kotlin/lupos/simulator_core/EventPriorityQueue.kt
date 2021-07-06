package lupos.simulator_core

internal class EventPriorityQueue {

    private val comparator = compareBy<Event> { it.occurrenceTime }.thenBy { it.eventNumber }

    private val queue = mutableListOf<Event>()

    internal fun enqueue(newEvent: Event) {
        var insertionIndex = queue.binarySearch(newEvent, comparator)
        if (insertionIndex < 0) {
            insertionIndex = insertionIndex.inv()
        }
        queue.add(insertionIndex, newEvent)
    }

    internal fun dequeue(): Event =
        queue.removeAt(0)

    internal fun peek(): Event =
        queue[0]

    internal fun hasNext() = queue.isNotEmpty()
}
