package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

import lupos.s04logicalOperators.singleinput.*
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerUnionUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerUnionUpID) {
    override val classname = "LogicalOptimizerUnionUp"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPJoin) {
            val childA = node.children[0]
            val childB = node.children[1]
            if (childA is LOPUnion)
                res = LOPUnion(query, LOPJoin(query, childA.children[0], childB, node.optional), LOPJoin(query, childA.children[1], childB.cloneOP(), node.optional))
            else if (childB is LOPUnion)
                res = LOPUnion(query, LOPJoin(query, childA, childB.children[0], node.optional), LOPJoin(query, childA.cloneOP(), childB.children[1], node.optional))
        }
        res
    })
}
