package lupos.s05tripleStore.index_SingleList
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
class ColumnIteratorStore2b(val values: MyListValue, start: Int) : ColumnIterator() {
    var counterSecondary: Int
    var counterTerniary: Int
    var index = start + 3
    init {
Coverage.funStart(6551)
        counterSecondary = values[index - 3] - 1
Coverage.statementStart(6552)
        counterTerniary = values[index - 1] - 1
Coverage.statementStart(6553)
        next = {
Coverage.statementStart(6554)
            //BenchmarkUtils.start(EBenchmark.STORE_NEXT2b)
Coverage.statementStart(6555)
            var res: Value? = values[index]
Coverage.statementStart(6556)
            index++
Coverage.statementStart(6557)
            if (counterTerniary == 0) {
Coverage.ifStart(6558)
                if (counterSecondary == 0) {
Coverage.ifStart(6559)
                    close()
Coverage.statementStart(6560)
                } else {
Coverage.ifStart(6561)
                    counterSecondary--
Coverage.statementStart(6562)
                    counterTerniary = values[index + 1] - 1
Coverage.statementStart(6563)
                    index += 2
Coverage.statementStart(6564)
                }
Coverage.statementStart(6565)
            } else {
Coverage.ifStart(6566)
                counterTerniary--
Coverage.statementStart(6567)
            }
Coverage.statementStart(6568)
//BenchmarkUtils.elapsedSeconds(EBenchmark.STORE_NEXT2b)
Coverage.statementStart(6569)
/*return*/res
        }
Coverage.statementStart(6570)
    }
}
