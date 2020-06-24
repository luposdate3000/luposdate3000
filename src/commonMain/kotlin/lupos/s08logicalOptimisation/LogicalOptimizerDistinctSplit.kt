package lupos.s08logicalOptimisation
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
import lupos.s04logicalOperators.multiinput.LOPJoin

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ESortType
import lupos.s00misc.SortHelper
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerDistinctSplit(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerDistinctSplitID) {
    override val classname = "LogicalOptimizerDistinctSplit"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res: OPBase = node
        if (node is LOPDistinct) {
            val child = node.children[0]
            val provided = child.getProvidedVariableNames().toMutableList()
            if (provided.size == 0) {
                //no variables -> no sort required
                res = LOPReduced(query, child)
                onChange()
            } else {
                if (child.mySortPriority.size == provided.size) {
                    res = LOPReduced(query, LOPSortAny(query, child.mySortPriority, child))
                    onChange()
                } else {
                    if (child is LOPJoin) {
                        val columns = LOPJoin.getColumns(child.children[0].getProvidedVariableNames(), child.children[1].getProvidedVariableNames())
                        val variables = mutableListOf<String>()
                        variables.addAll(columns[0])
                        variables.addAll(columns[1])
                        variables.addAll(columns[2])
                        res = LOPReduced(query, LOPSortAny(query, variables.map { SortHelper(it, ESortType.FAST) }, child))
                        onChange()
                    } else {
                        res = LOPReduced(query, LOPSortAny(query, provided.map { SortHelper(it, ESortType.FAST) }, child))
                        onChange()
                    }
                }
            }
        }
        return res
    }
}
