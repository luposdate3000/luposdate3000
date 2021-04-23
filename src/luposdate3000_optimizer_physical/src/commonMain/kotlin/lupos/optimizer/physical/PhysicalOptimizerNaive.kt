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

import lupos.operator_arithmetik.AOPBase
import lupos.operator_arithmetik.IAOPBase
import lupos.operator_arithmetik.noinput.AOPVariable
import lupos.operator_logical.IOPBase
import lupos.operator_logical.Query
import lupos.operator_logical.multiinput.LOPJoin
import lupos.operator_logical.multiinput.LOPMinus
import lupos.operator_logical.multiinput.LOPUnion
import lupos.operator_logical.noinput.LOPGraphOperation
import lupos.operator_logical.noinput.LOPModifyData
import lupos.operator_logical.noinput.LOPTriple
import lupos.operator_logical.noinput.LOPValues
import lupos.operator_logical.noinput.OPEmptyRow
import lupos.operator_logical.singleinput.LOPBind
import lupos.operator_logical.singleinput.LOPFilter
import lupos.operator_logical.singleinput.LOPGroup
import lupos.operator_logical.singleinput.LOPMakeBooleanResult
import lupos.operator_logical.singleinput.LOPModify
import lupos.operator_logical.singleinput.LOPProjection
import lupos.operator_logical.singleinput.LOPSort
import lupos.operator_logical.singleinput.modifiers.LOPLimit
import lupos.operator_logical.singleinput.modifiers.LOPOffset
import lupos.operator_logical.singleinput.modifiers.LOPReduced
import lupos.operator_logical.singleinput.modifiers.LOPSortAny
import lupos.operator_physical.POPBase
import lupos.operator_physical.multiinput.POPJoinHashMap
import lupos.operator_physical.multiinput.POPMinus
import lupos.operator_physical.multiinput.POPUnion
import lupos.operator_physical.noinput.POPEmptyRow
import lupos.operator_physical.noinput.POPGraphOperation
import lupos.operator_physical.noinput.POPModifyData
import lupos.operator_physical.noinput.POPValues
import lupos.operator_physical.singleinput.POPBind
import lupos.operator_physical.singleinput.POPFilter
import lupos.operator_physical.singleinput.POPGroup
import lupos.operator_physical.singleinput.POPMakeBooleanResult
import lupos.operator_physical.singleinput.POPModify
import lupos.operator_physical.singleinput.POPProjection
import lupos.operator_physical.singleinput.POPSort
import lupos.operator_physical.singleinput.modifiers.POPLimit
import lupos.operator_physical.singleinput.modifiers.POPOffset
import lupos.operator_physical.singleinput.modifiers.POPReduced
import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.s00misc.EIndexPatternExt
import lupos.s05tripleStore.tripleStoreManager

public class PhysicalOptimizerNaive(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerNaiveID, "PhysicalOptimizerNaive") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        var change = true
        val projectedVariables: List<String>
        try {
            projectedVariables = when {
                parent is LOPProjection -> {
                    parent.getProvidedVariableNames()
                }
                parent is POPProjection -> {
                    parent.getProvidedVariableNamesInternal()
                }
                node is POPBase -> {
                    node.getProvidedVariableNamesInternal()
                }
                else -> {
                    node.getProvidedVariableNames()
                }
            }
            when (node) {
                is LOPSortAny -> {
                    val child = node.getChildren()[0]
                    val v1 = node.possibleSortOrder
                    val v2 = child.getMySortPriority()
                    var flag = v1.size == v2.size
                    var i = 0
                    while (flag && i < v1.size) {
                        if (v1[i].variableName != v2[i].variableName || v1[i].sortType != v2[i].sortType) {
                            flag = false
                        }
                        i++
                    }
                    res = if (flag) {
                        child
                    } else {
                        POPSort(query, projectedVariables, arrayOf(), true, child)
                    }
                }
                is LOPGraphOperation -> {
                    res = POPGraphOperation(query, projectedVariables, node.silent, node.graph1type, node.graph1iri, node.graph2type, node.graph2iri, node.action)
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPModify -> {
                    res = POPModify(query, projectedVariables, node.insert, node.delete, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPModifyData -> {
                    res = POPModifyData(query, projectedVariables, node.type, node.data)
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPProjection -> {
                    res = POPProjection(query, projectedVariables, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPMakeBooleanResult -> {
                    res = if (node.getChildren()[0] is LOPProjection || node.getChildren()[0] is POPProjection) {
                        POPMakeBooleanResult(query, projectedVariables, node.getChildren()[0].getChildren()[0])
                    } else {
                        POPMakeBooleanResult(query, projectedVariables, node.getChildren()[0])
                    }
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPValues -> {
                    res = POPValues(query, projectedVariables, node)
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPLimit -> {
                    res = POPLimit(query, projectedVariables, node.limit, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPReduced -> {
                    res = POPReduced(query, projectedVariables, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPOffset -> {
                    res = POPOffset(query, projectedVariables, node.offset, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPGroup -> {
                    res = POPGroup(query, projectedVariables, node.by, node.bindings, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPUnion -> {
                    val countA = node.getChildren()[0].getChildrenCountRecoursive()
                    val countB = node.getChildren()[1].getChildrenCountRecoursive()
                    res = if (countA < countB) {
                        POPUnion(query, projectedVariables, node.getChildren()[0], node.getChildren()[1])
                    } else {
                        POPUnion(query, projectedVariables, node.getChildren()[1], node.getChildren()[0])
                    }
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPMinus -> {
                    res = POPMinus(query, projectedVariables, node.getChildren()[0], node.getChildren()[1])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
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
                        res.sortPriorities = node.sortPriorities
                        res.mySortPriority = node.mySortPriority
                        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                    } else {
                        change = false
                    }
                }
                is LOPFilter -> {
                    res = POPFilter(query, projectedVariables, node.getChildren()[1] as AOPBase, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPBind -> {
                    res = POPBind(query, projectedVariables, node.name, node.getChildren()[1] as AOPBase, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPJoin -> {
                    res = POPJoinHashMap(query, projectedVariables, node.getChildren()[0], node.getChildren()[1], node.optional)
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPTriple -> {
                    res = tripleStoreManager.getGraph(node.graph).getIterator(query, Array<IAOPBase>(3) { node.getChildren()[it] as IAOPBase }, EIndexPatternExt.SPO)
                }
                is OPEmptyRow -> {
                    res = POPEmptyRow(query, projectedVariables)
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                else -> {
                    change = false
                }
            }
        } finally {
            if (change) {
                onChange()
            }
        }
        return res
    }
}
