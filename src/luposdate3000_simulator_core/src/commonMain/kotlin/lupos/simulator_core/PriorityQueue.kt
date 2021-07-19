package lupos.simulator_core

public class PriorityQueue<E>(private val comparator: Comparator<E>) {

    private val queue = mutableListOf<E>()

    public fun enqueue(newElement: E) {
        var insertionIndex = queue.binarySearch(newElement, comparator)
        if (insertionIndex < 0) {
            insertionIndex = insertionIndex.inv()
        }
        queue.add(insertionIndex, newElement)
    }

    public fun dequeue(): E =
        queue.removeAt(0)

    public fun peek(): E =
        queue[0]

    public fun hasNext(): Boolean = queue.isNotEmpty()
}
