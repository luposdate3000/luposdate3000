package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s05tripleStore.*

class ColumnIteratorStore3a(val values: MyListValue) : ColumnIterator() {
    var counterPrimary = values[0]
    var index = 1
    var counterSecondary = values[index + 1]
    var counterTerniary = values[index + 3]
    var value = values[index]

    init {
        index += 4
        next = {
            var res: Value?
            if (counterTerniary > 0) {
                counterTerniary--
                index++
                res = value
            } else {
                if (counterSecondary > 0) {
                    counterSecondary--
                    counterTerniary = values[index + 1] - 1
                    index += 3
                    res = value
                } else {
                    if (counterPrimary > 0) {
                        counterPrimary--
                        value = values[index]
                        counterSecondary = values[index + 1]
                        counterTerniary = values[index + 3] - 1
                        index += 5
                        res = value
                    } else {
                        res = null
                    }
                }
            }
/*return*/res
        }
    }
}
