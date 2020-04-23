package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s05tripleStore.*

class ColumnIteratorStore1(val values: MyListValue, start: Int) : ColumnIterator() {
    var index = start + 1
    val end = start + values[start] + 1

    init {
        next = {
BenchmarkUtils.start(EBenchmark.STORE_NEXT1)
            var res: Value?
            if (index == end) {
                res = null
            } else {
                res = values[index++]
            }
BenchmarkUtils.elapsedSeconds(EBenchmark.STORE_NEXT1)
/*return*/res
        }
    }
}
