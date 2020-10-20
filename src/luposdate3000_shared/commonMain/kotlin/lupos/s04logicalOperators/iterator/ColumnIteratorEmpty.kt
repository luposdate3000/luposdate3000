package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.IResultSetDictionary
import kotlin.jvm.JvmField

open class ColumnIteratorEmpty(@JvmField val dictionary:IResultSetDictionary) : ColumnIterator() {
    override suspend fun next(): Int {
        return dictionary.getNullValue()
    }

    override suspend fun close() {
    }
}
