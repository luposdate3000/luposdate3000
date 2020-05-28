package lupos.s05tripleStore.index_SingleList
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
class ColumnIteratorStore3b(val values: MyListValue) : ColumnIterator() {
    var counterPrimary: Int
    var counterSecondary: Int
    var counterTerniary: Int
    var valueA = 0
    var valueB = 0
    var valueC = 0
    val it = values.iterator()
    init {
Coverage.funStart(6606)
        if (values.size > 4) {
Coverage.ifStart(6607)
            counterPrimary = it.next() - 1
Coverage.statementStart(6608)
            valueA = it.next()
Coverage.statementStart(6609)
            counterSecondary = it.next() - 1
Coverage.statementStart(6610)
            valueB = it.next()
Coverage.statementStart(6611)
            counterTerniary = it.next() - 1
Coverage.statementStart(6612)
            next = {
Coverage.statementStart(6613)
                valueC = it.next()
Coverage.statementStart(6614)
                var res = valueB
Coverage.statementStart(6615)
                if (counterTerniary == 0) {
Coverage.ifStart(6616)
                    if (counterSecondary == 0) {
Coverage.ifStart(6617)
                        if (counterPrimary == 0) {
Coverage.ifStart(6618)
                            close()
Coverage.statementStart(6619)
                        } else {
Coverage.ifStart(6620)
                            counterPrimary--
Coverage.statementStart(6621)
                            valueA = it.next()
Coverage.statementStart(6622)
                            counterSecondary = it.next() - 1
Coverage.statementStart(6623)
                            valueB = it.next()
Coverage.statementStart(6624)
                            counterTerniary = it.next() - 1
Coverage.statementStart(6625)
                        }
Coverage.statementStart(6626)
                    } else {
Coverage.ifStart(6627)
                        counterSecondary--
Coverage.statementStart(6628)
                        valueB = it.next()
Coverage.statementStart(6629)
                        counterTerniary = it.next() - 1
Coverage.statementStart(6630)
                    }
Coverage.statementStart(6631)
                } else {
Coverage.ifStart(6632)
                    counterTerniary--
Coverage.statementStart(6633)
                }
Coverage.statementStart(6634)
                /*return*/res
            }
Coverage.statementStart(6635)
        } else {
Coverage.ifStart(6636)
            counterPrimary = 0
Coverage.statementStart(6637)
            counterSecondary = 0
Coverage.statementStart(6638)
            counterTerniary = 0
Coverage.statementStart(6639)
        }
Coverage.statementStart(6640)
    }
}
