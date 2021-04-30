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
package lupos.optimizer.logical

import lupos.operator.base.Query
import lupos.operator.physical.partition.POPChangePartitionOrderedByIntId
import lupos.operator.physical.partition.POPMergePartition
import lupos.operator.physical.partition.POPMergePartitionCount
import lupos.operator.physical.partition.POPMergePartitionOrderedByIntId
import lupos.operator.physical.partition.POPSplitPartition
import lupos.operator.physical.partition.POPSplitPartitionFromStore
import lupos.operator.physical.partition.POPSplitPartitionFromStoreCount
import lupos.shared.SanityCheck
import lupos.shared.operator.IOPBase

public abstract class OptimizerCompoundBase public constructor(query: Query, optimizerID: EOptimizerID, classname: String) : OptimizerBase(query, optimizerID, classname) {
    public abstract val childrenOptimizers: Array<Array<OptimizerBase>>
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase = node
    private fun verifyPartitionOperators(node: IOPBase, allList: MutableMap<Int, MutableSet<Long>>, currentPartitions_: MutableMap<String, Int>, root: IOPBase) {
        val currentPartitions = mutableMapOf<String, Int>()
        currentPartitions.putAll(currentPartitions_)
        val ids = mutableListOf<Int>()
        when (node) {
            is POPMergePartitionCount -> {
                SanityCheck.check { !currentPartitions.contains(node.partitionVariable) }
                currentPartitions[node.partitionVariable] = node.partitionCount
                ids.add(node.partitionID)
            }
            is POPMergePartition -> {
                SanityCheck.check { !currentPartitions.contains(node.partitionVariable) }
                currentPartitions[node.partitionVariable] = node.partitionCount
                ids.add(node.partitionID)
            }
            is POPMergePartitionOrderedByIntId -> {
                SanityCheck.check { !currentPartitions.contains(node.partitionVariable) }
                currentPartitions[node.partitionVariable] = node.partitionCount
                ids.add(node.partitionID)
            }
            is POPSplitPartitionFromStore -> {
                SanityCheck.check({ currentPartitions[node.partitionVariable] == node.partitionCount }, { "${root.toXMLElement(false).toPrettyString()}" })
                currentPartitions[node.partitionVariable] = -node.partitionCount
                ids.add(node.partitionID)
            }
            is POPSplitPartitionFromStoreCount -> {
                SanityCheck.check({ currentPartitions[node.partitionVariable] == node.partitionCount }, { "${root.toXMLElement(false).toPrettyString()}" })
                currentPartitions[node.partitionVariable] = -node.partitionCount
                ids.add(node.partitionID)
            }
            is POPSplitPartition -> {
                SanityCheck.check({ currentPartitions[node.partitionVariable] == node.partitionCount }, { "$root" })
                currentPartitions.remove(node.partitionVariable)
                ids.add(node.partitionID)
            }
            is POPChangePartitionOrderedByIntId -> {
                SanityCheck.check({ currentPartitions[node.partitionVariable] == node.partitionCountTo }, { "$root" })
                currentPartitions[node.partitionVariable] = node.partitionCountFrom
                ids.add(node.partitionIDFrom)
                ids.add(node.partitionIDTo)
            }
        }
        for (id in ids) {
            val tmp = allList[id]
            if (tmp == null) {
                allList[id] = mutableSetOf(node.getUUID())
            } else {
                tmp.add(node.getUUID())
            }
        }
        for (c in node.getChildren()) {
            verifyPartitionOperators(c, allList, currentPartitions, root)
        }
    }

    override /*suspend*/ fun optimizeCall(node: IOPBase, onChange: () -> Unit, nextStep: (IOPBase) -> Unit): IOPBase {
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
                    var c = true
                    while (c) {
                        c = false
                        tmp = o.optimizeInternal(tmp, null) {
                            if (EOptimizerIDHelper.repeatOnChange[o.optimizerID]) {
                                c = true
                                d = true
                                onChange()
                            }
                        }
                        nextStep(tmp)
                        println("optimizer :: " + o.classname)
                        println(tmp)
                    }
                    SanityCheck {
                        val allPartitionOperators = mutableMapOf<Int, MutableSet<Long>>()
                        verifyPartitionOperators(tmp, allPartitionOperators, mutableMapOf<String, Int>(), tmp)
                        for ((k, v1) in allPartitionOperators) {
                            val v2 = query.partitionOperators[k]
                            SanityCheck.check({ v1 == v2 }, { "$allPartitionOperators  <-a-> ${query.partitionOperators}\n$tmp" })
                        }
                        for ((k, v1) in query.partitionOperators) {
                            val v2 = allPartitionOperators[k]
                            SanityCheck.check({ v1 == v2 }, { "$allPartitionOperators  <-b-> ${query.partitionOperators}\n$tmp" })
                        }
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
