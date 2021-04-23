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

import lupos.operator.logical.Query
import lupos.operator.logical.multiinput.LOPJoin
import lupos.operator.logical.multiinput.LOPJoin_Helper
import lupos.operator.logical.singleinput.modifiers.LOPDistinct
import lupos.operator.logical.singleinput.modifiers.LOPReduced
import lupos.operator.logical.singleinput.modifiers.LOPSortAny
import lupos.shared.ESortTypeExt
import lupos.shared.SortHelper
import lupos.shared.operator.IOPBase

public class LogicalOptimizerDistinctSplit(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerDistinctSplitID, "LogicalOptimizerDistinctSplit") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPDistinct) {
            val child = node.getChildren()[0]
            val provided = child.getProvidedVariableNames().toMutableList()
            if (provided.size == 0) {
                // no variables -> no sort required
                res = LOPReduced(query, child)
                onChange()
            } else {
                if (child.getMySortPriority().size == provided.size) {
                    res = LOPReduced(query, LOPSortAny(query, child.getMySortPriority(), child))
                    onChange()
                } else {
                    if (child is LOPJoin) {
                        val columns = LOPJoin_Helper.getColumns(child.getChildren()[0].getProvidedVariableNames(), child.getChildren()[1].getProvidedVariableNames())
                        val variables = mutableListOf<String>()
                        variables.addAll(columns[0])
                        variables.addAll(columns[1])
                        variables.addAll(columns[2])
                        res = LOPReduced(query, LOPSortAny(query, variables.map { SortHelper(it, ESortTypeExt.FAST) }, child))
                        onChange()
                    } else {
                        res = LOPReduced(query, LOPSortAny(query, provided.map { SortHelper(it, ESortTypeExt.FAST) }, child))
                        onChange()
                    }
                }
            }
        }
        return res
    }
}
