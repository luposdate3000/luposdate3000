
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
import lupos.operator.physical.partition.POPSplitPartitionPassThrough
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
                query.allVariationsKey[allNames[i]] = allIdx[i]
            }
            val xml = node.toXMLElementRoot(true)
            val keys = mutableSetOf<String>()
            for (c in xml.childs) {
                if (c.tag == "partitionDistributionKey") {
                    keys.add(c.attributes["key"]!!)
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
                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:77"/*SOURCE_FILE_END*/ },
                        { (allIdx.size == 1) },
                        { "${ allIdx.size}" }
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
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:90"/*SOURCE_FILE_END*/ }, { !query.operatorgraphPartsToHostMap.contains(key) || query.operatorgraphPartsToHostMap[key] == target })
                    query.operatorgraphPartsToHostMap[key] = target
                }
                is POPSplitMergePartitionFromStore -> {
                    SanityCheck.check(
                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:95"/*SOURCE_FILE_END*/ },
                        { (allIdx.size == 0) },
                        { "${ allIdx.size}" }
                    )
                    var n: IOPBase = node
                    while (n !is POPTripleStoreIterator) {
                        n = n.getChildren()[0]
                    }
                    var partition = Partition()
                    val target = n.getDesiredHostnameFor(partition)
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:105"/*SOURCE_FILE_END*/ }, { !query.operatorgraphPartsToHostMap.contains(key) || query.operatorgraphPartsToHostMap[key] == target })
                    query.operatorgraphPartsToHostMap[key] = target
                }
            }
            query.operatorgraphParts[key] = xml
            query.allVariationsKey.clear()
        } else {
            for (i in 0 until allSize[offset]) {
                allIdx[offset] = i
                splitPartitionsVariations(query, node, allNames, allSize, allIdx, offset + 1)
            }
        }
    }

    private fun splitPartitions(query: Query, node: IOPBase, currentPartitions: Map<String, Int>, root: Boolean) {
        if ((node is POPBase) || (node is OPBaseCompound)) {
            val currentPartitionsCopy = mutableMapOf<String, Int>()
            currentPartitionsCopy.putAll(currentPartitions)
            when (node) {
                is POPMergePartition -> {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:125"/*SOURCE_FILE_END*/ }, { currentPartitionsCopy[node.partitionVariable] == null })
                    currentPartitionsCopy[node.partitionVariable] = node.partitionCount
                }
                is POPMergePartitionCount -> {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:129"/*SOURCE_FILE_END*/ }, { currentPartitionsCopy[node.partitionVariable] == null })
                    currentPartitionsCopy[node.partitionVariable] = node.partitionCount
                }
                is POPMergePartitionOrderedByIntId -> {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:133"/*SOURCE_FILE_END*/ }, { currentPartitionsCopy[node.partitionVariable] == null })
                    currentPartitionsCopy[node.partitionVariable] = node.partitionCount
                }
                is POPChangePartitionOrderedByIntId -> {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:137"/*SOURCE_FILE_END*/ }, { currentPartitionsCopy[node.partitionVariable] == node.partitionCountTo })
                    currentPartitionsCopy[node.partitionVariable] = node.partitionCountFrom
                }
                is POPSplitPartition -> {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:141"/*SOURCE_FILE_END*/ }, { currentPartitionsCopy[node.partitionVariable] != null })
                    currentPartitionsCopy.remove(node.partitionVariable)
                }
                is POPSplitPartitionFromStore -> {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:145"/*SOURCE_FILE_END*/ }, { currentPartitionsCopy[node.partitionVariable] == node.partitionCount })
                }
                is POPSplitPartitionFromStoreCount -> {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:148"/*SOURCE_FILE_END*/ }, { currentPartitionsCopy[node.partitionVariable] == node.partitionCount })
                }
                is POPSplitPartitionPassThrough -> {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/src/commonMain/kotlin/lupos/optimizer/distributed/query/DistributedOptimizerQuery.kt:151"/*SOURCE_FILE_END*/ }, { currentPartitionsCopy[node.partitionVariable] == node.partitionCount })
                }
            }
            for (ci in 0 until (node as OPBase).childrenToVerifyCount()) {
                val c = node.getChildren()[ci]
                splitPartitions(query, c, currentPartitionsCopy, false)
            }
            when (node) {
                is POPMergePartition,
                is POPMergePartitionCount,
                is POPMergePartitionOrderedByIntId,
                is POPChangePartitionOrderedByIntId,
                is POPSplitPartition,
                is POPSplitMergePartitionFromStore,
                is POPSplitPartitionFromStore,
                is POPSplitPartitionFromStoreCount,
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
                    splitPartitionsVariations(query, node, allNames, allSize, IntArray(currentPartitionsCopy.size), 0)
                }
            }
        } else {
            throw Exception("this query is not executable ${node.getClassname()} ${(node as OPBase).uuid}")
        }
    }

    private fun calculateDependenciesTopDown(node: XMLElement, parentTag: String): Set<String> {
        val res = mutableSetOf<String>()
        for (c in node.childs) {
            res.addAll(calculateDependenciesTopDown(c, node.tag))
        }
        if (parentTag.startsWith("POPDistributedReceive") && node.tag == "partitionDistributionKey") {
            res.add(node.attributes["key"]!!)
        }
        return res
    }

    private fun calculateDependenciesBottomUp(node: XMLElement, parentTag: String): Set<String> {
        val res = mutableSetOf<String>()
        for (c in node.childs) {
            res.addAll(calculateDependenciesBottomUp(c, node.tag))
        }
        if (parentTag.startsWith("POPDistributedSend") && node.tag == "partitionDistributionKey") {
            res.add(node.attributes["key"]!!)
        }
        return res
    }

    private fun getHostForKey(query: Query, key: String): String? {
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
            node.addAttribute("host", getHostForKey(query, node.attributes["key"]!!)!!)
        }
    }

    private fun splitQuery(query2: IQuery) {
        val query = query2 as Query
        val root = query.root!!
        if (query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
            query.operatorgraphParts.clear()
// assign host to root node
            query.operatorgraphParts[""] = root.toXMLElement(true)
            query.operatorgraphPartsToHostMap[""] = (query.getInstance().tripleStoreManager!!).getLocalhost()
// split query into parts, and automatically assign hosts to triple store access parts
            splitPartitions(query, root, mutableMapOf(), true)
// calculate dependencies
            for ((k, v) in query.operatorgraphParts) {
                query.dependenciesMapTopDown[k] = calculateDependenciesTopDown(v, "")
            }
            for ((k, v) in query.operatorgraphParts) {
                query.dependenciesMapBottomUp[k] = calculateDependenciesBottomUp(v, "")
            }
        }
    }

    public override fun optimize(query2: IQuery, wantReturnValue: Boolean): IOPBase {
        val query = query2 as Query
        val root = query.root!!
        if (query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
            splitQuery(query)
// assign hosts to other parts
            for (childOptimizer2 in childOptimizer) {
                var changed = true
                loop@ while (changed) {
                    changed = false
                    for (opt in childOptimizer2) {
                        for ((k, v) in query.operatorgraphParts) {
                            if (!query.operatorgraphPartsToHostMap.contains(k)) {
                                opt.optimize(query, k, v, query.dependenciesMapTopDown[k]!!, query.dependenciesMapBottomUp[k]!!, { getHostForKey(query, it) }, { key, value -> query.operatorgraphPartsToHostMap[key] = value }) {
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
            for ((k, v) in query.operatorgraphParts) {
                assignHosts(query, v)
                if (k == "") {
                    res = v
                } else {
                    query.getInstance().communicationHandler!!.sendData(query.operatorgraphPartsToHostMap[k]!!, "/distributed/query/register", mapOf("query" to "$v"), query.getTransactionID().toInt())
                }
            }
            if (wantReturnValue) {
                return XMLElementToOPBase(query, res!!)
            } else {
                return root
            }
        } else {
            return root
        }
    }
}
