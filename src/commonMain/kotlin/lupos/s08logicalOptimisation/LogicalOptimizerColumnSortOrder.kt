package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LogicalOptimizerColumnSortOrder(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerColumnSortOrderID) {
    override val classname = "LogicalOptimizerColumnSortOrder"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        var hadChange = false
        val done = node.initializeSortPriorities {
            hadChange = true
            onChange()
        }
        if (!hadChange && !done) {
            var maxSize = 0
            for (x in node.sortPriorities) {
                if (x.size > maxSize) {
                    maxSize = x.size
                }
            }
            if (maxSize > 0) {
                for (x in node.sortPriorities) {
                    if (x.size == maxSize) {
                        node.selectSortPriority(x)
                        break
                    }
                }
            }
        }
/*return*/res
    })
}
