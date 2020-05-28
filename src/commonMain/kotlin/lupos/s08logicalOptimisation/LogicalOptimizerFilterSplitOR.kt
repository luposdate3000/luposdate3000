package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
class LogicalOptimizerFilterSplitOR(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterSplitORID) {
    override val classname = "LogicalOptimizerFilterSplitOR"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9136)
        var res: OPBase = node
Coverage.statementStart(9137)
        if (node is LOPFilter) {
Coverage.ifStart(9138)
            val child = node.children[0]
Coverage.statementStart(9139)
            val aopcompare = node.children[1]
Coverage.statementStart(9140)
            if (aopcompare is AOPOr && aopcompare.children[0] is AOPEQ && aopcompare.children[1] is AOPEQ) {
Coverage.ifStart(9141)
                onChange()
Coverage.statementStart(9142)
                SanityCheck.checkUnreachable()/*TODO check this - never called so far*/
Coverage.statementStart(9143)
                res = LOPUnion(query, LOPFilter(query, aopcompare.children[0] as AOPBase, child.cloneOP()), LOPFilter(query, aopcompare.children[1] as AOPBase, child.cloneOP()))
Coverage.statementStart(9144)
            }
Coverage.statementStart(9145)
        }
Coverage.statementStart(9146)
        return res
    }
}
