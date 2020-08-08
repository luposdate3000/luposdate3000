package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value

class ColumnIteratorReduced(@JvmField val child: ColumnIterator) : ColumnIterator() {
    @JvmField
    var last: Value = ResultSetDictionary.nullValue

    @JvmField
    var label = 1
    inline suspend fun _close() {
        if (label != 0) {
            label = 0
            child.close()
        }
    }

    override suspend fun close() {
        _close()
    }

    override suspend fun next(): Value {
        if (label == 1) {
            var res = child.next()
            while (res != ResultSetDictionary.nullValue && last == res) {
                res = child.next()
            }
            last = res
            if (res == ResultSetDictionary.nullValue) {
                _close()
            }
            return res
        } else {
            return ResultSetDictionary.nullValue
        }
    }
}
