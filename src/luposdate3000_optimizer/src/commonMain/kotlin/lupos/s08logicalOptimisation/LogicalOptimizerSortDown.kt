package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
public class LogicalOptimizerSortDown(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerSortDownID) {
    override val classname: String = "LogicalOptimizerSortDown"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPSortAny) {
            val child = node.getChildren()[0]
            if (child is LOPFilter) {
                child.getChildren()[0] = LOPSortAny(query, node.possibleSortOrder, child.getChildren()[0])
                res = child
                onChange()
            } else if (child is LOPSortAny || child is LOPSort) {
                node.getChildren()[0] = child.getChildren()[0]
                onChange()
            } else if (child is LOPReduced) {
                node.getChildren()[0] = child.getChildren()[0]
                res = LOPReduced(query, node)
            }
        }
        return res
    }
}
