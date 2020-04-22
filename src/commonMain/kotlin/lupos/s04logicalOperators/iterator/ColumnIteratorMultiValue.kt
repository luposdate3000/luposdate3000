package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s05tripleStore.*

class ColumnIteratorMultiValue(val values: MyListValue) : ColumnIterator() {
    var index = 0

    init {
        next = {
            var res: Value?
            if (index == values.size) {
                res = null
            } else {
                res = values[index++]
            }
/*return*/res
        }
    }
}
