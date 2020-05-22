package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPModify
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerBindUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerBindUpID) {
    override val classname = "LogicalOptimizerBindUp"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPBind) {
            if (node.children[1] !is AOPConstant) {
                val child = node.children[0]
                if (child is LOPBind) {
                    if (child.children[1] is AOPConstant) {
                        if (!node.children[1].getRequiredVariableNamesRecoursive().contains(child.name.name)) {
                            node.children[0] = child.children[0]
                            child.children[0] = node
                            res = child
                            onChange()
                        }
                    }
                }
            }
        } else if (node is LOPFilter) {
            val child0 = node.children[0]
            if (child0 is LOPBind) {
                val child01 = child0.children[1]
                if (child01 is AOPConstant) {
                    node.replaceVariableWithConstant(node.children[1], child0.name.name, child01.value)
                    node.children[0] = child0.children[0]
                    child0.children[0] = node
                    res = child0
                    onChange()
                }
            }
        } else if (node is LOPProjection) {
            val child0 = node.children[0]
            if (child0 is LOPBind) {
                val variables = node.variables.map { it.name }.toMutableList()
                if (variables.contains(child0.name.name) && variables.containsAll(child0.getRequiredVariableNames())) {
                    variables.remove(child0.name.name)
                    child0.children[0] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), child0.children[0])
                    res = child0
                    onChange()
                }
            }
        } else if (node is LOPMinus) {
            val child = node.children[0]
            if (child is LOPBind && child.children[1] is AOPConstant) {
                node.children[0] = child.children[0]
                child.children[0] = node
                res = child
                onChange()
            }
        } else if (node is LOPLimit || node is LOPOffset || node is LOPJoin) {
            for (i in 0 until node.children.size) {
                val child = node.children[i]
                if (child is LOPBind && child.children[1] is AOPConstant) {
                    node.children[i] = child.children[0]
                    child.children[0] = node
                    res = child
                    onChange()
                    break
                }
            }
        }
/*return*/res
    })
}
