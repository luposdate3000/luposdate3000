package lupos.s05tripleStore.index_SingleList
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
class ColumnIteratorStore2a(val values: MyListValue, start: Int) : ColumnIterator() {
    var counterSecondary: Int
    var counterTerniary: Int
    var index = start + 3
    var value = values[index - 2]
    init {
Coverage.funStart(6530)
        counterSecondary = values[index - 3] - 1
Coverage.statementStart(6531)
        counterTerniary = values[index - 1] - 1
Coverage.statementStart(6532)
        next = {
Coverage.statementStart(6533)
            //BenchmarkUtils.start(EBenchmark.STORE_NEXT2a)
Coverage.statementStart(6534)
            var res: Value? = value
Coverage.statementStart(6535)
            index++
Coverage.statementStart(6536)
            if (counterTerniary == 0) {
Coverage.ifStart(6537)
                if (counterSecondary == 0) {
Coverage.ifStart(6538)
                    close()
Coverage.statementStart(6539)
                } else {
Coverage.ifStart(6540)
                    counterSecondary--
Coverage.statementStart(6541)
                    value = values[index]
Coverage.statementStart(6542)
                    counterTerniary = values[index + 1] - 1
Coverage.statementStart(6543)
                    index += 2
Coverage.statementStart(6544)
                }
Coverage.statementStart(6545)
            } else {
Coverage.ifStart(6546)
                counterTerniary--
Coverage.statementStart(6547)
            }
Coverage.statementStart(6548)
//BenchmarkUtils.elapsedSeconds(EBenchmark.STORE_NEXT2a)
Coverage.statementStart(6549)
/*return*/res
        }
Coverage.statementStart(6550)
    }
}
