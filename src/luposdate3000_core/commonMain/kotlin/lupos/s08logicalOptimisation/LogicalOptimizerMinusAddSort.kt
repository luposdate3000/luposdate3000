package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.ESortType
import lupos.s00misc.SortHelper
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerMinusAddSort(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerMinusAddSortID) {
    override val classname = "LogicalOptimizerMinusAddSort"
    override suspend fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res: OPBase = node
        if (node is LOPMinus) {
            if (!node.hadSortPushDown) {
                node.hadSortPushDown = true
                val provided = node.children[0].getProvidedVariableNames().intersect(node.children[1].getProvidedVariableNames())
                node.children[1] = LOPReduced(query, LOPSortAny(query, provided.map { SortHelper(it, ESortType.FAST) }, LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), node.children[1])))
                node.children[0] = LOPSortAny(query, provided.map { SortHelper(it, ESortType.FAST) }, LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), node.children[0]))
                onChange()
            }
        }
        return res
    }
}
