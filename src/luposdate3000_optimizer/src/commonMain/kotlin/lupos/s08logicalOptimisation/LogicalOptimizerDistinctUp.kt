package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerIDExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
public class LogicalOptimizerDistinctUp(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerDistinctUpID) {
    override val classname: String = "LogicalOptimizerDistinctUp"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPDistinct) {
            if (node.getChildren()[0] is LOPDistinct) {
                res = node.getChildren()[0]
                onChange()
            }
        } else if (node !is LOPUnion && node !is OPBaseCompound && node !is LOPLimit && node !is LOPOffset) {
            for (i in node.getChildren().indices) {
                val c = node.getChildren()[i]
                if (c is LOPDistinct && c.getProvidedVariableNames().containsAll(node.getProvidedVariableNames())) {
                    node.getChildren()[i] = c.getChildren()[0]
                    res = LOPDistinct(query, node)
                    onChange()
                    break
                }
            }
        }
        return res
    }
}
