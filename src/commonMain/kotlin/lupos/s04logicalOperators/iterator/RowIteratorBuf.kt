package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value

open class RowIteratorBuf(buf: IntArray, columns: Array<String>, val size: Int) : RowIterator() {
    var offset = 0

    init {
        this.buf = buf
        this.columns = columns
        if (size == 0) {
            offset = -1
        }
        require(size >= 0)
        require(size <= buf.size)
        require((buf.size % columns.size) == 0)
        next = {
            var res = offset
            var tmp = offset + columns.size
            if (tmp >= size) {
                offset = -1
            } else {
                offset = tmp
            }
            /*return*/ res
        }
    }
}
