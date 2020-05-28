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
class LogicalOptimizerReducedDown(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerReducedDownID) {
    override val classname = "LogicalOptimizerReducedDown"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9716)
        var res: OPBase = node
Coverage.statementStart(9717)
        if (node is LOPReduced) {
Coverage.ifStart(9718)
            val child = node.children[0]
Coverage.statementStart(9719)
            if (child is LOPReduced) {
Coverage.ifStart(9720)
                res = child
Coverage.statementStart(9721)
                onChange()
Coverage.statementStart(9722)
            } else if (!node.hadPushDown) {
Coverage.ifStart(9723)
                node.hadPushDown = true
Coverage.statementStart(9724)
                if (child is LOPProjection) {
Coverage.ifStart(9725)
                    child.children[0] = LOPReduced(query, child.children[0])
Coverage.statementStart(9726)
                    onChange()
Coverage.statementStart(9727)
                } else if (child is LOPTriple) {
Coverage.ifStart(9728)
                    var flag = true
Coverage.statementStart(9729)
                    for (c in child.children) {
Coverage.forLoopStart(9730)
                        if (c is AOPVariable && c.name == "_") {
Coverage.ifStart(9731)
                            flag = false
Coverage.statementStart(9732)
                            break
                        }
Coverage.statementStart(9733)
                    }
Coverage.statementStart(9734)
                    if (flag) {
Coverage.ifStart(9735)
//keep the reduced, if_ there is a blank variable in the triple-pattern
Coverage.statementStart(9736)
                        res = child
Coverage.statementStart(9737)
                        onChange()
Coverage.statementStart(9738)
                    }
Coverage.statementStart(9739)
                } else if (child is LOPJoin) {
Coverage.ifStart(9740)
                    child.children[0] = LOPReduced(query, child.children[0])
Coverage.statementStart(9741)
                    child.children[1] = LOPReduced(query, child.children[1])
Coverage.statementStart(9742)
                    res = child
Coverage.statementStart(9743)
                    onChange()
Coverage.statementStart(9744)
                } else if (child is LOPUnion) {
Coverage.ifStart(9745)
                    child.children[0] = LOPReduced(query, child.children[0])
Coverage.statementStart(9746)
                    child.children[1] = LOPReduced(query, child.children[1])
Coverage.statementStart(9747)
                    onChange()
Coverage.statementStart(9748)
                } else if (child is LOPMinus) {
Coverage.ifStart(9749)
                    child.children[0] = LOPReduced(query, child.children[0])
Coverage.statementStart(9750)
                    res = child
Coverage.statementStart(9751)
                    onChange()
Coverage.statementStart(9752)
                } else if (child is LOPFilter) {
Coverage.ifStart(9753)
                    child.children[0] = LOPReduced(query, child.children[0])
Coverage.statementStart(9754)
                    res = child
Coverage.statementStart(9755)
                    onChange()
Coverage.statementStart(9756)
                } else if (child is LOPSortAny) {
Coverage.ifStart(9757)
                    child.children[0] = LOPReduced(query, child.children[0])
Coverage.statementStart(9758)
                    onChange()
Coverage.statementStart(9759)
                }
Coverage.statementStart(9760)
            }
Coverage.statementStart(9761)
        }
Coverage.statementStart(9762)
        return res
    }
}
