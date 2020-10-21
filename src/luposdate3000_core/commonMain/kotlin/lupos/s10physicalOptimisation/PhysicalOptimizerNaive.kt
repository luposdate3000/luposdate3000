package lupos.s10physicalOptimisation

import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOptimizerID
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.noinput.LOPModifyData
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPModify
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPMinus
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPGraphOperation
import lupos.s09physicalOperators.noinput.POPModifyData
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.modifiers.POPReduced
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPModify
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s15tripleStoreDistributed.distributedTripleStore

class PhysicalOptimizerNaive(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerNaiveID) {
    override val classname = "PhysicalOptimizerNaive"
    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        var change = true
        var projectedVariables = listOf<String>()
        try {
            if (parent is LOPProjection) {
                projectedVariables = parent.getProvidedVariableNames()
            } else if (parent is POPProjection) {
                projectedVariables = parent.getProvidedVariableNamesInternal()
            } else if (node is POPBase) {
                projectedVariables = node.getProvidedVariableNamesInternal()
            } else {
                projectedVariables = node.getProvidedVariableNames()
            }
            when (node) {
                is LOPSortAny -> {
                    val child = node.getChildren()[0]
                    val v1 = node.possibleSortOrder
                    val v2 = child.mySortPriority
                    var flag = v1.size == v2.size
                    var i = 0
                    while (flag && i < v1.size) {
                        if (v1[i].variableName != v2[i].variableName || v1[i].sortType != v2[i].sortType) {
                            flag = false
                        }
                        i++
                    }
                    if (flag) {
                        res = child
                    } else {
                        res = POPSort(query, projectedVariables, arrayOf<AOPVariable>(), true, child)
                    }
                }
                is LOPGraphOperation -> {
                    res = POPGraphOperation(query, projectedVariables, node.silent, node.graph1type, node.graph1iri, node.graph2type, node.graph2iri, node.action)
                }
                is LOPModify -> {
                    res = POPModify(query, projectedVariables, node.insert, node.delete, node.getChildren()[0])
                }
                is LOPModifyData -> {
                    res = POPModifyData(query, projectedVariables, node.type, node.data)
                }
                is LOPProjection -> {
                    res = POPProjection(query, projectedVariables, node.getChildren()[0])
                }
                is LOPMakeBooleanResult -> {
                    if (node.getChildren()[0] is LOPProjection || node.getChildren()[0] is POPProjection) {
                        res = POPMakeBooleanResult(query, projectedVariables, node.getChildren()[0].getChildren()[0])
                    } else {
                        res = POPMakeBooleanResult(query, projectedVariables, node.getChildren()[0])
                    }
                }
                is LOPValues -> {
                    res = POPValues(query, projectedVariables, node)
                }
                is LOPLimit -> {
                    res = POPLimit(query, projectedVariables, node.limit, node.getChildren()[0])
                }
                is LOPReduced -> {
                    res = POPReduced(query, projectedVariables, node.getChildren()[0])
                }
                is LOPOffset -> {
                    res = POPOffset(query, projectedVariables, node.offset, node.getChildren()[0])
                }
                is LOPGroup -> {
                    res = POPGroup(query, projectedVariables, node.by, node.bindings, node.getChildren()[0])
                }
                is LOPUnion -> {
                    var countA = node.getChildren()[0].getChildrenCountRecoursive()
                    var countB = node.getChildren()[1].getChildrenCountRecoursive()
                    if (countA < countB) {
                        res = POPUnion(query, projectedVariables, node.getChildren()[0], node.getChildren()[1])
                    } else {
                        res = POPUnion(query, projectedVariables, node.getChildren()[1], node.getChildren()[0])
                    }
                }
                is LOPMinus -> {
                    res = POPMinus(query, projectedVariables, node.getChildren()[0], node.getChildren()[1])
                }
                is LOPSort -> {
                    if (parent !is LOPSort) {
                        val sortBy = mutableListOf<AOPVariable>()
                        sortBy.add(node.by)
                        var child = node.getChildren()[0]
                        while (child is LOPSort) {
                            sortBy.add(child.by)
                            child = child.getChildren()[0]
                        }
                        res = POPSort(query, projectedVariables, sortBy.toTypedArray(), node.asc, child)
                    } else {
                        change = false
                    }
                }
                is LOPFilter -> {
                    res = POPFilter(query, projectedVariables, node.getChildren()[1] as AOPBase, node.getChildren()[0])
                }
                is LOPBind -> {
                    res = POPBind(query, projectedVariables, node.name, node.getChildren()[1] as AOPBase, node.getChildren()[0])
                }
                is LOPJoin -> {
                    res = POPJoinHashMap(query, projectedVariables, node.getChildren()[0], node.getChildren()[1], node.optional)
                }
                is LOPTriple -> {
                    res = distributedTripleStore.getNamedGraph(query, node.graph).getIterator(Array(3) { node.getChildren()[it] as IAOPBase }, EIndexPattern.SPO, Partition())
                }
                is OPEmptyRow -> {
                    res = POPEmptyRow(query, projectedVariables)
                }
                else -> {
                    change = false
                }
            }
        } finally {
            if (change) {
                SanityCheck {
                    val tmp = node.mySortPriority.map { it.variableName }
                    SanityCheck.check { (!projectedVariables.containsAll(tmp)) || (res.getProvidedVariableNames().containsAll(tmp) && projectedVariables.containsAll(tmp)) }
                }
                res.setMySortPriority ( node.getMySortPriority())
                res.setSortPriorities ( node.getSortPriorities())
                onChange()
            }
        }
        return res
    }
}
