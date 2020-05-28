package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
class ColumnIteratorMultiIterator(val childs: List<ColumnIterator>) : ColumnIterator() {
    var index = 0
    init {
Coverage.funStart(3820)
        next = {
Coverage.statementStart(3821)
            var res = childs[index].next()
Coverage.statementStart(3822)
            while (res == null && ++index < childs.size) {
Coverage.whileLoopStart(3823)
                res = childs[index].next()
Coverage.statementStart(3824)
            }
Coverage.statementStart(3825)
/*return*/res
        }
Coverage.statementStart(3826)
        close = {
Coverage.statementStart(3827)
            for (c in childs) {
Coverage.forLoopStart(3828)
                c.close()
Coverage.statementStart(3829)
            }
Coverage.statementStart(3830)
            _close()
Coverage.statementStart(3831)
        }
Coverage.statementStart(3832)
    }
}
