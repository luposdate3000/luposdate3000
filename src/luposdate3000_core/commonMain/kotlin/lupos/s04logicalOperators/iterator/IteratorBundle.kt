package lupos.s04logicalOperators.iterator

import kotlin.jvm.JvmField
import lupos.s00misc.IteratorBundleColumnModeNotImplementedException
import lupos.s00misc.IteratorBundleRowModeNotImplementedException
import lupos.s00misc.SanityCheck

enum class IteratorBundleMode {
    COUNT,
    COLUMN,
    ROW
}

open class IteratorBundle {
    @JvmField
    var mode: IteratorBundleMode

    @JvmField
    var _columns: Map<String, ColumnIterator>?

    @JvmField
    var _rows: RowIterator?

    @JvmField
    var counter = 0
    inline fun hasColumnMode() = mode == IteratorBundleMode.COLUMN
    inline fun hasCountMode() = mode == IteratorBundleMode.COUNT
    inline fun hasRowMode() = mode == IteratorBundleMode.ROW

    constructor (columns: Map<String, ColumnIterator>) {
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
                SanityCheck.check { _columns!!.size > 0 }
                return _columns!!
            } else if (mode == IteratorBundleMode.ROW) {
                if (_columns == null) {
                    _columns = ColumnIteratorFromRow(_rows!!)
                }
                return _columns!!
            } else {
                throw IteratorBundleColumnModeNotImplementedException()
            }
        }
    var rows: RowIterator = RowIterator()
        get() {
            if (mode == IteratorBundleMode.ROW) {
                return _rows!!
            } else if (mode == IteratorBundleMode.COLUMN) {
                SanityCheck.check { _columns!!.size > 0 }
                if (_rows == null) {
                    _rows = RowIteratorFromColumn(this)
                }
                return _rows!!
            } else {
                throw IteratorBundleRowModeNotImplementedException()
            }
        }

    suspend open fun hasNext2(): Boolean {
        if (counter > 0) {
            counter--
            return true
        }
        return false
    }

    suspend open fun hasNext2Close() {
    }

    suspend inline fun count(): Int {
        SanityCheck.check { mode == IteratorBundleMode.COUNT }
        if (counter > 0) {
            return counter
        } else {
            var res = 0
            while (hasNext2()) {
                res++
            }
            hasNext2Close()
            counter = res
            return res
        }
    }
}
