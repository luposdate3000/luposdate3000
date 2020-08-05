package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIteratorNext

class ColumnIteratorMultiIterator(val childs: List<ColumnIterator>) : ColumnIterator() {
    var index = 0

    init {
        next = object : ColumnIteratorNext("ColumnIteratorMultiIterator.next") {
            override fun invoke(): Value? {
                var res = childs[index].next()
                while (res == null && ++index < childs.size) {
                    res = childs[index].next()
                }
                return res
            }
        }
        close = {
            for (c in childs) {
                c.close()
            }
            _close()
        }
    }
}
