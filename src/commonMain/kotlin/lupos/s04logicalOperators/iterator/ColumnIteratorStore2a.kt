package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s05tripleStore.*

class ColumnIteratorStore2a(val values: MyListValue, start: Int) : ColumnIterator() {
    var counterPrimary = values[start]
    var index = start + 1
    var counterSecondary = values[index + 1]

    init {
        next = {
            var res: Value?
            if (counterSecondary > 0) {
                counterSecondary--
                res = values[index]
            } else {
                if (counterPrimary > 0) {
                    counterPrimary--
                    index += values[index + 1] + 1
                    counterSecondary = values[index + 1]
                    res = values[index]
                } else {
                    res = null
                }
            }
/*return*/res
        }
    }
}
