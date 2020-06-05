package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerUnionUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerUnionUpID) {
    override val classname = "LogicalOptimizerUnionUp"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res: OPBase = node
        if (node is LOPJoin) {
            val childA = node.children[0]
            val childB = node.children[1]
            if (childA is LOPUnion) {
                res = LOPUnion(query, LOPJoin(query, childA.children[0], childB, node.optional), LOPJoin(query, childA.children[1], childB.cloneOP(), node.optional))
		onChange()
            } else if (childB is LOPUnion) {
                res = LOPUnion(query, LOPJoin(query, childA, childB.children[0], node.optional), LOPJoin(query, childA.cloneOP(), childB.children[1], node.optional))
		onChange()
            }
        }
        return res
    }
}
