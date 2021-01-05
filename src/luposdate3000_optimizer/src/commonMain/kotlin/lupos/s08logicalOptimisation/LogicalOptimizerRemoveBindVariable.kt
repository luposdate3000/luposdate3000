package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPProjection
public class LogicalOptimizerRemoveBindVariable(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerRemoveBindVariableID) {
    override val classname: String = "LogicalOptimizerRemoveBindVariable"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        if (node is LOPProjection) {
            val child = node.getChildren()[0]
            if (child is LOPBind) {
                val exp = child.getChildren()[1]
                if (exp is AOPVariable) {
                    val provided = node.getProvidedVariableNames()
                    if (!provided.contains(exp.name)) {
                        node.replaceVariableWithAnother(child.getChildren()[0], exp.name, child.name.name, child, 0)
                        node.getChildren()[0] = child.getChildren()[0]
                        onChange()
                    }
                }
            }
        }
        return node
    }
}
