package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value

class ColumnIteratorQueueEmpty() : ColumnIteratorQueue() {
    override fun close() {
        _close()
    }

    override fun next(): Value? {
        return next_helper {}
    }
}
