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
package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerIDExt
import lupos.s00misc.ESortTypeExt
import lupos.s00misc.SortHelper
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
public class LogicalOptimizerDistinctSplit(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerDistinctSplitID) {
    override val classname: String = "LogicalOptimizerDistinctSplit"
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
                        val columns = LOPJoin.getColumns(child.getChildren()[0].getProvidedVariableNames(), child.getChildren()[1].getProvidedVariableNames())
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
