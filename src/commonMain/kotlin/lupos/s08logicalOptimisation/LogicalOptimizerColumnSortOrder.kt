package lupos.s08logicalOptimisation

import lupos.s00misc.SortHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.noinput.LOPTriple

class LogicalOptimizerColumnSortOrder(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerColumnSortOrderID) {
    override val classname = "LogicalOptimizerColumnSortOrder"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        var hadChange = false
        var done = node.initializeSortPriorities {
            hadChange = true
            onChange()
        }
        if (!hadChange && !done) {
            for (c in node.children) {
                if (c.sortPriorities.size > 1 && c !is LOPTriple) {
                    done = true
                    break
                }
            }
            if (!done) {
var flag=true
if(node is LOPTriple && parent!=null){
if(!parent.sortPrioritiesInitialized || parent.sortPriorities.size > 1){
//let the parent-operator choose first ..
flag=false
}
}
                if (flag){
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
            }
        }
/*return*/res
    })
}
