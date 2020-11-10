package lupos.s10physicalOptimisation
import lupos.s00misc.USE_PARTITIONS
import lupos.s00misc.DontCareWhichException
import lupos.s00misc.EOptimizerID
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.TripleStoreLocal
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.IAOPVariable
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
import lupos.s15tripleStoreDistributed.distributedTripleStore
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal

class PhysicalOptimizerPartition2(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerPartition2ID) {
    override val classname = "PhysicalOptimizerPartition2"
    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
if (USE_PARTITIONS && Partition.default_k > 1) {
        when (node) {
            is POPSplitPartitionFromStore -> {
                var storeNodeTmp = node.children[0]
                while (storeNodeTmp !is TripleStoreIteratorGlobal) {
//this is POPDebug or something similar with is not affecting the calculation - otherwise this node wont be POPSplitPartitionFromStore
                    storeNodeTmp = storeNodeTmp.getChildren()[0]
                }
                SanityCheck.check { storeNodeTmp is TripleStoreIteratorGlobal }
                val storeNode = storeNodeTmp as TripleStoreIteratorGlobal
                val idx = storeNode.idx
                var partitionColumn = 0
                for (ii in 0 until 3) {
                    val i = idx.tripleIndicees[ii]
                    val param = storeNode.children[i]
                    if (param is IAOPVariable) {
                        if (param.getName() == node.partitionVariable) {
                            break
                        } else {
                            partitionColumn++
                        }
                    } else {
                        partitionColumn++ //constants at the front do count
                    }
                }
                SanityCheck.check({ partitionColumn <= 2 && partitionColumn >= 1 }, { "$partitionColumn ${node.partitionVariable} $idx ${idx.tripleIndicees.map { it }} ${storeNode.children.map { "${(it as OPBase).classname} ${(it as? IAOPVariable)?.getName()}" }}" })
                var count = 1
                val partitions = distributedTripleStore.getLocalStore().getDefaultGraph(query).getEnabledPartitions()
                for (p in partitions) {
                    if (p.index.contains(idx) && p.column == partitionColumn) {
                        if (p.partitionCount > count) {
                            count = p.partitionCount
                        }
                    }
                }
                val tmp = query.partitionOperatorCount[node.partitionID]
                println("check ${node.getUUID()} $tmp $count ${node.partitionID}")
                if (tmp == null || count < tmp) {
                    query.partitionOperatorCount[node.partitionID] = count
                    node.partitionCount = count
                    storeNode.partition.limit[node.partitionVariable] = count
                    println("change ${node.getUUID()} $tmp $count ${node.partitionID} 1")
                    onChange()
                } else if (tmp != null && node.partitionCount != tmp) {
                    node.partitionCount = tmp
                    storeNode.partition.limit[node.partitionVariable] = tmp
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
            }
        }
}
        return res
    }
}
