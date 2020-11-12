package lupos.s04logicalOperators.iterator

import kotlin.jvm.JvmField
import lupos.s00misc.IteratorBundleColumnModeNotImplementedException
import lupos.s00misc.IteratorBundleRowModeNotImplementedException
import lupos.s00misc.SanityCheck

open class IteratorBundle {
    @JvmField
    internal var mode: IteratorBundleMode

    @JvmField
    var _columns: Map<String, ColumnIterator>?

    @JvmField
    var _rows: RowIterator?

    @JvmField
    var counter = 0
    fun hasColumnMode() = mode == IteratorBundleMode.COLUMN
    fun hasCountMode() = mode == IteratorBundleMode.COUNT
    fun hasRowMode() = mode == IteratorBundleMode.ROW

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
            return if (mode == IteratorBundleMode.COLUMN) {
                SanityCheck.check { _columns!!.isNotEmpty() }
                _columns!!
            } else if (mode == IteratorBundleMode.ROW) {
                if (_columns == null) {
                    _columns = ColumnIteratorFromRow(_rows!!)
                }
                _columns!!
            } else {
                throw IteratorBundleColumnModeNotImplementedException()
            }
        }
    var rows: RowIterator = RowIterator()
        get() {
            return if (mode == IteratorBundleMode.ROW) {
                _rows!!
            } else if (mode == IteratorBundleMode.COLUMN) {
                SanityCheck.check { _columns!!.isNotEmpty() }
                if (_rows == null) {
                    _rows = RowIteratorFromColumn(this)
                }
                _rows!!
            } else {
                throw IteratorBundleRowModeNotImplementedException()
            }
        }

    open /*suspend*/ fun hasNext2(): Boolean {
        if (counter > 0) {
            counter--
            return true
        }
        return false
    }

    open /*suspend*/ fun hasNext2Close() {
    }

    /*suspend*/ fun count(): Int {
        SanityCheck.check { mode == IteratorBundleMode.COUNT }
        return if (counter > 0) {
            counter
        } else {
            var res = 0
            while (hasNext2()) {
                res++
            }
            hasNext2Close()
            counter = res
            res
        }
    }
}
