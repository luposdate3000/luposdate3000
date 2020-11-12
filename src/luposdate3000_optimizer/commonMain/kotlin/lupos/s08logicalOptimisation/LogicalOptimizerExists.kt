package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced

class LogicalOptimizerExists(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerExistsID) {
    override val classname = "LogicalOptimizerExists"
    private fun applyRecoursive(node: IOPBase, askFlag: Boolean) {
        if (node !is LOPLimit && node !is LOPOffset) {
            if (askFlag) {
                node.setPartOfAskQuery(true)
            }
            node.setOnlyExistenceRequired(true)
            if (node is LOPMinus) {
                applyRecoursive(node.getChildren()[0], askFlag)
            } else {
                for (c in node.getChildren()) {
                    applyRecoursive(c, askFlag)
                }
            }
        }
    }

    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        if (node is LOPMakeBooleanResult) {
            if (!node.partOfAskQuery) {
                applyRecoursive(node, true)
                onChange()
            }
        } else if (node is LOPDistinct || node is LOPReduced) {
            if (!node.getOnlyExistenceRequired()) {
                applyRecoursive(node, false)
                onChange()
            }
        }
        return node
    }
}
