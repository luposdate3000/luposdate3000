package lupos.s05tripleStore.index_SingleList
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
class ColumnIteratorStore1(val values: MyListValue, start: Int) : ColumnIterator() {
    var index = start + 1
    val end = start + values[start] + 1
    init {
Coverage.funStart(6519)
        next = {
Coverage.statementStart(6520)
            //BenchmarkUtils.start(EBenchmark.STORE_NEXT1)
Coverage.statementStart(6521)
            var res: Value?
Coverage.statementStart(6522)
            if (index == end) {
Coverage.ifStart(6523)
                res = null
Coverage.statementStart(6524)
            } else {
Coverage.ifStart(6525)
                res = values[index++]
Coverage.statementStart(6526)
            }
Coverage.statementStart(6527)
//BenchmarkUtils.elapsedSeconds(EBenchmark.STORE_NEXT1)
Coverage.statementStart(6528)
/*return*/res
        }
Coverage.statementStart(6529)
    }
}
