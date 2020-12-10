package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField
class ColumnIteratorRepeatIterator(@JvmField val count: Int, @JvmField val child: ColumnIterator) : ColumnIterator() {
    @JvmField
    var index: Int = 0
    @JvmField
    var index2: Int = 0
    // TODO use pages instead
    @JvmField
    val data: MutableList<Int> = mutableListOf()
    @JvmField
    var label: Int = 1
    /*suspend*/ inline fun _close() {
        if (label != 0) {
            label = 0
            child.close()
        }
    }
    override /*suspend*/ fun close() {
        _close()
    }
    override /*suspend*/ fun next(): Int {
        when (label) {
            1 -> {
                val tmp = child.next()
                return if (tmp == ResultSetDictionaryExt.nullValue) {
                    child.close()
                    if (data.size == 0 || count == 1) {
                        label = 0
                        ResultSetDictionaryExt.nullValue
                    } else {
                        index = 2
                        label = 2
                        data[index2++]
                    }
                } else {
                    data.add(tmp)
                    tmp
                }
            }
            2 -> {
                return when {
                    index2 < data.size -> {
                        data[index2++]
                    }
                    index < count -> {
                        index++
                        index2 = 0
                        data[index2++]
                    }
                    else -> {
                        label = 0
                        ResultSetDictionaryExt.nullValue
                    }
                }
            }
            else -> {
                return ResultSetDictionaryExt.nullValue
            }
        }
    }
}
