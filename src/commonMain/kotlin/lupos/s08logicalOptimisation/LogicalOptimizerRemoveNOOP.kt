package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerRemoveNOOP(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerRemoveNOOPID) {
    override val classname = "LogicalOptimizerRemoveNOOP"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
        if (node is LOPNOOP || node is LOPSubGroup) {
            onChange()
            res = node.children[0]
        }
/*return*/res
    })
}
