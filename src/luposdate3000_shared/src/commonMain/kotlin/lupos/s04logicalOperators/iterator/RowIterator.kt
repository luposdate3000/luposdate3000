package lupos.s04logicalOperators.iterator
import kotlin.jvm.JvmField
public open class RowIterator {
    @JvmField
    public var columns: Array<String> = arrayOf()
    @JvmField
    public var buf: IntArray = IntArray(0)
    @JvmField
    public var next: /*suspend*/ () -> Int = ::_next
    /*next returns start index in buf, or -1 otherwise*/
    @JvmField
    public var close: /*suspend*/ () -> Unit = ::_close
    public /*suspend*/ fun _close() {
        next = ::_next
        close = ::_close
    }
    /*suspend*/ private fun _next() = -1
}
