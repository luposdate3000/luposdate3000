package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s00misc.ClassCacheManager

abstract class ColumnIteratorChildIterator() : ColumnIterator() {

    var queue = Array<ColumnIterator>(100) { this }
    var queue_read = 0
    var queue_write = 0

    @JvmField
    var label = 1

    inline fun addChildColumnIteratorValue(value: Value) {
        var res = ColumnIteratorValue()
        res.value = value
        res.done = false
        addChild(res)
    }

    inline fun addChild(child: ColumnIterator) {
        if (queue_read == queue_write) {
            queue_read = 0
            queue_write = 0
        } else if (queue_write == queue.size) {
            if (queue_read == 0) {
                val buf = Array<ColumnIterator>(queue.size * 2) { this }
                queue.copyInto(buf, 0, queue_read, queue_write)
                queue = buf
            } else {
                queue.copyInto(queue, 0, queue_read, queue_write)
                queue_write -= queue_read
            }
            queue_read = 0
        }
        queue[queue_write] = child
        queue_write++
    }

    inline fun closeOnNoMoreElements() {
        if (label != 0) {
            label = 2
        }
    }

    inline fun releaseValue(obj: ColumnIterator) {
        obj.close()
    }

    inline suspend fun _close() {
        if (label != 0) {
            label = 0
            for (i in queue_read until queue_write) {
                releaseValue(queue[i])
            }
        }
    }

    inline suspend fun next_helper(crossinline onNoMoreElements: suspend () -> Unit, crossinline onClose: suspend () -> Unit): Value {
        when (label) {
            1 -> {
                while (queue_read < queue_write) {
                    val res = queue[queue_read].next()
                    if (res == ResultSetDictionary.nullValue) {
                        releaseValue(queue[queue_read])
                        queue_read++
                    } else {
                        return res
                    }
                }
                onNoMoreElements()
                if ( queue_read==queue_write) {
                    onClose()
                    return ResultSetDictionary.nullValue
                } else {
                    val res = queue[queue_read].next()
                    if (res == ResultSetDictionary.nullValue) {
                        onClose()
                    }
                    return res
                }
            }
            2 -> {
                while (queue_read < queue_write) {
                    val res = queue[queue_read].next()
                    if (res == ResultSetDictionary.nullValue) {
                        releaseValue(queue[queue_read])
                        queue_read++
                    } else {
                        return res
                    }
                }
                onClose()
                return ResultSetDictionary.nullValue
            }
            else -> {
                return ResultSetDictionary.nullValue
            }
        }
    }
}
