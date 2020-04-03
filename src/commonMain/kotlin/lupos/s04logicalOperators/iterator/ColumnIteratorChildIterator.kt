package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

class ColumnIteratorChildIterator() : ColumnIterator() {
    val childs = mutableListOf(ColumnIterator())
    var onNoMoreElements: suspend () -> Unit = ::_onNoMoreElements

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
            /*return*/res
        }
        close = {
            onNoMoreElements = ::_onNoMoreElements
            for (child in childs) {
                child.close()
            }
            _close()
        }
    }

    suspend fun _onNoMoreElements() {
        close()
    }
}
