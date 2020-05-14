package lupos.s08logicalOptimisation

import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerBindUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerBindUpID) {
    override val classname = "LogicalOptimizerBindUp"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
println("LogicalOptimizerBindUp ${node.classname}")
        if (node is LOPBind) {
if(node.children[1] !is AOPConstant){
            val child = node.children[0]
            if (child is LOPBind) {
                if (child.children[1] is AOPConstant) {
                    node.children[0] = child.children[0]
                    child.children[0] = node
                    res = child
                    onChange()
                }
            }
}
        } else if (node is LOPUnion) {
            /*nothing here*/
        } else if (node is LOPMinus) {
            val child = node.children[0]
            if (child is LOPBind && child.children[1] is AOPConstant) {
                node.children[0] = child.children[0]
                child.children[0] = node
                res = child
                onChange()
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
                val child01 = child0.children[1]
                if (child01 is AOPVariable && !node.getProvidedVariableNames().contains(child01.name)) {
                    node.replaceVariableWithAnother(child0.children[0], child01.name, child0.name.name)
                    res = child0.children[0]
                    onChange()
                }
            }
        } else {
            for (i in 0 until node.children.size) {
                val child = node.children[i]
                if (child is LOPBind && child.children[1] is AOPConstant) {
                    node.children[i] = child.children[i]
                    child.children[i] = node
                    res = child
                    onChange()
                    break
                }
            }
        }
/*return*/res
    })
}
