package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
class LogicalOptimizerFilterMergeAND(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterMergeANDID) {
    override val classname = "LogicalOptimizerFilterMergeAND"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9117)
        var res: OPBase = node
Coverage.statementStart(9118)
        if (node is LOPFilter) {
Coverage.ifStart(9119)
            val child = node.children[0]
Coverage.statementStart(9120)
            if (child is LOPFilter) {
Coverage.ifStart(9121)
                res = LOPFilter(query, AOPAnd(query, node.children[1] as AOPBase, child.children[1] as AOPBase), child.children[0])
Coverage.statementStart(9122)
                onChange()
Coverage.statementStart(9123)
            }
Coverage.statementStart(9124)
        }
Coverage.statementStart(9125)
        return res
    }
}
