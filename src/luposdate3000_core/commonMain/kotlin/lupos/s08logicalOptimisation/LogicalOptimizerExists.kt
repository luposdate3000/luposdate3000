package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerExists(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerExistsID) {
    override val classname = "LogicalOptimizerExists"
    fun applyRecoursive(node: IOPBase, askFlag: Boolean) {
        if (node !is LOPLimit && node !is LOPOffset) {
            if (askFlag) {
                node.partOfAskQuery = true
            }
            node.onlyExistenceRequired = true
            if (node is LOPMinus) {
                applyRecoursive(node.getChildren()[0], askFlag)
            } else {
                for (c in node.getChildren()) {
                    applyRecoursive(c, askFlag)
                }
            }
        }
    }

    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if (node is LOPMakeBooleanResult) {
            if (!node.partOfAskQuery) {
                applyRecoursive(node, true)
                onChange()
            }
        } else if (node is LOPDistinct || node is LOPReduced) {
            if (!node.onlyExistenceRequired) {
                applyRecoursive(node, false)
                onChange()
            }
        }
        return res
    }
}
