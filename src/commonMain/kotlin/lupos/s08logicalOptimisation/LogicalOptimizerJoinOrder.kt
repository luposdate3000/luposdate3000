package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerJoinOrder(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerJoinOrderID) {
    override val classname = "LogicalOptimizerJoinOrder"
    fun findAllJoinsInChildren(node: LOPJoin): List<OPBase> {
        val res = mutableListOf<OPBase>()
        for (c in node.children) {
            if (c is LOPJoin && !c.optional) {
                res.addAll(findAllJoinsInChildren(c))
            } else {
                res.add(c)
            }
        }
        return res
    }

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPJoin && !node.optional && (parent !is LOPJoin || parent.optional)) {
            val allChilds = findAllJoinsInChildren(node)
            var result = LogicalOptimizerJoinOrderCostBased(allChilds, node)
            if (result != null && result != res) {
                res = result
                onChange()
            }
        }
        /*return*/ res
    })
}
