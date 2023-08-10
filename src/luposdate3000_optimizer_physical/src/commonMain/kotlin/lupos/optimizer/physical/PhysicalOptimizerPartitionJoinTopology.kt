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
import lupos.operator.physical.partition.POPMergePartitionOrderedByIntId
import lupos.operator.physical.partition.POPSplitPartition
import lupos.operator.physical.singleinput.POPProjection
import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.shared.operator.IOPBase
import lupos.triple_store_manager.POPTripleStoreIterator

public class PhysicalOptimizerPartitionJoinTopology(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerPartitionJoinTopologyID, "PhysicalOptimizerPartitionJoinTopology") {
    // this store introduces fixes, if the desired triple store does not participate in any partitioning at all, but it is required to do so
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        if (node is LOPJoinTopology) {
            if (parent !is POPMergePartitionOrderedByIntId && parent !is POPMergePartition) {
                val variableNamesAndWhereTheyAppear = mutableMapOf<String, MutableSet<Int>>()
                val partitionings = mutableMapOf<String, MutableSet<Int>>()
                val possiblePartitions = mutableMapOf<Pair<String, Int>, MutableSet<Int>>()
                for (ci in 0 until node.children.size) {
                    val c = node.children[ci]
                    for (v in c.getProvidedVariableNames()) {
                        variableNamesAndWhereTheyAppear.getOrPut(v, { mutableSetOf(ci) }).add(ci)
                    }
                }
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
                                        if (variableNamesAndWhereTheyAppear.keys.contains(cc.name)) {
                                            val new_count = c.changeToIndexWithMaximumPartitions(null, cc.name)
                                            if (new_count > 0) {
                                                possiblePartitions.getOrPut(cc.name to new_count) { mutableSetOf(ci) }.add(ci)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (possiblePartitions.size> 0) {
                    var largestC = 0
                    var largestV = "" to 0
                    for ((k, v) in possiblePartitions) {
                        if (v.size> largestC) {
                            largestC = v.size
                            largestV = k
                        }
                    }
                    if (largestC> 1) {
                        val choosenPartition = possiblePartitions[largestV]!!

                        val childInputs = mutableListOf<IOPBase>()
                        val parentInputs = mutableListOf<IOPBase>()
                        for (i in 0 until node.children.size) {
                            if (i in choosenPartition) {
                                childInputs.add(node.children[i])
                            } else {
                                parentInputs.add(node.children[i])
                            }
                        }
                        val partitionID = query.getNextPartitionOperatorID()

                        val childInputs2 = childInputs.map { POPSplitPartition(query, it.getProvidedVariableNames(), largestV.first, largestV.second, partitionID, it) }

                        for (c in childInputs2) {
                            query.addPartitionOperator(c.getUUID(), partitionID)
                        }

                        val child = if (childInputs2.size> 1) {
                            LOPJoinTopology(node.query, childInputs2.toTypedArray())
                        } else {
                            if (node.projectedVariables != null) {
                                POPProjection(query, node.projectedVariables!!, childInputs2[0])
                            } else {
                                childInputs2[0]
                            }
                        }
                        parentInputs.add(POPMergePartition(query, childInputs.map { it.getProvidedVariableNames() }.flatten(), largestV.first, largestV.second, partitionID, child))
                        query.addPartitionOperator(parentInputs.last().getUUID(), partitionID)

                        onChange()
                        val res = if (parentInputs.size> 1) {
                            val rr = LOPJoinTopology(node.query, parentInputs.toTypedArray())
                            rr.projectedVariables = node.projectedVariables
                            rr
                        } else {
                            if (node.projectedVariables != null) {
                                POPProjection(query, node.projectedVariables!!, parentInputs[0])
                            } else {
                                parentInputs[0]
                            }
                        }
                        return res
                    }
                }
            }
        }
        return node
    }
}
