package lupos.s04logicalOperators.singleinput.modifiers
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPReduced(query: Query, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPReducedID, "LOPReduced", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    var hadPushDown = false
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5379)
        if (other !is LOPReduced) {
Coverage.ifStart(5380)
            return false
        }
Coverage.statementStart(5381)
        for (i in children.indices) {
Coverage.forLoopStart(5382)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5383)
                return false
            }
Coverage.statementStart(5384)
        }
Coverage.statementStart(5385)
        return true
    }
    override fun cloneOP() = LOPReduced(query, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5386)
        return children[0].getHistogram()
    }
}
