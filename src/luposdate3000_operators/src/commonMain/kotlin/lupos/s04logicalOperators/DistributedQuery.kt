package lupos.s04logicalOperators
import lupos.s00misc.SanityCheck
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.partition.POPChangePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s09physicalOperators.partition.POPSplitPartitionPassThrough
internal object DistributedQuery {
    private fun getAllVariations(query: Query, node: IOPBase, allNames: Array<String>, allSize: IntArray, allIdx: IntArray, offset: Int) {
        if (offset == allNames.size) {
            for (i in 0 until allNames.size) {
                query.allVariationsKey[allNames[i]] = allIdx[i]
            }
            val xml = node.toXMLElementRoot(true)
            val key = xml["partitionDistributionProvideKey"]!!.attributes["key"]!!
            query.operatorgraphParts[key] = xml
            query.allVariationsKey.clear()
        } else {
            for (i in 0 until allSize[offset]) {
                allIdx[offset] = i
                getAllVariations(query, node, allNames, allSize, allIdx, offset + 1)
            }
        }
    }
    private fun initialize_helper(query: Query, node: IOPBase, currentPartitions: Map<String, Int>, root: Boolean) {
        if ((node is POPBase) || (node is OPBaseCompound)) {
            val currentPartitionsCopy = mutableMapOf<String, Int>()
            currentPartitionsCopy.putAll(currentPartitions)
            when (node) {
                is POPMergePartition -> {
                    SanityCheck.check { currentPartitionsCopy[node.partitionVariable] == null }
                    currentPartitionsCopy[node.partitionVariable] = node.partitionCount
                }
                is POPMergePartitionCount -> {
                    SanityCheck.check { currentPartitionsCopy[node.partitionVariable] == null }
                    currentPartitionsCopy[node.partitionVariable] = node.partitionCount
                }
                is POPMergePartitionOrderedByIntId -> {
                    SanityCheck.check { currentPartitionsCopy[node.partitionVariable] == null }
                    currentPartitionsCopy[node.partitionVariable] = node.partitionCount
                }
                is POPChangePartitionOrderedByIntId -> {
                    SanityCheck.check { currentPartitionsCopy[node.partitionVariable] == node.partitionCountTo }
                    currentPartitionsCopy[node.partitionVariable] = node.partitionCountFrom
                }
                is POPSplitPartition -> {
                    SanityCheck.check { currentPartitionsCopy[node.partitionVariable] != null }
                    currentPartitionsCopy.remove(node.partitionVariable)
                }
                is POPSplitPartitionFromStore -> {
                    SanityCheck.check { currentPartitionsCopy[node.partitionVariable] == node.partitionCount }
                }
                is POPSplitPartitionPassThrough -> {
                    SanityCheck.check { currentPartitionsCopy[node.partitionVariable] == node.partitionCount }
                }
            }
            for (ci in 0 until (node as OPBase).childrenToVerifyCount()) {
                val c = node.getChildren()[ci]
                initialize_helper(query, c, currentPartitionsCopy, false)
            }
            when (node) {
                is POPMergePartition,
                is POPMergePartitionCount,
                is POPMergePartitionOrderedByIntId,
                is POPChangePartitionOrderedByIntId,
                is POPSplitPartition,
                is POPSplitPartitionFromStore,
                is POPSplitPartitionPassThrough
                -> {
                    val allNames = Array(currentPartitionsCopy.size) { "" }
                    val allSize = IntArray(currentPartitionsCopy.size)
                    var i = 0
                    for ((k, v) in currentPartitionsCopy) {
                        allNames[i] = k
                        allSize[i] = v
                        i++
                    }
                    getAllVariations(query, node, allNames, allSize, IntArray(currentPartitionsCopy.size), 0)
                    if (root) {
                        query.operatorgraphParts[""] = node.toXMLElement(true)
                    }
                }
            }
        } else {
            throw Exception("this query is not executable ${node.getClassname()} ${(node as OPBase).uuid}")
        }
    }
    internal fun initialize(query: Query) {
        query.operatorgraphParts.clear()
        initialize_helper(query, query.root!!, mutableMapOf(), true)
        for ((k, v) in query.operatorgraphParts) {
            println("subgraph $k")
            println(v.toPrettyString())
        }
    }
}
