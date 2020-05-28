package lupos.s04logicalOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPOptional(query: Query, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPOptionalID, "LOPOptional", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5218)
        if (other !is LOPOptional) {
Coverage.ifStart(5219)
            return false
        }
Coverage.statementStart(5220)
        for (i in children.indices) {
Coverage.forLoopStart(5221)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5222)
                return false
            }
Coverage.statementStart(5223)
        }
Coverage.statementStart(5224)
        return true
    }
    override fun cloneOP() = LOPOptional(query, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5225)
        return children[0].getHistogram()
    }
}
