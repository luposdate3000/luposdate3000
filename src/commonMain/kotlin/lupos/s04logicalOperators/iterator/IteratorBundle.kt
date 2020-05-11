package lupos.s04logicalOperators.iterator

import kotlinx.coroutines.runBlocking

enum class IteratorBundleMode {
    COUNT,
    COLUMN,
    ROW
}

class IteratorBundle {
    var mode: IteratorBundleMode
    var _columns: Map<String, ColumnIterator>?
    var _rows: RowIterator?
    fun hasColumnMode() = mode == IteratorBundleMode.COLUMN
    fun hasCountMode() = mode == IteratorBundleMode.COUNT
    fun hasRowMode() = mode == IteratorBundleMode.ROW

    constructor (columns: Map<String, ColumnIterator>) {
        require(columns.size > 0)
        _rows = null
        _columns = columns
        mode = IteratorBundleMode.COLUMN
    }

    constructor(count: Int) {
        _rows = null
        _columns = null
        counter = count
        mode = IteratorBundleMode.COUNT
    }

    constructor(rows: RowIterator) {
        _rows = rows
        _columns = null
        mode = IteratorBundleMode.ROW
    }

    var columns: Map<String, ColumnIterator> = mapOf()
        get() {
            if (mode == IteratorBundleMode.COLUMN) {
                return _columns!!
            } else if (mode == IteratorBundleMode.ROW) {
                if (_columns == null) {
                    _columns = ColumnIteratorFromRow(_rows!!)
                }
                return _columns!!
            } else {
                throw Exception("not implemented")
            }
        }
    var rows: RowIterator = RowIterator()
        get() {
            if (mode == IteratorBundleMode.ROW) {
                return _rows!!
            } else if (mode == IteratorBundleMode.COLUMN) {
                if (_rows == null) {
                    _rows = RowIteratorFromColumn(this)
                }
                return _rows!!
            } else {
                throw Exception("not implemented")
            }
        }

    suspend fun _hasNext(): Boolean {
        if (counter > 0) {
            counter--
            return true
        }
        return false
    }

    var hasNext: suspend () -> Boolean = ::_hasNext
    var counter = 0
    var count: Int = 0
        get() {
            require(mode == IteratorBundleMode.COUNT)
            if (counter > 0) {
                return counter
            } else {
                var res = 0
                runBlocking {
                    while (hasNext()) {
                        res++
                    }
                }
                counter = res
                return res
            }
        }
}
