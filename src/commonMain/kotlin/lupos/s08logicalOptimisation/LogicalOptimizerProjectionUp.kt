package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerProjectionUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerProjectionUpID) {
    override val classname = "LogicalOptimizerProjectionUp"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res: OPBase = node
        if (node !is LOPProjection && node !is OPBaseCompound && node !is LOPUnion && node !is LOPMinus && node !is LOPReduced && node !is LOPDistinct) {
            for (i in 0 until node.children.size) {
                val child = node.children[i]
                if (child is LOPProjection) {
                    res = LOPProjection(query, node.getProvidedVariableNames().map { AOPVariable(query, it) }.toMutableList(), node)
                    node.children[i] = child.children[0]
                    onChange()
                    break
                }
            }
        }
        return res
    }
}
