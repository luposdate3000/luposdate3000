package lupos.s05tripleStore.index_SingleList

import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator

class ColumnIteratorStore3a(val values: MyListValue) : ColumnIterator() {
    var counterPrimary: Int
    var counterSecondary: Int
    var counterTerniary: Int
    var valueA = 0
    var valueB = 0
    var valueC = 0
    val it = values.iterator()

    init {
        if (values.size > 4) {
            counterPrimary = it.next() - 1
            valueA = it.next()
            counterSecondary = it.next() - 1
            valueB = it.next()
            counterTerniary = it.next() - 1
            next = {
                valueC = it.next()
                var res = valueA
                if (counterTerniary == 0) {
                    if (counterSecondary == 0) {
                        if (counterPrimary == 0) {
                            close()
                        } else {
                            counterPrimary--
                            valueA = it.next()
                            counterSecondary = it.next() - 1
                            valueB = it.next()
                            counterTerniary = it.next() - 1
                        }
                    } else {
                        counterSecondary--
                        valueB = it.next()
                        counterTerniary = it.next() - 1
                    }
                } else {
                    counterTerniary--
                }
                /*return*/res
            }
        } else {
            counterPrimary = 0
            counterSecondary = 0
            counterTerniary = 0
        }
    }
}
