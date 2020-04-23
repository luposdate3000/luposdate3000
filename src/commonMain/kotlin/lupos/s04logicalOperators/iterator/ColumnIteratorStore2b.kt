package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s05tripleStore.*

class ColumnIteratorStore2b(val values: MyListValue, start: Int) : ColumnIterator() {
    var counterSecondary: Int
    var counterTerniary: Int
    var index = start + 3

    init {
        counterSecondary = values[index - 3] - 1
        counterTerniary = values[index - 1] - 1
        next = {
//BenchmarkUtils.start(EBenchmark.STORE_NEXT2b)
            var res: Value? = values[index]
            index++
            if (counterTerniary == 0) {
                if (counterSecondary == 0) {
                    close()
                } else {
                    counterSecondary--
                    counterTerniary = values[index + 1] - 1
                    index += 2
                }
            } else {
                counterTerniary--
            }
//BenchmarkUtils.elapsedSeconds(EBenchmark.STORE_NEXT2b)
/*return*/res
        }
    }
}
