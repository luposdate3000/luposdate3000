package lupos.s04logicalOperators.iterator

import kotlinx.coroutines.runBlocking

class ColumnIteratorRow(val columns: Map<String, ColumnIterator>) {
    suspend fun _hasNext(): Boolean {
        if (counter > 0) {
            counter--
            return true
        }
        return false
    }

    var hasNext: suspend () -> Boolean = ::_hasNext
    var counter = 0
    var count: Int
        get() {
            if (counter > 0) {
                return counter
            } else {
                var res = 0
                runBlocking {
                    while (hasNext()) {
                        res++
                    }
                }
                counter = res
                return res
            }
        }
        set(value) {
            counter = value
        }
}
