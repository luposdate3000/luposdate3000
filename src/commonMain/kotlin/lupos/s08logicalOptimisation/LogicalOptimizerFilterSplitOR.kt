package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter

class LogicalOptimizerFilterSplitOR(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterSplitORID) {
    override val classname = "LogicalOptimizerFilterSplitOR"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res: OPBase = node
        if (node is LOPFilter && node.dontSplitFilter == 0) {
            val child = node.children[0]
            val aopcompare = node.children[1]
            if (aopcompare is AOPOr) {
                res = LOPUnion(query, LOPFilter(query, aopcompare.children[0] as AOPBase, child), LOPFilter(query, aopcompare.children[1] as AOPBase, child.cloneOP()))
                onChange()
            }
        }
        return res
    }
}
