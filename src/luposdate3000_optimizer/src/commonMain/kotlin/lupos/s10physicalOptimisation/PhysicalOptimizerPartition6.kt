package lupos.s10physicalOptimisation
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s00misc.DontCareWhichException
import lupos.s00misc.EOptimizerID
import lupos.s00misc.Partition
import lupos.s00misc.TripleStoreLocal
import lupos.s00misc.USE_PARTITIONS
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.TripleStoreFeature
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal
import lupos.s15tripleStoreDistributed.distributedTripleStore
class PhysicalOptimizerPartition6(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerPartition6ID) {
    override val classname: String = "PhysicalOptimizerPartition6"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if (USE_PARTITIONS && Partition.default_k > 1) {
            when (node) {
                is TripleStoreIteratorGlobal -> {
                    if (TripleStoreLocal.providesFeature(TripleStoreFeature.PARTITION, null)) {
                        if (node.partition.limit.size == 0) {

val enabledPartitions=distributedTripleStore.getLocalStore().getEnabledPartitions(node.graphName)
                            var countToUse = enabledPartitions[0].partitionCount
			var columnToUse=enabledPartitions[0].column
var idx=0
for (p in enabledPartitions) {
                    if (p.index.contains(node.idx)&&p.partitionCount<countToUse) {
columnToUse=p.column
countToUse=p.partitionCount
                    }
                    idx++
                }
val variableToUse=(node.children[node.idx.tripleIndicees[columnToUse]]as AOPVariable).name
                            try {
                                val p = Partition(Partition(), variableToUse, columnToUse, countToUse)
                                val partitionID = query.getNextPartitionOperatorID()
                                res = POPSplitPartitionFromStore(query, node.projectedVariables, variableToUse, countToUse, partitionID, node)
                                query.addPartitionOperator(res.getUUID(), partitionID)
                                res = POPMergePartition(query, node.projectedVariables, variableToUse, countToUse, partitionID, res)
                                query.addPartitionOperator(res.getUUID(), partitionID)
                                onChange()
                            } catch (e: DontCareWhichException) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
        }
        return res
    }
}


//fun getEnabledPartitions():Array<EnabledPartitionContainer> 
