package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerRemovePrefix(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerRemovePrefixID) {
    override val classname = "LogicalOptimizerRemovePrefix"
    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if (node is LOPPrefix) {
            node.children[0].applyPrefix(node.name, node.iri)
            res = node.children[0]
            onChange()
        }
        return res
    }
}
