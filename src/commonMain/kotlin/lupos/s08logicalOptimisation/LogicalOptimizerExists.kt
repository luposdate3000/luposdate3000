package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerExists(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerExistsID) {
    override val classname = "LogicalOptimizerExists"
    fun applyRecoursive(node: OPBase) {
        if (node !is LOPLimit && node !is LOPOffset) {
            node.onlyExistenceRequired = true
            for (c in node.children) {
                applyRecoursive(c)
            }
        }
    }

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
        if (node is LOPMakeBooleanResult) {
            if (!node.onlyExistenceRequired) {
                applyRecoursive(node)
                onChange()
            }
        }
/*return*/res
    })
}
