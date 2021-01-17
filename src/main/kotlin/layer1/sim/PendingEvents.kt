package layer1.sim

import java.util.*


class PendingEvents {

    private val sortedSet: SortedSet<Event> = TreeSet()

    private var serialNumber: Long = 0

    fun addEvent(newEvent: Event) {
        serialNumber++
        newEvent.serialNumber = serialNumber
        sortedSet.add(newEvent)
    }

    /**
     * Add a new event to the head of the queue.
     *
     * @param newEvent The event to be put in the queue.
     * sieht falsch aus, da: Serialnumber nicht mitgezählt wird und außerdem sowieso die time bei der SOrtierung
     * mehr zählt. wofür brauchen wir die serialnumber überhaupt?? bei gleichheit ist eh die reihenfolge egal?
     */
//    fun addEventFirst(newEvent: Event) {
//        newEvent.setSerial(0)
//        sortedSet.add(newEvent)
//    }

    fun getIterator() = sortedSet.iterator()

    fun getSize() = sortedSet.size

    fun remove(event: Event) = sortedSet.remove(event)

    fun removeAll(events: Collection<Event>?) = sortedSet.removeAll(events!!)

    fun clear() = sortedSet.clear()
}