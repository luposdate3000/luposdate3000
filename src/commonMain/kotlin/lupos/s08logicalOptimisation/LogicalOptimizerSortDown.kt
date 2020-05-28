package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ESortType
import lupos.s00misc.SortHelper
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.OptimizerBase
class LogicalOptimizerSortDown(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerSortDownID) {
    override val classname = "LogicalOptimizerSortDown"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9871)
        var res: OPBase = node
Coverage.statementStart(9872)
        if (node is LOPSortAny) {
Coverage.ifStart(9873)
            val child = node.children[0]
Coverage.statementStart(9874)
            if (child is LOPFilter) {
Coverage.ifStart(9875)
                child.children[0] = LOPSortAny(query, node.possibleSortOrder, child.children[0])
Coverage.statementStart(9876)
                res = child
Coverage.statementStart(9877)
                onChange()
Coverage.statementStart(9878)
            } else if (child is LOPSortAny || child is LOPSort) {
Coverage.ifStart(9879)
                node.children[0] = child.children[0]
Coverage.statementStart(9880)
                onChange()
Coverage.statementStart(9881)
            } else if (child is LOPReduced) {
Coverage.ifStart(9882)
                node.children[0] = child.children[0]
Coverage.statementStart(9883)
                res = LOPReduced(query, node)
Coverage.statementStart(9884)
            }
Coverage.statementStart(9885)
        }
Coverage.statementStart(9886)
        return res
    }
}
