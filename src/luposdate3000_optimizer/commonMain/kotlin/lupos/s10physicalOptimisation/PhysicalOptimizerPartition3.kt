package lupos.s10physicalOptimisation

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
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s09physicalOperators.singleinput.modifiers.POPReduced
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal
import lupos.s15tripleStoreDistributed.distributedTripleStore

class PhysicalOptimizerPartition3(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerPartition3ID) {
    override val classname = "PhysicalOptimizerPartition3"
    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        when (node) {
            is POPSplitPartitionFromStore -> {
                val storeNode = node.children[0] as TripleStoreIteratorGlobal
                val idx = storeNode.idx
                var count = 1
                val partitions = distributedTripleStore.getLocalStore().getDefaultGraph(query).getEnabledPartitions()
                for (p in partitions) {
                    if (p.index.contains(idx)) {
                        if (p.partitionCount > count) {
                            count = p.partitionCount
                        }
                    }
                }
                val tmp = query.partitionOperatorCount[node.partitionID]
                if (tmp == null || count < tmp) {
                    query.partitionOperatorCount[node.partitionID] = count
                    node.partitionCount = count
                    println("change ${node.getUUID()} $tmp $count ${node.partitionID} 1")
                    onChange()
                } else if (tmp != null && count != tmp) {
                    node.partitionCount = count
                    println("change ${node.getUUID()} 2")
                    onChange()
                }
            }
            is POPMergePartition -> {
                val tmp = query.partitionOperatorCount[node.partitionID]
                if (tmp != null && tmp != node.partitionCount) {
                    node.partitionCount = tmp
                    println("change ${node.getUUID()} 3")
                    onChange()
                }
            }
            is POPMergePartitionCount -> {
                val tmp = query.partitionOperatorCount[node.partitionID]
                if (tmp != null && tmp != node.partitionCount) {
                    node.partitionCount = tmp
                    println("change ${node.getUUID()} 4")
                    onChange()
                }
            }
            is POPMergePartitionOrderedByIntId -> {
                val tmp = query.partitionOperatorCount[node.partitionID]
                if (tmp != null && tmp != node.partitionCount) {
                    node.partitionCount = tmp
                    println("change ${node.getUUID()} 5")
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
            }
        }
        return res
    }
}
