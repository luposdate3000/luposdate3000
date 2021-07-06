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

import lupos.operator.base.Query
import lupos.operator.physical.POPBase
import lupos.operator.physical.singleinput.POPVisualisation
import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.shared.operator.IOPBase

public class PhysicalOptimizerVisualisation(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerVisualisationID, "PhysicalOptimizerVisualisation") {

    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        when (node) {
            !is POPVisualisation -> {
                if (node is POPBase && (parent !is POPVisualisation)) {
                    res = POPVisualisation(query, node.projectedVariables, node)
                    if (parent != null) {
                        res.setParent(parent)
                    }
                    onChange()
                }
            }
        }
        return res
    }
}
