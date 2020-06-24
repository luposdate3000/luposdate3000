package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter

class LogicalOptimizerFilterMergeAND(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterMergeANDID) {
    override val classname = "LogicalOptimizerFilterMergeAND"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res: OPBase = node
        if (node is LOPFilter) {
            val child = node.children[0]
            if (child is LOPFilter) {
                res = LOPFilter(query, AOPAnd(query, node.children[1] as AOPBase, child.children[1] as AOPBase), child.children[0])
                onChange()
            }
        }
        return res
    }
}
