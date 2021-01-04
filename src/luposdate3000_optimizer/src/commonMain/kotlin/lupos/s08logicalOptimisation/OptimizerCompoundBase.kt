package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.partition.POPChangePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal
abstract class OptimizerCompoundBase(query: Query, optimizerID: EOptimizerID) : OptimizerBase(query, optimizerID) {
    override val classname: String = "OptimizerCompoundBase"
    abstract val childrenOptimizers: Array<Array<OptimizerBase>>
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase = node
    private fun verifyPartitionOperators(root: IOPBase, allList: MutableMap<Int, MutableSet<Long>>, currentPartitions_: MutableMap<String, Int>) {
        var currentPartitions = mutableMapOf<String, Int>()
        currentPartitions.putAll(currentPartitions_)
        val ids = mutableListOf<Int>()
        when (root) {
            is POPMergePartitionCount -> {
                SanityCheck.check { !currentPartitions.contains(root.partitionVariable) }
                currentPartitions[root.partitionVariable] = root.partitionCount
                ids.add(root.partitionID)
            }
            is POPMergePartition -> {
                SanityCheck.check { !currentPartitions.contains(root.partitionVariable) }
                currentPartitions[root.partitionVariable] = root.partitionCount
                ids.add(root.partitionID)
            }
            is POPMergePartitionOrderedByIntId -> {
                SanityCheck.check { !currentPartitions.contains(root.partitionVariable) }
                currentPartitions[root.partitionVariable] = root.partitionCount
                ids.add(root.partitionID)
            }
            is POPSplitPartitionFromStore -> {
                SanityCheck.check { currentPartitions[root.partitionVariable] == root.partitionCount }
                currentPartitions[root.partitionVariable] = -root.partitionCount
                ids.add(root.partitionID)
            }
            is POPSplitPartition -> {
                SanityCheck.check { currentPartitions[root.partitionVariable] == root.partitionCount }
                currentPartitions.remove(root.partitionVariable)
                ids.add(root.partitionID)
            }
            is POPChangePartitionOrderedByIntId -> {
                SanityCheck.check { currentPartitions[root.partitionVariable] == root.partitionCountFrom }
                currentPartitions[root.partitionVariable] = root.partitionCountTo
                ids.add(root.partitionIDFrom)
                ids.add(root.partitionIDTo)
            }
            is TripleStoreIteratorGlobal -> {
                SanityCheck.check({ currentPartitions.size == 0 || currentPartitions.size == 1 }, { "$currentPartitions" })
                SanityCheck.check({ root.partition.limit.size == currentPartitions.size }, { "${root.partition.limit} $currentPartitions" })
                if (currentPartitions.size == 1) {
                    for ((k, v) in root.partition.limit) {
                        for ((k2, v2) in currentPartitions) {
                            SanityCheck.check({ k == k2 }, { "$k $k2" })
                            SanityCheck.check({ v == -v2 }, { "$v $v2" })
                        }
                    }
                }
            }
        }
        for (id in ids) {
            val tmp = allList[id]
            if (tmp == null) {
                allList[id] = mutableSetOf(root.getUUID())
            } else {
                tmp.add(root.getUUID())
            }
        }
        for (c in root.getChildren()) {
            verifyPartitionOperators(c, allList, currentPartitions)
        }
    }
    override /*suspend*/ fun optimizeCall(node: IOPBase, onChange: () -> Unit): IOPBase {
        if (query.filtersMovedUpFromOptionals) {
            node.syntaxVerifyAllVariableExists(listOf(), true)
        }
        var tmp = node
        var d: Boolean
        for (opt in childrenOptimizers) {
            d = true
            while (d) {
                d = false
                for (o in opt) {
                    SanityCheck.println { "debug ${o.optimizerID}" }
                    var c = true
                    while (c) {
                        c = false
                        tmp = o.optimizeInternal(tmp, null) {
                            if (o.optimizerID.repeatOnChange) {
                                c = true
                                d = true
                                onChange()
                            }
                        }
                        SanityCheck {
                            val allPartitionOperators = mutableMapOf<Int, MutableSet<Long>>()
                            verifyPartitionOperators(tmp, allPartitionOperators, mutableMapOf<String, Int>())
                            for ((k, v1) in allPartitionOperators) {
                                val v2 = query.partitionOperators[k]
                                SanityCheck.check({ v1 == v2 }, { "$allPartitionOperators  <-a-> ${query.partitionOperators}\n$tmp" })
                            }
                            for ((k, v1) in query.partitionOperators) {
                                val v2 = allPartitionOperators[k]
                                SanityCheck.check({ v1 == v2 }, { "$allPartitionOperators  <-b-> ${query.partitionOperators}\n$tmp" })
                            }
                        }
                    }
                    SanityCheck {
                        if (query.filtersMovedUpFromOptionals) {
                            tmp.syntaxVerifyAllVariableExists(listOf(), false)
                        }
                    }
                }
            }
        }
        if (query.filtersMovedUpFromOptionals) {
            tmp.syntaxVerifyAllVariableExists(listOf(), false)
        }
        return tmp
    }
}
