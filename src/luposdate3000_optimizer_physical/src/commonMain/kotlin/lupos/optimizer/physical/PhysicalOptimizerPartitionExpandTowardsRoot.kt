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

import lupos.operator.arithmetik.AOPBase
import lupos.operator.base.Query
import lupos.operator.physical.multiinput.POPUnion
import lupos.operator.physical.partition.POPChangePartitionOrderedByIntId
import lupos.operator.physical.partition.POPMergePartition
import lupos.operator.physical.partition.POPMergePartitionCount
import lupos.operator.physical.partition.POPMergePartitionOrderedByIntId
import lupos.operator.physical.partition.POPSplitPartition
import lupos.operator.physical.partition.POPSplitPartitionFromStore
import lupos.operator.physical.partition.POPSplitPartitionFromStoreCount
import lupos.operator.physical.singleinput.POPBind
import lupos.operator.physical.singleinput.POPFilter
import lupos.operator.physical.singleinput.POPGroup
import lupos.operator.physical.singleinput.POPProjection
import lupos.operator.physical.singleinput.modifiers.POPReduced
import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.shared.EPartitionModeExt
import lupos.shared.IQuery
import lupos.shared.operator.IOPBase
import lupos.triple_store_manager.POPTripleStoreIterator

public class PhysicalOptimizerPartitionExpandTowardsRoot(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerPartitionExpandTowardsRootID, "PhysicalOptimizerPartitionExpandTowardsRoot") {
    // this optimizer moves the partitioning upwards to the root
    private fun createPOPMergePartition(
        query: IQuery,
        projectedVariables: List<String>,
        partitionVariable: String?,
        partitionCount: Int,
        partitionID: Int,
        child: IOPBase,
    ): IOPBase {
        return if (projectedVariables.isEmpty()) {
            POPMergePartitionCount(query, projectedVariables, partitionVariable!!, partitionCount, partitionID, child)
        } else {
            POPMergePartition(query, projectedVariables, partitionVariable, partitionCount, partitionID, child)
        }
    }
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if (query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Thread || query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
            when (node) {
                is POPGroup -> {
                    when (val c = node.children[0]) {
                        is POPMergePartition -> {
                            if (node.by.map { it.name }.contains(c.partitionVariable)) {
                                res = createPOPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPGroup(query, node.projectedVariables, node.by, node.bindings, c.children[0]))
                                query.removePartitionOperator(c.getUUID(), c.partitionID)
                                query.addPartitionOperator(res.getUUID(), c.partitionID)
                                query.partitionOperatorCount.clear()
                                onChange()
                            }
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            if (node.by.map { it.name }.contains(c.partitionVariable)) {
                                if (node.projectedVariables.contains(c.partitionVariable)) {
                                    res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, c.partitionVariable, c.partitionCount2, c.partitionID, POPGroup(query, node.projectedVariables, node.by, node.bindings, c.children[0]))
                                    res.setMySortPriority(c.mySortPriority, node.projectedVariables)
                                } else {
                                    res = createPOPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount2, c.partitionID, POPGroup(query, node.projectedVariables, node.by, node.bindings, c.children[0]))
                                }
                                query.removePartitionOperator(c.getUUID(), c.partitionID)
                                query.addPartitionOperator(res.getUUID(), c.partitionID)
                                query.partitionOperatorCount.clear()
                                onChange()
                            }
                        }
                        is POPMergePartitionCount -> {
                            if (node.by.map { it.name }.contains(c.partitionVariable)) {
                                res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPGroup(query, node.projectedVariables, node.by, node.bindings, c.children[0]))
                                query.removePartitionOperator(c.getUUID(), c.partitionID)
                                query.addPartitionOperator(res.getUUID(), c.partitionID)
                                query.partitionOperatorCount.clear()
                                onChange()
                            }
                        }
                    }
                }
                is POPSplitPartitionFromStore -> {
                    var storeNodeTmp = node.children[0]
                    while (storeNodeTmp !is POPTripleStoreIterator) {
// this is POPDebug or something similar with is not affecting the calculation - otherwise this node wont be POPSplitPartitionFromStore
                        storeNodeTmp = storeNodeTmp.getChildren()[0]
                    }
                    val storeNode = storeNodeTmp
                    val max_count = node.partitionCount
                    val new_count = storeNode.changeToIndexWithMaximumPartitions(max_count, node.partitionVariable)
                    if (new_count > 0) {
                        if (new_count != max_count) {
                            val newID = query.getNextPartitionOperatorID()
                            query.removePartitionOperator(node.getUUID(), node.partitionID)
                            res = POPChangePartitionOrderedByIntId(query, node.projectedVariables, node.partitionVariable, new_count, node.partitionCount, newID, node.partitionID, node)
                            node.partitionID = newID
                            node.partitionCount = new_count
                            query.addPartitionOperator(node.getUUID(), node.partitionID)
                            query.addPartitionOperator(res.getUUID(), res.partitionIDTo)
                            query.addPartitionOperator(res.getUUID(), res.partitionIDFrom)
                            onChange()
                        }
                    }
                }
                is POPSplitPartitionFromStoreCount -> {
                    var storeNodeTmp = node.children[0]
                    while (storeNodeTmp !is POPTripleStoreIterator) {
// this is POPDebug or something similar with is not affecting the calculation - otherwise this node wont be POPSplitPartitionFromStoreCount
                        storeNodeTmp = storeNodeTmp.getChildren()[0]
                    }
                    val storeNode = storeNodeTmp
                    val max_count = node.partitionCount
                    val new_count = storeNode.changeToIndexWithMaximumPartitions(max_count, node.partitionVariable)
                    if (new_count > 0) {
                        if (new_count != max_count) {
                            val newID = query.getNextPartitionOperatorID()
                            query.removePartitionOperator(node.getUUID(), node.partitionID)
                            res = POPChangePartitionOrderedByIntId(query, node.projectedVariables, node.partitionVariable, new_count, node.partitionCount, newID, node.partitionID, node)
                            node.partitionID = newID
                            node.partitionCount = new_count
                            query.addPartitionOperator(node.getUUID(), node.partitionID)
                            query.addPartitionOperator(res.getUUID(), res.partitionIDTo)
                            query.addPartitionOperator(res.getUUID(), res.partitionIDFrom)
                            onChange()
                        }
                    }
                }
                is POPBind -> {
                    when (val c = node.children[0]) {
                        is POPMergePartition -> {
                            res = createPOPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPBind(query, node.projectedVariables, node.name, node.children[1] as AOPBase, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            if (node.projectedVariables.contains(c.partitionVariable)) {
                                res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, c.partitionVariable, c.partitionCount2, c.partitionID, POPBind(query, node.projectedVariables, node.name, node.children[1] as AOPBase, c.children[0]))
                                res.setMySortPriority(c.mySortPriority, node.projectedVariables)
                            } else {
                                res = createPOPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount2, c.partitionID, POPBind(query, node.projectedVariables, node.name, node.children[1] as AOPBase, c.children[0]))
                            }
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionCount -> {
                            res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPBind(query, node.projectedVariables, node.name, node.children[1] as AOPBase, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                    }
                }
                is POPUnion -> {
                    val c0 = node.children[0]
                    val c1 = node.children[1]
                    var modeC0 = 0
                    var modeC1 = 0
                    var columnNameC0: String? = null
                    var columnCountC0 = 0
                    var columnIDC0 = 0
                    var columnNameC1: String? = null
                    var columnCountC1 = 0
                    var columnIDC1 = 0
                    when (c0) {
                        is POPMergePartition -> {
                            modeC0 = 1
                            columnNameC0 = c0.partitionVariable
                            columnCountC0 = c0.partitionCount
                            columnIDC0 = c0.partitionID
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            modeC0 = 2
                            columnNameC0 = c0.partitionVariable
                            columnCountC0 = c0.partitionCount2
                            columnIDC0 = c0.partitionID
                        }
                        is POPMergePartitionCount -> {
                            modeC0 = 3
                            columnNameC0 = c0.partitionVariable
                            columnCountC0 = c0.partitionCount
                            columnIDC0 = c0.partitionID
                        }
                    }
                    when (c1) {
                        is POPMergePartition -> {
                            modeC1 = 1
                            columnNameC1 = c1.partitionVariable
                            columnCountC1 = c1.partitionCount
                            columnIDC1 = c1.partitionID
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            modeC1 = 2
                            columnNameC1 = c1.partitionVariable
                            columnCountC1 = c1.partitionCount2
                            columnIDC1 = c1.partitionID
                        }
                        is POPMergePartitionCount -> {
                            modeC1 = 3
                            columnNameC1 = c1.partitionVariable
                            columnCountC1 = c1.partitionCount
                            columnIDC1 = c1.partitionID
                        }
                    }
                    if (modeC0 == modeC1 && columnNameC0 == columnNameC1 && modeC0 > 0) {
                        if (columnCountC0 == columnCountC1) {
                            val columnID = query.mergePartitionOperator(columnIDC0, columnIDC1, c1.getChildren()[0])
                            when (modeC0) {
                                1 -> {
                                    res = createPOPMergePartition(query, node.projectedVariables, columnNameC0, columnCountC0, columnID, POPUnion(query, node.projectedVariables, c0.getChildren()[0], c1.getChildren()[0]))
                                    query.removePartitionOperator(c0.getUUID(), columnID)
                                    query.removePartitionOperator(c1.getUUID(), columnID)
                                    query.addPartitionOperator(res.getUUID(), columnID)
                                    query.partitionOperatorCount.clear()
                                    onChange()
                                }
                                2 -> {
                                    if (node.projectedVariables.contains(columnNameC0!!)) {
                                        res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, columnNameC0!!, columnCountC0, columnID, POPUnion(query, node.projectedVariables, c0.getChildren()[0], c1.getChildren()[0]))
                                        res.setMySortPriority((c1 as POPMergePartitionOrderedByIntId).mySortPriority, node.projectedVariables)
                                    } else {
                                        res = createPOPMergePartition(query, node.projectedVariables, columnNameC0!!, columnCountC0, columnID, POPUnion(query, node.projectedVariables, c0.getChildren()[0], c1.getChildren()[0]))
                                    }
                                    query.removePartitionOperator(c0.getUUID(), columnID)
                                    query.removePartitionOperator(c1.getUUID(), columnID)
                                    query.addPartitionOperator(res.getUUID(), columnID)
                                    query.partitionOperatorCount.clear()
                                    onChange()
                                }
                                3 -> {
                                    res = POPMergePartitionCount(query, node.projectedVariables, columnNameC0!!, columnCountC0, columnID, POPUnion(query, node.projectedVariables, c0.getChildren()[0], c1.getChildren()[0]))
                                    query.removePartitionOperator(c0.getUUID(), columnID)
                                    query.removePartitionOperator(c1.getUUID(), columnID)
                                    query.addPartitionOperator(res.getUUID(), columnID)
                                    query.partitionOperatorCount.clear()
                                    onChange()
                                }
                                else -> {
                                    TODO()
                                }
                            }
                        } else {
                            TODO()
                        }
                    }
                }
                is POPProjection -> {
                    when (val c = node.children[0]) {
                        is POPMergePartition -> {
                            res = createPOPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPProjection(query, node.projectedVariables, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            if (node.projectedVariables.contains(c.partitionVariable)) {
                                res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, c.partitionVariable, c.partitionCount2, c.partitionID, POPProjection(query, node.projectedVariables, c.children[0]))
                                res.setMySortPriority(c.mySortPriority, node.projectedVariables)
                            } else {
                                res = createPOPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount2, c.partitionID, POPProjection(query, node.projectedVariables, c.children[0]))
                            }
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionCount -> {
                            res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPProjection(query, node.projectedVariables, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                    }
                }
                is POPReduced -> {
                    when (val c = node.children[0]) {
                        is POPMergePartition -> {
                            res = createPOPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPReduced(query, node.projectedVariables, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            if (node.projectedVariables.contains(c.partitionVariable)) {
                                res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, c.partitionVariable, c.partitionCount2, c.partitionID, POPReduced(query, node.projectedVariables, c.children[0]))
                                res.setMySortPriority(c.mySortPriority, node.projectedVariables)
                            } else {
                                res = createPOPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount2, c.partitionID, POPReduced(query, node.projectedVariables, c.children[0]))
                            }
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionCount -> {
                            res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPReduced(query, node.projectedVariables, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                    }
                }
                is POPFilter -> {
                    when (val c = node.children[0]) {
                        is POPMergePartition -> {
                            res = createPOPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPFilter(query, node.projectedVariables, node.children[1] as AOPBase, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            if (node.projectedVariables.contains(c.partitionVariable)) {
                                res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, c.partitionVariable, c.partitionCount2, c.partitionID, POPFilter(query, node.projectedVariables, node.children[1] as AOPBase, c.children[0]))
                                res.setMySortPriority(c.mySortPriority, node.projectedVariables)
                            } else {
                                res = createPOPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount2, c.partitionID, POPFilter(query, node.projectedVariables, node.children[1] as AOPBase, c.children[0]))
                            }
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionCount -> {
                            res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPFilter(query, node.projectedVariables, node.children[1] as AOPBase, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                    }
                }
                is POPSplitPartition -> {
// splitting must always split all variables provided by its direct children - if there is a different children, adapt the variables
                    val partitionVariable = node.partitionVariable
                    when (val c = node.children[0]) {
                        is POPMergePartition -> {
                            if (partitionVariable == c.partitionVariable) {
                                if (node.partitionCount == c.partitionCount) {
                                    res = c.children[0]
                                    query.removePartitionOperator(c.getUUID(), c.partitionID)
                                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                                    query.mergePartitionOperator(node.partitionID, c.partitionID, res)
                                    query.partitionOperatorCount.clear()
                                    onChange()
                                } else if (node.partitionCount < c.partitionCount) {
                                    query.removePartitionOperator(c.getUUID(), c.partitionID)
                                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                                    res = POPChangePartitionOrderedByIntId(query, node.projectedVariables, partitionVariable!!, c.partitionCount, node.partitionCount, c.partitionID, node.partitionID, c.children[0])
                                    query.addPartitionOperator(res.getUUID(), res.partitionIDTo)
                                    query.addPartitionOperator(res.getUUID(), res.partitionIDFrom)
                                    onChange()
                                }
                            }
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            if (partitionVariable == c.partitionVariable) {
                                if (node.partitionCount == c.partitionCount2) {
                                    res = c.children[0]
                                    query.removePartitionOperator(c.getUUID(), c.partitionID)
                                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                                    query.mergePartitionOperator(node.partitionID, c.partitionID, res)
                                    query.partitionOperatorCount.clear()
                                    onChange()
                                } else if (node.partitionCount < c.partitionCount2) {
                                    query.removePartitionOperator(c.getUUID(), c.partitionID)
                                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                                    res = POPChangePartitionOrderedByIntId(query, node.projectedVariables, partitionVariable, c.partitionCount2, node.partitionCount, c.partitionID, node.partitionID, c.children[0])
                                    query.addPartitionOperator(res.getUUID(), res.partitionIDTo)
                                    query.addPartitionOperator(res.getUUID(), res.partitionIDFrom)
                                    onChange()
                                }
                            }
                        }
                        is POPMergePartitionCount -> {
                            if (partitionVariable == c.partitionVariable) {
                                if (node.partitionCount == c.partitionCount) {
                                    res = c.children[0]
                                    query.removePartitionOperator(c.getUUID(), c.partitionID)
                                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                                    query.mergePartitionOperator(node.partitionID, c.partitionID, res)
                                    query.partitionOperatorCount.clear()
                                    onChange()
                                }
                            }
                        }
                        is POPReduced -> {
                            res = POPReduced(query, c.projectedVariables, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), partitionVariable, node.partitionCount, node.partitionID, c.children[0]))
                            query.removePartitionOperator(node.getUUID(), node.partitionID)
                            query.addPartitionOperator(res.children[0].getUUID(), node.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPProjection -> {
                            res = POPProjection(query, c.projectedVariables, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), partitionVariable, node.partitionCount, node.partitionID, c.children[0]))
                            query.removePartitionOperator(node.getUUID(), node.partitionID)
                            query.addPartitionOperator(res.children[0].getUUID(), node.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPFilter -> {
                            res = POPFilter(query, c.projectedVariables, c.children[1] as AOPBase, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), partitionVariable, node.partitionCount, node.partitionID, c.children[0]))
                            query.removePartitionOperator(node.getUUID(), node.partitionID)
                            query.addPartitionOperator(res.children[0].getUUID(), node.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPTripleStoreIterator -> {
                            if (partitionVariable != null) {
                                val new_count = c.changeToIndexWithMaximumPartitions(node.partitionCount, partitionVariable)
                                if (new_count > 0) {
                                    c.hasSplitFromStore = true
                                    res = if (node.projectedVariables.isNotEmpty()) {
                                        POPSplitPartitionFromStore(query, node.projectedVariables, partitionVariable, new_count, node.partitionID, c)
                                    } else {
                                        POPSplitPartitionFromStoreCount(query, node.projectedVariables, partitionVariable, new_count, node.partitionID, c)
                                    }
                                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                                    query.addPartitionOperator(res.getUUID(), node.partitionID)
                                    onChange()
                                }
                            }
                        }
                    }
                }
            }
        }
        return res
    }
}
