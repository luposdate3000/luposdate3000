package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
class ColumnIteratorRepeatValue(val count: Int, val value: Value) : ColumnIterator() {
    var index = 0
    init {
Coverage.funStart(3920)
        next = {
Coverage.statementStart(3921)
            var res: Value?
Coverage.statementStart(3922)
            if (index == count) {
Coverage.ifStart(3923)
                res = null
Coverage.statementStart(3924)
            } else {
Coverage.ifStart(3925)
                index++
Coverage.statementStart(3926)
                res = value
Coverage.statementStart(3927)
            }
Coverage.statementStart(3928)
/*return*/res
        }
Coverage.statementStart(3929)
    }
}
