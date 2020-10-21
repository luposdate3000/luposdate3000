package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter

class LogicalOptimizerFilterSplitAND(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterSplitANDID) {
    override val classname = "LogicalOptimizerFilterSplitAND"
    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: OPBase = node
        if (node is LOPFilter && node.dontSplitFilter == 0) {
            val child = node.children[0]
            val aopcompare = node.children[1]
            if (aopcompare is AOPAnd) {
                onChange()
                res = LOPFilter(query, aopcompare.children[0] as AOPBase, LOPFilter(query, aopcompare.children[1] as AOPBase, child))
            }
        }
        return res
    }
}
