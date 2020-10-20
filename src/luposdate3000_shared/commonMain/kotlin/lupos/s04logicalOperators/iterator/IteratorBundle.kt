package lupos.s04logicalOperators.iterator

import kotlin.jvm.JvmField
import lupos.s00misc.IteratorBundleColumnModeNotImplementedException
import lupos.s00misc.IteratorBundleRowModeNotImplementedException
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.IResultSetDictionary

open class IteratorBundle {
internal enum class IteratorBundleMode {
    COUNT,
    COLUMN,
    ROW
}
    @JvmField
internal    var mode: IteratorBundleMode

    @JvmField
    var _columns: Map<String, ColumnIterator>?

    @JvmField
    var _rows: RowIterator?
    @JvmField
    val _dictionary: IResultSetDictionary

    @JvmField
    var counter = 0
     fun hasColumnMode() = mode == IteratorBundleMode.COLUMN
     fun hasCountMode() = mode == IteratorBundleMode.COUNT
     fun hasRowMode() = mode == IteratorBundleMode.ROW

    constructor (dictionary: IResultSetDictionary,columns: Map<String, ColumnIterator>) {
_dictionary=dictionary
        _rows = null
        _columns = columns
        mode = IteratorBundleMode.COLUMN
    }

    constructor(dictionary: IResultSetDictionary,count: Int) {
_dictionary=dictionary
        _rows = null
        _columns = null
        counter = count
        mode = IteratorBundleMode.COUNT
    }

    constructor(dictionary: IResultSetDictionary,rows: RowIterator) {
_dictionary=dictionary
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
                    _columns = ColumnIteratorFromRow(_dictionary,_rows!!)
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
                    _rows = RowIteratorFromColumn(_dictionary,this)
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

    suspend  fun count(): Int {
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
