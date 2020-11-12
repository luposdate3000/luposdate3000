package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny

class LogicalOptimizerReducedDown(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerReducedDownID) {
    override val classname = "LogicalOptimizerReducedDown"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPReduced) {
            val child = node.getChildren()[0]
            if (child is LOPReduced) {
                res = child
                onChange()
            } else if (!node.hadPushDown) {
                node.hadPushDown = true
                if (child is LOPProjection) {
                    child.getChildren()[0] = LOPReduced(query, child.getChildren()[0])
                    onChange()
                } else if (child is LOPTriple) {
                    var flag = true
                    for (c in child.getChildren()) {
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
                    child.getChildren()[0] = LOPReduced(query, child.getChildren()[0])
                    child.getChildren()[1] = LOPReduced(query, child.getChildren()[1])
                    res = child
                    onChange()
                } else if (child is LOPUnion) {
                    child.getChildren()[0] = LOPReduced(query, child.getChildren()[0])
                    child.getChildren()[1] = LOPReduced(query, child.getChildren()[1])
                    onChange()
                } else if (child is LOPMinus) {
                    child.getChildren()[0] = LOPReduced(query, child.getChildren()[0])
                    res = child
                    onChange()
                } else if (child is LOPFilter) {
                    child.getChildren()[0] = LOPReduced(query, child.getChildren()[0])
                    res = child
                    onChange()
                } else if (child is LOPSortAny) {
                    child.getChildren()[0] = LOPReduced(query, child.getChildren()[0])
                    onChange()
                }
            }
        }
        return res
    }
}
