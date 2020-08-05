package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.FuncColumnIteratorClose
import lupos.s04logicalOperators.iterator.FuncColumnIteratorNext

class ColumnIteratorReduced(val child: ColumnIterator) : ColumnIterator() {
    var last: Value? = null

    init {
        next = object : FuncColumnIteratorNext("ColumnIteratorReduced.next") {
            override fun invoke(): Value? {
                var res = child.next()
                while (res != null && last == res) {
                    res = child.next()
                }
                last = res
                return res
            }
        }
        close = object : FuncColumnIteratorClose("ColumnIteratorReduced.close") {
            override fun invoke() {
                child.close()
                _close()
            }
        }
    }
}
