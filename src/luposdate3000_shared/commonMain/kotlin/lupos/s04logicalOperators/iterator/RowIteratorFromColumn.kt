package lupos.s04logicalOperators.iterator

import kotlin.jvm.JvmField
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt

open class RowIteratorFromColumn(@JvmField val bundle: IteratorBundle) : RowIterator() {
    @JvmField
    var iterators: Array<ColumnIterator>

    init {
        SanityCheck.check { bundle.hasColumnMode() }
        val keys = bundle.columns.keys.toList()
        columns = Array<String>(bundle.columns.size) { keys[it] }
        iterators = Array<ColumnIterator>(bundle.columns.size) { bundle.columns[columns[it]]!! }
        buf = IntArray(keys.size)
        next = {
            var res = 0
            for (columnIndex in 0 until columns.size) {
                var tmp = iterators[columnIndex].next()
                if (tmp == ResultSetDictionaryExt.nullValue) {
                    SanityCheck.check({ columnIndex == 0 }, { "" + iterators[columnIndex] })
                    res = -1
                    close()
                    break
                } else {
                    buf[columnIndex] = tmp
                }
            }
            /*return*/ res
        }
        close = {
            for (columnIndex in 0 until columns.size) {
                iterators[columnIndex].close()
            }
            _close()
        }
    }
}
