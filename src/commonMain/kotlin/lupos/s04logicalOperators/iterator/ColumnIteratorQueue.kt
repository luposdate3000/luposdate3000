package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value

class ColumnIteratorQueue() : ColumnIterator() {
    var tmp: Value? = null
    val queue = MyListValue()
    var onEmptyQueue: () -> Unit = ::_onEmptyQueue
    fun _onEmptyQueue() {
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
