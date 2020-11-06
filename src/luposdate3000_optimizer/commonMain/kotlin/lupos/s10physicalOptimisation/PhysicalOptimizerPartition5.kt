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

class PhysicalOptimizerPartition5(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerPartition5ID) {
    override val classname = "PhysicalOptimizerPartition5"
    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        when (node) {
            is POPSplitPartitionFromStore -> {
                if (node.partitionCount == 1) {
                    res = node.children[0]
                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                    onChange()
                }
            }
            is POPSplitPartition -> {
                if (node.partitionCount == 1) {
                    res = node.children[0]
                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                    onChange()
                }
            }
            is POPMergePartition -> {
                if (node.partitionCount == 1) {
                    res = node.children[0]
                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                    onChange()
                }
            }
            is POPMergePartitionCount -> {
                if (node.partitionCount == 1) {
                    res = node.children[0]
                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                    onChange()
                }
            }
            is POPMergePartitionOrderedByIntId -> {
                if (node.partitionCount == 1) {
                    res = node.children[0]
                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                    onChange()
                }
            }
            is POPChangePartitionOrderedByIntId -> {
                if (node.partitionCountFrom == 1 && node.partitionCountTo == 1) {
                    res = node.children[0]
                    query.removePartitionOperator(node.getUUID(), node.partitionIDFrom)
                    query.removePartitionOperator(node.getUUID(), node.partitionIDTo)
                    onChange()
                } else if (node.partitionCountFrom == 1) {
                    res = POPSplitPartition(query, node.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCountTo, node.partitionIDTo, node.children[0])
                    query.removePartitionOperator(node.getUUID(), node.partitionIDFrom)
                    query.removePartitionOperator(node.getUUID(), node.partitionIDTo)
                    query.addPartitionOperator(res.getUUID(), res.partitionID)
                    onChange()
                } else if (node.partitionCountTo == 1) {
                    res = POPMergePartitionOrderedByIntId(query, node.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCountFrom, node.partitionIDFrom, node.children[0])
                    query.removePartitionOperator(node.getUUID(), node.partitionIDFrom)
                    query.removePartitionOperator(node.getUUID(), node.partitionIDTo)
                    query.addPartitionOperator(res.getUUID(), res.partitionID)
                    onChange()
                }
            }
        }
        return res
    }
}
