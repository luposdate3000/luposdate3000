package lupos.s04logicalOperators.multiinput

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
    override fun equals(other: Any?) = other is LOPMinus && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP() = LOPMinus(query, children[0].cloneOP(), children[1].cloneOP(), tmpFakeVariables.toMutableList())
    override fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
