package lupos.s10physicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.Partition
import lupos.s00misc.USE_PARTITIONS
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.partition.*

class PhysicalOptimizerPartition4(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerPartition4ID) {
    override val classname = "PhysicalOptimizerPartition4"
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
        val res = node
        if (USE_PARTITIONS && Partition.default_k > 1) {
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
        return res
    }
}
