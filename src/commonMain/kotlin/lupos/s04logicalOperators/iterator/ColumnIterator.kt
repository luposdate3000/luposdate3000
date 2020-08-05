package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value

open class ColumnIterator() {
    @JvmField
    var next: ColumnIteratorNext = ColumnIteratorNext.empty

    @JvmField
    var close: () -> Unit = ::_close
    fun _close() {
        next = ColumnIteratorNext.empty
        close = ::_close
    }
}
