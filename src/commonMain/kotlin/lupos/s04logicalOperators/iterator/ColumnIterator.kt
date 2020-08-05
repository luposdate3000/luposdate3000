package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.FuncColumnIteratorClose

open class ColumnIterator() {
    companion object {
        @JvmField
        val _next: FuncColumnIteratorNext = object : FuncColumnIteratorNext("FuncColumnIteratorNextEmpty") {
            override fun invoke(): Value? {
                return null
            }
        }

        @JvmField
        var __close: FuncColumnIteratorClose = object : FuncColumnIteratorClose("FuncColumnIteratorCloseEmpty") {
            override fun invoke() {
            }
        }
    }

    @JvmField
    var _close: FuncColumnIteratorClose = object : FuncColumnIteratorClose("FuncColumnIteratorCloseEmpty") {
        override fun invoke() {
            next = _next
            close = __close
        }
    }

    @JvmField
    var close: FuncColumnIteratorClose = _close

    @JvmField
    var next: FuncColumnIteratorNext = _next
}
