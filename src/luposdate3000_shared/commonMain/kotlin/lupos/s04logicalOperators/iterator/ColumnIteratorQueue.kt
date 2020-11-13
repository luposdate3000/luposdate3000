package lupos.s04logicalOperators.iterator

import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultSetDictionaryExt

abstract class ColumnIteratorQueue : ColumnIterator() {
    @JvmField
    var tmp: Int = ResultSetDictionaryExt.nullValue

    @JvmField
    val queue: MutableList<Int> = mutableListOf<Int>()

    @JvmField
    var label: Int = 1
}
