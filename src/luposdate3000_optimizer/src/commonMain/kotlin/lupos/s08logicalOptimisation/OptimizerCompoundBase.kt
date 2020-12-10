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

abstract class OptimizerCompoundBase(query: Query, optimizerID: EOptimizerID) : OptimizerBase(query, optimizerID) {
    override val classname: String = "OptimizerCompoundBase"
    abstract val childrenOptimizers: Array<Array<OptimizerBase>>
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase = node
    private fun verifyPartitionOperators(root: IOPBase, allList: MutableMap<Int, MutableSet<Long>>) {
        val ids = mutableListOf<Int>()
        when (root) {
            is POPMergePartitionCount -> ids.add(root.partitionID)
            is POPMergePartition -> ids.add(root.partitionID)
            is POPMergePartitionOrderedByIntId -> ids.add(root.partitionID)
            is POPSplitPartitionFromStore -> ids.add(root.partitionID)
            is POPSplitPartition -> ids.add(root.partitionID)
            is POPChangePartitionOrderedByIntId -> {
                ids.add(root.partitionIDFrom)
                ids.add(root.partitionIDTo)
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
            verifyPartitionOperators(c, allList)
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
                            verifyPartitionOperators(tmp, allPartitionOperators)
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
