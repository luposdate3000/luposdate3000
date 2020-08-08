package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value

object ColumnIteratorMultiValue {
    operator fun invoke(values: IntArray, size: Int) = ColumnIteratorMultiValue_3(values, size)
    operator fun invoke(values: MyListValue) = ColumnIteratorMultiValue_1(values)
    operator fun invoke(iterator: Iterator<Value>) = ColumnIteratorMultiValue_2(iterator)
}

class ColumnIteratorMultiValue_1(@JvmField val values: MyListValue) : ColumnIterator() {
    @JvmField
    var index = 0
    override suspend fun close() {
        index = values.size
    }

    override suspend fun next(): Value {
        if (index == values.size) {
            return ResultSetDictionary.nullValue
        } else {
            return values[index++]
        }
    }
}

class ColumnIteratorMultiValue_3(@JvmField val values: IntArray, @JvmField val size: Int) : ColumnIterator() {
    @JvmField
    var index = 0
    override suspend fun close() {
        index = size
    }

    override suspend fun next(): Value {
        if (index == size) {
            return ResultSetDictionary.nullValue
        } else {
            return values[index++]
        }
    }
}

class ColumnIteratorMultiValue_2(@JvmField val iterator: Iterator<Value>) : ColumnIterator() {
    @JvmField
    var label = 1
    override suspend fun close() {
        label = 0
    }

    override suspend fun next(): Value {
        if (label != 0 && iterator.hasNext()) {
            return iterator.next()
        } else {
            return ResultSetDictionary.nullValue
        }
    }
}
