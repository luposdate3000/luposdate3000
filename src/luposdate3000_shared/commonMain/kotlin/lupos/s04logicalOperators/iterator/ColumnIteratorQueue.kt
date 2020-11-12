package lupos.s04logicalOperators.iterator

import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultSetDictionaryExt

abstract class ColumnIteratorQueue : ColumnIterator() {
    @JvmField
    var tmp = ResultSetDictionaryExt.nullValue

    @JvmField
    val queue = mutableListOf<Int>()

    @JvmField
    var label = 1
}
