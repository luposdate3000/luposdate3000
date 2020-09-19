package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s00misc.ClassCacheManager

abstract class ColumnIteratorChildIterator() : ColumnIterator() {
    @JvmField
    val cacheManagerColumnIteratorValue = object : ClassCacheManager<ColumnIteratorValue>() {
        override fun allocNew() = ColumnIteratorValue()
    }

    var queue = Array<ColumnIterator?>(100) { null }
    var queue_read = 0
    var queue_write = 0

    @JvmField
    var label = 1

    inline fun addChildColumnIteratorValue(value: Value) {
        var res = cacheManagerColumnIteratorValue.alloc()
        res.value = value
        res.done = false
        addChild(res)
    }

    inline fun addChild(child: ColumnIterator) {
        if ((1 + queue_write) % queue.size == queue_read) {
            val buf = Array<ColumnIterator?>(queue.size * 2) { null }
            var j = 0
            if (queue_read < queue_write) {
                for (i in queue_read until queue_write) {
                    buf[j++] = queue[i]!!
                }
                queue_write = queue_write - queue_read
            } else if (queue_read > queue_write) {
                for (i in 0 until queue_write) {
                    buf[j++] = queue[i]!!
                }
                for (i in queue_read until queue.size) {
                    buf[j++] = queue[i]!!
                }
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
        when (obj) {
            is ColumnIteratorValue -> {
                cacheManagerColumnIteratorValue.release(obj)
            }
            else -> {
                obj.close()
            }
        }
    }

    inline suspend fun _close() {
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

    inline suspend fun next_helper(crossinline onNoMoreElements: suspend () -> Unit, crossinline onClose: suspend () -> Unit): Value {
        when (label) {
            1 -> {
                while (true) {
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
                    }
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
