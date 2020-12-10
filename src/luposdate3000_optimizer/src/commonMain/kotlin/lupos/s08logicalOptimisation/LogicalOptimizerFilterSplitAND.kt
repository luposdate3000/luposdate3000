package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
class LogicalOptimizerFilterSplitAND(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterSplitANDID) {
    override val classname: String = "LogicalOptimizerFilterSplitAND"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPFilter && node.dontSplitFilter == 0) {
            val child = node.getChildren()[0]
            val aopcompare = node.getChildren()[1]
            if (aopcompare is AOPAnd) {
                onChange()
                res = LOPFilter(query, aopcompare.getChildren()[0] as AOPBase, LOPFilter(query, aopcompare.getChildren()[1] as AOPBase, child))
            }
        }
        return res
    }
}
