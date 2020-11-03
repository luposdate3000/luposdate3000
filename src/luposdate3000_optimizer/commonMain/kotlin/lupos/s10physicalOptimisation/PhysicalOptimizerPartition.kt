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

class PhysicalOptimizerPartition(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerPartitionID) {
    override val classname = "PhysicalOptimizerPartition"
    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        when (node) {
            is POPBind -> {
                val c = node.children[0]
                if (c is POPMergePartition) {
                    res = POPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount, POPBind(query, node.projectedVariables, node.name, node.children[1] as AOPBase, c.children[0]))
                    onChange()
                } else if (c is POPMergePartitionOrderedByIntId) {
                    res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, c.partitionVariable, c.partitionCount, POPBind(query, node.projectedVariables, node.name, node.children[1] as AOPBase, c.children[0]))
                    onChange()
                } else if (c is POPMergePartitionCount) {
                    res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, c.partitionCount, POPBind(query, node.projectedVariables, node.name, node.children[1] as AOPBase, c.children[0]))
                    onChange()
                }
            }
            is POPUnion -> {
                val c0 = node.children[0]
                val c1 = node.children[1]
                var modeC0 = 0
                var modeC1 = 0
                var columnNameC0 = ""
                var columnCountC0 = 0
                var columnNameC1 = ""
                var columnCountC1 = 0
                if (c0 is POPMergePartition) {
                    modeC0 = 1
                    columnNameC0 = c0.partitionVariable
                    columnCountC0 = c0.partitionCount
                } else if (c0 is POPMergePartitionOrderedByIntId) {
                    modeC0 = 2
                    columnNameC0 = c0.partitionVariable
                    columnCountC0 = c0.partitionCount
                } else if (c0 is POPMergePartitionCount) {
                    modeC0 = 3
                    columnNameC0 = c0.partitionVariable
                    columnCountC0 = c0.partitionCount
                }
                if (c1 is POPMergePartition) {
                    modeC1 = 1
                    columnNameC1 = c1.partitionVariable
                    columnCountC1 = c1.partitionCount
                } else if (c1 is POPMergePartitionOrderedByIntId) {
                    modeC1 = 2
                    columnNameC1 = c1.partitionVariable
                    columnCountC1 = c1.partitionCount
                } else if (c1 is POPMergePartitionCount) {
                    modeC1 = 3
                    columnNameC1 = c1.partitionVariable
                    columnCountC1 = c1.partitionCount
                }
                if (modeC0 == modeC1 && columnNameC0 == columnNameC1 && modeC0 > 0) {
                    if (columnCountC0 == columnCountC1) {
                        if (modeC0 == 1) {
                            res = POPMergePartition(query, node.projectedVariables, columnNameC0, columnCountC0, POPUnion(query, node.projectedVariables, c0.getChildren()[0], c1.getChildren()[0]))
                            onChange()
                        } else if (modeC0 == 2) {
                            res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, columnNameC0, columnCountC0, POPUnion(query, node.projectedVariables, c0.getChildren()[0], c1.getChildren()[0]))
                            onChange()
                        } else if (modeC0 == 3) {
                            res = POPMergePartitionCount(query, node.projectedVariables, columnNameC0, columnCountC0, POPUnion(query, node.projectedVariables, c0.getChildren()[0], c1.getChildren()[0]))
                            onChange()
                        }
                    } else {
                        throw Exception("not implemented ... column counts are different")
                    }
                }
            }
            is POPProjection -> {
                val c = node.children[0]
                if (c is POPMergePartition) {
                    res = POPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount, POPProjection(query, node.projectedVariables, c.children[0]))
                    onChange()
                } else if (c is POPMergePartitionOrderedByIntId) {
                    res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, c.partitionVariable, c.partitionCount, POPProjection(query, node.projectedVariables, c.children[0]))
                    onChange()
                } else if (c is POPMergePartitionCount) {
                    res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, c.partitionCount, POPProjection(query, node.projectedVariables, c.children[0]))
                    onChange()
                }
            }
            is POPReduced -> {
                val c = node.children[0]
                if (c is POPMergePartition) {
                    res = POPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount, POPReduced(query, node.projectedVariables, c.children[0]))
                    onChange()
                } else if (c is POPMergePartitionOrderedByIntId) {
                    res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, c.partitionVariable, c.partitionCount, POPReduced(query, node.projectedVariables, c.children[0]))
                    onChange()
                } else if (c is POPMergePartitionCount) {
                    res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, c.partitionCount, POPReduced(query, node.projectedVariables, c.children[0]))
                    onChange()
                }
            }
            is POPFilter -> {
                val c = node.children[0]
                if (c is POPMergePartition) {
                    res = POPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount, POPFilter(query, node.projectedVariables, node.children[1] as AOPBase, c.children[0]))
                    onChange()
                } else if (c is POPMergePartitionOrderedByIntId) {
                    res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, c.partitionVariable, c.partitionCount, POPFilter(query, node.projectedVariables, node.children[1] as AOPBase, c.children[0]))
                    onChange()
                } else if (c is POPMergePartitionCount) {
                    res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, c.partitionCount, POPFilter(query, node.projectedVariables, node.children[1] as AOPBase, c.children[0]))
                    onChange()
                }
            }
            is POPSplitPartition -> {
//splitting must always split all variables provided by its direct children - if there is a different children, adapt the variables
                val c = node.children[0]
                when (c) {
                    is POPMergePartition -> {
                        if (node.partitionVariable == c.partitionVariable && node.partitionCount == c.partitionCount) {
                            res = c.children[0]
                            onChange()
                        }
                    }
                    is POPMergePartitionOrderedByIntId -> {
                        if (node.partitionVariable == c.partitionVariable && node.partitionCount == c.partitionCount) {
                            res = c.children[0]
                            onChange()
                        }
                    }
                    is POPMergePartitionCount -> {
                        if (node.partitionVariable == c.partitionVariable && node.partitionCount == c.partitionCount) {
                            res = c.children[0]
                            onChange()
                        }
                    }
                    is POPReduced -> {
                        res = POPReduced(query, c.projectedVariables, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, c.children[0]))
                        onChange()
                    }
                    is POPProjection -> {
                        res = POPProjection(query, c.projectedVariables, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, c.children[0]))
                        onChange()
                    }
                    is POPFilter -> {
                        res = POPFilter(query, c.projectedVariables, c.children[1] as AOPBase, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, c.children[0]))
                        onChange()
                    }
                    is TripleStoreIteratorGlobal -> {
                        if (TripleStoreLocal.providesFeature(TripleStoreFeature.PARTITION, null)) {
                            try {
                                val p = Partition(Partition(), node.partitionVariable, 0, node.partitionCount)
                                val params = TripleStoreFeatureParamsPartition(c.idx, Array(3) { c.children[it] as AOPBase }, p)
                                if (params.getColumn() > 0 && TripleStoreLocal.providesFeature(TripleStoreFeature.PARTITION, params)) {
                                    res = POPSplitPartitionFromStore(query, node.projectedVariables, node.partitionVariable, node.partitionCount, c)
                                    c.partition.limit[node.partitionVariable] = node.partitionCount
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
