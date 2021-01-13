package lupos.s04logicalOperators.iterator
import lupos.s00misc.IteratorBundleColumnModeNotImplementedException
import lupos.s00misc.IteratorBundleRowModeNotImplementedException
import lupos.s00misc.SanityCheck
import kotlin.jvm.JvmField
public open class IteratorBundle {
    @JvmField
    internal var mode: IteratorBundleMode
    @JvmField
    public var _columns: Map<String, ColumnIterator>?
    @JvmField
    public var _rows: RowIterator?
    @JvmField
    public var counter: Int = 0
    public fun hasColumnMode(): Boolean = mode == IteratorBundleModeExt.COLUMN
    public fun hasCountMode(): Boolean = mode == IteratorBundleModeExt.COUNT
    public fun hasRowMode(): Boolean = mode == IteratorBundleModeExt.ROW
    public constructor (columns: Map<String, ColumnIterator>) {
        _rows = null
        _columns = columns
        mode = IteratorBundleModeExt.COLUMN
    }
    public constructor(count: Int) {
        _rows = null
        _columns = null
        counter = count
        mode = IteratorBundleModeExt.COUNT
    }
    public constructor(rows: RowIterator) {
        _rows = rows
        _columns = null
        mode = IteratorBundleModeExt.ROW
    }
    public val columns: Map<String, ColumnIterator>
        public get() {
            return when (mode) {
                IteratorBundleModeExt.COLUMN -> {
                    SanityCheck.check { _columns!!.isNotEmpty() }
                    _columns!!
                }
                IteratorBundleModeExt.ROW -> {
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
    public val rows: RowIterator
        public get() {
            return when (mode) {
                IteratorBundleModeExt.ROW -> {
                    _rows!!
                }
                IteratorBundleModeExt.COLUMN -> {
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
    public open /*suspend*/ fun hasNext2(): Boolean {
        if (counter > 0) {
            counter--
            return true
        }
        return false
    }
    public open /*suspend*/ fun hasNext2Close() {
    }
    /*suspend*/ public fun count(): Int {
        SanityCheck.check { mode == IteratorBundleModeExt.COUNT }
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
