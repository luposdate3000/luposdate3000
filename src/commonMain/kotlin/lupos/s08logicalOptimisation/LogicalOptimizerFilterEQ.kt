package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerFilterEQ(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterEQID) {
    override val classname = "LogicalOptimizerFilterEQ"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res: OPBase = node
        if (node is LOPFilter) {
            val filter = node.children[1]
            if (filter is AOPEQ) {
                val v1 = filter.children[0]
                val v2 = filter.children[1]
                if (v1 is AOPVariable && v2 is AOPVariable) {
                    val child = node.children[0]
                    if (child !is LOPTriple) {
                        /* child may only be a triple, if_ both variables are from the same triple - which leads to errors if_ those are inlined */
                        if (parent != null) {
                            if (parent is LOPProjection && parent.variables.map { it.name }.contains(v1.name)) {
                                node.replaceVariableWithAnother(node.children[0], v2.name, v1.name, node, 0)
                                res = LOPBind(query, v2, v1, node.children[0])
                            } else {
                                node.replaceVariableWithAnother(node.children[0], v1.name, v2.name, node, 0)
                                res = LOPBind(query, v1, v2, node.children[0])
                            }
                        } else {
                            node.replaceVariableWithAnother(node.children[0], v1.name, v2.name, node, 0)
                            res = LOPBind(query, v1, v2, node.children[0])
                        }
                        onChange()
                    }
                }
            }
        }
        return res
    }
}
