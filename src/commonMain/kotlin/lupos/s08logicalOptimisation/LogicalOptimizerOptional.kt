package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPOptional

class LogicalOptimizerOptional(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerOptionalID) {
    override val classname = "LogicalOptimizerOptional"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPOptional) {
            res = LOPJoin(query, OPNothing(query), node.children[0], true)
        }
/*return*/res
    })
}
