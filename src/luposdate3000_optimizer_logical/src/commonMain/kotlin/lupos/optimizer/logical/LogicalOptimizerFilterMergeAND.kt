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
import lupos.operator.arithmetik.generated.AOPBuildInCallBOUND
import lupos.operator.arithmetik.generated.AOPNot
import lupos.operator.arithmetik.generated.AOPOr
import lupos.operator.arithmetik.multiinput.AOPBuildInCallCOALESCE
import lupos.operator.base.Query
import lupos.operator.logical.singleinput.LOPFilter
import lupos.shared.BugException
import lupos.shared.SanityCheck
import lupos.shared.operator.IOPBase

public class LogicalOptimizerFilterMergeAND(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerFilterMergeANDID, "LogicalOptimizerFilterMergeAND") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPFilter) {
            val child = node.getChildren()[0]
            if (child is LOPFilter) {
                    res = LOPFilter(query, AOPAnd(query, node.getChildren()[1] as AOPBase, child.getChildren()[1] as AOPBase), child.getChildren()[0])
                    onChange()
            }
        }
        return res
    }

    private fun containsBound(filter: AOPBase): Boolean {
        if (filter is AOPBuildInCallBOUND) {
            return true
        }
        for (f in filter.getChildren()) {
            if (containsBound(f as AOPBase)) {
                return true
            }
        }
        return false
    }
}
