package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s00misc.SortHelper
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.noinput.LOPTriple

class LogicalOptimizerColumnSortOrder(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerColumnSortOrderID) {
    override val classname = "LogicalOptimizerColumnSortOrder"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        val res: IOPBase = node
        var hadChange = false
        SanityCheck {
            if (parent != null) {
                var found = false
                for (c in parent.getChildren()) {
                    if (c === node) {
                        found = true
                        break
                    }
                }
                SanityCheck.check { found }
            }
        }
        var done = node.initializeSortPriorities {
            hadChange = true
            onChange()
        }
        if (!hadChange && !done) {
            for (c in node.getChildren()) {
                if (c.getSortPriorities().size > 1 && c !is LOPTriple) {
                    done = true
                    break
                }
            }
            if (!done) {
                var flag = true
                if (node is LOPTriple && parent != null) {
                    if (!parent.getSortPrioritiesInitialized() || parent.getSortPriorities().size > 1) {
                        //let the parent-operator choose first ..
                        flag = false
                    }
                }
                if (flag) {
                    var maxSize = 0
                    if (node.getChildren().isNotEmpty() && node !is LOPTriple) {
                        //filter only valid sort orders based on children, which may had an update
                        val tmp = mutableListOf<List<SortHelper>>()
                        loop@ for (x in node.getSortPriorities()) {
                            var maxI = 0
                            for (c in node.getChildren()) {
                                loop2@ for (p in c.getSortPriorities()) {
                                    var i = 0
                                    while (i < x.size && i < p.size) {
                                        if (x[i] != p[i]) {
                                            if (i > maxI) {
                                                maxI = i
                                            }
                                            continue@loop2
                                        }
                                        i++
                                    }
                                    tmp.add(x)
                                    continue@loop
                                }
                            }
                            if (maxI > 0) {
                                val y = mutableListOf<SortHelper>()
                                for (i in 0 until maxI) {
                                    y.add(x[i])
                                }
                                tmp.add(y)
                            }
                        }
                        if (node.getSortPriorities() != tmp) {
                            node.setSortPriorities(tmp)
                            onChange()
                        }
                    }
                    for (x in node.getSortPriorities()) {
                        if (x.size > maxSize) {
                            maxSize = x.size
                        }
                    }
                    if (maxSize > 0) {
                        for (x in node.getSortPriorities()) {
                            if (x.size == maxSize) {
                                node.selectSortPriority(x)
                                break
                            }
                        }
                    }
                }
            }
        }
        return res
    }
}
