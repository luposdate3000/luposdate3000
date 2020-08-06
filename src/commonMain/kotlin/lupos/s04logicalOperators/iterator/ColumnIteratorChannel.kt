package lupos.s04logicalOperators.iterator

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value

class ColumnIteratorChannel() : ColumnIterator() {
    @JvmField
    var queue = Channel<Value>(CoroutinesHelper.channelType)
    @JvmField
    var doneReading = false
    @JvmField
    var doneWriting = false
    inline fun append(v: Value) {
        runBlocking {
            queue.send(v)
        }
    }

    inline fun writeFinish() {
        doneWriting = true
        queue.close()
    }

    @JvmField
    var label = 1
    inline fun _close() {
        if (label != 0) {
            label = 0
            queue.close()
        }
    }

    override fun close() {
        _close()
    }

    override fun next(): Value? {
        if (label == 1) {
            var res: Value? = null
            try {
                runBlocking {
                    res = queue.receive()
                }
            } catch (e: Throwable) {
                SanityCheck.println({ "TODO exception 12" })
                e.printStackTrace()
                SanityCheck.check { doneWriting }
                doneReading = true
                _close()
            }
            return res
        } else {
            return null
        }
    }
}
