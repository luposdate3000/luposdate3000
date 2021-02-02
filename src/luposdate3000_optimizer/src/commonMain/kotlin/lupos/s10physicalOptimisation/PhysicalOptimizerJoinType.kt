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
package lupos.s10physicalOptimisation
import lupos.s00misc.EOptimizerIDExt
import lupos.s00misc.EPartitionModeExt
import lupos.s00misc.Partition
import lupos.s00misc.USE_PARTITIONS
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.multiinput.POPJoinCartesianProduct
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPJoinMerge
import lupos.s09physicalOperators.multiinput.POPJoinMergeOptional
import lupos.s09physicalOperators.multiinput.POPJoinMergeSingleColumn
import lupos.s09physicalOperators.multiinput.POPJoinWithStore
import lupos.s09physicalOperators.multiinput.POPJoinWithStoreExists
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal
public class PhysicalOptimizerJoinType(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerJoinTypeID) {
    override val classname: String = "PhysicalOptimizerJoinType"
    private fun localGetProjected(node: IOPBase, parent: IOPBase?): List<String> {
        return when {
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
    }
    private fun embedWithinPartitionContext(joinColumns: MutableList<String>, childA: IOPBase, childB: IOPBase, create: (IOPBase, IOPBase) -> IOPBase, keepOrder: Boolean): IOPBase {
        if ((USE_PARTITIONS == EPartitionModeExt.Threads || USE_PARTITIONS == EPartitionModeExt.Process) && Partition.default_k > 1) {
            var a = childA
            var b = childB
            val newID = IntArray(joinColumns.size) { 0 }
            var i = joinColumns.size - 1
            while (i >= 0) {
                newID[i] = query.getNextPartitionOperatorID()
                val s = joinColumns[i]
                a = POPSplitPartition(query, a.getProvidedVariableNames(), s, Partition.default_k, newID[i], a)
                b = POPSplitPartition(query, b.getProvidedVariableNames(), s, Partition.default_k, newID[i], b)
                query.addPartitionOperator(a.uuid, newID[i])
                query.addPartitionOperator(b.uuid, newID[i])
                i--
            }
            i = 0
            var c = create(a, b)
            if (c.getProvidedVariableNames().isEmpty()) {
                for (s in joinColumns) {
                    c = POPMergePartitionCount(query, c.getProvidedVariableNames(), s, Partition.default_k, newID[i], c)
                    query.addPartitionOperator(c.uuid, newID[i])
                    i++
                }
            } else {
                for (s in joinColumns) {
                    if (keepOrder) {
                        c = POPMergePartitionOrderedByIntId(query, c.getProvidedVariableNames(), s, Partition.default_k, newID[i], c)
                        query.addPartitionOperator(c.uuid, newID[i])
                    } else {
                        c = POPMergePartition(query, c.getProvidedVariableNames(), s, Partition.default_k, newID[i], c)
                        query.addPartitionOperator(c.uuid, newID[i])
                    }
                    i++
                }
            }
            return c
        } else {
            return create(childA, childB)
        }
    }
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        val projectedVariables = localGetProjected(node, parent)
        if (node is LOPJoin) {
            val childA = node.children[0]
            val childB = node.children[1]
            val columns = LOPJoin.getColumns(childA.getProvidedVariableNames(), childB.getProvidedVariableNames())
            if (columns[0].size == 0) {
                res = POPJoinCartesianProduct(query, projectedVariables, childA, childB, false)
            } else {
                if (node.getMySortPriority().size >= columns[0].size) {
                    if (projectedVariables.size == 1 && childA.getProvidedVariableNames().size == 1 && childB.getProvidedVariableNames().size == 1 && childA.getProvidedVariableNames()[0] == projectedVariables[0] && childB.getProvidedVariableNames()[0] == projectedVariables[0]) {
                        res = if (node.optional) {
                            embedWithinPartitionContext(columns[0], childA, childB, { a, b -> POPJoinMergeOptional(query, projectedVariables, a, b, true) }, true)
                        } else {
                            embedWithinPartitionContext(columns[0], childA, childB, { a, b -> POPJoinMergeSingleColumn(query, projectedVariables, a, b, false) }, true)
                        }
                    } else {
                        var flag = true
                        for (i in 0 until columns[0].size) {
                            if ((childA.getMySortPriority().size > i && childA.getMySortPriority()[i] != node.getMySortPriority()[i]) || (childB.getMySortPriority().size > i && childB.getMySortPriority()[i] != node.getMySortPriority()[i])) {
                                flag = false
                                break
                            }
                        }
                        if (flag) {
                            res = if (childA.getProvidedVariableNames().containsAll(node.getMySortPriority().map { it.variableName })) {
                                if (node.optional) {
                                    embedWithinPartitionContext(columns[0], childA, childB, { a, b -> POPJoinMergeOptional(query, projectedVariables, a, b, true) }, true)
                                } else {
                                    embedWithinPartitionContext(columns[0], childA, childB, { a, b -> POPJoinMerge(query, projectedVariables, a, b, false) }, true)
                                }
                            } else {
                                if (node.optional) {
                                    embedWithinPartitionContext(columns[0], childA, childB, { a, b -> POPJoinMergeOptional(query, projectedVariables, a, b, true) }, true)
                                } else {
                                    embedWithinPartitionContext(columns[0], childB, childA, { a, b -> POPJoinMerge(query, projectedVariables, a, b, false) }, true)
                                }
                            }
                        }
                    }
                }
                if (res is LOPJoin) {
                    val keepOrder = node.getMySortPriority().size != 0
                    res = if (node.optional) {
                        embedWithinPartitionContext(columns[0], childA, childB, { a, b -> POPJoinHashMap(query, projectedVariables, a, b, true) }, keepOrder)
                    } else if (node.partOfAskQuery && projectedVariables.isEmpty() && childA is LOPTriple) {
                        POPJoinWithStoreExists(query, projectedVariables, childB, childA, false)
                    } else if (node.partOfAskQuery && projectedVariables.isEmpty() && childB is LOPTriple) {
                        POPJoinWithStoreExists(query, projectedVariables, childA, childB, false)
                    } else if (node.partOfAskQuery && childA is LOPTriple && columns[1].size > 0 && childB.getProvidedVariableNames().containsAll(node.getMySortPriority().map { it.variableName })) {
                        POPJoinWithStore(query, projectedVariables, childB, childA, false)
                    } else if (node.partOfAskQuery && childB is LOPTriple && columns[2].size > 0 && childA.getProvidedVariableNames().containsAll(node.getMySortPriority().map { it.variableName })) {
                        POPJoinWithStore(query, projectedVariables, childA, childB, false)
                    } else if (childA is TripleStoreIteratorGlobal || childA is LOPTriple && childB.getProvidedVariableNames().containsAll(node.getMySortPriority().map { it.variableName })) {
                        embedWithinPartitionContext(columns[0], childB, childA, { a, b -> POPJoinHashMap(query, projectedVariables, a, b, false) }, keepOrder)
                    } else {
                        embedWithinPartitionContext(columns[0], childA, childB, { a, b -> POPJoinHashMap(query, projectedVariables, a, b, false) }, keepOrder)
                    }
                }
            }
            onChange()
        }
        return res
    }
}
