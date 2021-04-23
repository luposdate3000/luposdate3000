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

import lupos.operator_arithmetik.multiinput.AOPEQ
import lupos.operator_arithmetik.noinput.AOPVariable
import lupos.operator_logical.IOPBase
import lupos.operator_logical.Query
import lupos.operator_logical.noinput.LOPTriple
import lupos.operator_logical.singleinput.LOPBind
import lupos.operator_logical.singleinput.LOPFilter
import lupos.operator_logical.singleinput.LOPProjection

public class LogicalOptimizerFilterEQ(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerFilterEQID, "LogicalOptimizerFilterEQ") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPFilter) {
            val filter = node.getChildren()[1]
            if (filter is AOPEQ) {
                val v1 = filter.getChildren()[0]
                val v2 = filter.getChildren()[1]
                if (v1 is AOPVariable && v2 is AOPVariable) {
                    val child = node.getChildren()[0]
                    if (child !is LOPTriple) {
                        /* child may only be a triple, if_ both variables are from the same triple - which leads to errors if_ those are inlined */
                        res = if (parent != null) {
                            if (parent is LOPProjection && parent.variables.map { it.name }.contains(v1.name)) {
                                node.getChildren()[0].replaceVariableWithAnother(v2.name, v1.name, node, 0)
                                LOPBind(query, v2, v1, node.getChildren()[0])
                            } else {
                                node.getChildren()[0].replaceVariableWithAnother(v1.name, v2.name, node, 0)
                                LOPBind(query, v1, v2, node.getChildren()[0])
                            }
                        } else {
                            node.getChildren()[0].replaceVariableWithAnother(v1.name, v2.name, node, 0)
                            LOPBind(query, v1, v2, node.getChildren()[0])
                        }
                        onChange()
                    }
                }
            }
        }
        return res
    }
}
