package lupos.s04logicalOperators.iterator

import lupos.s00misc.SanityCheck

fun RowIteratorBuf(buf: IntArray, columns: Array<String>, size: Int) = RowIteratorBuf1(buf, columns, size)
fun RowIteratorBuf(buf: List<Int>, columns: Array<String>): RowIteratorBuf1 {
    SanityCheck.check { buf.size % columns.size == 0 }
    return RowIteratorBuf1(buf.toIntArray(), columns, buf.size)
}

class RowIteratorBuf1(buf: IntArray, columns: Array<String>, val size: Int) : RowIterator() {
    var offset = 0

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
