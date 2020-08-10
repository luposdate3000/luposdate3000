package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value

class ColumnIteratorRepeatIterator(@JvmField val count: Int, @JvmField val child: ColumnIterator) : ColumnIterator() {
    @JvmField
    var index = 0

    @JvmField
    var index2 = 0

    //TODO use pages instead
    @JvmField
    val data = MyListValue()

    @JvmField
    var label = 1
    inline suspend fun _close() {
        if (label != 0) {
            label = 0
            child.close()
        }
    }

    override suspend fun close() {
        _close()
    }

    override suspend fun next(): Value {
        when (label) {
            1 -> {
                val tmp = child.next()
                if (tmp == ResultSetDictionary.nullValue) {
                    child.close()
                    if (data.size == 0 || count == 1) {
                        label = 0
println("ColumnIteratorRepeatIterator 0 ${label} ${index2} ${data.size} ${index} ${count}")
                        return ResultSetDictionary.nullValue
                    } else {
                        index = 2
                        label = 2
println("ColumnIteratorRepeatIterator 1 ${label} ${index2} ${data.size} ${index} ${count}")
                        return data[index2++]
                    }
                } else {
                    data.add(tmp)
println("ColumnIteratorRepeatIterator 2 ${label} ${index2} ${data.size} ${index} ${count}")
                    return tmp
                }
            }
            2 -> {
                if (index2 < data.size) {
println("ColumnIteratorRepeatIterator 3 ${label} ${index2} ${data.size} ${index} ${count}")
                    return data[index2++]
                } else if (index < count) {
                    index++
                    index2 = 0
println("ColumnIteratorRepeatIterator 4 ${label} ${index2} ${data.size} ${index} ${count}")
                    return data[index2++]
                } else {
                    label = 0
println("ColumnIteratorRepeatIterator 5 ${label} ${index2} ${data.size} ${index} ${count}")
                    return ResultSetDictionary.nullValue
                }
            }
            else -> {
println("ColumnIteratorRepeatIterator 6 ${label} ${index2} ${data.size} ${index} ${count}")
                return ResultSetDictionary.nullValue
            }
        }
    }
}
