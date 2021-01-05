package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField
public class ColumnIteratorMultiIterator(@JvmField public val childs: List<ColumnIterator>) : ColumnIterator() {
    @JvmField
    public var index: Int = 0
    @JvmField
    public var label: Int = 1
    /*suspend*/ internal inline fun _close() {
        if (label != 0) {
            label = 0
            for (c in childs) {
                c.close()
            }
        }
    }
    override /*suspend*/ fun close() {
        _close()
    }
    override /*suspend*/ fun next(): Int {
        return if (label == 1) {
            var res = childs[index].next()
            while (res == ResultSetDictionaryExt.nullValue && ++index < childs.size) {
                res = childs[index].next()
            }
            if (res == ResultSetDictionaryExt.nullValue) {
                _close()
            }
            res
        } else {
            ResultSetDictionaryExt.nullValue
        }
    }
}
