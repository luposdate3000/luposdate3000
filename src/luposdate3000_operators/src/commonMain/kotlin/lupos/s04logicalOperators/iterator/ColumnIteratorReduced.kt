package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField
public class ColumnIteratorReduced(@JvmField public val child: ColumnIterator) : ColumnIterator() {
    @JvmField
    public var last: Int = ResultSetDictionaryExt.nullValue
    @JvmField
    public var label: Int = 1
    /*suspend*/ internal inline fun _close() {
        if (label != 0) {
            label = 0
            child.close()
        }
    }
    override /*suspend*/ fun close() {
        _close()
    }
    override /*suspend*/ fun next(): Int {
        return if (label == 1) {
            var res = child.next()
            while (res != ResultSetDictionaryExt.nullValue && last == res) {
                res = child.next()
            }
            last = res
            if (res == ResultSetDictionaryExt.nullValue) {
                _close()
            }
            res
        } else {
            ResultSetDictionaryExt.nullValue
        }
    }
}
