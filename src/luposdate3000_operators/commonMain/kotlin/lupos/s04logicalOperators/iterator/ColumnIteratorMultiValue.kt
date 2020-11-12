package lupos.s04logicalOperators.iterator

import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultSetDictionaryExt

object ColumnIteratorMultiValue {
    operator fun invoke(values: IntArray, size: Int) = ColumnIteratorMultiValue3(values, size)
    operator fun invoke(values: MutableList<Int>) = ColumnIteratorMultiValue1(values)
    operator fun invoke(iterator: Iterator<Int>) = ColumnIteratorMultiValue2(iterator)
}

class ColumnIteratorMultiValue1(@JvmField val values: MutableList<Int>) : ColumnIterator() {
    @JvmField
    var index = 0
    override suspend fun close() {
        index = values.size
    }

    override suspend fun next(): Int {
        if (index == values.size) {
            return ResultSetDictionaryExt.nullValue
        } else {
            return values[index++]
        }
    }
}

class ColumnIteratorMultiValue3(@JvmField val values: IntArray, @JvmField val size: Int) : ColumnIterator() {
    @JvmField
    var index = 0
    override suspend fun close() {
        index = size
    }

    override suspend fun next(): Int {
        if (index == size) {
            return ResultSetDictionaryExt.nullValue
        } else {
            return values[index++]
        }
    }
}

class ColumnIteratorMultiValue2(@JvmField val iterator: Iterator<Int>) : ColumnIterator() {
    @JvmField
    var label = 1
    override suspend fun close() {
        label = 0
    }

    override suspend fun next(): Int {
        if (label != 0 && iterator.hasNext()) {
            return iterator.next()
        } else {
            return ResultSetDictionaryExt.nullValue
        }
    }
}
