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
package lupos.s04logicalOperators
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
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
            if (node is POPSplitPartitionFromStore) {
                SanityCheck.check { allIdx.size == 1 }
                query.operatorgraphPartsToHostMap[key] = Partition.myProcessUrls[allIdx[0] % Partition.myProcessCount]
            }
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
                }
            }
        } else {
            throw Exception("this query is not executable ${node.getClassname()} ${(node as OPBase).uuid}")
        }
    }
    internal fun walkXMLElement(node: XMLElement, dependencies: MutableList<String>) {
        for (c in node.childs) {
            walkXMLElement(c, dependencies)
        }
        if (node.tag == "partitionDistributionReceiveKey") {
            dependencies.add(node.attributes["key"]!!)
        }
    }
    internal fun initialize(query: Query): OPBase {
        query.operatorgraphParts.clear()
        query.operatorgraphParts[""] = query.root!!.toXMLElement(true)
        query.operatorgraphPartsToHostMap[""] = Partition.myProcessUrls[Partition.myProcessId]
        initialize_helper(query, query.root!!, mutableMapOf(), true)
        for ((k, v) in query.operatorgraphParts) {
            println("subgraph $k")
            println(v.toPrettyString())
        }
        println("host mappings :: init")
        var dependenciesMap = mutableMapOf<String, List<String>>()
        for ((k, v) in query.operatorgraphParts) {
            val dependencies = mutableListOf<String>()
            walkXMLElement(v, dependencies)
            dependenciesMap[k] = dependencies
            println("$k :: ${query.operatorgraphPartsToHostMap[k] ?: "?"} :: $dependencies")
        }
        var changed = true
        var iteration = 0
        while (changed) {
            changed = false
            println("host mappings :: iteration $iteration")
            iteration++
            for ((k, v) in query.operatorgraphParts) {
                if (!query.operatorgraphPartsToHostMap.contains(k)) {
                    var possibleHosts = mutableSetOf<String>()
                    for (s in dependenciesMap[k]!!) {
                        possibleHosts.add(query.operatorgraphPartsToHostMap[s] ?: "?")
                    }
                    println("possibleHosts $possibleHosts")
                    if (possibleHosts.size == 1) {
                        query.operatorgraphPartsToHostMap[k] = possibleHosts.first()
                        changed = true
                    }
                }
                println("$k :: ${query.operatorgraphPartsToHostMap[k] ?: "?"}")
            }
            if (!changed) {
                loop@for ((k, v) in query.operatorgraphParts) {
                    if (!query.operatorgraphPartsToHostMap.contains(k)) {
                        var possibleHosts = mutableSetOf<String>()
                        for (s in dependenciesMap[k]!!) {
                            possibleHosts.add(query.operatorgraphPartsToHostMap[s] ?: "?")
                        }
                        possibleHosts.remove("?")
                        println("possibleHosts $possibleHosts")
                        if (possibleHosts.size > 0) {
                            query.operatorgraphPartsToHostMap[k] = possibleHosts.first()
                            changed = true
                            break@loop
                        }
                    }
                    println("$k :: ${query.operatorgraphPartsToHostMap[k] ?: "?"}")
                }
            }
        }
        for ((k, v) in query.operatorgraphParts) {
            if (k != "") {
                query.communicationHandler!!.sendData(query.operatorgraphPartsToHostMap[k]!!, "/distributed/query/register", mapOf("query" to "$v"))
            }
        }
        return query.root
    }
}
