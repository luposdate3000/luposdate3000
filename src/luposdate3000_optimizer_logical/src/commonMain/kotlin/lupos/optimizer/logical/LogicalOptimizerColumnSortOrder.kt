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

import lupos.operator_logical.IOPBase
import lupos.operator_logical.Query
import lupos.operator_logical.noinput.LOPTriple
import lupos.s00misc.SanityCheck
import lupos.s00misc.SortHelper

public class LogicalOptimizerColumnSortOrder(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerColumnSortOrderID, "LogicalOptimizerColumnSortOrder") {
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
                        // let the parent-operator choose first ..
                        flag = false
                    }
                }
                if (flag) {
                    if (node.getChildren().isNotEmpty() && node !is LOPTriple) {
                        // filter only valid sort orders based on children, which may had an update
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
                    var maxSize = 0
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
        if (node.getSortPriorities().size > 0 && node.getMySortPriority().size == 0) {
// TODO debug why this is wrong ...
            var maxSize = 0
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
        return res
    }
}
