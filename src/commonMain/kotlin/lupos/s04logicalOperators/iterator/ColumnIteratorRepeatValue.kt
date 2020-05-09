package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.Value

class ColumnIteratorRepeatValue(val count: Int, val value: Value) : ColumnIterator() {
    var index = 0

    init {
        next = {
            var res: Value?
            if (index == count) {
                res = null
            } else {
                index++
                res = value
            }
/*return*/res
        }
    }
}
