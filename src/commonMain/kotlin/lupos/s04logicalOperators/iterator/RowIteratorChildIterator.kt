package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
class RowIteratorChildIterator(columns: Array<String>) : RowIterator() {
    val childs = mutableListOf(RowIterator())
    var onNoMoreElements: suspend () -> Unit = ::_onNoMoreElements
    init {
Coverage.funStart(3960)
        this.columns = columns
Coverage.statementStart(3961)
        next = {
Coverage.statementStart(3962)
            var res = -1
Coverage.statementStart(3963)
            while (childs.size > 0) {
Coverage.whileLoopStart(3964)
                res = childs[0].next()
Coverage.statementStart(3965)
                buf = childs[0].buf
Coverage.statementStart(3966)
                if (res == -1) {
Coverage.ifStart(3967)
                    childs.removeAt(0)
Coverage.statementStart(3968)
                } else {
Coverage.ifStart(3969)
                    break
                }
Coverage.statementStart(3970)
            }
Coverage.statementStart(3971)
            if (res == -1) {
Coverage.ifStart(3972)
                onNoMoreElements()
Coverage.statementStart(3973)
                if (childs.size == 0) {
Coverage.ifStart(3974)
                    close()
Coverage.statementStart(3975)
                } else {
Coverage.ifStart(3976)
                    res = next()
Coverage.statementStart(3977)
                    if (res != -1) {
Coverage.ifStart(3978)
                        buf = childs[0].buf
Coverage.statementStart(3979)
                    }
Coverage.statementStart(3980)
                }
Coverage.statementStart(3981)
            }
Coverage.statementStart(3982)
            SanityCheck.check { res == -1 || buf == childs[0].buf }
Coverage.statementStart(3983)
            /*return*/res
        }
Coverage.statementStart(3984)
        close = {
Coverage.statementStart(3985)
            onNoMoreElements = ::_onNoMoreElements
Coverage.statementStart(3986)
            for (child in childs) {
Coverage.forLoopStart(3987)
                child.close()
Coverage.statementStart(3988)
            }
Coverage.statementStart(3989)
            _close()
Coverage.statementStart(3990)
        }
Coverage.statementStart(3991)
    }
    suspend fun _onNoMoreElements() {
Coverage.funStart(3992)
        close()
Coverage.statementStart(3993)
    }
}
