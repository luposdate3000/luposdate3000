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
package lupos.optimizer.physical
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.operator.logical.multiinput.LOPJoinTopology
import lupos.operator.physical.partition.POPMergePartition
import lupos.triple_store_manager.POPTripleStoreIterator
import lupos.operator.physical.partition.POPMergePartitionOrderedByIntId
import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.shared.operator.IOPBase

public class PhysicalOptimizerPartitionJoinTopology(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerPartitionAssingPartitionsToRemainingID, "PhysicalOptimizerPartitionJoinTopology") {
    // this store introduces fixes, if the desired triple store does not participate in any partitioning at all, but it is required to do so
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        if (node is LOPJoinTopology) {
            println()
            println()
            println()
            println("PhysicalOptimizerPartitionJoinTopology start")

            println("found ${node.children.size} childs")
            val variableNamesAndWhereTheyAppear = mutableMapOf<String, MutableSet<Int>>()
            val partitionings = mutableMapOf<String, MutableSet<Int>>()
            val possiblePartitions = List(node.children.size) { mutableMapOf<String, MutableSet<Int>>() }
            for (ci in 0 until node.children.size) {
                val c = node.children[ci]
                for (v in c.getProvidedVariableNames()) {
                    variableNamesAndWhereTheyAppear.getOrPut(v, { mutableSetOf(ci) }).add(ci)
                }
            }
            println("original variable mapping is: $variableNamesAndWhereTheyAppear")
            var variablesWhichAreNeverPartOfJoinColumn = mutableSetOf<String>()
            for ((k, v) in variableNamesAndWhereTheyAppear) {
                if (v.size == 1) {
                    variablesWhichAreNeverPartOfJoinColumn.add(k)
                }
            }
            for (k in variablesWhichAreNeverPartOfJoinColumn) {
                variableNamesAndWhereTheyAppear.remove(k)
            }
for (ci in 0 until node.children.size) {
                val c = node.children[ci]
                when (c) {
                    is POPMergePartitionOrderedByIntId -> {
                        partitionings.getOrPut(c.partitionVariable) { mutableSetOf(ci) }.add(ci)
                    }
                    is POPMergePartition -> {
                        val s = c.partitionVariable
                        if (s != null) {
                            partitionings.getOrPut(s) { mutableSetOf(ci) }.add(ci)
                        }
                    }
                    is POPTripleStoreIterator -> {
                        if (!c.hasSplitFromStore) {
                            for (cc in c.children) {
                                if (cc is AOPVariable) {
                                    val new_count = c.changeToIndexWithMaximumPartitions(null, cc.name)
                                    if (new_count > 0) {
                                        possiblePartitions[ci].getOrPut(cc.name) { mutableSetOf(new_count) }.add(new_count)
                                    }
                                }
                            }
                        }
                    }
                }

            }
            println("join variable mapping is: $variableNamesAndWhereTheyAppear")
            println("partitions $partitionings")
            println("possible partitions $possiblePartitions")

            println("PhysicalOptimizerPartitionJoinTopology end")
            println(node)
            println()
            println()
        }
        return node
    }
}
