package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value

abstract class ColumnIteratorChildIterator() : ColumnIterator() {
    @JvmField
    val childs = mutableListOf<ColumnIterator>()

    @JvmField
    var label = 1
    abstract fun onNoMoreElements()
    inline fun closeOnNoMoreElements() {
        label = 2
    }

    inline fun _close() {
        if (label != 0) {
            label = 0
            for (child in childs) {
                child.close()
            }
        }
    }

    override fun next(): Value? {
        when (label) {
            0 -> {
                return null
            }
            1 -> {
                var res: Value? = null
                while (res == null) {
                    while (childs.size > 0) {
                        res = childs[0].next()
                        if (res == null) {
                            childs.removeAt(0)
                        } else {
                            return res
                        }
                    }
                    onNoMoreElements()
                    if (childs.size == 0) {
                        close()
                        return null
                    }
                }
                return res
            }
            2 -> {
                var res: Value? = null
                while (childs.size > 0) {
                    res = childs[0].next()
                    if (res == null) {
                        childs.removeAt(0)
                    } else {
                        return res
                    }
                }
                close()
                return null
            }
        }
    }
}
