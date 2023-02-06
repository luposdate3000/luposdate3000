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

import lupos.operator.base.Query
import lupos.operator.logical.singleinput.LOPSort
import lupos.shared.SanityCheck
import lupos.shared.operator.IOPBase

public class LogicalOptimizerColumnSortOrder(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerColumnSortOrderID, "LogicalOptimizerColumnSortOrder") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        val res: IOPBase = node
        var hadChange = false
  if(SanityCheck.enabled)            {
                if (parent != null) {
                    var found = false
                    for (c in parent.getChildren()) {
                        if (c === node) {
                            found = true
                            break
                        }
                    }
if(SanityCheck.enabled){if(!( found )){throw Exception("SanityCheck failed")}}
                }
            }
        
        val done = node.initializeSortPriorities {
            hadChange = true
            onChange()
        }
        if (!hadChange && !done) {
            if (parent == null || parent is LOPSort) {
                val tmp = node.getSortPriorities()
                if (tmp.size > 1) {
                    node.selectSortPriority(tmp.first())
if(SanityCheck.enabled){if(!( node.getSortPriorities().size == 1 )){throw Exception("SanityCheck failed")}}
                    onChange()
                }
            } else {
                val tmp2 = node.getSortPriorities()
                if (tmp2.size > 1) {
                    val tmp = parent.getSortPriorities()
                    if (tmp.size == 1) {
                        node.selectSortPriority(tmp.first())
                        onChange()
                    }
                    if (tmp.size <= 1) {
                        val tmp3 = node.getSortPriorities()
                        if (tmp3.size > 1) {
                            node.selectSortPriority(tmp3.first())
if(SanityCheck.enabled){if(!( node.getSortPriorities().size == 1 )){throw Exception("SanityCheck failed")}}
                            onChange()
                        }
                    }
                }
            }
        }
        return res
    }
}
