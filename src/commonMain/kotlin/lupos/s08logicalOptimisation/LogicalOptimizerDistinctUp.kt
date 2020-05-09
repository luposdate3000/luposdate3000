package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerDistinctUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerDistinctUpID) {
    override val classname = "LogicalOptimizerDistinctUp"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPDistinct) {
            if (node.children[0] is LOPDistinct) {
                res = node.children[0]
                onChange()
            }
        } else if (node !is LOPUnion) {
            for (i in node.children.indices) {
                val c = node.children[i]
                if (c is LOPDistinct && c.getProvidedVariableNames().containsAll(node.getProvidedVariableNames())) {
                    node.children[i] = c.children[0]
                    res = LOPDistinct(query, node)
                    onChange()
                    break
                }
            }
        }
/*return*/res
    })
}
