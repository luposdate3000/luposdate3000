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

import lupos.operator.factory.XMLElementToOPBase
import lupos.operator.logical.IQuery
import lupos.operator.logical.OPBase
import lupos.operator.logical.OPBaseCompound
import lupos.operator.logical.Query
import lupos.operator.physical.POPBase
import lupos.operator.physical.partition.POPChangePartitionOrderedByIntId
import lupos.operator.physical.partition.POPMergePartition
import lupos.operator.physical.partition.POPMergePartitionCount
import lupos.operator.physical.partition.POPMergePartitionOrderedByIntId
import lupos.operator.physical.partition.POPSplitPartition
import lupos.operator.physical.partition.POPSplitPartitionFromStore
import lupos.operator.physical.partition.POPSplitPartitionFromStoreCount
import lupos.operator.physical.partition.POPSplitPartitionPassThrough
import lupos.shared.EPartitionModeExt
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.communicationHandler
import lupos.shared.operator.IOPBase
import lupos.shared.optimizer.IDistributedOptimizer
import lupos.triple_store_id_triple.POPTripleStoreIterator
import lupos.triple_store_id_triple.tripleStoreManager
import kotlin.jvm.JvmField

public class DistributedOptimizerQuery() : IDistributedOptimizer {
    internal var query: Query? = null

    @JvmField
    internal var operatorgraphParts: MutableMap<String, XMLElement> = mutableMapOf<String, XMLElement>()

    @JvmField
    internal var operatorgraphPartsToHostMap: MutableMap<String, String> = mutableMapOf<String, String>()

    @JvmField
    internal var dependenciesMapTopDown = mutableMapOf<String, Set<String>>()

    @JvmField
    internal var dependenciesMapBottomUp = mutableMapOf<String, Set<String>>()

    @JvmField
    internal var keyRepresentative = mutableMapOf<String, String>()

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

    private fun splitPartitionsVariations(node: IOPBase, allNames: Array<String>, allSize: IntArray, allIdx: IntArray, offset: Int) {
        if (offset == allNames.size) {
            for (i in 0 until allNames.size) {
                query!!.allVariationsKey[allNames[i]] = allIdx[i]
            }
            val xml = node.toXMLElementRoot(true)
            val keys = mutableSetOf<String>()
            for (c in xml.childs) {
                if (c.tag == "partitionDistributionProvideKey") {
                    keys.add(c.attributes["key"]!!)
                }
            }
            val key = keys.first()
            if (keys.size > 1) {
                for (k in keys) {
                    keyRepresentative[k] = key
                }
            }
            if (node is POPSplitPartitionFromStore) {
                SanityCheck.check { allIdx.size == 1 }
                var n: IOPBase = node
                while (n !is POPTripleStoreIterator) {
                    n = n.getChildren()[0]
                }
                var partition = Partition()
                for (i in 0 until allNames.size) {
                    partition = Partition(partition, allNames[i], allIdx[i], allSize[i])
                }
                val target = n.getDesiredHostnameFor(partition)
                SanityCheck.check { !operatorgraphPartsToHostMap.contains(key) || operatorgraphPartsToHostMap[key] == target }
                operatorgraphPartsToHostMap[key] = target
            }
            if (node is POPSplitPartitionFromStoreCount) {
                SanityCheck.check { allIdx.size == 1 }
                var n: IOPBase = node
                while (n !is POPTripleStoreIterator) {
                    n = n.getChildren()[0]
                }
                var partition = Partition()
                for (i in 0 until allNames.size) {
                    partition = Partition(partition, allNames[i], allIdx[i], allSize[i])
                }
                val target = n.getDesiredHostnameFor(partition)
                SanityCheck.check { !operatorgraphPartsToHostMap.contains(key) || operatorgraphPartsToHostMap[key] == target }
                operatorgraphPartsToHostMap[key] = target
            }
            operatorgraphParts[key] = xml
            query!!.allVariationsKey.clear()
        } else {
            for (i in 0 until allSize[offset]) {
                allIdx[offset] = i
                splitPartitionsVariations(node, allNames, allSize, allIdx, offset + 1)
            }
        }
    }

    private fun splitPartitions(node: IOPBase, currentPartitions: Map<String, Int>, root: Boolean) {
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
                is POPSplitPartitionFromStoreCount -> {
                    SanityCheck.check { currentPartitionsCopy[node.partitionVariable] == node.partitionCount }
                }
                is POPSplitPartitionPassThrough -> {
                    SanityCheck.check { currentPartitionsCopy[node.partitionVariable] == node.partitionCount }
                }
            }
            for (ci in 0 until (node as OPBase).childrenToVerifyCount()) {
                val c = node.getChildren()[ci]
                splitPartitions(c, currentPartitionsCopy, false)
            }
            when (node) {
                is POPMergePartition,
                is POPMergePartitionCount,
                is POPMergePartitionOrderedByIntId,
                is POPChangePartitionOrderedByIntId,
                is POPSplitPartition,
                is POPSplitPartitionFromStore,
                is POPSplitPartitionFromStoreCount,
                is POPSplitPartitionPassThrough
                -> {
                    val allNames = Array<String>(currentPartitionsCopy.size) { "" }
                    val allSize = IntArray(currentPartitionsCopy.size)
                    var i = 0
                    for ((k, v) in currentPartitionsCopy) {
                        allNames[i] = k
                        allSize[i] = v
                        i++
                    }
                    splitPartitionsVariations(node, allNames, allSize, IntArray(currentPartitionsCopy.size), 0)
                }
            }
        } else {
            throw Exception("this query is not executable ${node.getClassname()} ${(node as OPBase).uuid}")
        }
    }

    private fun calculateDependenciesTopDown(node: XMLElement): Set<String> {
        var res = mutableSetOf<String>()
        for (c in node.childs) {
            res.addAll(calculateDependenciesTopDown(c))
        }
        if (node.tag == "partitionDistributionReceiveKey") {
            res.add(node.attributes["key"]!!)
        }
        return res
    }

    private fun calculateDependenciesBottomUp(node: XMLElement): Set<String> {
        var res = mutableSetOf<String>()
        for (c in node.childs) {
            res.addAll(calculateDependenciesBottomUp(c))
        }
        if (node.tag == "partitionDistributionProvideKey") {
            res.add(node.attributes["key"]!!)
        }
        return res
    }

    private fun getHostForKey(key: String): String? {
        var res = operatorgraphPartsToHostMap[key]
        if (res != null) {
            return res
        }
        return operatorgraphPartsToHostMap[keyRepresentative[key]]
    }

    private fun assignHosts(node: XMLElement) {
        for (c in node.childs) {
            assignHosts(c)
        }
        if (node.tag == "partitionDistributionReceiveKey") {
            println("key -> ${node.attributes["key"]}")
            node.addAttribute("host", getHostForKey(node.attributes["key"]!!)!!)
        }
    }

    public override fun optimize(query: IQuery): IOPBase {
        this.query = query as Query
        val root = query.root!!
        if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
            operatorgraphParts.clear()
// assign host to root node
            operatorgraphParts[""] = root.toXMLElement(true)
            operatorgraphPartsToHostMap[""] = tripleStoreManager.getLocalhost()
// split query into parts, and automatically assign hosts to triple store access parts
            splitPartitions(root, mutableMapOf(), true)
// calculate dependencies
            for ((k, v) in operatorgraphParts) {
                dependenciesMapTopDown[k] = calculateDependenciesTopDown(v)
            }
            for ((k, v) in operatorgraphParts) {
                dependenciesMapBottomUp[k] = calculateDependenciesBottomUp(v)
            }
// assign hosts to other parts
            for (childOptimizer2 in childOptimizer) {
                var changed = true
                loop@ while (changed) {
                    changed = false
                    for (opt in childOptimizer2) {
                        for ((k, v) in operatorgraphParts) {
                            if (!operatorgraphPartsToHostMap.contains(k)) {
                                opt.optimize(k, v, dependenciesMapTopDown[k]!!, dependenciesMapBottomUp[k]!!, { it -> getHostForKey(it) }, { key, value -> operatorgraphPartsToHostMap[key] = value }) {
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
            for (k in operatorgraphParts.keys) {
                println("$k -> ${operatorgraphPartsToHostMap[k]}")
            }
            var res: XMLElement? = null
            for ((k, v) in operatorgraphParts) {
                println(v.toPrettyString())
                assignHosts(v)
                if (k == "") {
                    res = v
                } else {
                    communicationHandler.sendData(operatorgraphPartsToHostMap[k]!!, "/distributed/query/register", mapOf("query" to "$v"))
                }
            }
            return XMLElementToOPBase(query, res!!)
        } else {
            return root
        }
    }
}
