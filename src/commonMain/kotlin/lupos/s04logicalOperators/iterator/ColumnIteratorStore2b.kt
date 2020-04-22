package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s05tripleStore.*

class ColumnIteratorStore2b(val values: MyListValue, start: Int) : ColumnIterator() {
    var counterPrimary = values[start]
    var index = start + 3
    var currentEnd = index + values[index - 1]

    init {
        next = {
            var res: Value?
            if (index == currentEnd) {
                if (counterPrimary > 1) {
                    counterPrimary--
                    index += 2
                    currentEnd = index + values[index - 1]
                    res = values[index++]
                } else {
                    res = null
                }
            } else {
                res = values[index++]
            }
/*return*/res
        }
    }
}
