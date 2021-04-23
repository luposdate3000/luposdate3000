/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.optimizer.logical

import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.generated.AOPAnd
import lupos.operator.arithmetik.generated.AOPBuildInCallBOUND
import lupos.operator.logical.Query
import lupos.operator.logical.multiinput.LOPJoin
import lupos.operator.logical.multiinput.LOPMinus
import lupos.operator.logical.multiinput.LOPUnion
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.logical.singleinput.LOPFilter
import lupos.operator.logical.singleinput.LOPGroup
import lupos.shared.operator.IOPBase

public class LogicalOptimizerFilterDown(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerFilterDownID, "LogicalOptimizerFilterDown") {
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
                    var found = false
                    for (f in filters) {
                        if (f == filter) {
                            found = true
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
                    loop@ for (targetIndex in child.getChildren().indices) {
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
