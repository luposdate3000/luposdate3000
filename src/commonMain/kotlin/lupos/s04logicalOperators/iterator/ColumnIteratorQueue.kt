package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value



class ColumnIteratorQueue() : ColumnIterator() {
    var tmp: Value? = null
    val queue = MyListValue()
    var onEmptyQueue: suspend () -> Unit = ::_onEmptyQueue
    suspend fun _onEmptyQueue() {
    }

    init {
        next = {
            if (queue.size == 0) {
                onEmptyQueue()
            }
            var res: Value? = null
            if (queue.size > 0) {
                res = queue.removeAt(0)
            }
            /*return*/res
        }
        close = {
            _close()
        }
    }
}
