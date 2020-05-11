package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.Value

open class RowIteratorFromColumn(val bundle: IteratorBundle) : RowIterator() {
    var iterators: Array<ColumnIterator>

    init {
        require(bundle.hasColumnMode())
        val keys = bundle.columns.keys.toList()
        columns = Array<String>(bundle.columns.size) { keys[it] }
        iterators = Array<ColumnIterator>(bundle.columns.size) { bundle.columns[columns[it]]!! }
        buf = IntArray(keys.size)
        next = {
            var res = 0
            for (columnIndex in 0 until columns.size) {
                var tmp = iterators[columnIndex].next()
                if (tmp == null) {
                    require(columnIndex == 0)
                    res = -1
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
