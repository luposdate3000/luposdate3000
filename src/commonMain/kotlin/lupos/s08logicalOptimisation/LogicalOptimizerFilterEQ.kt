package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerFilterEQ(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterEQID) {
    override val classname = "LogicalOptimizerFilterEQ"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPFilter) {
            val filter = node.children[1]
            if (filter is AOPEQ) {
                val v1 = filter.children[0]
                val v2 = filter.children[1]
                if (v1 is AOPVariable && v2 is AOPVariable) {
                    node.replaceVariableWithAnother(node, v1.name, v2.name)
                    res = LOPBind(query, v1, v2, node.children[0])
                    onChange()
                }
            }
        }
/*return*/res
    })
}
