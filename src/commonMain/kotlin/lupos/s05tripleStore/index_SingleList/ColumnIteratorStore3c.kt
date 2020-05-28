package lupos.s05tripleStore.index_SingleList
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
class ColumnIteratorStore3c(val values: MyListValue) : ColumnIterator() {
    var counterPrimary: Int
    var counterSecondary: Int
    var counterTerniary: Int
    var valueA = 0
    var valueB = 0
    var valueC = 0
    val it = values.iterator()
    init {
Coverage.funStart(6641)
        if (values.size > 4) {
Coverage.ifStart(6642)
            counterPrimary = it.next() - 1
Coverage.statementStart(6643)
            valueA = it.next()
Coverage.statementStart(6644)
            counterSecondary = it.next() - 1
Coverage.statementStart(6645)
            valueB = it.next()
Coverage.statementStart(6646)
            counterTerniary = it.next() - 1
Coverage.statementStart(6647)
            next = {
Coverage.statementStart(6648)
                valueC = it.next()
Coverage.statementStart(6649)
                var res = valueC
Coverage.statementStart(6650)
                if (counterTerniary == 0) {
Coverage.ifStart(6651)
                    if (counterSecondary == 0) {
Coverage.ifStart(6652)
                        if (counterPrimary == 0) {
Coverage.ifStart(6653)
                            close()
Coverage.statementStart(6654)
                        } else {
Coverage.ifStart(6655)
                            counterPrimary--
Coverage.statementStart(6656)
                            valueA = it.next()
Coverage.statementStart(6657)
                            counterSecondary = it.next() - 1
Coverage.statementStart(6658)
                            valueB = it.next()
Coverage.statementStart(6659)
                            counterTerniary = it.next() - 1
Coverage.statementStart(6660)
                        }
Coverage.statementStart(6661)
                    } else {
Coverage.ifStart(6662)
                        counterSecondary--
Coverage.statementStart(6663)
                        valueB = it.next()
Coverage.statementStart(6664)
                        counterTerniary = it.next() - 1
Coverage.statementStart(6665)
                    }
Coverage.statementStart(6666)
                } else {
Coverage.ifStart(6667)
                    counterTerniary--
Coverage.statementStart(6668)
                }
Coverage.statementStart(6669)
                /*return*/res
            }
Coverage.statementStart(6670)
        } else {
Coverage.ifStart(6671)
            counterPrimary = 0
Coverage.statementStart(6672)
            counterSecondary = 0
Coverage.statementStart(6673)
            counterTerniary = 0
Coverage.statementStart(6674)
        }
Coverage.statementStart(6675)
    }
}
