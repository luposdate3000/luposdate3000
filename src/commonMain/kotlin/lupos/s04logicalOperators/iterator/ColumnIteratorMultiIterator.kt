package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value

class ColumnIteratorMultiIterator(@JvmField val childs: List<ColumnIterator>) : ColumnIterator() {
    @JvmField
    var index = 0

    @JvmField
    var label = 1
    inline fun _close() {
        if (label != 0) {
            label = 0
            for (c in childs) {
                c.close()
            }
        }
    }

    override fun close() {
        _close()
    }

    override fun next(): Value? {
        if (label == 1) {
            var res = childs[index].next()
            while (res == null && ++index < childs.size) {
                res = childs[index].next()
            }
            if (res == null) {
                _close
            }
            return res
        } else {
            return null
        }
    }
}
