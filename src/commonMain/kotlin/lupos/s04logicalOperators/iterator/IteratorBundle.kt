package lupos.s04logicalOperators.iterator
import kotlinx.coroutines.runBlocking
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
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
        SanityCheck.check { columns.size > 0 }
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
Coverage.ifStart(3930)
                return _columns!!
            } else if (mode == IteratorBundleMode.ROW) {
                if (_columns == null) {
Coverage.ifStart(3931)
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
Coverage.ifStart(3932)
                return _rows!!
            } else if (mode == IteratorBundleMode.COLUMN) {
                if (_rows == null) {
Coverage.ifStart(3933)
                    _rows = RowIteratorFromColumn(this)
                }
                return _rows!!
            } else {
                throw Exception("not implemented")
            }
        }
    suspend fun _hasNext(): Boolean {
Coverage.funStart(3934)
        if (counter > 0) {
Coverage.ifStart(3935)
            counter--
Coverage.statementStart(3936)
            return true
        }
Coverage.statementStart(3937)
        return false
    }
    var hasNext: suspend () -> Boolean = ::_hasNext
    var counter = 0
    var count: Int = 0
        get() {
            SanityCheck.check { mode == IteratorBundleMode.COUNT }
            if (counter > 0) {
Coverage.ifStart(3938)
                return counter
            } else {
                var res = 0
                runBlocking {
                    while (hasNext()) {
Coverage.whileLoopStart(3939)
                        res++
                    }
                }
                counter = res
                return res
            }
        }
}
