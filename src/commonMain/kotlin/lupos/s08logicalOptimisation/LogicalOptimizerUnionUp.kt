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
Coverage.funStart(9954)
        var res: OPBase = node
Coverage.statementStart(9955)
        if (node is LOPJoin) {
Coverage.ifStart(9956)
            val childA = node.children[0]
Coverage.statementStart(9957)
            val childB = node.children[1]
Coverage.statementStart(9958)
            if (childA is LOPUnion) {
Coverage.ifStart(9959)
                res = LOPUnion(query, LOPJoin(query, childA.children[0], childB, node.optional), LOPJoin(query, childA.children[1], childB.cloneOP(), node.optional))
Coverage.statementStart(9960)
            } else if (childB is LOPUnion) {
Coverage.ifStart(9961)
                res = LOPUnion(query, LOPJoin(query, childA, childB.children[0], node.optional), LOPJoin(query, childA.cloneOP(), childB.children[1], node.optional))
Coverage.statementStart(9962)
            }
Coverage.statementStart(9963)
        }
Coverage.statementStart(9964)
        return res
    }
}
