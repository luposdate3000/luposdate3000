package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*
import lupos.s05tripleStore.*

class ColumnIteratorMultiValue(val values: MyListValue) : ColumnIterator() {
    var index = 0
    val end = values.size

    init {
        next = {
            var res: Value?
            if (index == end) {
                res = null
            } else {
                res = values[index++]
            }
/*return*/res
        }
    }
}
