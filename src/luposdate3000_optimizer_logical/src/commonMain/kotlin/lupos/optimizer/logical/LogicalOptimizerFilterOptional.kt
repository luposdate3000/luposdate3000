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
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.shared.InvalidInputException
import lupos.operator.base.Query
import lupos.operator.logical.multiinput.LOPJoin
import lupos.operator.logical.singleinput.LOPFilter
import lupos.operator.logical.singleinput.LOPSubGroup
import lupos.shared.DictionaryValueHelper
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
                var filterInside: AOPBase? = null
                var filterOutside: AOPBase? = null
                for (filter in filters) {
                    val req = filter.getRequiredVariableNamesRecoursive()
                    if (childProvided.containsAll(req)) {
                        filterInside = if (filterInside == null) {
                            filter
                        } else {
                            AOPAnd(query, filterInside, filter)
                        }
                    } else {
                        filterOutside = if (filterOutside == null) {
                            filter
                        } else {
                            AOPAnd(query, filterOutside, filter)
                        }
                    }
                }
                if (filterOutside != null) {
                    if (filterInside != null) {
                        node.getChildren()[1] = LOPFilter(query, filterInside, child)
                    } else {
                        node.getChildren()[1] = child
                    }
                    val optionalIndicatorList = childProvided.toMutableSet()
                    optionalIndicatorList.removeAll(node.getChildren()[0].getProvidedVariableNames())
                    val t = optionalIndicatorList.toList()
                    if (t.isEmpty()) {
                        throw InvalidInputException("optional clause must add at least 1 new variable")
                    }
                    val optionalIndicator = t[0]
                    res = LOPFilter(
                        query,
                        AOPOr(
                            query,
                            AOPAnd(
                                query,
                                AOPBuildInCallBOUND(
                                    query,
                                    AOPVariable(
                                        query,
                                        optionalIndicator
                                    )
                                ),
                                AOPBuildInCallCOALESCE(
                                    query,
                                    listOf(
                                        filterOutside,
                                        AOPConstant(query, DictionaryValueHelper.booleanFalseValue)
                                    )
                                )
                            ),
                            AOPNot(
                                query,
                                AOPBuildInCallBOUND(
                                    query,
                                    AOPVariable(
                                        query,
                                        optionalIndicator
                                    )
                                )
                            )
                        ),
                        node
                    )
                    res.dontSplitFilter = 1
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
