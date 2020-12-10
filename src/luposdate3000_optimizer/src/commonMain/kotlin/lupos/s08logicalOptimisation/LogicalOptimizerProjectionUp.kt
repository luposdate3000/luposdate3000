package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced

class LogicalOptimizerProjectionUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerProjectionUpID) {
    override val classname: String = "LogicalOptimizerProjectionUp"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node !is LOPProjection && node !is OPBaseCompound && node !is LOPUnion && node !is LOPMinus && node !is LOPReduced && node !is LOPDistinct) {
            for (i in node.getChildren().indices) {
                val child = node.getChildren()[i]
                if (child is LOPProjection) {
                    res = LOPProjection(query, node.getProvidedVariableNames().map { AOPVariable(query, it) }.toMutableList(), node)
                    node.getChildren()[i] = child.getChildren()[0]
                    onChange()
                    break
                }
            }
        }
        return res
    }
}
