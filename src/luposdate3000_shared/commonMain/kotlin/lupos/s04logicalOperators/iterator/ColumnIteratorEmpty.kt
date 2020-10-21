package lupos.s04logicalOperators.iterator

import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultSetDictionaryExt

open class ColumnIteratorEmpty() : ColumnIterator() {
    override suspend fun next(): Int {
        return ResultSetDictionaryExt.nullValue
    }

    override suspend fun close() {
    }
}
