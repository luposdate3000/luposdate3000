package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.FuncColumnIteratorNext

class ColumnIteratorRepeatValue(val count: Int, val value: Value) : ColumnIterator() {
    var index = 0

    init {
        next = object : FuncColumnIteratorNext("ColumnIteratorRepeatValue.next") {
            override fun invoke(): Value? {
                var res: Value?
                if (index == count) {
                    res = null
                } else {
                    index++
                    res = value
                }
                return res
            }
        }
    }
}
