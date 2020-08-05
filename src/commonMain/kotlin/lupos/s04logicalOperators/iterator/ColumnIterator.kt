package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value


abstract class ColumnIteratorNext(@JvmField val classname: String) {
    companion object {
        inline operator fun invoke(classname: String, crossinline action: () -> Value?): ColumnIteratorNext {
            return object: ColumnIteratorNext (classname){
                override fun invoke() : Value?{
                    return action()
                }
            }
        }
    }

abstract    operator fun invoke(): Value?
}

val ColumnIteratorNextEmpty=ColumnIteratorNext("ColumnIteratorNextEmpty", {null})

open class ColumnIterator() {
    @JvmField
    var next: ColumnIteratorNext = ColumnIteratorNextEmpty

    @JvmField
    var close: () -> Unit = ::_close
    fun _close() {
        next = ColumnIteratorNextEmpty
        close = ::_close
    }
}
