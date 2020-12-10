package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPProjection
class LogicalOptimizerRemoveProjection(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerRemoveProjectionID) {
    override val classname: String = "LogicalOptimizerRemoveProjection"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPProjection) {
            val child = node.getChildren()[0]
            val projection = node.getProvidedVariableNames()
            if (projection.containsAll(child.getProvidedVariableNames())) {
                onChange()
                res = child
            } else if (child is LOPTriple) {
                for (i in 0 until 3) {
                    val cc = child.getChildren()[i]
                    if (cc is AOPVariable && !projection.contains(cc.name)) {
                        child.getChildren()[i] = AOPVariable(query, "_")
                        onChange()
                    }
                }
            } else if (child is LOPBind) {
                if (!projection.contains(child.name.name)) {
                    res.getChildren()[0] = child.getChildren()[0]
                    onChange()
                }
            }
        }
        return res
    }
}
