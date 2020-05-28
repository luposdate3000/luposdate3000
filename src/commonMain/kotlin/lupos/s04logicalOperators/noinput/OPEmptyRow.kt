package lupos.s04logicalOperators.noinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class OPEmptyRow(query: Query) : LOPBase(query, EOperatorID.OPEmptyRowID, "OPEmptyRow", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun toSparql() = "{}"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(4630)
        if (other !is OPEmptyRow) {
Coverage.ifStart(4631)
            return false
        }
Coverage.statementStart(4632)
        return true
    }
    override fun cloneOP() = this
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(4633)
        var res = HistogramResult()
Coverage.statementStart(4634)
        res.count = 1
Coverage.statementStart(4635)
        return res
    }
}
