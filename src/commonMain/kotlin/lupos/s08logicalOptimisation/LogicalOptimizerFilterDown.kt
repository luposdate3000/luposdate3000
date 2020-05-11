package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerFilterDown(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterDownID) {
    override val classname = "LogicalOptimizerFilterDown"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPFilter) {
            val filters = mutableListOf(node.children[1] as AOPBase)
            var child = node.children[0]
            while (child is LOPFilter) {
                val filter = child.children[1] as AOPBase
                var found = false
                for (f in filters) {
                    if (f == filter) {
                        found == true
                        break
                    }
                }
                if (!found) {
                    filters.add(filter)
                }
                child = child.children[0]
            }
            if (child !is LOPGroup && child !is LOPUnion && child.children.size > 0) {
                loop@ for (targetIndex in 0 until child.children.size) {
                    val target = child.children[targetIndex]
                    for (filterIndex in 0 until filters.size) {
                        val filter = filters[filterIndex]
                        if (target.getProvidedVariableNames().containsAll(filter.getRequiredVariableNamesRecoursive())) {
                            child.children[targetIndex] = LOPFilter(query, filter, target)
                            filters.removeAt(filterIndex)
                            res = child
                            for (filter2 in filters) {
                                res = LOPFilter(query, filter2, res)
                            }
                            onChange()
                            break@loop
                        }
                    }
                }
            }
        }
/*return*/res
    })
}
