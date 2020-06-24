package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.Value

class ColumnIteratorReduced(val child: ColumnIterator) : ColumnIterator() {
    var last: Value? = null

    init {
        next = {
            var res = child.next()
            while (res != null && last == res) {
                res = child.next()
            }
            last = res
            /*return*/res
        }
        close = {
            child.close()
            _close()
        }
    }
}
