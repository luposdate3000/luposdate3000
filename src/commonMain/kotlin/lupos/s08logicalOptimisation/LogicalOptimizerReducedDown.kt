package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ESortType
import lupos.s00misc.ExecuteOptimizer
import lupos.s00misc.SortHelper
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerReducedDown(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerReducedDownID) {
    override val classname = "LogicalOptimizerReducedDown"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPReduced) {
            val child = node.children[0]
            if (child is LOPReduced) {
                res = child
                onChange()
            } else if (child is LOPProjection && child.children[0] is LOPMinus) {
                val child2 = child.children[0]//as LOPMinus
                child2.children[0] = LOPReduced(query, LOPProjection(query, child.variables.toMutableList(), child2.children[0]))
                child2.children[1] = LOPReduced(query, LOPProjection(query, child.variables.toMutableList(), child2.children[1]))
                res = child
                onChange()
            } else if (!node.hadPushDown) {
                node.hadPushDown = true
                if (child is LOPProjection) {
                    if (node.partOfAskQuery) {
                        child.children[0] = LOPReduced(query, child.children[0])
                    } else if (child.variables.size == 1) {
                        child.children[0] = LOPReduced(query, LOPSortAny(query, mutableListOf(SortHelper(child.variables[0].name, ESortType.FAST)), child.children[0]))
                    }
                    onChange()
                } else if (child is LOPTriple) {
                    res = child
                    onChange()
                } else if (child is LOPJoin) {
                    child.children[0] = LOPReduced(query, child.children[0])
                    child.children[1] = LOPReduced(query, child.children[1])
                    res = child
                    onChange()
                } else if (child is LOPUnion) {
                    child.children[0] = LOPReduced(query, child.children[0])
                    child.children[1] = LOPReduced(query, child.children[1])
                    onChange()
                } else if (child is LOPMinus) {
                    child.children[0] = LOPReduced(query, child.children[0])
                    res = child
                    onChange()
                } else if (child is LOPFilter) {
                    child.children[0] = LOPReduced(query, child.children[0])
                    res = child
                    onChange()
                } else if (child is LOPSortAny) {
                    child.children[0] = LOPReduced(query, child.children[0])
                    onChange()
                }
            }
        }
/*return*/res
    })
}
