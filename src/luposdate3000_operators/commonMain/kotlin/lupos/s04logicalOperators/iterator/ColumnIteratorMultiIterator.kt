package lupos.s04logicalOperators.iterator

import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultSetDictionaryExt

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

    override suspend fun next(): Int {
        if (label == 1) {
            var res = childs[index].next()
            while (res == ResultSetDictionaryExt.nullValue && ++index < childs.size) {
                res = childs[index].next()
            }
            if (res == ResultSetDictionaryExt.nullValue) {
                _close()
            }
            return res
        } else {
            return ResultSetDictionaryExt.nullValue
        }
    }
}
