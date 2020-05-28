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
class LogicalOptimizerMinusAddSort(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerMinusAddSortID) {
    override val classname = "LogicalOptimizerMinusAddSort"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9485)
        var res: OPBase = node
Coverage.statementStart(9486)
        if (node is LOPMinus) {
Coverage.ifStart(9487)
            if (!node.hadSortPushDown) {
Coverage.ifStart(9488)
                node.hadSortPushDown = true
Coverage.statementStart(9489)
                val provided = node.children[0].getProvidedVariableNames().intersect(node.children[1].getProvidedVariableNames())
Coverage.statementStart(9490)
                node.children[1] = LOPReduced(query, LOPSortAny(query, provided.map { SortHelper(it, ESortType.FAST) }, LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), node.children[1])))
Coverage.statementStart(9491)
                node.children[0] = LOPSortAny(query, provided.map { SortHelper(it, ESortType.FAST) }, LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), node.children[0]))
Coverage.statementStart(9492)
                onChange()
Coverage.statementStart(9493)
            }
Coverage.statementStart(9494)
        }
Coverage.statementStart(9495)
        return res
    }
}
