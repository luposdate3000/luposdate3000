package lupos.s04logicalOperators.iterator

import lupos.s00misc.Parallel
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value

class ColumnIteratorChannel() : ColumnIterator() {
    @JvmField
    var queue = Parallel.createQueue<Int>(ResultSetDictionary.nullValue)

    @JvmField
    var doneReading = false

    @JvmField
    var doneWriting = false
    suspend inline fun append(v: Value) {
        queue.send(v)
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

    override suspend fun close() {
        _close()
    }

    override suspend fun next(): Value {
        if (label == 1) {
            var res: Value = ResultSetDictionary.nullValue
            try {
                res = queue.receive()
            } catch (e: Throwable) {
                SanityCheck.println({ "TODO exception 12" })
                e.printStackTrace()
                SanityCheck.check { doneWriting }
                doneReading = true
                _close()
            }
            return res
        } else {
            return ResultSetDictionary.nullValue
        }
    }
}
