package lupos.s10physicalOptimisation
import lupos.s00misc.EIndexPatternHelper
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EOptimizerIDExt
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.USE_PARTITIONS
import lupos.s04arithmetikOperators.noinput.IAOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.partition.POPChangePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal
import lupos.s15tripleStoreDistributed.distributedTripleStore
public class PhysicalOptimizerPartition2(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerPartition2ID) {
    override val classname: String = "PhysicalOptimizerPartition2"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        if (USE_PARTITIONS && Partition.default_k > 1) {
            when (node) {
                is POPSplitPartitionFromStore -> {
                    var storeNodeTmp = node.children[0]
                    while (storeNodeTmp !is TripleStoreIteratorGlobal) {
// this is POPDebug or something similar with is not affecting the calculation - otherwise this node wont be POPSplitPartitionFromStore
                        storeNodeTmp = storeNodeTmp.getChildren()[0]
                    }
                    val storeNode = storeNodeTmp
                    val idx = storeNode.idx
                    var partitionColumn = 0
                    for (ii in 0 until 3) {
                        val i = EIndexPatternHelper.tripleIndicees[idx][ii]
                        val param = storeNode.children[i]
                        if (param is IAOPVariable) {
                            if (param.getName() == node.partitionVariable) {
                                break
                            } else {
                                partitionColumn++
                            }
                        } else {
                            partitionColumn++ // constants at the front do count
                        }
                    }
                    SanityCheck.check({ partitionColumn in 1..2 }, { "$partitionColumn ${node.partitionVariable} ${EIndexPatternExt.names[idx]} ${EIndexPatternHelper.tripleIndicees[idx].map { it }} ${storeNode.children.map { "${(it as OPBase).classname} ${(it as? IAOPVariable)?.getName()}" }}" })
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
                    if (tmp == null || count < tmp) {
                        query.partitionOperatorCount[node.partitionID] = count
                        node.partitionCount = count
                        storeNode.partition.limit[node.partitionVariable] = count
                        onChange()
                    } else if (node.partitionCount != tmp) {
                        node.partitionCount = tmp
                        storeNode.partition.limit[node.partitionVariable] = tmp
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
                is POPSplitPartition -> {
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
                }
            }
        }
        return node
    }
}
