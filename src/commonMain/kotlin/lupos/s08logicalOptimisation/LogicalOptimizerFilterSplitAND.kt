package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
class LogicalOptimizerFilterSplitAND(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterSplitANDID) {
    override val classname = "LogicalOptimizerFilterSplitAND"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9126)
        var res: OPBase = node
Coverage.statementStart(9127)
        if (node is LOPFilter) {
Coverage.ifStart(9128)
            val child = node.children[0]
Coverage.statementStart(9129)
            val aopcompare = node.children[1]
Coverage.statementStart(9130)
            if (aopcompare is AOPAnd) {
Coverage.ifStart(9131)
                onChange()
Coverage.statementStart(9132)
                res = LOPFilter(query, aopcompare.children[0] as AOPBase, LOPFilter(query, aopcompare.children[1] as AOPBase, child))
Coverage.statementStart(9133)
            }
Coverage.statementStart(9134)
        }
Coverage.statementStart(9135)
        return res
    }
}
