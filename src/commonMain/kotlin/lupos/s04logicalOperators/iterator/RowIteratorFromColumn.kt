package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
open class RowIteratorFromColumn(val bundle: IteratorBundle) : RowIterator() {
    var iterators: Array<ColumnIterator>
    init {
Coverage.funStart(3994)
        SanityCheck.check { bundle.hasColumnMode() }
Coverage.statementStart(3995)
        val keys = bundle.columns.keys.toList()
Coverage.statementStart(3996)
        columns = Array<String>(bundle.columns.size) { keys[it] }
Coverage.statementStart(3997)
        iterators = Array<ColumnIterator>(bundle.columns.size) { bundle.columns[columns[it]]!! }
Coverage.statementStart(3998)
        buf = IntArray(keys.size)
Coverage.statementStart(3999)
        next = {
Coverage.statementStart(4000)
            var res = 0
Coverage.statementStart(4001)
            for (columnIndex in 0 until columns.size) {
Coverage.forLoopStart(4002)
                var tmp = iterators[columnIndex].next()
Coverage.statementStart(4003)
                if (tmp == null) {
Coverage.ifStart(4004)
                    SanityCheck.check({ columnIndex == 0 }, { "" + iterators[columnIndex] })
Coverage.statementStart(4005)
                    res = -1
Coverage.statementStart(4006)
                    break
                } else {
Coverage.statementStart(4007)
                    buf[columnIndex] = tmp
Coverage.statementStart(4008)
                }
Coverage.statementStart(4009)
            }
Coverage.statementStart(4010)
            /*return*/ res
        }
Coverage.statementStart(4011)
        close = {
Coverage.statementStart(4012)
            for (columnIndex in 0 until columns.size) {
Coverage.forLoopStart(4013)
                iterators[columnIndex].close()
Coverage.statementStart(4014)
            }
Coverage.statementStart(4015)
            _close()
Coverage.statementStart(4016)
        }
Coverage.statementStart(4017)
    }
}
