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
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPProjection
public class LogicalOptimizerBindToFilter(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerBindToFilterID) {
    override val classname: String = "LogicalOptimizerBindToFilter"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        if (node is LOPBind) {
            val v = node.getChildren()[0].getProvidedVariableNames()
            if (v.contains(node.name.name)) {
                val v2 = mutableListOf<String>()
                v2.addAll(v)
                v2.remove(node.name.name)
                node.getChildren()[0] = LOPProjection(query, v2.map { AOPVariable(query, it) }.toMutableList(), LOPFilter(query, AOPEQ(query, AOPVariable(query, node.name.name), node.getChildren()[1] as AOPBase), node.getChildren()[0]))
                onChange()
            }
        }
        return node
    }

}
