package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBOUND
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup

class LogicalOptimizerFilterDown(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterDownID) {
    override val classname = "LogicalOptimizerFilterDown"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPFilter) {
            var child = node.getChildren()[0]
            if (child is LOPMinus) {
                if (child.getChildren()[0].getProvidedVariableNames().containsAll(node.getChildren()[1].getRequiredVariableNamesRecoursive()) && child.tmpFakeVariables.intersect(node.getChildren()[1].getRequiredVariableNamesRecoursive()).isEmpty()) {
                    child.getChildren()[0] = LOPFilter(query, node.getChildren()[1] as AOPBase, child.getChildren()[0])
                    res = child
                    onChange()
                } else if (child.getChildren()[1].getProvidedVariableNames().containsAll(node.getChildren()[1].getRequiredVariableNamesRecoursive())) {
                    child.getChildren()[1] = LOPFilter(query, node.getChildren()[1] as AOPBase, child.getChildren()[1])
                    res = child
                    onChange()
                }
            } else {
                val filters = mutableListOf<AOPBase>()
                addFilters(filters, node.getChildren()[1] as AOPBase)
                while (child is LOPFilter) {
                    val filter = child.getChildren()[1] as AOPBase
                    val found = false
                    for (f in filters) {
                        if (f == filter) {
                            found
                            break
                        }
                    }
                    if (!found) {
                        addFilters(filters, filter)
                    }
                    child = child.getChildren()[0]
                }
                if (child is LOPUnion) {
                    var a = child.getChildren()[0]
                    var b = child.getChildren()[1]
                    for (filterIndex in 0 until filters.size) {
                        val filter = filters[filterIndex]
                        a = LOPFilter(query, filter, a)
                        b = LOPFilter(query, filter, b)
                        res = LOPUnion(query, a, b)
                        onChange()
                    }
                } else if (child !is LOPGroup && child !is LOPTriple && child.getChildren().isNotEmpty()) {
                    loop@ for (targetIndex in 0 until child.getChildren().size) {
                        val target = child.getChildren()[targetIndex]
                        loop2@ for (filterIndex in 0 until filters.size) {
                            val filter = filters[filterIndex]
                            if (target.getProvidedVariableNames().containsAll(filter.getRequiredVariableNamesRecoursive())) {
                                if (child is LOPJoin && child.optional && targetIndex == 1 && containsBound(filter)) {
                                    continue@loop2
                                }
                                child.getChildren()[targetIndex] = LOPFilter(query, filter, target)
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
        }
        return res
    }

    private fun addFilters(filters: MutableList<AOPBase>, filter: AOPBase) {
        if (filter is AOPAnd) {
            addFilters(filters, filter.getChildren()[0] as AOPBase)
            addFilters(filters, filter.getChildren()[1] as AOPBase)
        } else {
            filters.add(filter)
        }
    }

    private fun containsBound(filter: AOPBase): Boolean {
        if (filter is AOPBuildInCallBOUND) {
            return true
        }
        for (f in filter.getChildren()) {
            if (containsBound(f as AOPBase)) {
                return true
            }
        }
        return false
    }
}
