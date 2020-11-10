package lupos.s10physicalOptimisation
import lupos.s00misc.USE_PARTITIONS
import lupos.s00misc.DontCareWhichException
import lupos.s00misc.EOptimizerID
import lupos.s00misc.Partition
import lupos.s00misc.TripleStoreLocal
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.TripleStoreFeature
import lupos.s05tripleStore.TripleStoreFeatureParamsPartition
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.partition.POPChangePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s09physicalOperators.singleinput.modifiers.POPReduced
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal

class PhysicalOptimizerPartition4(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerPartition4ID) {
    override val classname = "PhysicalOptimizerPartition4"
internal    fun getNumberOfEnclosingPartitions(node: IOPBase): Int {
        var count = 1
        val childs = node.getChildren()
        if (childs.size > 0) {
            val tmp = getNumberOfEnclosingPartitions(childs[0])
            count = tmp
        }
        when (node) {
            is POPSplitPartitionFromStore -> count = count * node.partitionCount
            is POPSplitPartition -> count = count * node.partitionCount
            is POPChangePartitionOrderedByIntId -> count = count * node.partitionCountTo / node.partitionCountFrom
            is POPMergePartition -> count = count / node.partitionCount
            is POPMergePartitionCount -> count = count / node.partitionCount
            is POPMergePartitionOrderedByIntId -> count = count / node.partitionCount
        }
        return count
    }

    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
if (USE_PARTITIONS && Partition.default_k > 1) {
        when (node) {
            is POPSplitPartitionFromStore -> {
                val tmp = query.partitionOperatorCount[node.partitionID]
                if (tmp != null && tmp != node.partitionCount) {
                    node.partitionCount = tmp
                    println("change ${node.getUUID()} 6")
                    onChange()
                }
                query.partitionOperatorCount[node.partitionID] = node.partitionCount
                var newCount = node.partitionCount
                val count = getNumberOfEnclosingPartitions(node.children[0]) * node.partitionCount
                if (count > Partition.default_k) {
                    val reduceFactor = count / Partition.default_k
                    if (reduceFactor > node.partitionCount) {
                        println("DEBUG a ${node.uuid} ${count} ${node.partitionCount} ${Partition.default_k} ${count / Partition.default_k} ${node.partitionCount / (count / Partition.default_k)}")
                        newCount = 1
                    } else {
                        println("DEBUG b ${node.uuid} ${count} ${node.partitionCount} ${Partition.default_k} ${count / Partition.default_k} ${node.partitionCount / (count / Partition.default_k)}")
                        newCount = node.partitionCount / reduceFactor
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
                    println("change ${node.getUUID()} 6")
                    onChange()
                }
                query.partitionOperatorCount[node.partitionID] = node.partitionCount
                var newCount = node.partitionCount
                val count = getNumberOfEnclosingPartitions(node.children[0]) * node.partitionCount
                if (count > Partition.default_k) {
                    val reduceFactor = count / Partition.default_k
                    if (reduceFactor > node.partitionCount) {
                        println("DEBUG c ${node.uuid} ${count} ${node.partitionCount} ${Partition.default_k} ${count / Partition.default_k} ${node.partitionCount / (count / Partition.default_k)}")
                        newCount = 1
                    } else {
                        println("DEBUG d ${node.uuid} ${count} ${node.partitionCount} ${Partition.default_k} ${count / Partition.default_k} ${node.partitionCount / (count / Partition.default_k)}")
                        newCount = node.partitionCount / reduceFactor
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
                    println("change ${node.getUUID()} 6")
                    onChange()
                }
            }
            is POPMergePartitionCount -> {
                val tmp = query.partitionOperatorCount[node.partitionID]
                if (tmp != null && tmp != node.partitionCount) {
                    node.partitionCount = tmp
                    println("change ${node.getUUID()} 6")
                    onChange()
                }
            }
            is POPMergePartitionOrderedByIntId -> {
                val tmp = query.partitionOperatorCount[node.partitionID]
                if (tmp != null && tmp != node.partitionCount) {
                    node.partitionCount = tmp
                    println("change ${node.getUUID()} 6")
                    onChange()
                }
            }
            is POPChangePartitionOrderedByIntId -> {
                val tmp = query.partitionOperatorCount[node.partitionIDFrom]
                if (tmp != null && tmp != node.partitionCountFrom) {
                    node.partitionCountFrom = tmp
                    println("change ${node.getUUID()} 6")
                    onChange()
                }
                val tmp2 = query.partitionOperatorCount[node.partitionIDTo]
                if (tmp2 != null && tmp2 != node.partitionCountTo) {
                    node.partitionCountTo = tmp2
                    println("change ${node.getUUID()} 6")
                    onChange()
                }
                query.partitionOperatorCount[node.partitionIDTo] = node.partitionCountTo
                var newCount = node.partitionCountTo
                val count = getNumberOfEnclosingPartitions(node.children[0]) * node.partitionCountTo / node.partitionCountFrom
                if (count > Partition.default_k) {
                    val reduceFactor = count / Partition.default_k
                    if (reduceFactor > node.partitionCountTo) {
                        println("DEBUG g ${node.uuid} ${count} ${node.partitionCountTo} ${Partition.default_k} ${count / Partition.default_k} ${node.partitionCountTo / (count / Partition.default_k)}")
                        newCount = 1
                    } else {
                        println("DEBUG h ${node.uuid} ${count} ${node.partitionCountTo} ${Partition.default_k} ${count / Partition.default_k} ${node.partitionCountTo / (count / Partition.default_k)}")
                        newCount = node.partitionCountTo / reduceFactor
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
