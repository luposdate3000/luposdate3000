package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
class ColumnIteratorReduced(val child: ColumnIterator) : ColumnIterator() {
    var last: Value? = null
    init {
Coverage.funStart(3872)
        next = {
Coverage.statementStart(3873)
            var res = child.next()
Coverage.statementStart(3874)
            while (res != null && last == res) {
Coverage.whileLoopStart(3875)
                res = child.next()
Coverage.statementStart(3876)
            }
Coverage.statementStart(3877)
            last = res
Coverage.statementStart(3878)
            /*return*/res
        }
Coverage.statementStart(3879)
        close = {
Coverage.statementStart(3880)
            child.close()
Coverage.statementStart(3881)
            _close()
Coverage.statementStart(3882)
        }
Coverage.statementStart(3883)
    }
}
