package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value

abstract class ColumnIteratorChildIterator() : ColumnIterator() {
    @JvmField
    val childs = mutableListOf<ColumnIterator>()

    @JvmField
    var label = 1

    inline fun closeOnNoMoreElements() {
        if (label != 0) {
            label = 2
        }
    }

    inline suspend fun _close() {
            label = 0
while(childs.size>0){
val x=childs.removeAt(0)
x.close()
}
    }

    inline suspend fun next_helper(crossinline onNoMoreElements: suspend () -> Unit): Value {
        when (label) {
            1 -> {
                while (true) {
                    while (childs.size > 0) {
                        val res = childs[0].next()
                        if (res == ResultSetDictionary.nullValue) {
                            childs.removeAt(0)
                        } else {
                            return res
                        }
                    }
                    onNoMoreElements()
                    if (childs.size == 0) {
                        close()
                        return ResultSetDictionary.nullValue
                    }
                }
            }
            2 -> {
                while (childs.size > 0) {
                    val res = childs[0].next()
                    if (res == ResultSetDictionary.nullValue) {
                        childs.removeAt(0)
                    } else {
                        return res
                    }
                }
                close()
                return ResultSetDictionary.nullValue
            }
            else -> {
                return ResultSetDictionary.nullValue
            }
        }
    }
}
