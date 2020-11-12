package lupos.s04logicalOperators.iterator

import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultSetDictionaryExt

abstract class ColumnIteratorChildIterator : ColumnIterator() {
    var queue = Array<ColumnIterator>(100) { this }
    var queue_read = 0
    var queue_write = 0

    @JvmField
    var label = 1
    internal inline fun addChildColumnIteratorValue(value: Int) {
        var res = ColumnIteratorValue()
        res.value = value
        res.done = false
        addChild(res)
    }

    internal inline fun addChild(child: ColumnIterator) {
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

    internal inline fun closeOnNoMoreElements() {
        if (label != 0) {
            label = 2
        }
    }

    internal inline fun releaseValue(obj: ColumnIterator) {
        obj.close()
    }

    internal /*suspend*/ inline fun _close() {
        if (label != 0) {
            label = 0
            for (i in queue_read until queue_write) {
                releaseValue(queue[i])
            }
        }
    }

    internal /*suspend*/ inline fun nextHelper(crossinline onNoMoreElements: /*suspend*/ () -> Unit, crossinline onClose: /*suspend*/ () -> Unit): Int {
        when (label) {
            1 -> {
                while (queue_read < queue_write) {
                    val res = queue[queue_read].next()
                    if (res == ResultSetDictionaryExt.nullValue) {
                        releaseValue(queue[queue_read])
                        queue_read++
                    } else {
                        return res
                    }
                }
                onNoMoreElements()
                if (queue_read == queue_write) {
                    onClose()
                    return ResultSetDictionaryExt.nullValue
                } else {
                    val res = queue[queue_read].next()
                    if (res == ResultSetDictionaryExt.nullValue) {
                        onClose()
                    }
                    return res
                }
            }
            2 -> {
                while (queue_read < queue_write) {
                    val res = queue[queue_read].next()
                    if (res == ResultSetDictionaryExt.nullValue) {
                        releaseValue(queue[queue_read])
                        queue_read++
                    } else {
                        return res
                    }
                }
                onClose()
                return ResultSetDictionaryExt.nullValue
            }
            else -> {
                return ResultSetDictionaryExt.nullValue
            }
        }
    }
}
