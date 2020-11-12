package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset

class LogicalOptimizerBindUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerBindUpID) {
    override val classname = "LogicalOptimizerBindUp"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPBind) {
            if (node.getChildren()[1] !is AOPConstant) {
                val child = node.getChildren()[0]
                if (child is LOPBind) {
                    if (child.getChildren()[1] is AOPConstant) {
                        if (!node.getChildren()[1].getRequiredVariableNamesRecoursive().contains(child.name.name)) {
                            node.getChildren()[0] = child.getChildren()[0]
                            child.getChildren()[0] = node
                            res = child
                            onChange()
                        }
                    }
                }
            }
        } else if (node is LOPFilter) {
            val child0 = node.getChildren()[0]
            if (child0 is LOPBind) {
                val child01 = child0.getChildren()[1]
                if (child01 is AOPConstant) {
                    node.replaceVariableWithConstant(node.getChildren()[1], child0.name.name, child01.value)
                    node.getChildren()[0] = child0.getChildren()[0]
                    child0.getChildren()[0] = node
                    res = child0
                    onChange()
                }
            }
        } else if (node is LOPProjection) {
            val child0 = node.getChildren()[0]
            if (child0 is LOPBind) {
                val variables = node.variables.map { it.name }.toMutableList()
                if (variables.contains(child0.name.name) && variables.containsAll(child0.getRequiredVariableNames())) {
                    variables.remove(child0.name.name)
                    child0.getChildren()[0] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), child0.getChildren()[0])
                    res = child0
                    onChange()
                }
            }
        } else if (node is LOPMinus) {
            val child = node.getChildren()[0]
            if (child is LOPBind && child.getChildren()[1] is AOPConstant) {
                node.getChildren()[0] = child.getChildren()[0]
                child.getChildren()[0] = node
                res = child
                onChange()
            }
        } else if (node is LOPLimit || node is LOPOffset || node is LOPJoin) {
            for (i in 0 until node.getChildren().size) {
                val child = node.getChildren()[i]
                if (child is LOPBind && child.getChildren()[1] is AOPConstant) {
                    node.getChildren()[i] = child.getChildren()[0]
                    child.getChildren()[0] = node
                    res = child
                    onChange()
                    break
                }
            }
        }
        return res
    }
}
