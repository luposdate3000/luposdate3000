package lupos.s04logicalOperators.multiinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPMinus(query: Query, first: OPBase, second: OPBase, var tmpFakeVariables: List<String>) : LOPBase(query, EOperatorID.LOPMinusID, "LOPMinus", arrayOf(first, second), ESortPriority.MINUS) {
    var hadReducedPushDown = false
    override fun getProvidedVariableNames() = (children[0].getProvidedVariableNames() + tmpFakeVariables).distinct()
    override fun getRequiredVariableNames() = listOf<String>()
    override fun equals(other: Any?): Boolean {
        if (other !is LOPMinus) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun cloneOP() = LOPMinus(query, children[0].cloneOP(), children[1].cloneOP(), tmpFakeVariables.toMutableList())
}
