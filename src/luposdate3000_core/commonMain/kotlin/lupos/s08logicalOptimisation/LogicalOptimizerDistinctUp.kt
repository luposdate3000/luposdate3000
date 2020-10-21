package lupos.s08logicalOptimisation
import lupos.s04logicalOperators.IOPBase
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerDistinctUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerDistinctUpID) {
    override val classname = "LogicalOptimizerDistinctUp"
    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: OPBase = node
        if (node is LOPDistinct) {
            if (node.children[0] is LOPDistinct) {
                res = node.children[0]
                onChange()
            }
        } else if (node !is LOPUnion && node !is OPBaseCompound && node !is LOPLimit && node !is LOPOffset) {
            for (i in node.children.indices) {
                val c = node.children[i]
                if (c is LOPDistinct && c.getProvidedVariableNames().containsAll(node.getProvidedVariableNames())) {
                    node.children[i] = c.children[0]
                    res = LOPDistinct(query, node)
                    onChange()
                    break
                }
            }
        }
        return res
    }
}
