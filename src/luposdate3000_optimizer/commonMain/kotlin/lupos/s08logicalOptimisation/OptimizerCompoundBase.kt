package lupos.s08logicalOptimisation
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

abstract class OptimizerCompoundBase(query: Query, optimizerID: EOptimizerID) : OptimizerBase(query, optimizerID) {
    override val classname = "OptimizerCompoundBase"
    abstract val childrenOptimizers: Array<Array<OptimizerBase>>
    override suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit) = node
internal fun verifyPartitionOperators(root: IOPBase, allList: MutableMap<Int, MutableSet<Long>>) {
        var id = -1
        when (root) {
            is POPMergePartitionCount -> id = root.partitionID
            is POPMergePartition -> id = root.partitionID
            is POPMergePartitionOrderedByIntId -> id = root.partitionID
            is POPSplitPartitionFromStore -> id = root.partitionID
            is POPSplitPartition -> id = root.partitionID
        }
        if (id >= 0) {
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
    override suspend fun optimizeCall(node: IOPBase, onChange: () -> Unit): IOPBase {
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
                    SanityCheck.println({ "debug ${o.optimizerID}" })
                    var c = true
                    while (c) {
                        c = false
                        tmp = o.optimizeInternal(tmp, null, {
                            if (o.optimizerID.repeatOnChange) {
                                c = true
                                d = true
                                onChange()
                            }
                        })
SanityCheck {
            val allPartitionOperators = mutableMapOf<Int, MutableSet<Long>>()
            verifyPartitionOperators(tmp, allPartitionOperators)
            for ((k, v1) in allPartitionOperators) {
                val v2 = query.partitionOperators[k]
                SanityCheck.check({ v1 == v2 }, { "$allPartitionOperators  <-a-> ${query.partitionOperators}" })
            }
            for ((k, v1) in query.partitionOperators) {
                val v2 = allPartitionOperators[k]
                SanityCheck.check({ v1 == v2 }, { "$allPartitionOperators  <-b-> ${query.partitionOperators}" })
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
