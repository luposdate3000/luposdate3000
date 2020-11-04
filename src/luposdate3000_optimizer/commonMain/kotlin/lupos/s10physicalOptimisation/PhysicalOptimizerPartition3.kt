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

class PhysicalOptimizerPartition3(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerPartition3ID) {
    override val classname = "PhysicalOptimizerPartition3"
    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        when (node) {
            is POPSplitPartitionFromStore-> {
val storeNode=node.children[0]as TripleStoreIteratorGlobal
         val idx=storeNode.idx
       var count = 1
for(p in TripleStoreLocalBase.enabledPartitions){
if(p.index.contains(idx)){
if(p.partitionCount>count){
count=p.partitionCount
}
}
}
                val tmp = query.partitionOperatorCount[node.partitionID]
                if (tmp == null || count < tmp) {
                    query.partitionOperatorCount[node.partitionID] = count
			node.partitionCount = count
                    onChange()
                } else if(tmp != null&&count != tmp){
                    node.partitionCount = count
                    onChange()
                }
            }
            is POPMergePartition -> {
                val tmp = query.partitionOperatorCount[node.partitionID]
                if (tmp!=null&&tmp != node.partitionCount) {
                    node.partitionCount = tmp
                    onChange()
		}
            }
            is POPMergePartitionCount -> {
                val tmp = query.partitionOperatorCount[node.partitionID]
                if (tmp!=null&&tmp != node.partitionCount) {
                    node.partitionCount = tmp
                    onChange()
                }
            }
            is POPMergePartitionOrderedByIntId -> {
                val tmp = query.partitionOperatorCount[node.partitionID]
                if (tmp!=null&&tmp != node.partitionCount) {
                    node.partitionCount = tmp
                    onChange()
                }
            }
            is POPSplitPartition -> {
                val tmp = query.partitionOperatorCount[node.partitionID]
                if (tmp!=null&&tmp != node.partitionCount) {
                    node.partitionCount = tmp
                    onChange()
                }
            }
        }
        return res
    }
}
