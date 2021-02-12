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
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.partition.POPChangePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore

public class PhysicalOptimizerPartition4(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerPartition4ID, "PhysicalOptimizerPartition4") {
    private fun getNumberOfEnclosingPartitions(node: IOPBase): Int {
        var count = 1
        val childs = node.getChildren()
        if (childs.isNotEmpty()) {
            val tmp = getNumberOfEnclosingPartitions(childs[0])
            count = tmp
        }
        when (node) {
            is POPSplitPartitionFromStore -> count *= node.partitionCount
            is POPSplitPartition -> count *= node.partitionCount
            is POPChangePartitionOrderedByIntId -> count = count * node.partitionCountTo / node.partitionCountFrom
            is POPMergePartition -> count /= node.partitionCount
            is POPMergePartitionCount -> count /= node.partitionCount
            is POPMergePartitionOrderedByIntId -> count /= node.partitionCount
        }
        return count
    }

    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        if ((USE_PARTITIONS == EPartitionModeExt.Thread || USE_PARTITIONS == EPartitionModeExt.Process) && Partition.default_k > 1) {
            when (node) {
                is POPSplitPartitionFromStore -> {
                    val tmp = query.partitionOperatorCount[node.partitionID]
                    if (tmp != null && tmp != node.partitionCount) {
                        node.partitionCount = tmp
                        onChange()
                    }
                    query.partitionOperatorCount[node.partitionID] = node.partitionCount
                    var newCount = node.partitionCount
                    val count = getNumberOfEnclosingPartitions(node.children[0]) * node.partitionCount
                    if (count > Partition.default_k) {
                        val reduceFactor = count / Partition.default_k
                        newCount = if (reduceFactor > node.partitionCount) {
                            1
                        } else {
                            node.partitionCount / reduceFactor
                        }
                    }
                    if (newCount < node.partitionCount) {
                        node.partitionCount = newCount
                        query.partitionOperatorCount[node.partitionID] = newCount
                        onChange()
                    }
                }
                is POPSplitPartition -> {
                    val tmp = query.partitionOperatorCount[node.partitionID]
                    if (tmp != null && tmp != node.partitionCount) {
                        node.partitionCount = tmp
                        onChange()
                    }
                    query.partitionOperatorCount[node.partitionID] = node.partitionCount
                    var newCount = node.partitionCount
                    val count = getNumberOfEnclosingPartitions(node.children[0]) * node.partitionCount
                    if (count > Partition.default_k) {
                        val reduceFactor = count / Partition.default_k
                        newCount = if (reduceFactor > node.partitionCount) {
                            1
                        } else {
                            node.partitionCount / reduceFactor
                        }
                    }
                    if (newCount < node.partitionCount) {
                        node.partitionCount = newCount
                        query.partitionOperatorCount[node.partitionID] = newCount
                        onChange()
                    }
                }
                is POPMergePartition -> {
                    val tmp = query.partitionOperatorCount[node.partitionID]
                    if (tmp != null && tmp != node.partitionCount) {
                        node.partitionCount = tmp
                        onChange()
                    }
                }
                is POPMergePartitionCount -> {
                    val tmp = query.partitionOperatorCount[node.partitionID]
                    if (tmp != null && tmp != node.partitionCount) {
                        node.partitionCount = tmp
                        onChange()
                    }
                }
                is POPMergePartitionOrderedByIntId -> {
                    val tmp = query.partitionOperatorCount[node.partitionID]
                    if (tmp != null && tmp != node.partitionCount) {
                        node.partitionCount = tmp
                        onChange()
                    }
                }
                is POPChangePartitionOrderedByIntId -> {
                    val tmp = query.partitionOperatorCount[node.partitionIDFrom]
                    if (tmp != null && tmp != node.partitionCountFrom) {
                        node.partitionCountFrom = tmp
                        onChange()
                    }
                    val tmp2 = query.partitionOperatorCount[node.partitionIDTo]
                    if (tmp2 != null && tmp2 != node.partitionCountTo) {
                        node.partitionCountTo = tmp2
                        onChange()
                    }
                    query.partitionOperatorCount[node.partitionIDTo] = node.partitionCountTo
                    var newCount = node.partitionCountTo
                    val count = getNumberOfEnclosingPartitions(node.children[0]) * node.partitionCountTo / node.partitionCountFrom
                    if (count > Partition.default_k) {
                        val reduceFactor = count / Partition.default_k
                        newCount = if (reduceFactor > node.partitionCountTo) {
                            1
                        } else {
                            node.partitionCountTo / reduceFactor
                        }
                    }
                    if (newCount < node.partitionCountTo) {
                        node.partitionCountTo = newCount
                        query.partitionOperatorCount[node.partitionIDTo] = newCount
                        onChange()
                    }
                }
            }
        }
        return node
    }
}
