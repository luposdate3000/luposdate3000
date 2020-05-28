package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
class ColumnIteratorQueue() : ColumnIterator() {
    var tmp: Value? = null
    val queue = MyListValue()
    var onEmptyQueue: suspend () -> Unit = ::_onEmptyQueue
    suspend fun _onEmptyQueue() {
Coverage.funStart(3858)
    }
    init {
Coverage.funStart(3859)
        next = {
Coverage.statementStart(3860)
            if (queue.size == 0) {
Coverage.ifStart(3861)
                onEmptyQueue()
Coverage.statementStart(3862)
            }
Coverage.statementStart(3863)
            var res: Value? = null
Coverage.statementStart(3864)
            if (queue.size > 0) {
Coverage.ifStart(3865)
                res = queue.removeAt(0)
Coverage.statementStart(3866)
            }
Coverage.statementStart(3867)
            /*return*/res
        }
Coverage.statementStart(3868)
        close = {
Coverage.statementStart(3869)
            _close()
Coverage.statementStart(3870)
        }
Coverage.statementStart(3871)
    }
}
