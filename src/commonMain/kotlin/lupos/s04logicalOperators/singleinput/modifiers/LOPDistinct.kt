package lupos.s04logicalOperators.singleinput.modifiers
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPDistinct(query: Query, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPDistinctID, "LOPDistinct", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5308)
        if (other !is LOPDistinct) {
Coverage.ifStart(5309)
            return false
        }
Coverage.statementStart(5310)
        for (i in children.indices) {
Coverage.forLoopStart(5311)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5312)
                return false
            }
Coverage.statementStart(5313)
        }
Coverage.statementStart(5314)
        return true
    }
    override fun cloneOP() = LOPDistinct(query, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5315)
        return children[0].getHistogram()
    }
}
