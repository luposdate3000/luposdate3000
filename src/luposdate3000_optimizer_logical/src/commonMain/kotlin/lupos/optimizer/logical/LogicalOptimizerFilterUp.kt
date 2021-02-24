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

import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.singleinput.LOPFilter

public class LogicalOptimizerFilterUp(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerFilterUpID, "LogicalOptimizerFilterUp") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node !is LOPFilter && node !is LOPMinus && node !is LOPUnion && (node !is LOPJoin || !node.optional)) {
            for (idx in node.getChildren().indices) {
                val child = node.getChildren()[idx]
                if (child is LOPFilter && node.getProvidedVariableNames().containsAll(child.getChildren()[1].getRequiredVariableNamesRecoursive())) {
                    res = child
                    node.getChildren()[idx] = res.getChildren()[0]
                    res.getChildren()[0] = node
                    onChange()
                    break
                }
            }
        }
        return res
    }
}
