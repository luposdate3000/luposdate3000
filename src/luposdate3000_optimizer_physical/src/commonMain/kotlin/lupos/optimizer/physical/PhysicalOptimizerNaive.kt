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

import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.s00misc.EIndexPatternExt
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s04logicalOperators.singleinput.modifiers.LOPSortAny
import lupos.s05tripleStore.tripleStoreManager
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPMinus
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPGraphOperation
import lupos.s09physicalOperators.noinput.POPModifyData
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.modifiers.POPReduced

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
                    res = tripleStoreManager.getGraph(node.graph).getIterator(query, Array(3) { node.getChildren()[it] as IAOPBase }, EIndexPatternExt.SPO)
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
