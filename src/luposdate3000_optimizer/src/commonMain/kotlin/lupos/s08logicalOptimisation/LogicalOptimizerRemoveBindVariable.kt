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
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPProjection
public class LogicalOptimizerRemoveBindVariable(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerRemoveBindVariableID) {
    override val classname: String = "LogicalOptimizerRemoveBindVariable"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        if (node is LOPProjection) {
            val child = node.getChildren()[0]
            if (child is LOPBind) {
                val exp = child.getChildren()[1]
                if (exp is AOPVariable) {
                    val provided = node.getProvidedVariableNames()
                    if (!provided.contains(exp.name)) {
                        node.replaceVariableWithAnother(child.getChildren()[0], exp.name, child.name.name, child, 0)
                        node.getChildren()[0] = child.getChildren()[0]
                        onChange()
                    }
                }
            }
        }
        return node
    }

    override fun optimizeCallRico(node: IOPBase, onChange: () -> Unit): MutableList<IOPBase> {
        TODO("Not yet implemented")
    }
}
