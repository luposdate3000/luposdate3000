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
import lupos.operator.base.Query
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.logical.singleinput.LOPBind
import lupos.operator.logical.singleinput.LOPProjection
import lupos.shared.operator.IOPBase

public class LogicalOptimizerRemoveProjection(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerRemoveProjectionID, "LogicalOptimizerRemoveProjection") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPProjection) {
            val child = node.getChildren()[0]
            val projection = node.getProvidedVariableNames()
            if (projection.containsAll(child.getProvidedVariableNames())) {
                onChange()
                res = child
            } else if (child is LOPTriple) {
                for (i in 0 until 3) {
                    val cc = child.getChildren()[i]
                    if (cc is AOPVariable && !projection.contains(cc.name)) {
                        child.getChildren()[i] = AOPVariable(query, "_")
                        onChange()
                    }
                }
            } else if (child is LOPBind) {
                if (!projection.contains(child.name.name)) {
                    res.getChildren()[0] = child.getChildren()[0]
                    onChange()
                }
            }
        }
        return res
    }
}
