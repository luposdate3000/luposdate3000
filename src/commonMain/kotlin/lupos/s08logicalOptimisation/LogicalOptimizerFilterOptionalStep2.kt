package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPOptional

class LogicalOptimizerFilterOptionalStep2(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterOptionalStep2ID) {
    override val classname = "LogicalOptimizerFilterOptionalStep2"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
query.filtersMovedUpFromOptionals=true
        return node
    }
}
