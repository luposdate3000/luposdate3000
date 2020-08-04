package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerFilterUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterUpID) {
    override val classname = "LogicalOptimizerFilterUp"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res: OPBase = node
        if (node !is LOPFilter && node !is LOPMinus && node !is LOPUnion && (node !is LOPJoin || !node.optional)) {
            for (idx in 0 until node.children.size) {
                val child = node.children[idx]
                if (child is LOPFilter && node.getProvidedVariableNames().containsAll(child.children[1].getRequiredVariableNamesRecoursive())) {
                    res = child
                    node.children[idx] = res.children[0]
                    res.children[0] = node
                    onChange()
                    break
                }
            }
        }
        return res
    }
}
