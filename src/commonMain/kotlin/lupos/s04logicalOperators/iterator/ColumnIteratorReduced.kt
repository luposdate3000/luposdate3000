package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value

class ColumnIteratorReduced(@JvmField val child: ColumnIterator) : ColumnIterator() {
    @JvmField
    var last: Value? = null

    @JvmField
    var label = 1
    inline fun _close() {
        if (label != 0) {
            label = 0
            child.close()
        }
    }

    override fun close() {
        _close()
    }

    override fun next(): Value? {
        if (label == 1) {
            var res = child.next()
            while (res != null && last == res) {
                res = child.next()
            }
            last = res
            if (res == null) {
                _close()
            }
            return res
        } else {
            return null
        }
    }
}
