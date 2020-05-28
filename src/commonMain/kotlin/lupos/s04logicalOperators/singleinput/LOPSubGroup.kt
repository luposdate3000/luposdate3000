package lupos.s04logicalOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPSubGroup(query: Query, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPSubGroupID, "LOPSubGroup", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5300)
        if (other !is LOPSubGroup) {
Coverage.ifStart(5301)
            return false
        }
Coverage.statementStart(5302)
        for (i in children.indices) {
Coverage.forLoopStart(5303)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5304)
                return false
            }
Coverage.statementStart(5305)
        }
Coverage.statementStart(5306)
        return true
    }
    override fun cloneOP() = LOPSubGroup(query, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5307)
        return children[0].getHistogram()
    }
}
