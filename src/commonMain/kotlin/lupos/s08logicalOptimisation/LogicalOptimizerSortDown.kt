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
        var res: OPBase = node
        if (node is LOPSortAny) {
            val child = node.children[0]
            if (child is LOPFilter) {
                child.children[0] = LOPSortAny(query, node.possibleSortOrder, child.children[0])
                res = child
                onChange()
            } else if (child is LOPSortAny || child is LOPSort) {
                node.children[0] = child.children[0]
                onChange()
            } else if (child is LOPReduced) {
                node.children[0] = child.children[0]
                res = LOPReduced(query, node)
            }
        }
        return res
    }
}
