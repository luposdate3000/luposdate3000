package lupos.s04logicalOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPNOOP(query: Query, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPNOOPID, "LOPNOOP", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5210)
        if (other !is LOPNOOP) {
Coverage.ifStart(5211)
            return false
        }
Coverage.statementStart(5212)
        for (i in children.indices) {
Coverage.forLoopStart(5213)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5214)
                return false
            }
Coverage.statementStart(5215)
        }
Coverage.statementStart(5216)
        return true
    }
    override fun cloneOP() = LOPNOOP(query, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5217)
        return children[0].getHistogram()
    }
}
