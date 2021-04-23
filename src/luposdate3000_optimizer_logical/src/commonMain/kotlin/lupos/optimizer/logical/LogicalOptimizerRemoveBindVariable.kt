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

import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.logical.Query
import lupos.operator.logical.singleinput.LOPBind
import lupos.operator.logical.singleinput.LOPProjection
import lupos.shared.operator.IOPBase

public class LogicalOptimizerRemoveBindVariable(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerRemoveBindVariableID, "LogicalOptimizerRemoveBindVariable") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        if (node is LOPProjection) {
            val child = node.getChildren()[0]
            if (child is LOPBind) {
                val exp = child.getChildren()[1]
                if (exp is AOPVariable) {
                    val provided = node.getProvidedVariableNames()
                    if (!provided.contains(exp.name)) {
                        child.getChildren()[0].replaceVariableWithAnother(exp.name, child.name.name, child, 0)
                        node.getChildren()[0] = child.getChildren()[0]
                        onChange()
                    }
                }
            }
        }
        return node
    }
}
