package lupos.s04logicalOperators.iterator
import kotlin.jvm.JvmField
open class RowIterator {
    @JvmField
    var columns: Array<String> = arrayOf()
    @JvmField
    var buf: IntArray = IntArray(0)
    @JvmField
    var next: /*suspend*/ () -> Int = ::_next
    /*next returns start index in buf, or -1 otherwise*/
    @JvmField
    var close: /*suspend*/ () -> Unit = ::_close
    /*suspend*/ fun _close() {
        next = ::_next
        close = ::_close
    }
    /*suspend*/ private fun _next() = -1
}
