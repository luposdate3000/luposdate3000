package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerIDExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPUnion
public class LogicalOptimizerUnionUp(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerUnionUpID) {
    override val classname: String = "LogicalOptimizerUnionUp"
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
