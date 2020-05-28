package lupos.s04logicalOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPMinus(query: Query, first: OPBase, second: OPBase, var tmpFakeVariables: List<String>) : LOPBase(query, EOperatorID.LOPMinusID, "LOPMinus", arrayOf(first, second), ESortPriority.MINUS) {
    var hadReducedPushDown = false
    var hadSortPushDown = false
    override fun getProvidedVariableNames() = (children[0].getProvidedVariableNames() + tmpFakeVariables).distinct()
    override fun getRequiredVariableNames() = listOf<String>()
    override fun equals(other: Any?): Boolean {
Coverage.funStart(4439)
        if (other !is LOPMinus) {
Coverage.ifStart(4440)
            return false
        }
Coverage.statementStart(4441)
        for (i in children.indices) {
Coverage.forLoopStart(4442)
            if (children[i] != other.children[i]) {
Coverage.ifStart(4443)
                return false
            }
Coverage.statementStart(4444)
        }
Coverage.statementStart(4445)
        return true
    }
    override fun cloneOP() = LOPMinus(query, children[0].cloneOP(), children[1].cloneOP(), tmpFakeVariables.toMutableList())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(4446)
        return children[0].getHistogram()
    }
}
