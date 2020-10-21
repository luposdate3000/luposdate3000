package lupos.s08logicalOptimisation
import lupos.s04logicalOperators.IOPBase
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s00misc.SortHelper
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LogicalOptimizerColumnSortOrder(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerColumnSortOrderID) {
    override val classname = "LogicalOptimizerColumnSortOrder"
    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: OPBase = node
        var hadChange = false
        SanityCheck {
            if (parent != null) {
                var found = false
                for (c in parent.children) {
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
            for (c in node.children) {
                if (c.sortPriorities.size > 1 && c !is LOPTriple) {
                    done = true
                    break
                }
            }
            if (!done) {
                var flag = true
                if (node is LOPTriple && parent != null) {
                    if (!parent.sortPrioritiesInitialized || parent.sortPriorities.size > 1) {
                        //let the parent-operator choose first ..
                        flag = false
                    }
                }
                if (flag) {
                    var maxSize = 0
                    if (node.children.size > 0 && node !is LOPTriple) {
                        //filter only valid sort orders based on children, which may had an update
                        var tmp = mutableListOf<List<SortHelper>>()
                        loop@ for (x in node.sortPriorities) {
                            var maxI = 0
                            for (c in node.children) {
                                loop2@ for (p in c.sortPriorities) {
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
                                var y = mutableListOf<SortHelper>()
                                for (i in 0 until maxI) {
                                    y.add(x[i])
                                }
                                tmp.add(y)
                            }
                        }
                        if (node.sortPriorities != tmp) {
                            node.sortPriorities = tmp
                            onChange()
                        }
                    }
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
        return res
    }
}
