package lupos.s10physicalOptimisation
import lupos.s00misc.DontCareWhichException
import lupos.s00misc.EIndexPatternHelper
import lupos.s00misc.EOptimizerIDExt
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.TripleStoreLocal
import lupos.s00misc.USE_PARTITIONS
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.IAOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.TripleStoreFeatureExt
import lupos.s05tripleStore.TripleStoreFeatureParamsPartition
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.partition.POPChangePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.modifiers.POPReduced
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal
import lupos.s15tripleStoreDistributed.distributedTripleStore
public class PhysicalOptimizerPartition3(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerPartition3ID) {
    override val classname: String = "PhysicalOptimizerPartition3"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
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
                        val i = EIndexPatternHelper.tripleIndicees[idx.ordinal][ii]
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
                    SanityCheck.check({ partitionColumn in 1..2 }, { "$partitionColumn ${node.partitionVariable} $idx ${EIndexPatternHelper.tripleIndicees[idx.ordinal].map { it }} ${storeNode.children.map { "${(it as OPBase).classname} ${(it as? IAOPVariable)?.getName()}" }}" })
                    var count = -1
                    val partitions = distributedTripleStore.getLocalStore().getDefaultGraph(query).getEnabledPartitions()
                    for (p in partitions) {
                        if (p.index.contains(idx) && p.column == partitionColumn) {
                            if (count == -1 || (p.partitionCount >= count && p.partitionCount <= node.partitionCount)) {
                                count = p.partitionCount
                            }
                        }
                    }
// SanityCheck failed :: -1 0 P_SO 1
                    SanityCheck.check({ count != -1 }, { "$count $partitionColumn $idx ${node.partitionCount}" })
                    if (count != node.partitionCount) {
                        val newID = query.getNextPartitionOperatorID()
                        query.removePartitionOperator(node.getUUID(), node.partitionID)
                        res = POPChangePartitionOrderedByIntId(query, node.projectedVariables, node.partitionVariable, count, node.partitionCount, newID, node.partitionID, node)
                        node.partitionID = newID
                        node.partitionCount = count
                        storeNode.partition.limit[node.partitionVariable] = count
                        query.addPartitionOperator(node.getUUID(), node.partitionID)
                        query.addPartitionOperator(res.getUUID(), res.partitionIDTo)
                        query.addPartitionOperator(res.getUUID(), res.partitionIDFrom)
                        onChange()
                    }
                }
                is POPBind -> {
                    when (val c = node.children[0]) {
                        is POPMergePartition -> {
                            res = POPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPBind(query, node.projectedVariables, node.name, node.children[1] as AOPBase, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPBind(query, node.projectedVariables, node.name, node.children[1] as AOPBase, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionCount -> {
                            res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPBind(query, node.projectedVariables, node.name, node.children[1] as AOPBase, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                    }
                }
                is POPUnion -> {
                    val c0 = node.children[0]
                    val c1 = node.children[1]
                    var modeC0 = 0
                    var modeC1 = 0
                    var columnNameC0 = ""
                    var columnCountC0 = 0
                    var columnIDC0 = 0
                    var columnNameC1 = ""
                    var columnCountC1 = 0
                    var columnIDC1 = 0
                    when (c0) {
                        is POPMergePartition -> {
                            modeC0 = 1
                            columnNameC0 = c0.partitionVariable
                            columnCountC0 = c0.partitionCount
                            columnIDC0 = c0.partitionID
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            modeC0 = 2
                            columnNameC0 = c0.partitionVariable
                            columnCountC0 = c0.partitionCount
                            columnIDC0 = c0.partitionID
                        }
                        is POPMergePartitionCount -> {
                            modeC0 = 3
                            columnNameC0 = c0.partitionVariable
                            columnCountC0 = c0.partitionCount
                            columnIDC0 = c0.partitionID
                        }
                    }
                    when (c1) {
                        is POPMergePartition -> {
                            modeC1 = 1
                            columnNameC1 = c1.partitionVariable
                            columnCountC1 = c1.partitionCount
                            columnIDC1 = c1.partitionID
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            modeC1 = 2
                            columnNameC1 = c1.partitionVariable
                            columnCountC1 = c1.partitionCount
                            columnIDC1 = c1.partitionID
                        }
                        is POPMergePartitionCount -> {
                            modeC1 = 3
                            columnNameC1 = c1.partitionVariable
                            columnCountC1 = c1.partitionCount
                            columnIDC1 = c1.partitionID
                        }
                    }
                    if (modeC0 == modeC1 && columnNameC0 == columnNameC1 && modeC0 > 0) {
                        if (columnCountC0 == columnCountC1) {
                            val columnID = query.mergePartitionOperator(columnIDC0, columnIDC1, c1.getChildren()[0])
                            when (modeC0) {
                                1 -> {
                                    res = POPMergePartition(query, node.projectedVariables, columnNameC0, columnCountC0, columnID, POPUnion(query, node.projectedVariables, c0.getChildren()[0], c1.getChildren()[0]))
                                    query.removePartitionOperator(c0.getUUID(), columnID)
                                    query.removePartitionOperator(c1.getUUID(), columnID)
                                    query.addPartitionOperator(res.getUUID(), columnID)
                                    query.partitionOperatorCount.clear()
                                    onChange()
                                }
                                2 -> {
                                    res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, columnNameC0, columnCountC0, columnID, POPUnion(query, node.projectedVariables, c0.getChildren()[0], c1.getChildren()[0]))
                                    query.removePartitionOperator(c0.getUUID(), columnID)
                                    query.removePartitionOperator(c1.getUUID(), columnID)
                                    query.addPartitionOperator(res.getUUID(), columnID)
                                    query.partitionOperatorCount.clear()
                                    onChange()
                                }
                                3 -> {
                                    res = POPMergePartitionCount(query, node.projectedVariables, columnNameC0, columnCountC0, columnID, POPUnion(query, node.projectedVariables, c0.getChildren()[0], c1.getChildren()[0]))
                                    query.removePartitionOperator(c0.getUUID(), columnID)
                                    query.removePartitionOperator(c1.getUUID(), columnID)
                                    query.addPartitionOperator(res.getUUID(), columnID)
                                    query.partitionOperatorCount.clear()
                                    onChange()
                                }
                                else -> {
                                    throw Exception("not reachable - implementation error")
                                }
                            }
                        } else {
                            throw Exception("not implemented ... column counts are different")
                        }
                    }
                }
                is POPProjection -> {
                    when (val c = node.children[0]) {
                        is POPMergePartition -> {
                            res = POPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPProjection(query, node.projectedVariables, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPProjection(query, node.projectedVariables, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionCount -> {
                            res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPProjection(query, node.projectedVariables, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                    }
                }
                is POPReduced -> {
                    when (val c = node.children[0]) {
                        is POPMergePartition -> {
                            res = POPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPReduced(query, node.projectedVariables, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPReduced(query, node.projectedVariables, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionCount -> {
                            res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPReduced(query, node.projectedVariables, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                    }
                }
                is POPFilter -> {
                    when (val c = node.children[0]) {
                        is POPMergePartition -> {
                            res = POPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPFilter(query, node.projectedVariables, node.children[1] as AOPBase, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPFilter(query, node.projectedVariables, node.children[1] as AOPBase, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionCount -> {
                            res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, POPFilter(query, node.projectedVariables, node.children[1] as AOPBase, c.children[0]))
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                    }
                }
                is POPSplitPartition -> {
// splitting must always split all variables provided by its direct children - if there is a different children, adapt the variables
                    when (val c = node.children[0]) {
                        is POPMergePartition -> {
                            if (node.partitionVariable == c.partitionVariable) {
                                if (node.partitionCount == c.partitionCount) {
                                    res = c.children[0]
                                    query.removePartitionOperator(c.getUUID(), c.partitionID)
                                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                                    query.mergePartitionOperator(node.partitionID, c.partitionID, res)
                                    query.partitionOperatorCount.clear()
                                    onChange()
                                } else if (node.partitionCount < c.partitionCount) {
                                    query.removePartitionOperator(c.getUUID(), c.partitionID)
                                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                                    res = POPChangePartitionOrderedByIntId(query, node.projectedVariables, node.partitionVariable, c.partitionCount, node.partitionCount, c.partitionID, node.partitionID, c.children[0])
                                    query.addPartitionOperator(res.getUUID(), res.partitionIDTo)
                                    query.addPartitionOperator(res.getUUID(), res.partitionIDFrom)
                                    onChange()
                                }
                            }
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            if (node.partitionVariable == c.partitionVariable) {
                                if (node.partitionCount == c.partitionCount) {
                                    res = c.children[0]
                                    query.removePartitionOperator(c.getUUID(), c.partitionID)
                                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                                    query.mergePartitionOperator(node.partitionID, c.partitionID, res)
                                    query.partitionOperatorCount.clear()
                                    onChange()
                                } else if (node.partitionCount < c.partitionCount) {
                                    query.removePartitionOperator(c.getUUID(), c.partitionID)
                                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                                    res = POPChangePartitionOrderedByIntId(query, node.projectedVariables, node.partitionVariable, c.partitionCount, node.partitionCount, c.partitionID, node.partitionID, c.children[0])
                                    query.addPartitionOperator(res.getUUID(), res.partitionIDTo)
                                    query.addPartitionOperator(res.getUUID(), res.partitionIDFrom)
                                    onChange()
                                }
                            }
                        }
                        is POPMergePartitionCount -> {
                            if (node.partitionVariable == c.partitionVariable) {
                                if (node.partitionCount == c.partitionCount) {
                                    res = c.children[0]
                                    query.removePartitionOperator(c.getUUID(), c.partitionID)
                                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                                    query.mergePartitionOperator(node.partitionID, c.partitionID, res)
                                    query.partitionOperatorCount.clear()
                                    onChange()
                                }
                            }
                        }
                        is POPReduced -> {
                            res = POPReduced(query, c.projectedVariables, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0]))
                            query.removePartitionOperator(node.getUUID(), node.partitionID)
                            query.addPartitionOperator(res.children[0].getUUID(), node.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPProjection -> {
                            res = POPProjection(query, c.projectedVariables, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0]))
                            query.removePartitionOperator(node.getUUID(), node.partitionID)
                            query.addPartitionOperator(res.children[0].getUUID(), node.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPFilter -> {
                            res = POPFilter(query, c.projectedVariables, c.children[1] as AOPBase, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0]))
                            query.removePartitionOperator(node.getUUID(), node.partitionID)
                            query.addPartitionOperator(res.children[0].getUUID(), node.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is TripleStoreIteratorGlobal -> {
                            if (TripleStoreLocal.providesFeature(TripleStoreFeatureExt.PARTITION, null)) {
                                try {
                                    val p = Partition(Partition(), node.partitionVariable, 0, node.partitionCount)
                                    val params = TripleStoreFeatureParamsPartition(c.idx, Array(3) { c.children[it] as AOPBase }, p)
                                    if (params.getColumn() > 0 && TripleStoreLocal.providesFeature(TripleStoreFeatureExt.PARTITION, params)) {
                                        res = POPSplitPartitionFromStore(query, node.projectedVariables, node.partitionVariable, node.partitionCount, node.partitionID, c)
                                        c.partition.limit[node.partitionVariable] = node.partitionCount
                                        query.removePartitionOperator(node.getUUID(), node.partitionID)
                                        query.addPartitionOperator(res.getUUID(), node.partitionID)
                                        query.partitionOperatorCount.clear()
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
        }
        return res
    }
}
