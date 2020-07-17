package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s04arithmetikOperators.singleinput.AOPNot
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBOUND
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCOALESCE
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.singleinput.LOPFilter

class LogicalOptimizerFilterOptional(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterOptionalID) {
    override val classname = "LogicalOptimizerFilterOptional"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res: OPBase = node
        if (node is LOPJoin && node.optional) {
println("LogicalOptimizerFilterOptional ${node.uuid}")
            var child = node.children[1]
            var changed = false
            val filters = mutableListOf<AOPBase>()
            loop@ while (true) {
println("LogicalOptimizerFilterOptional ${child.classname}")
                when (child) {
                    is LOPSubGroup -> {
                        child = child.children[0]
                        changed = true
                    }
                    is LOPFilter -> {
                        addFilters(filters, child.children[1] as AOPBase)
                        child = child.children[0]
                        changed = true
                    }
                    else -> {
                        break@loop
                    }
                }
            }
            if (changed) {
                var childProvided = child.getProvidedVariableNames()
println("LogicalOptimizerFilterOptional changed $childProvided")
                var filterInside: AOPBase? = null
                var filterOutside: AOPBase? = null
                for (filter in filters) {
                    val req = filter.getRequiredVariableNamesRecoursive()
println("LogicalOptimizerFilterOptional filter ${filter.toSparql()} $req")
                    if (childProvided.containsAll(req)) {
                        if (filterInside == null) {
                            filterInside = filter
                        } else {
                            filterInside = AOPAnd(query, filterInside, filter)
                        }
                    } else {
                        if (filterOutside == null) {
                            filterOutside = filter
                        } else {
                            filterOutside = AOPAnd(query, filterOutside, filter)
                        }
                    }
                }
println("LogicalOptimizerFilterOptional ${filterInside?.toSparql()} ${filterOutside?.toSparql()}")
                if (filterOutside != null) {
                    if (filterInside != null) {
                        node.children[1] = LOPFilter(query, filterInside, child)
                    } else {
                        node.children[1] = child
                    }
                    val optionalIndicatorList = childProvided.toMutableSet()
                    optionalIndicatorList.removeAll(node.children[0].getProvidedVariableNames())
                    var t = optionalIndicatorList.toList()
                    if (t.size < 1) {
                        throw Exception("optional clause must add at least 1 new variable")
                    }
                    var optionalIndicator = t[0]
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
                                                            AOPConstant(query,ValueBoolean(false))
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
                    onChange()
                }
            }
        }
        return res
    }

    fun addFilters(filters: MutableList<AOPBase>, filter: AOPBase) {
        if (filter is AOPAnd) {
            addFilters(filters, filter.children[0] as AOPBase)
            addFilters(filters, filter.children[1] as AOPBase)
        } else {
            filters.add(filter)
        }
    }
}
