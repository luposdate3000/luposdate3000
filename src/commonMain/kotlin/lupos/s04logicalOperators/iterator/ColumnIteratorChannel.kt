package lupos.s04logicalOperators.iterator
import lupos.s04logicalOperators.iterator.ColumnIteratorNext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value

class ColumnIteratorChannel() : ColumnIterator() {
    var queue = Channel<Value>(CoroutinesHelper.channelType)
    var doneReading = false
    var doneWriting = false
    fun append(v: Value) {
runBlocking{
        queue.send(v)
}
    }

    fun writeFinish() {
        doneWriting = true
        queue.close()
    }

    init {
        next = ColumnIteratorNext("ColumnIteratorChannel.next"){
            var res: Value? = null
            try {
runBlocking{
                res = queue.receive()
}
            } catch (e: Throwable) {
                SanityCheck.println({ "TODO exception 12" })
                e.printStackTrace()
                SanityCheck.check { doneWriting }
                doneReading = true
                close()
            }
            /*return*/res
        }
        close = {
            queue.close()
            _close()
        }
    }
}
