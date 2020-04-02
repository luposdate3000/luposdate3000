package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

class ColumnIteratorDistinct(val child: ColumnIterator) : ColumnIterator() {
    //TODO only for_ single column .... replace with sort-distinct
    var last: Value? = null

    init {
        next = {
            var res = child.next()
            while (last != null && last == res) {
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
