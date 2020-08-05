package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.FuncColumnIteratorNext

class ColumnIteratorMultiIterator(val childs: List<ColumnIterator>) : ColumnIterator() {
    var index = 0

    init {
        next = object : FuncColumnIteratorNext("ColumnIteratorMultiIterator.next") {
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
