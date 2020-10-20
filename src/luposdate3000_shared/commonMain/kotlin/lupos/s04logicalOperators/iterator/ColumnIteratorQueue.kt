package lupos.s04logicalOperators.iterator
import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultSetDictionaryExt

abstract class ColumnIteratorQueue() : ColumnIterator() {
    @JvmField
    var tmp = ResultSetDictionaryExt.nullValue

    @JvmField
    val queue = mutableListOf<Int>()

    @JvmField
    var label = 1
    inline fun _close() {
        if (label != 0) {
            label = 0
            queue.clear()
        }
    }

internal    inline suspend fun next_helper(crossinline onEmptyQueue: suspend () -> Unit, crossinline onClose: suspend () -> Unit): Int {
        when (label) {
            1 -> {
                if (queue.size == 0) {
                    onEmptyQueue()
                    if (queue.size > 0) {
                        return queue.removeAt(0)
                    } else {
                        onClose()
                        return ResultSetDictionaryExt.nullValue
                    }
                } else {
                    return queue.removeAt(0)
                }
            }
            2 -> {
                if (queue.size == 0) {
                    onClose()
                    return ResultSetDictionaryExt.nullValue
                } else {
                    return queue.removeAt(0)
                }
            }
            else -> {
                return ResultSetDictionaryExt.nullValue
            }
        }
    }

    inline fun closeOnEmptyQueue() {
        if (label != 0) {
            label = 2
        }
    }
}
