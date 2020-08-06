package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary

open class RowIteratorFromColumn(val bundle: IteratorBundle) : RowIterator() {
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
                if (tmp == ResultSetDictionary.nullValue) {
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
