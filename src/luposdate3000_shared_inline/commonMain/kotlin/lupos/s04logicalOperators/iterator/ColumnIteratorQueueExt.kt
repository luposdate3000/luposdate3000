package  lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.ResultSetDictionaryExt

internal object ColumnIteratorQueueExt {
    inline fun _close(it: ColumnIteratorQueue) {
        if (it.label != 0) {
            it.label = 0
            it.queue.clear()
        }
    }

    inline suspend fun next_helper(it: ColumnIteratorQueue, crossinline onEmptyQueue: suspend () -> Unit, crossinline onClose: suspend () -> Unit): Int {
        when (it.label) {
            1 -> {
                if (it.queue.size == 0) {
                    onEmptyQueue()
                    if (it.queue.size > 0) {
                        return it.queue.removeAt(0)
                    } else {
                        onClose()
                        return ResultSetDictionaryExt.nullValue
                    }
                } else {
                    return it.queue.removeAt(0)
                }
            }
            2 -> {
                if (it.queue.size == 0) {
                    onClose()
                    return ResultSetDictionaryExt.nullValue
                } else {
                    return it.queue.removeAt(0)
                }
            }
            else -> {
                return ResultSetDictionaryExt.nullValue
            }
        }
    }

    inline fun closeOnEmptyQueue(it: ColumnIteratorQueue) {
        if (it.label != 0) {
            it.label = 2
        }
    }
}
