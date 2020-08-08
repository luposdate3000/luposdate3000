package lupos.s10physicalOptimisation

import kotlinx.coroutines.GlobalScope
import lupos.s00misc.DontCareWhichException
import lupos.s00misc.EOptimizerID
import lupos.s00misc.Partition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.TripleStoreFeature
import lupos.s05tripleStore.TripleStoreFeatureParamsPartition
import lupos.s05tripleStore.TripleStoreLocal
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s09physicalOperators.singleinput.modifiers.POPReduced
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal

class PhysicalOptimizerPartition(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerPartitionID) {
    override val classname = "PhysicalOptimizerPartition"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res = node
        when (node) {
            is POPProjection -> {
                val c = node.children[0]
                if (c is POPMergePartition) {
                    res = POPMergePartition(query, node.projectedVariables, c.partitionVariable, POPProjection(query, node.projectedVariables, c.children[0]))
                    onChange()
                } else if (c is POPMergePartitionCount) {
                    res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, POPProjection(query, node.projectedVariables, c.children[0]))
                    onChange()
                }
            }
            is POPReduced -> {
                val c = node.children[0]
                if (c is POPMergePartition) {
                    res = POPMergePartition(query, node.projectedVariables, c.partitionVariable, POPReduced(query, node.projectedVariables, c.children[0]))
                    onChange()
                } else if (c is POPMergePartitionCount) {
                    res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, POPReduced(query, node.projectedVariables, c.children[0]))
                    onChange()
                }
            }
            is POPFilter -> {
                val c = node.children[0]
                if (c is POPMergePartition) {
                    res = POPMergePartition(query, node.projectedVariables, c.partitionVariable, POPFilter(query, node.projectedVariables, node.children[1] as AOPBase, c.children[0]))
                    onChange()
                } else if (c is POPMergePartitionCount) {
                    res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, POPFilter(query, node.projectedVariables, node.children[1] as AOPBase, c.children[0]))
                    onChange()
                }
            }
            is POPSplitPartition -> {
//splitting must always split all variables provided by its direct children - if there is a different children, adapt the variables
                val c = node.children[0]
                when (c) {
                    is POPMergePartition -> {
                        if (node.partitionVariable == c.partitionVariable) {
                            res = c.children[0]
                            onChange()
                        }
                    }
                    is POPMergePartitionCount -> {
                        if (node.partitionVariable == c.partitionVariable) {
                            res = c.children[0]
                            onChange()
                        }
                    }
                    is POPReduced -> {
                        res = POPReduced(query, c.projectedVariables, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, c.children[0]))
                        onChange()
                    }
                    is POPProjection -> {
                        res = POPProjection(query, c.projectedVariables, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, c.children[0]))
                        onChange()
                    }
                    is POPFilter -> {
                        res = POPFilter(query, c.projectedVariables, c.children[1] as AOPBase, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, c.children[0]))
                        onChange()
                    }
                    is TripleStoreIteratorGlobal -> {
                        if (TripleStoreLocal.providesFeature(TripleStoreFeature.PARTITION, null)) {
                            try {
                                val p = Partition(Partition(), node.partitionVariable, 0, GlobalScope)
                                val params = TripleStoreFeatureParamsPartition(c.idx, Array(3) { c.children[it] as AOPBase }, p)
                                if (params.getColumn() > 0 && TripleStoreLocal.providesFeature(TripleStoreFeature.PARTITION, params)) {
                                    res = POPSplitPartitionFromStore(query, node.projectedVariables, node.partitionVariable, c)
                                    onChange()
                                }
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
