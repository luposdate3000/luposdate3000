package lupos.s04logicalOperators.iterator
import lupos.s00misc.IteratorBundleColumnModeNotImplementedException
import lupos.s00misc.IteratorBundleRowModeNotImplementedException
import lupos.s00misc.SanityCheck
import kotlin.jvm.JvmField
open class IteratorBundle {
    @JvmField
    internal var mode: IteratorBundleMode
    @JvmField
    var _columns: Map<String, ColumnIterator>?
    @JvmField
    var _rows: RowIterator?
    @JvmField
    var counter: Int = 0
    fun hasColumnMode(): Boolean = mode == IteratorBundleMode.COLUMN
    fun hasCountMode(): Boolean = mode == IteratorBundleMode.COUNT
    fun hasRowMode(): Boolean = mode == IteratorBundleMode.ROW
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
    val columns: Map<String, ColumnIterator>
        get() {
            return when (mode) {
                IteratorBundleMode.COLUMN -> {
                    SanityCheck.check { _columns!!.isNotEmpty() }
                    _columns!!
                }
                IteratorBundleMode.ROW -> {
                    if (_columns == null) {
                        _columns = ColumnIteratorFromRow(_rows!!)
                    }
                    _columns!!
                }
                else -> {
                    throw IteratorBundleColumnModeNotImplementedException()
                }
            }
        }
    val rows: RowIterator
        get() {
            return when (mode) {
                IteratorBundleMode.ROW -> {
                    _rows!!
                }
                IteratorBundleMode.COLUMN -> {
                    SanityCheck.check { _columns!!.isNotEmpty() }
                    if (_rows == null) {
                        _rows = RowIteratorFromColumn(this)
                    }
                    _rows!!
                }
                else -> {
                    throw IteratorBundleRowModeNotImplementedException()
                }
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
