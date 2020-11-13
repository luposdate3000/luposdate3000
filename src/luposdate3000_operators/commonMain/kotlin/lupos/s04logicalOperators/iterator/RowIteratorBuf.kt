package lupos.s04logicalOperators.iterator

import lupos.s00misc.SanityCheck

class RowIteratorBuf(buf: IntArray, columns: Array<String>, val size: Int) : RowIterator() {
    var offset: Int = 0

    init {
        this.buf = buf
        this.columns = columns
        if (size == 0) {
            offset = -1
        }
        SanityCheck.check { size >= 0 }
        SanityCheck.check { size <= buf.size }
        SanityCheck.check { (buf.size % columns.size) == 0 }
        next = {
            val res = offset
            val tmp = offset + columns.size
            offset = if (tmp >= size) {
                -1
            } else {
                tmp
            }
            res
        }
    }
}
