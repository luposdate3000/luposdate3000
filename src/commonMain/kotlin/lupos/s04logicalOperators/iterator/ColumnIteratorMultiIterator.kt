package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value

class ColumnIteratorMultiIterator(@JvmField val childs: List<ColumnIterator>) : ColumnIterator() {
    @JvmField
    var index = 0

    @JvmField
    var label = 1
    inline suspend fun _close() {
        if (label != 0) {
            label = 0
            for (c in childs) {
                c.close()
            }
        }
    }

    override suspend fun close() {
        _close()
    }

    override suspend fun next(): Value {
        if (label == 1) {
            var res = childs[index].next()
            while (res == ResultSetDictionary.nullValue && ++index < childs.size) {
                res = childs[index].next()
            }
            if (res == ResultSetDictionary.nullValue) {
                _close()
            }
            return res
        } else {
            return ResultSetDictionary.nullValue
        }
    }
}
