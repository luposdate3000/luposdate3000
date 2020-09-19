package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s00misc.Lock
import lupos.s00misc.ClassCacheManager

class ColumnIteratorValue (): ColumnIterator() {
    companion object {
        inline operator fun invoke(value: Value): ColumnIteratorValue {
            var res = ColumnIteratorValue()
            res.value = value
            res.done = false
            return res
        }
    }


    @JvmField
    var value = ResultSetDictionary.nullValue

    @JvmField
    var done = false

    override suspend fun close() {
done=true
    }

    override suspend fun next(): Value {
        if (done) {
            return ResultSetDictionary.nullValue
        } else {
            done = true
            return value
        }
    }
}
