package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField
public abstract class ColumnIteratorChildIterator : ColumnIterator() {
    @JvmField public var queue: Array<ColumnIterator> = Array(100) { this }
    @JvmField public var queueRead: Int = 0
    @JvmField public var queueWrite: Int = 0
    @JvmField
    public var label: Int = 1
    @Suppress("NOTHING_TO_INLINE") internal inline fun addChildColumnIteratorValue(value: Int) {
        val res = ColumnIteratorValue()
        res.value = value
        res.done = false
        addChild(res)
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun addChild(child: ColumnIterator) {
        if (queueRead == queueWrite) {
            queueRead = 0
            queueWrite = 0
        } else if (queueWrite == queue.size) {
            if (queueRead == 0) {
                val buf = Array<ColumnIterator>(queue.size * 2) { this }
                queue.copyInto(buf, 0, queueRead, queueWrite)
                queue = buf
            } else {
                queue.copyInto(queue, 0, queueRead, queueWrite)
                queueWrite -= queueRead
            }
            queueRead = 0
        }
        queue[queueWrite] = child
        queueWrite++
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun closeOnNoMoreElements() {
        if (label != 0) {
            label = 2
        }
    }
    @Suppress("NOTHING_TO_INLINE") internal inline fun releaseValue(obj: ColumnIterator) {
        obj.close()
    }
    @Suppress("NOTHING_TO_INLINE") /*suspend*/ internal inline fun _close() {
        if (label != 0) {
            label = 0
            for (i in queueRead until queueWrite) {
                releaseValue(queue[i])
            }
        }
    }
    /*suspend*/ internal inline fun nextHelper(crossinline onNoMoreElements: /*suspend*/ () -> Unit, crossinline onClose: /*suspend*/ () -> Unit): Int {
        when (label) {
            1 -> {
                while (queueRead < queueWrite) {
                    val res = queue[queueRead].next()
                    if (res == ResultSetDictionaryExt.nullValue) {
                        releaseValue(queue[queueRead])
                        queueRead++
                    } else {
                        return res
                    }
                }
                onNoMoreElements()
                return if (queueRead == queueWrite) {
                    onClose()
                    ResultSetDictionaryExt.nullValue
                } else {
                    val res = queue[queueRead].next()
                    if (res == ResultSetDictionaryExt.nullValue) {
                        onClose()
                    }
                    res
                }
            }
            2 -> {
                while (queueRead < queueWrite) {
                    val res = queue[queueRead].next()
                    if (res == ResultSetDictionaryExt.nullValue) {
                        releaseValue(queue[queueRead])
                        queueRead++
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
