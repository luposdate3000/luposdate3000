package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value

abstract class ColumnIteratorQueue() : ColumnIterator() {
    @JvmField
    var tmp: Value? = null

    @JvmField
    val queue = MyListValue()

    @JvmField
    var label = 1
    inline fun _close() {
        label = 0
    }

    inline fun next_helper(crossinline onEmptyQueue: () -> Unit): Value? {
        when (label) {
            1 -> {
                if (queue.size == 0) {
                    onEmptyQueue()
                    if (queue.size > 0) {
                        return queue.removeAt(0)
                    } else {
                        close()
                        return null
                    }
                } else {
                    return queue.removeAt(0)
                }
            }
            2 -> {
                if (queue.size == 0) {
                    close()
                    return null
                } else {
                    return queue.removeAt(0)
                }
            }
            else -> {
                return null
            }
        }
    }

    inline fun closeOnEmptyQueue() {
        label = 2
    }
}
