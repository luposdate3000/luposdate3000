package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

class ColumnIteratorMultiValue(val values: List<Value>) : ColumnIterator() {
    var index = 0

    init {
        next = {
            var res: Value?
            println("$index ${values.size}")
            if (index == values.size) {
                res = null
            } else {
                res = values[index++]
            }
/*return*/res
        }
    }
}
