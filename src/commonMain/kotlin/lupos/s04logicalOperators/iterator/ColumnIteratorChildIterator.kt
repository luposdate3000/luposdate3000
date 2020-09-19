package lupos.s04logicalOperators.iterator

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value

abstract class ColumnIteratorChildIterator() : ColumnIterator() {

    var queue = Array<ColumnIterator?>(100) { null }
    var queue_read = 0
    var queue_write = 0


    @JvmField
    var label = 1

    inline fun addChild(child: ColumnIterator) {
        if ((1 + queue_write) % queue.size == queue_read) {
            val buf = Array<ColumnIterator?>(queue.size * 2) { null }
            var j = 0
            if (queue_read < queue_write) {
                for (i in queue_read until queue_write) {
                    buf[j++] = queue[i]
                }
                queue_write = queue_write - queue_read
            } else if (queue_read > queue_write) {
                for (i in 0 until queue_write) {
                    buf[j++] = queue[i]
                }
                for (i in queue_read until queue.size) {
                    buf[j++] = queue[i]
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

    inline suspend fun _close() {
        label = 0
        if (queue_read < queue_write) {
            for (i in queue_read until queue_write) {
                queue[i].close()
            }
        } else {
            for (i in 0 until queue_write) {
                queue[i].close()
            }
            for (i in queue_read until queue.size) {
                queue[i].close()
            }
        }
    }

    inline suspend fun next_helper(crossinline onNoMoreElements: suspend () -> Unit, crossinline onClose: suspend () -> Unit): Value {
        when (label) {
            1 -> {
                while (true) {
                    while (queue_read != queue_write) {
                        val res = queue[queue_read].next()
                        if (res == ResultSetDictionary.nullValue) {
                            queue[queue_read].close()
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
                    val res = queue[queue_read].next()
                    if (res == ResultSetDictionary.nullValue) {
                        queue[queue_read].close()
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
