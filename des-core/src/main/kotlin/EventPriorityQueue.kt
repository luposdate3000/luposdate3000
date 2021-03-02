import java.util.*


class EventPriorityQueue {

    private val comparator = compareBy<Event> { it.occurrenceTime }
    private val queue: PriorityQueue<Event> = PriorityQueue(comparator)

    fun enqueue(newEvent: Event) {
        queue.add(newEvent)
    }

    fun dequeue(): Event = queue.remove()

    fun peek(): Event = queue.element()

    fun hasNext() = queue.size > 0

    fun clear() = queue.clear()

}