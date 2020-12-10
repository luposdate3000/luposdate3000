package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.ResultSetDictionaryExt
internal object ColumnIteratorQueueExt {
    inline fun _close(it: ColumnIteratorQueue) {
        if (it.label != 0) {
            it.label = 0
            it.queue.clear()
        }
    }
    /*suspend*/ inline fun nextHelper(it: ColumnIteratorQueue, crossinline onEmptyQueue: /*suspend*/ () -> Unit, crossinline onClose: /*suspend*/ () -> Unit): Int {
        when (it.label) {
            1 -> {
                return if (it.queue.size == 0) {
                    onEmptyQueue()
                    if (it.queue.size > 0) {
                        it.queue.removeAt(0)
                    } else {
                        onClose()
                        ResultSetDictionaryExt.nullValue
                    }
                } else {
                    it.queue.removeAt(0)
                }
            }
            2 -> {
                return if (it.queue.size == 0) {
                    onClose()
                    ResultSetDictionaryExt.nullValue
                } else {
                    it.queue.removeAt(0)
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
