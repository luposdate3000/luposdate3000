package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCOALESCE
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBOUND
import lupos.s04arithmetikOperators.singleinput.AOPNot
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPSubGroup

class LogicalOptimizerFilterOptional(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterOptionalID) {
    override val classname = "LogicalOptimizerFilterOptional"
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
                        throw Exception("optional clause must add at least 1 new variable")
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
                                                            query, optionalIndicator
                                                    )
                                            ),
                                            AOPBuildInCallCOALESCE(
                                                    query,
                                                    listOf(
                                                            filterOutside,
                                                            AOPConstant(query, ValueBoolean(false))
                                                    )
                                            )
                                    ),
                                    AOPNot(
                                            query,
                                            AOPBuildInCallBOUND(
                                                    query,
                                                    AOPVariable(
                                                            query, optionalIndicator
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
