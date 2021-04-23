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
package lupos.optimizer.physical

import lupos.operator_logical.IOPBase
import lupos.operator_logical.OPBaseCompound
import lupos.operator_logical.Query
import lupos.operator_physical.POPBase
import lupos.operator_physical.singleinput.POPDebug
import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.s00misc.SanityCheck

public class PhysicalOptimizerDebug(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerDebugID, "PhysicalOptimizerDebug") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        when (node) {
            !is POPDebug -> {
                SanityCheck {
                    // this code is intended to be debugging only - even if it changes the resulting operator-graph
                    if (node is POPBase && (parent == null || (parent !is POPDebug && parent !is OPBaseCompound))) {
                        res = POPDebug(query, node.projectedVariables, node)
                        onChange()
                    }
                }
            }
        }
        return res
    }
}
