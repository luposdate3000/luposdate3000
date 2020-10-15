package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerReducedDown(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerReducedDownID) {
    override val classname = "LogicalOptimizerReducedDown"
    override suspend fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res: OPBase = node
        if (node is LOPReduced) {
            val child = node.children[0]
            if (child is LOPReduced) {
                res = child
                onChange()
            } else if (!node.hadPushDown) {
                node.hadPushDown = true
                if (child is LOPProjection) {
                    child.children[0] = LOPReduced(query, child.children[0])
                    onChange()
                } else if (child is LOPTriple) {
                    var flag = true
                    for (c in child.children) {
                        if (c is AOPVariable && c.name == "_") {
                            flag = false
                            break
                        }
                    }
                    if (flag) {
//keep the reduced, if_ there is a blank variable in the triple-pattern
                        res = child
                        onChange()
                    }
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
        return res
    }
}
