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
class LogicalOptimizerDistinctSplit(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerDistinctSplitID) {
    override val classname = "LogicalOptimizerDistinctSplit"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(8922)
        var res: OPBase = node
Coverage.statementStart(8923)
        if (node is LOPDistinct) {
Coverage.ifStart(8924)
            val child = node.children[0]
Coverage.statementStart(8925)
            val provided = child.getProvidedVariableNames().toMutableList()
Coverage.statementStart(8926)
            if (provided.size == 0) {
Coverage.ifStart(8927)
                //no variables -> no sort required
Coverage.statementStart(8928)
                res = LOPReduced(query, child)
Coverage.statementStart(8929)
                onChange()
Coverage.statementStart(8930)
            } else {
Coverage.ifStart(8931)
                if (child.mySortPriority.size == provided.size) {
Coverage.ifStart(8932)
                    res = LOPReduced(query, LOPSortAny(query, child.mySortPriority, child))
Coverage.statementStart(8933)
                    onChange()
Coverage.statementStart(8934)
                } else {
Coverage.ifStart(8935)
                    if (child is LOPJoin) {
Coverage.ifStart(8936)
                        val columns = LOPJoin.getColumns(child.children[0].getProvidedVariableNames(), child.children[1].getProvidedVariableNames())
Coverage.statementStart(8937)
                        val variables = mutableListOf<String>()
Coverage.statementStart(8938)
                        variables.addAll(columns[0])
Coverage.statementStart(8939)
                        variables.addAll(columns[1])
Coverage.statementStart(8940)
                        variables.addAll(columns[2])
Coverage.statementStart(8941)
                        res = LOPReduced(query, LOPSortAny(query, variables.map { SortHelper(it, ESortType.FAST) }, child))
Coverage.statementStart(8942)
                        onChange()
Coverage.statementStart(8943)
                    } else {
Coverage.ifStart(8944)
                        res = LOPReduced(query, LOPSortAny(query, provided.map { SortHelper(it, ESortType.FAST) }, child))
Coverage.statementStart(8945)
                        onChange()
Coverage.statementStart(8946)
                    }
Coverage.statementStart(8947)
                }
Coverage.statementStart(8948)
            }
Coverage.statementStart(8949)
        }
Coverage.statementStart(8950)
        return res
    }
}
