package lupos.s04logicalOperators.iterator

import kotlinx.coroutines.runBlocking
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value

object RowIteratorDebugFast {
    operator fun invoke(label: String, child: RowIterator) = child
}

var debugiteratoruuid = 0

class RowIteratorDebugVerbose(val label: String, val child: RowIterator) : RowIterator() {
    val uuid = debugiteratoruuid++

    init {
        columns = child.columns
        buf = IntArray(columns.size)
        next = {
            var res = -1
            val off = child.next()
            if (off < 0) {
                close()
            } else {
                var str = StringBuilder()
                str.append("debug row ($label - $uuid): ")
                for (i in 0 until columns.size) {
                    buf[i] = child.buf[off + i]
                    str.append("${buf[i]}, ")
                }
                res = 0
            }
            /*return*/ res
        }
        close = {
            child.close()
            _close()
        }
    }
}
