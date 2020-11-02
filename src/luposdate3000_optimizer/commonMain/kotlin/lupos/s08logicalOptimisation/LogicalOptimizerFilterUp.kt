package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerFilterUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterUpID) {
    override val classname = "LogicalOptimizerFilterUp"
    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node !is LOPFilter && node !is LOPMinus && node !is LOPUnion && (node !is LOPJoin || !node.optional)) {
            for (idx in 0 until node.getChildren().size) {
                val child = node.getChildren()[idx]
                if (child is LOPFilter && node.getProvidedVariableNames().containsAll(child.getChildren()[1].getRequiredVariableNamesRecoursive())) {
                    res = child
                    node.getChildren()[idx] = res.getChildren()[0]
                    res.getChildren()[0] = node
                    onChange()
                    break
                }
            }
        }
        return res
    }
}
