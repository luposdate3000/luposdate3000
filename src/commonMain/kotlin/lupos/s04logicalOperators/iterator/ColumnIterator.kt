package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value

open class ColumnIterator() {
    @JvmField
    var next: FuncColumnIteratorNext = FuncColumnIteratorNext.empty

    @JvmField
    var close: () -> Unit = ::_close
    fun _close() {
        next = FuncColumnIteratorNext.empty
        close = ::_close
    }
}
