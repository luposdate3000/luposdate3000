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
import lupos.operator.logical.multiinput.LOPUnion
import lupos.operator.logical.singleinput.modifiers.LOPDistinct
import lupos.operator.logical.singleinput.modifiers.LOPLimit
import lupos.operator.logical.singleinput.modifiers.LOPOffset
import lupos.operator_base.OPBaseCompound
import lupos.shared.operator.IOPBase

public class LogicalOptimizerDistinctUp(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerDistinctUpID, "LogicalOptimizerDistinctUp") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPDistinct) {
            if (node.getChildren()[0] is LOPDistinct) {
                res = node.getChildren()[0]
                onChange()
            }
        } else if (node !is LOPUnion && node !is OPBaseCompound && node !is LOPLimit && node !is LOPOffset) {
            for (i in node.getChildren().indices) {
                val c = node.getChildren()[i]
                if (c is LOPDistinct && c.getProvidedVariableNames().containsAll(node.getProvidedVariableNames())) {
                    node.getChildren()[i] = c.getChildren()[0]
                    res = LOPDistinct(query, node)
                    onChange()
                    break
                }
            }
        }
        return res
    }
}
