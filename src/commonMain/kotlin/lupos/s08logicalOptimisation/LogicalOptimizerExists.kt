package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s08logicalOptimisation.OptimizerBase
class LogicalOptimizerExists(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerExistsID) {
    override val classname = "LogicalOptimizerExists"
    fun applyRecoursive(node: OPBase, askFlag: Boolean) {
Coverage.funStart(8968)
        if (node !is LOPLimit && node !is LOPOffset) {
Coverage.ifStart(8969)
            if (askFlag) {
Coverage.ifStart(8970)
                node.partOfAskQuery = true
Coverage.statementStart(8971)
            }
Coverage.statementStart(8972)
            node.onlyExistenceRequired = true
Coverage.statementStart(8973)
            if (node is LOPMinus) {
Coverage.ifStart(8974)
                applyRecoursive(node.children[0], askFlag)
Coverage.statementStart(8975)
            } else {
Coverage.ifStart(8976)
                for (c in node.children) {
Coverage.forLoopStart(8977)
                    applyRecoursive(c, askFlag)
Coverage.statementStart(8978)
                }
Coverage.statementStart(8979)
            }
Coverage.statementStart(8980)
        }
Coverage.statementStart(8981)
    }
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(8982)
        var res = node
Coverage.statementStart(8983)
        if (node is LOPMakeBooleanResult) {
Coverage.ifStart(8984)
            if (!node.partOfAskQuery) {
Coverage.ifStart(8985)
                applyRecoursive(node, true)
Coverage.statementStart(8986)
                onChange()
Coverage.statementStart(8987)
            }
Coverage.statementStart(8988)
        } else if (node is LOPDistinct || node is LOPReduced) {
Coverage.ifStart(8989)
            if (!node.onlyExistenceRequired) {
Coverage.ifStart(8990)
                applyRecoursive(node, false)
Coverage.statementStart(8991)
                onChange()
Coverage.statementStart(8992)
            }
Coverage.statementStart(8993)
        }
Coverage.statementStart(8994)
        return res
    }
}
