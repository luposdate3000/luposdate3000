package lupos.s05tripleStore.index_SingleList

import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator

class ColumnIteratorStore1(@JvmField val values: MyListValue, start: Int) : ColumnIterator() {
    @JvmField
    var index = start + 1

    @JvmField
    val end = start + values[start] + 1
    override suspend fun close() {
    }

    override suspend fun next(): Value {
        if (index == end) {
            return ResultSetDictionary.nullValue
        } else {
            return values[index++]
        }
    }
}
