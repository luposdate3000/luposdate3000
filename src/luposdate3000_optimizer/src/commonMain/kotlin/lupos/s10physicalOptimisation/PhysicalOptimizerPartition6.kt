package lupos.s10physicalOptimisation
import lupos.s00misc.DontCareWhichException
import lupos.s00misc.EOptimizerIDExt
import lupos.s00misc.ESortTypeExt
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EIndexPatternHelper
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.SortHelper
import lupos.s00misc.TripleStoreLocal
import lupos.s00misc.USE_PARTITIONS
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.TripleStoreFeatureExt
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal
import lupos.s15tripleStoreDistributed.distributedTripleStore
public class PhysicalOptimizerPartition6(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerPartition6ID) {
    override val classname: String = "PhysicalOptimizerPartition6"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        println("PhysicalOptimizerPartition6 :: $USE_PARTITIONS ${Partition.default_k}")
        if (USE_PARTITIONS && Partition.default_k > 1) {
            println("PhysicalOptimizerPartition6 :: should optimize 1")
            when (node) {
                is TripleStoreIteratorGlobal -> {
                    println("PhysicalOptimizerPartition6 :: should optimize 2")
                    if (TripleStoreLocal.providesFeature(TripleStoreFeatureExt.PARTITION, null)) {
                        println("PhysicalOptimizerPartition6 :: should optimize 3 ${node.partition.limit}")
                        if (node.partition.limit.isEmpty()) {
                            println("PhysicalOptimizerPartition6 :: should optimize 4")
                            val enabledPartitions = distributedTripleStore.getLocalStore().getEnabledPartitions(node.graphName)
                            var countToUse = -1
                            var columnToUse = -1
                            var idx = 0
                            for (p in enabledPartitions) {
                                if (p.index.contains(node.idx) && (p.partitionCount <countToUse || countToUse == -1) && (node.children[EIndexPatternHelper.tripleIndicees[node.idx][p.column]]is AOPVariable)) {
                                    columnToUse = p.column
                                    countToUse = p.partitionCount
                                }
                                idx++
                            }
                            var variableToUse = ""
                            if (columnToUse == -1) {
                                for (p in enabledPartitions) {
                                    if (p.index.contains(node.idx) && (p.partitionCount <countToUse || countToUse == -1)) {
                                        columnToUse = p.column
                                        countToUse = p.partitionCount
                                    }
                                    idx++
                                }
                                variableToUse = "_$columnToUse"
                            } else {
                                variableToUse = (node.children[EIndexPatternHelper.tripleIndicees[node.idx][columnToUse]]as AOPVariable).name
                                if (variableToUse == "_") {
                                    variableToUse = "_$columnToUse"
                                }
                            }
                            println("PhysicalOptimizerPartition6 :: $countToUse $columnToUse $idx $variableToUse")
                            try {
                                val partitionID = query.getNextPartitionOperatorID()
                                node.partition.limit.clear()
                                node.partition.limit[variableToUse] = countToUse
                                res = POPSplitPartitionFromStore(query, node.projectedVariables, variableToUse, countToUse, partitionID, node)
                                query.addPartitionOperator(res.getUUID(), partitionID)
                                if (node.projectedVariables.isNotEmpty()) {
                                    res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, variableToUse, countToUse, partitionID, res)
                                    for (i in EIndexPatternHelper.valueIndices[node.idx]) {
                                        val c = node.children[i]
                                        SanityCheck.check { c is AOPVariable }
                                        if (c is AOPVariable && c.name != "_") {
                                            res.mySortPriority.add(SortHelper(c.name, ESortTypeExt.FAST))
                                        }
                                    }
                                } else {
                                    res = POPMergePartitionCount(query, node.projectedVariables, variableToUse, countToUse, partitionID, res)
                                }
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
