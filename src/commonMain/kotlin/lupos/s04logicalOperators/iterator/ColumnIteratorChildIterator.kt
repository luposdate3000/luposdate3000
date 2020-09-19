package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s00misc.ClassCacheManager

abstract class ColumnIteratorChildIterator() : ColumnIterator() {

    var queue = Array<ColumnIterator?>(100) { null }
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
        } else if ((1 + queue_write) % queue.size == queue_read) {
            val buf = Array<ColumnIterator?>(queue.size * 2) { null }
            var j = 0
            if (queue_read < queue_write) {
                queue.copyInto(buf, 0, queue_read, queue_write)
                queue_write = queue_write - queue_read
            } else if (queue_read > queue_write) {
                queue.copyInto(buf, 0, queue_read, queue.size)
                queue.copyInto(buf, queue.size - queue_read, 0, queue_write)
                queue_write = queue_write + queue.size - queue_read
            } else {
                queue_write = 0
            }
            queue_read = 0
            queue = buf
        }
        queue[queue_write] = child
        queue_write = (1 + queue_write) % queue.size
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
            if (queue_read < queue_write) {
                for (i in queue_read until queue_write) {
                    releaseValue(queue[i]!!)
                }
            } else {
                for (i in 0 until queue_write) {
                    releaseValue(queue[i]!!)
                }
                for (i in queue_read until queue.size) {
                    releaseValue(queue[i]!!)
                }
            }
        }
    }

    inline suspend fun next_helper(crossinline onNoMoreElements: suspend () -> Unit, crossinline onClose: suspend () -> Unit): Value {
        when (label) {
            1 -> {
                while (queue_read != queue_write) {
                    val res = queue[queue_read]!!.next()
                    if (res == ResultSetDictionary.nullValue) {
                        releaseValue(queue[queue_read]!!)
                        queue_read = (1 + queue_read) % queue.size
                    } else {
                        return res
                    }
                }
                onNoMoreElements()
                if (queue_read == queue_write) {
                    onClose()
                    return ResultSetDictionary.nullValue
                } else {
                    val res = queue[queue_read]!!.next()
                    if (res == ResultSetDictionary.nullValue) {
                        onClose()
                    }
                    return res
                }
            }
            2 -> {
                while (queue_read != queue_write) {
                    val res = queue[queue_read]!!.next()
                    if (res == ResultSetDictionary.nullValue) {
                        releaseValue(queue[queue_read]!!)
                        queue_read = (1 + queue_read) % queue.size
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
