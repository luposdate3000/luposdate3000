package lupos.s05tripleStore.index_SingleList
import lupos.s00misc.Coverage
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
Coverage.funStart(6571)
        if (values.size > 4) {
Coverage.ifStart(6572)
            counterPrimary = it.next() - 1
Coverage.statementStart(6573)
            valueA = it.next()
Coverage.statementStart(6574)
            counterSecondary = it.next() - 1
Coverage.statementStart(6575)
            valueB = it.next()
Coverage.statementStart(6576)
            counterTerniary = it.next() - 1
Coverage.statementStart(6577)
            next = {
Coverage.statementStart(6578)
                valueC = it.next()
Coverage.statementStart(6579)
                var res = valueA
Coverage.statementStart(6580)
                if (counterTerniary == 0) {
Coverage.ifStart(6581)
                    if (counterSecondary == 0) {
Coverage.ifStart(6582)
                        if (counterPrimary == 0) {
Coverage.ifStart(6583)
                            close()
Coverage.statementStart(6584)
                        } else {
Coverage.ifStart(6585)
                            counterPrimary--
Coverage.statementStart(6586)
                            valueA = it.next()
Coverage.statementStart(6587)
                            counterSecondary = it.next() - 1
Coverage.statementStart(6588)
                            valueB = it.next()
Coverage.statementStart(6589)
                            counterTerniary = it.next() - 1
Coverage.statementStart(6590)
                        }
Coverage.statementStart(6591)
                    } else {
Coverage.ifStart(6592)
                        counterSecondary--
Coverage.statementStart(6593)
                        valueB = it.next()
Coverage.statementStart(6594)
                        counterTerniary = it.next() - 1
Coverage.statementStart(6595)
                    }
Coverage.statementStart(6596)
                } else {
Coverage.ifStart(6597)
                    counterTerniary--
Coverage.statementStart(6598)
                }
Coverage.statementStart(6599)
                /*return*/res
            }
Coverage.statementStart(6600)
        } else {
Coverage.ifStart(6601)
            counterPrimary = 0
Coverage.statementStart(6602)
            counterSecondary = 0
Coverage.statementStart(6603)
            counterTerniary = 0
Coverage.statementStart(6604)
        }
Coverage.statementStart(6605)
    }
}
