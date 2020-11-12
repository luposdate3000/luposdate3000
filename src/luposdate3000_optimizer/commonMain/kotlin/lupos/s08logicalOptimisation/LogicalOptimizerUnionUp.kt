package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.Query

class LogicalOptimizerUnionUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerUnionUpID) {
    override val classname = "LogicalOptimizerUnionUp"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPJoin && !node.optional) {
            val childA = node.getChildren()[0]
            val childB = node.getChildren()[1]
            if (childA is LOPUnion) {
                res = LOPUnion(query, LOPJoin(query, childA.getChildren()[0], childB, node.optional), LOPJoin(query, childA.getChildren()[1], childB.cloneOP(), node.optional))
                onChange()
            } else if (childB is LOPUnion) {
                res = LOPUnion(query, LOPJoin(query, childA, childB.getChildren()[0], node.optional), LOPJoin(query, childA.cloneOP(), childB.getChildren()[1], node.optional))
                onChange()
            }
        }
        return res
    }
}
