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
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
public class LogicalOptimizerMinusAddSort(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerMinusAddSortID) {
    override val classname: String = "LogicalOptimizerMinusAddSort"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        val res: IOPBase = node
        if (node is LOPMinus) {
            if (!node.hadSortPushDown) {
                node.hadSortPushDown = true
                val provided = node.getChildren()[0].getProvidedVariableNames().intersect(node.getChildren()[1].getProvidedVariableNames())
                node.getChildren()[1] = LOPReduced(query, LOPSortAny(query, provided.map { SortHelper(it, ESortTypeExt.FAST) }, LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), node.getChildren()[1])))
                node.getChildren()[0] = LOPSortAny(query, provided.map { SortHelper(it, ESortTypeExt.FAST) }, LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), node.getChildren()[0]))
                onChange()
            }
        }
        return res
    }
}
