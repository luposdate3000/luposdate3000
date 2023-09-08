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
import lupos.operator.logical.multiinput.LOPJoin
import lupos.operator.logical.singleinput.LOPFilter
import lupos.operator.logical.singleinput.LOPSubGroup
import lupos.operator.logical.singleinput.modifiers.LOPDistinct
import lupos.shared.operator.IOPBase
public class LogicalOptimizerFilterOptional(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerFilterOptionalID, "LogicalOptimizerFilterOptional") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPJoin && node.optional) {
            var child = node.getChildren()[1]
            var changed = false
            val filters = mutableListOf<AOPBase>()
            loop@ while (true) {
                when (child) {
                    is LOPSubGroup -> {
                        child = child.getChildren()[0]
                        changed = true
                    }
                    is LOPFilter -> {
                        addFilters(filters, child.getChildren()[1] as AOPBase)
                        child = child.getChildren()[0]
                        changed = true
                    }
                    else -> {
                        break@loop
                    }
                }
            }
            if (changed) {
                val childProvided = child.getProvidedVariableNames()
                var needsOutsideVariables = false
                for (filter in filters) {
                    if (!childProvided.containsAll(filter.getRequiredVariableNamesRecoursive())) {
                        needsOutsideVariables = true
                    }
                }
                if (needsOutsideVariables) {
                    val r1 = LOPDistinct(query, node.getChildren()[0].cloneOP())
                    val r2 = LOPJoin(query, child, r1, false)
                    val r3 = LOPFilter(query, filters.reduce { s, t -> AOPAnd(query, s, t) }, r2)
                    res = LOPJoin(query, node.getChildren()[0], r3, true)
                    onChange()
                }
            }
        }
        return res
    }
    private fun addFilters(filters: MutableList<AOPBase>, filter: AOPBase) {
        if (filter is AOPAnd) {
            addFilters(filters, filter.getChildren()[0] as AOPBase)
            addFilters(filters, filter.getChildren()[1] as AOPBase)
        } else {
            filters.add(filter)
        }
    }
}
