package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerIDExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
public class LogicalOptimizerRemovePrefix(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerRemovePrefixID) {
    override val classname: String = "LogicalOptimizerRemovePrefix"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if (node is LOPPrefix) {
            node.getChildren()[0].applyPrefix(node.name, node.iri)
            res = node.getChildren()[0]
            onChange()
        }
        return res
    }
}
