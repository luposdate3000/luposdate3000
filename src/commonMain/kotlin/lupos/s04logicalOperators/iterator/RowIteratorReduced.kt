package lupos.s04logicalOperators.iterator

import kotlinx.coroutines.runBlocking

open class RowIteratorReduced(val child: RowIterator) : RowIterator() {
    var off = -1

    init {
        columns = child.columns
        buf = IntArray(columns.size)
        runBlocking {
            off = child.next()
        }
        if (off >= 0) {
            next = {
                for (i in 0 until columns.size) {
                    buf[i] = child.buf[off + i]
                }
                loop@ while (true) {
                    off = child.next()
                    if (off < 0) {
                        close()
                        break
                    } else {
                        for (i in 0 until columns.size) {
                            if (buf[i] != child.buf[off + i]) {
                                break@loop
                            }
                        }
                    }
                }
                /*return*/ 0
            }
        }
        close = {
            child.close()
            _close()
        }
    }
}
