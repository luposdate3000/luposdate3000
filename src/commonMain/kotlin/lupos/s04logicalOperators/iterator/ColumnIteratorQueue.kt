package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value

abstract class ColumnIteratorQueue() : ColumnIterator() {
    @JvmField
    var tmp: Value = ResultSetDictionary.nullValue

    @JvmField
    val queue = MyListValue()

    @JvmField
    var label = 1
    inline fun _close() {
        label = 0
    }

    inline suspend fun next_helper(crossinline onEmptyQueue: suspend () -> Unit): Value {
        when (label) {
            1 -> {
                if (queue.size == 0) {
                    onEmptyQueue()
                    if (queue.size > 0) {
                        return queue.removeAt(0)
                    } else {
                        close()
                        return ResultSetDictionary.nullValue
                    }
                } else {
                    return queue.removeAt(0)
                }
            }
            2 -> {
                if (queue.size == 0) {
                    close()
                    return ResultSetDictionary.nullValue
                } else {
                    return queue.removeAt(0)
                }
            }
            else -> {
                return ResultSetDictionary.nullValue
            }
        }
    }

    inline fun closeOnEmptyQueue() {
        if (label != 0) {
            label = 2
        }
    }
}
