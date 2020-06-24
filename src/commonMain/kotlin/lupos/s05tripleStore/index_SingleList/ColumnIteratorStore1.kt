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
        next = {
            //BenchmarkUtils.start(EBenchmark.STORE_NEXT1)
            var res: Value?
            if (index == end) {
                res = null
            } else {
                res = values[index++]
            }
//BenchmarkUtils.elapsedSeconds(EBenchmark.STORE_NEXT1)
/*return*/res
        }
    }
}
