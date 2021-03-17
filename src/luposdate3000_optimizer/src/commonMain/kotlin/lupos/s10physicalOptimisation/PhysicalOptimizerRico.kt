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

/*
    Added by Rico

    Is adding a POPRico operator between every Operator for the visualization

 */

package lupos.s10physicalOptimisation
import lupos.s00misc.EOptimizerIDExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPRico

//TODO: PhysicalOptimizerDebugID ersetzen

public class PhysicalOptimizerRico(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerDebugID) {

    override val classname: String = "PhysicalOptimizerRico"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        when (node) {

            !is POPRico -> {
                    // this code is intended to be debugging only - even if it changes the resulting operator-graph
                    if (node is POPBase && (parent !is POPRico)) {
                        res = POPRico(query, node.projectedVariables, node)
                        if (parent != null) {
                            res.setParent(parent)
                        }
                        onChange()
                    }
            }
        }
        return res
    }

    override fun optimizeCallRico(node: IOPBase, onChange: () -> Unit): MutableList<IOPBase> {
        TODO("No implementation")
    }
}
