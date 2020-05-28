package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerFilterDown(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterDownID) {
    override val classname = "LogicalOptimizerFilterDown"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res: OPBase = node
        if (node is LOPFilter) {
            var child = node.children[0]
            if (child is LOPMinus) {
                if (child.children[0].getProvidedVariableNames().containsAll(node.children[1].getRequiredVariableNamesRecoursive()) && child.tmpFakeVariables.intersect(node.children[1].getRequiredVariableNamesRecoursive()).size == 0) {
                    child.children[0] = LOPFilter(query, node.children[1] as AOPBase, child.children[0])
                    res = child
                    onChange()
                } else if (child.children[1].getProvidedVariableNames().containsAll(node.children[1].getRequiredVariableNamesRecoursive())) {
                    child.children[1] = LOPFilter(query, node.children[1] as AOPBase, child.children[1])
                    res = child
                    onChange()
                }
            } else {
                val filters = mutableListOf(node.children[1] as AOPBase)
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
                if (child is LOPUnion) {
                    var a = child.children[0]
                    var b = child.children[1]
                    for (filterIndex in 0 until filters.size) {
                        val filter = filters[filterIndex]
                        a = LOPFilter(query, filter, a)
                        b = LOPFilter(query, filter, b)
                        res = LOPUnion(query, a, b)
                        onChange()
                    }
                } else if (child !is LOPGroup && child.children.size > 0) {
                    var flag = true
                    if (child is LOPJoin) {
                        if (child.optional) {
                            var provided = child.children[1].getProvidedVariableNames()
                            for (filter in filters) {
                                for (v in filter.getRequiredVariableNamesRecoursive()) {
                                    if (provided.contains(v)) {
//prevent any filters from beeing pulled down _if those require variables out of the optional join-part
                                        flag = false
                                        break
                                    }
                                }
                            }
                        }
                    }
                    if (flag) {
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
            }
        }
        return res
    }
}
