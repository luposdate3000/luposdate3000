package lupos.s04logicalOperators.iterator
import kotlin.jvm.JvmField
import lupos.s00misc.Parallel
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt


 class ColumnIteratorChannel() : ColumnIterator() {
    @JvmField
internal    var queue = Parallel.createQueue<Int>(ResultSetDictionaryExt.nullValue)

    @JvmField
    var doneReading = false

    @JvmField
    var doneWriting = false
internal    suspend inline fun append(v: Int) {
        queue.send(v)
    }

internal    inline fun writeFinish() {
        doneWriting = true
        queue.close()
    }

    @JvmField
    var label = 1
internal    inline fun _close() {
        if (label != 0) {
            label = 0
            queue.close()
        }
    }

    override suspend fun close() {
        _close()
    }

    override suspend fun next(): Int {
        if (label == 1) {
            var res: Int = ResultSetDictionaryExt.nullValue
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
            return ResultSetDictionaryExt.nullValue
        }
    }
}
