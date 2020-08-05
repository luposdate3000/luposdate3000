package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value

abstract class ColumnIteratorNext(@JvmField val classname: String) {
    companion object {
        /*
                inline operator fun invoke(classname: String, crossinline action: () -> Value?): ColumnIteratorNext {
                    return object : ColumnIteratorNext(classname) {
                        override fun invoke(): Value? {
                            return action()
                        }
                    }
                }
        */
        @JvmField
        val empty = object : ColumnIteratorNext("ColumnIteratorNextEmpty") {
            override fun invoke(): Value? {
                return null
            }
        }
    }

    abstract operator fun invoke(): Value?
}

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
