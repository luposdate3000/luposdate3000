package lupos.s08logicalOptimisation

import lupos.s00misc.SortHelper
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
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
 SanityCheck {
            if (parent != null) {
                var found = false
                for (c in parent.children) {
                    if (c === node) {
                        found = true
                        break
                    }
                }
                require(found)
            }
        }
        var done = node.initializeSortPriorities {
            hadChange = true
            println("initializing ${node.uuid} ${node.sortPriorities}")
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
if(node.uuid==79259L){
println("verify??? 79259 ${parent!=null} ${parent?.sortPrioritiesInitialized} ${parent?.sortPriorities} ${parent?.uuid} $flag ${node.classname}")
}
                if (flag) {
                    var maxSize = 0
                    if (node.children.size > 0 && node !is LOPTriple) {
                        println("start filter ${node.uuid} ${node.sortPriorities}")
                        for (c in node.children) {
                            println("print childs ${c.uuid} ${c.sortPriorities}")
                        }
                        //filter only valid sort orders based on children, which may had an update
                        var tmp = mutableListOf<List<SortHelper>>()
                        loop@ for (x in node.sortPriorities) {
                            println("iterating :: $x")
                            var maxI = 0
                            for (c in node.children) {
                                println("nextchild :: ${c.sortPriorities}")
                                loop2@ for (p in c.sortPriorities) {
                                    println("childpossibility :: ${p}")
                                    var i = 0
                                    while (i < x.size && i < p.size) {
                                        if (x[i] != p[i]) {
                                            println("failed :: $i")
                                            if (i > maxI) {
                                                maxI = i
                                            }
                                            continue@loop2
                                        }
                                        i++
                                    }
                                    println("added :: $x")
                                    tmp.add(x)
                                    continue@loop
                                }
                            }
                            if (maxI > 0) {
var y=mutableListOf<SortHelper>()
for(i in 0 until maxI){
y.add(x[i])
}
tmp.add(y)
                            }
                        }
                        if (node.sortPriorities != tmp) {
                            println("filtered ${node.uuid} ${node.sortPriorities} -> $tmp")
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
                                println("selecting ${node.uuid} $x")
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
