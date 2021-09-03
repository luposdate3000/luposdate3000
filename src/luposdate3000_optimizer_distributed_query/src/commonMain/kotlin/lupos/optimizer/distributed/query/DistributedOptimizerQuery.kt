
/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.optimizer.distributed.query
import lupos.operator.base.OPBase
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.Query
import lupos.operator.factory.XMLElementToOPBase
import lupos.operator.physical.POPBase
import lupos.operator.physical.partition.POPChangePartitionOrderedByIntId
import lupos.operator.physical.partition.POPMergePartition
import lupos.operator.physical.partition.POPMergePartitionCount
import lupos.operator.physical.partition.POPMergePartitionOrderedByIntId
import lupos.operator.physical.partition.POPSplitMergePartitionFromStore
import lupos.operator.physical.partition.POPSplitPartition
import lupos.operator.physical.partition.POPSplitPartitionFromStore
import lupos.operator.physical.partition.POPSplitPartitionFromStoreCount
import lupos.optimizer.physical.PhysicalOptimizerSplitMergePartition
import lupos.shared.EPartitionModeExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.optimizer.IDistributedOptimizer
import lupos.triple_store_manager.POPTripleStoreIterator
import kotlin.jvm.JvmField

public class DistributedOptimizerQuery : IDistributedOptimizer {

    @JvmField
    internal val childOptimizer = arrayOf(
        arrayOf(
            DistributedOptimizerAssignChild(),
            DistributedOptimizerAssignParent(),
            DistributedOptimizerAssignAnyChild(),
            DistributedOptimizerAssignAnyParent(),
        ),
        arrayOf(DistributedOptimizerAssignLocalhost())
    )

    private fun splitPartitionsVariations(query: Query, node: IOPBase, allNames: Array<String>, allSize: IntArray, allIdx: IntArray, offset: Int) {
        if (offset == allNames.size) {
            for (i in 0 until allNames.size) {
                query.partitionedBy[allNames[i]] = allIdx[i]
            }
            val thePartition = mutableMapOf<String, Int>()
            for (i in 0 until offset) {
                thePartition[allNames[i]] = allIdx[i]
            }
            val xml = node.toXMLElementRoot(true, thePartition)
            val keys = mutableSetOf<Int>()
            for (c in xml.childs) {
                if (c.tag == "partitionDistributionKey") {
                    keys.add(c.attributes["key"]!!.toInt())
                }
            }
            val key = keys.first()
            if (keys.size > 1) {
                for (k in keys) {
                    query.keyRepresentative[k] = key
                }
            }
            when (node) {
                is POPSplitPartitionFromStore, is POPSplitPartitionFromStoreCount -> {
                    SanityCheck.check(
                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:80"/*SOURCE_FILE_END*/ },
                        { (allIdx.size == 1) },
                        { "${ allIdx.size} ${query.root}" }
                    )
                    var n: IOPBase = node
                    while (n !is POPTripleStoreIterator) {
                        n = n.getChildren()[0]
                    }
                    var partition = Partition()
                    for (i in 0 until allNames.size) {
                        partition = Partition(partition, allNames[i], allIdx[i], allSize[i])
                    }
                    val target = n.getDesiredHostnameFor(partition)
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:93"/*SOURCE_FILE_END*/ }, { !query.operatorgraphPartsToHostMap.contains(key) || query.operatorgraphPartsToHostMap[key] == target })
                    query.operatorgraphPartsToHostMap[key] = target
                }
                is POPSplitMergePartitionFromStore -> {
                    SanityCheck.check(
                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:98"/*SOURCE_FILE_END*/ },
                        { (allIdx.size == 0) },
                        { "${ allIdx.size}" }
                    )
                    var n: IOPBase = node
                    while (n !is POPTripleStoreIterator) {
                        n = n.getChildren()[0]
                    }
                    var partition = Partition()
                    val target = n.getDesiredHostnameFor(partition)
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:108"/*SOURCE_FILE_END*/ }, { !query.operatorgraphPartsToHostMap.contains(key) || query.operatorgraphPartsToHostMap[key] == target })
                    query.operatorgraphPartsToHostMap[key] = target
                }
            }
            query.operatorgraphParts[key] = xml
            query.partitionedBy.clear()
        } else {
            for (i in 0 until allSize[offset]) {
                allIdx[offset] = i
                splitPartitionsVariations(query, node, allNames, allSize, allIdx, offset + 1)
            }
        }
    }

    private fun splitPartitions(query: Query, node: IOPBase, currentPartitions: Map<String, Int>) {
        if ((node is POPBase) || (node is OPBaseCompound)) {
            val currentPartitionsCopy = mutableMapOf<String, Int>()
            currentPartitionsCopy.putAll(currentPartitions)
            when (node) {
                is POPMergePartition -> {
                    if (node.partitionCount> 1) {
                        SanityCheck.check(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:130"/*SOURCE_FILE_END*/ },
                            { currentPartitionsCopy[node.partitionVariable] == null }
                        )
                        currentPartitionsCopy[node.partitionVariable!!] = node.partitionCount
                    }
                }
                is POPMergePartitionCount -> {
                    if (node.partitionCount> 1) {
                        SanityCheck.check(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:139"/*SOURCE_FILE_END*/ },
                            { currentPartitionsCopy[node.partitionVariable] == null }
                        )
                        currentPartitionsCopy[node.partitionVariable] = node.partitionCount
                    }
                }
                is POPMergePartitionOrderedByIntId -> {
                    if (node.partitionCount> 1) {
                        SanityCheck.check(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:148"/*SOURCE_FILE_END*/ },
                            { currentPartitionsCopy[node.partitionVariable] == null }
                        )
                        currentPartitionsCopy[node.partitionVariable] = node.partitionCount
                    }
                }
                is POPChangePartitionOrderedByIntId -> {
                    SanityCheck.check(
                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:156"/*SOURCE_FILE_END*/ },
                        { currentPartitionsCopy[node.partitionVariable] == node.partitionCountTo }
                    )
                    currentPartitionsCopy[node.partitionVariable] = node.partitionCountFrom
                }
                is POPSplitPartition -> {
                    if (node.partitionCount> 1) {
                        SanityCheck.check(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:164"/*SOURCE_FILE_END*/ },
                            { currentPartitionsCopy[node.partitionVariable] != null }
                        )
                        currentPartitionsCopy.remove(node.partitionVariable!!)
                    }
                }
                is POPSplitPartitionFromStore -> {
                    if (node.partitionCount> 1) {
                        SanityCheck.check(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:173"/*SOURCE_FILE_END*/ },
                            { currentPartitionsCopy[node.partitionVariable] == node.partitionCount }
                        )
                    }
                }
                is POPSplitPartitionFromStoreCount -> {
                    if (node.partitionCount> 1) {
                        SanityCheck.check(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:181"/*SOURCE_FILE_END*/ },
                            { currentPartitionsCopy[node.partitionVariable] == node.partitionCount }
                        )
                    }
                }
            }
            for (ci in 0 until (node as OPBase).childrenToVerifyCount()) {
                val c = node.getChildren()[ci]
                splitPartitions(query, c, currentPartitionsCopy)
            }
            when (node) {
                is POPMergePartition,
                is POPMergePartitionCount,
                is POPMergePartitionOrderedByIntId,
                is POPChangePartitionOrderedByIntId,
                is POPSplitPartition,
                is POPSplitMergePartitionFromStore,
                is POPSplitPartitionFromStore,
                is POPSplitPartitionFromStoreCount
                -> {
                    val allNames = Array(currentPartitionsCopy.size) { "" }
                    val allSize = IntArray(currentPartitionsCopy.size)
                    var i = 0
                    for ((k, v) in currentPartitionsCopy) {
                        allNames[i] = k
                        allSize[i] = v
                        i++
                    }
                    splitPartitionsVariations(query, node, allNames, allSize, IntArray(currentPartitionsCopy.size), 0)
                }
            }
        } else {
            throw Exception("this query is not executable ${node.getClassname()} ${(node as OPBase).uuid}")
        }
    }

    private fun calculateDependenciesTopDown(node: XMLElement, parentTag: String): Set<Int> {
        val res = mutableSetOf<Int>()
        for (c in node.childs) {
            res.addAll(calculateDependenciesTopDown(c, node.tag))
        }
        if (parentTag.startsWith("POPDistributedReceive") && node.tag == "partitionDistributionKey") {
            res.add(node.attributes["key"]!!.toInt())
        }
        return res
    }

    private fun calculateDependenciesBottomUp(node: XMLElement, parentTag: String): Set<Int> {
        val res = mutableSetOf<Int>()
        for (c in node.childs) {
            res.addAll(calculateDependenciesBottomUp(c, node.tag))
        }
        if (parentTag.startsWith("POPDistributedSend") && node.tag == "partitionDistributionKey") {
            res.add(node.attributes["key"]!!.toInt())
        }
        return res
    }

    private fun getHostForKey(query: Query, key: Int): String? {
        val res = query.operatorgraphPartsToHostMap[key]
        if (res != null) {
            return res
        }
        return query.operatorgraphPartsToHostMap[query.keyRepresentative[key]]
    }

    private fun assignHosts(query: Query, node: XMLElement) {
        for (c in node.childs) {
            assignHosts(query, c)
        }
        if (node.tag == "partitionDistributionKey") {
            val k = node.attributes["key"]!!.toInt()
            val h = getHostForKey(query, k)
            if (h == null) {
                TODO("$k .. ${query.operatorgraphPartsToHostMap} .. ${query.keyRepresentative}")
            } else {
                node.addAttribute("host", h)
            }
        }
    }

    private fun splitQuery(query2: IQuery, splitEverything: Boolean) {
        val query = query2 as Query
        if (query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
            val root = if (splitEverything) {
                PhysicalOptimizerSplitMergePartition(query).optimizeCall(query.root!!)
            } else {
                query.root!!
            }
            query.operatorgraphParts.clear()
// assign host to root node
            query.operatorgraphParts[-1] = root.toXMLElement(true, mapOf())
            query.operatorgraphPartsToHostMap[-1] = (query.getInstance().tripleStoreManager!!).getLocalhost()
// split query into parts, and automatically assign hosts to triple store access parts
            splitPartitions(query, root, mutableMapOf())
// calculate dependencies
            for ((k, v) in query.operatorgraphParts) {
                query.dependenciesMapTopDown[k] = calculateDependenciesTopDown(v, "")
            }
            for ((k, v) in query.operatorgraphParts) {
                query.dependenciesMapBottomUp[k] = calculateDependenciesBottomUp(v, "")
            }
        }
    }

    public override fun optimize(query: IQuery, wantReturnValue: Boolean, splitEverything: Boolean): IOPBase {
        val query2 = query as Query
        val root = query2.root!!
        if (query2.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
            splitQuery(query2, splitEverything)
// assign hosts to other parts
            for (childOptimizer2 in childOptimizer) {
                var changed = true
                loop@ while (changed) {
                    changed = false
                    for (opt in childOptimizer2) {
                        for ((k, v) in query2.operatorgraphParts) {
                            if (!query2.operatorgraphPartsToHostMap.contains(k)) {
                                opt.optimize(query2, k, v, query2.dependenciesMapTopDown[k]!!, query2.dependenciesMapBottomUp[k]!!, { getHostForKey(query2, it) }, { key, value -> query2.operatorgraphPartsToHostMap[key] = value }) {
                                    changed = true
                                }
                                if (changed) {
                                    continue@loop
                                }
                            }
                        }
                    }
                }
            }
// publish the query to the other database instances
            var res: XMLElement? = null
            for ((k, v) in query2.operatorgraphParts) {
                assignHosts(query2, v)
                if (wantReturnValue) {
                    if (k == -1) {
                        res = v
                    } else {
                        query2.getInstance().communicationHandler!!.sendData(query2.operatorgraphPartsToHostMap[k]!!, "/distributed/query/register", mapOf("query" to "$v"), query2.getTransactionID().toInt())
                    }
                }
            }
            if (wantReturnValue) {
                return XMLElementToOPBase(query2, res!!)
            } else {
                return root
            }
        } else {
            return root
        }
    }
}
