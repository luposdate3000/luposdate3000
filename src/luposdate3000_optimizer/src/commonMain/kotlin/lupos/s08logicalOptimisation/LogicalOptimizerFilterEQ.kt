package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPProjection
class LogicalOptimizerFilterEQ(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterEQID) {
    override val classname: String = "LogicalOptimizerFilterEQ"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPFilter) {
            val filter = node.getChildren()[1]
            if (filter is AOPEQ) {
                val v1 = filter.getChildren()[0]
                val v2 = filter.getChildren()[1]
                if (v1 is AOPVariable && v2 is AOPVariable) {
                    val child = node.getChildren()[0]
                    if (child !is LOPTriple) {
                        /* child may only be a triple, if_ both variables are from the same triple - which leads to errors if_ those are inlined */
                        res = if (parent != null) {
                            if (parent is LOPProjection && parent.variables.map { it.name }.contains(v1.name)) {
                                node.replaceVariableWithAnother(node.getChildren()[0], v2.name, v1.name, node, 0)
                                LOPBind(query, v2, v1, node.getChildren()[0])
                            } else {
                                node.replaceVariableWithAnother(node.getChildren()[0], v1.name, v2.name, node, 0)
                                LOPBind(query, v1, v2, node.getChildren()[0])
                            }
                        } else {
                            node.replaceVariableWithAnother(node.getChildren()[0], v1.name, v2.name, node, 0)
                            LOPBind(query, v1, v2, node.getChildren()[0])
                        }
                        onChange()
                    }
                }
            }
        }
        return res
    }
}
