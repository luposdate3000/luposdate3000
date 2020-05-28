package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
class ColumnIteratorChildIterator() : ColumnIterator() {
    val childs = mutableListOf(ColumnIterator())
    var onNoMoreElements: suspend () -> Unit = ::_onNoMoreElements
    init {
Coverage.funStart(3393)
        next = {
Coverage.statementStart(3394)
            var res: Value? = null
Coverage.statementStart(3395)
            while (childs.size > 0) {
Coverage.whileLoopStart(3396)
                res = childs[0].next()
Coverage.statementStart(3397)
                if (res == null) {
Coverage.ifStart(3398)
                    childs.removeAt(0)
Coverage.statementStart(3399)
                } else {
Coverage.ifStart(3400)
                    break
                }
Coverage.statementStart(3401)
            }
Coverage.statementStart(3402)
            if (res == null) {
Coverage.ifStart(3403)
                onNoMoreElements()
Coverage.statementStart(3404)
                if (childs.size == 0) {
Coverage.ifStart(3405)
                    close()
Coverage.statementStart(3406)
                }
Coverage.statementStart(3407)
                res = next()
Coverage.statementStart(3408)
            }
Coverage.statementStart(3409)
            /*return*/res
        }
Coverage.statementStart(3410)
        close = {
Coverage.statementStart(3411)
            onNoMoreElements = ::_onNoMoreElements
Coverage.statementStart(3412)
            for (child in childs) {
Coverage.forLoopStart(3413)
                child.close()
Coverage.statementStart(3414)
            }
Coverage.statementStart(3415)
            _close()
Coverage.statementStart(3416)
        }
Coverage.statementStart(3417)
    }
    suspend fun _onNoMoreElements() {
Coverage.funStart(3418)
        close()
Coverage.statementStart(3419)
    }
}
