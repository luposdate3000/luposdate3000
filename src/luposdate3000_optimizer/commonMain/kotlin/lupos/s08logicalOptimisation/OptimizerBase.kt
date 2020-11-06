package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore

abstract class OptimizerBase(@JvmField val query: Query, @JvmField val optimizerID: EOptimizerID) {
    abstract val classname: String
    abstract suspend fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase
    suspend fun optimizeInternal(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        SanityCheck {
            if (parent != null) {
                var found = false
                for (c in parent.getChildren()) {
                    if (c === node) {
                        found = true
                        break
                    }
                }
                SanityCheck.check({ found })
            }
        }
        for (i in node.getChildren().indices) {
            val tmp = optimizeInternal(node.getChildren()[i], node, onChange)
            node.updateChildren(i, tmp)
        }
        val res = optimize(node, parent, onChange)
        return res
    }

    open suspend fun optimizeCall(node: IOPBase, onChange: () -> Unit = {}): IOPBase {
        if (query.filtersMovedUpFromOptionals) {
            node.syntaxVerifyAllVariableExists(listOf(), true)
        }
        val res = optimizeInternal(node, null, onChange)
        if (query.filtersMovedUpFromOptionals) {
            res.syntaxVerifyAllVariableExists(listOf(), false)
        }
        return res
    }
}
