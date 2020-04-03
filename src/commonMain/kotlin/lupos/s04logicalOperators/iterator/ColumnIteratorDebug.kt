package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

class ColumnIteratorDebug(val uuid: Long, val child: ColumnIterator) : ColumnIterator() {
    companion object {
        val counters = mutableMapOf<Long, Int>()
        fun debug() {
            println(counters)
            counters.clear()
        }
    }

    init {
        counters[uuid] = 0
        next = {
            val res = child.next()
            if (res != null) {
                counters[uuid] = counters[uuid]!! + 1
            }
            /*return*/res
        }
        close = {
            child.close()
            _close()
        }
    }
}
