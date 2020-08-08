package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LogicalOptimizerFilterOptionalStep2(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterOptionalStep2ID) {
    override val classname = "LogicalOptimizerFilterOptionalStep2"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        query.filtersMovedUpFromOptionals = true
        node.syntaxVerifyAllVariableExists(listOf(), true)
        return node
    }
}
