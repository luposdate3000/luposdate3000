package layer1.sim
import java.util.*



class CurrentEvents {

    private val list: MutableList<Event> = LinkedList<Event>()

    private var maxTime = -1.0


    fun addEvent(newEvent: Event) {
        if (isLastOfAllEvents(newEvent)) {
            addAsLastEvent(newEvent)
        }
        else {
            addSorted(newEvent)
        }
    }

    private fun addAsLastEvent(newEvent: Event) {
        list.add(newEvent)
        maxTime = newEvent.timeOfOccurrence
    }

    private fun addSorted(newEvent: Event) {
        val iterator: MutableListIterator<Event> = list.listIterator()
        var current: Event
        while (iterator.hasNext()) {
            current = iterator.next()
            if (current.timeOfOccurrence > newEvent.timeOfOccurrence) {
                iterator.previous()
                iterator.add(newEvent)
                return
            }
        }
    }

    private fun isLastOfAllEvents(ev: Event) = ev.timeOfOccurrence >= maxTime

    fun getIterator() = list.iterator()

    fun getQueueSize() = list.size

    fun clearTheQueue() {
        list.clear()
    }
}