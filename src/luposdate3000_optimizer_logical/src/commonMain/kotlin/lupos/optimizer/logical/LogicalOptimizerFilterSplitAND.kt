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

import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.generated.AOPAnd
import lupos.operator.base.Query
import lupos.operator.logical.singleinput.LOPFilter
import lupos.shared.operator.IOPBase

public class LogicalOptimizerFilterSplitAND(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerFilterSplitANDID, "LogicalOptimizerFilterSplitAND") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPFilter && node.dontSplitFilter == 0) {
            val child = node.getChildren()[0]
            val aopcompare = node.getChildren()[1]
            if (aopcompare is AOPAnd) {
                onChange()
                res = LOPFilter(query, aopcompare.getChildren()[0] as AOPBase, LOPFilter(query, aopcompare.getChildren()[1] as AOPBase, child))
            }
        }
        return res
    }
}
