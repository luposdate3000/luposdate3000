package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField

abstract class ColumnIteratorQueue : ColumnIterator() {
    @JvmField
    var tmp: Int = ResultSetDictionaryExt.nullValue

    @JvmField
    val queue: MutableList<Int> = mutableListOf()

    @JvmField
    var label: Int = 1
}
