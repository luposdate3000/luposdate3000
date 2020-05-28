package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s08logicalOptimisation.OptimizerBase
class LogicalOptimizerFilterUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterUpID) {
    override val classname = "LogicalOptimizerFilterUp"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9147)
        var res: OPBase = node
Coverage.statementStart(9148)
        if (node is LOPUnion) {
Coverage.ifStart(9149)
            for (idx in 0 until node.children.size) {
Coverage.forLoopStart(9150)
                val child = node.children[idx]
Coverage.statementStart(9151)
                if (child is LOPFilter && node.children[1 - idx].getProvidedVariableNames().containsAll(child.children[1].getRequiredVariableNamesRecoursive())) {
Coverage.ifStart(9152)
                    res = child
Coverage.statementStart(9153)
                    node.children[idx] = res.children[0]
Coverage.statementStart(9154)
                    res.children[0] = node
Coverage.statementStart(9155)
                    onChange()
Coverage.statementStart(9156)
                    break
                }
Coverage.statementStart(9157)
            }
Coverage.statementStart(9158)
        } else if (node !is LOPFilter && node !is LOPMinus) {
Coverage.ifStart(9159)
            for (idx in 0 until node.children.size) {
Coverage.forLoopStart(9160)
                val child = node.children[idx]
Coverage.statementStart(9161)
                if (child is LOPFilter && node.getProvidedVariableNames().containsAll(child.children[1].getRequiredVariableNamesRecoursive())) {
Coverage.ifStart(9162)
                    res = child
Coverage.statementStart(9163)
                    node.children[idx] = res.children[0]
Coverage.statementStart(9164)
                    res.children[0] = node
Coverage.statementStart(9165)
                    onChange()
Coverage.statementStart(9166)
                    break
                }
Coverage.statementStart(9167)
            }
Coverage.statementStart(9168)
        }
Coverage.statementStart(9169)
        return res
    }
}
