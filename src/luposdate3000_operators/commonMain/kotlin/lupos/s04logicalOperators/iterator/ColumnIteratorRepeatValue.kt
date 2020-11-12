package lupos.s04logicalOperators.iterator

import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultSetDictionaryExt

class ColumnIteratorRepeatValue(@JvmField val count: Int, @JvmField val value: Int) : ColumnIterator() {
    @JvmField
    var index = 0
    override /*suspend*/ fun close() {
        index = count
    }

    override /*suspend*/ fun next(): Int {
        return if (index == count) {
            ResultSetDictionaryExt.nullValue
        } else {
            index++
            value
        }
    }
}
