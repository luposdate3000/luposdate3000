package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

class ColumnIteratorChildIterator() : ColumnIterator() {
    val childs = mutableListOf(ColumnIterator())

    init {
        next = {
            require(childs.size > 0)
            var res = childs[0].next()
            if (res == null) {
                childs.removeAt(0)
                if (childs.size == 0) {
                    onNoMoreElements()
                }
                if (childs.size == 0) {
                    close()
                }
                res = next()
            }
            /*return*/ res
        }
        close = {
            for (child in childs) {
                child.close()
            }
            _close()
        }
    }
}
