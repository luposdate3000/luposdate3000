package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s05tripleStore.*

class ColumnIteratorStore3c(val values: MyListValue) : ColumnIterator() {
    var counterPrimary = values[0]
    var index = 1
    var counterSecondary = values[index + 1]
    var counterTerniary = values[index + 3]

    init {
        index += 4
        next = {
            var res: Value?
            if (counterTerniary > 0) {
                counterTerniary--
                res = values[index++]
            } else {
                if (counterSecondary > 0) {
                    counterSecondary--
                    counterTerniary = values[index + 1] - 1
                    index += 2
                    res = values[index++]
                } else {
                    if (counterPrimary > 0) {
                        counterPrimary--
                        counterSecondary = values[index + 1]
                        counterTerniary = values[index + 3] - 1
                        index += 4
                        res = values[index++]
                    } else {
                        res = null
                    }
                }
            }
/*return*/res
        }
    }
}
