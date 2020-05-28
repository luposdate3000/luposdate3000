package lupos.s04logicalOperators.iterator
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
class ColumnIteratorChannel() : ColumnIterator() {
    var queue = Channel<Value>(CoroutinesHelper.channelType)
    var doneReading = false
    var doneWriting = false
    suspend fun append(v: Value) {
Coverage.funStart(3373)
        queue.send(v)
Coverage.statementStart(3374)
    }
    suspend fun writeFinish() {
Coverage.funStart(3375)
        doneWriting = true
Coverage.statementStart(3376)
        queue.close()
Coverage.statementStart(3377)
    }
    init {
Coverage.funStart(3378)
        next = {
Coverage.statementStart(3379)
            var res: Value? = null
Coverage.statementStart(3380)
            try {
Coverage.statementStart(3381)
                res = queue.receive()
Coverage.statementStart(3382)
            } catch (e: Throwable) {
Coverage.statementStart(3383)
                SanityCheck.check { doneWriting }
Coverage.statementStart(3384)
                doneReading = true
Coverage.statementStart(3385)
                close()
Coverage.statementStart(3386)
            }
Coverage.statementStart(3387)
            /*return*/res
        }
Coverage.statementStart(3388)
        close = {
Coverage.statementStart(3389)
            queue.close()
Coverage.statementStart(3390)
            _close()
Coverage.statementStart(3391)
        }
Coverage.statementStart(3392)
    }
}
