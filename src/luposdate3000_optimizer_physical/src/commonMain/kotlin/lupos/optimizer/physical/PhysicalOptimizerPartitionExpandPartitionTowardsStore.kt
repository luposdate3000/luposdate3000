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

import lupos.operator.arithmetik.AOPBase
import lupos.operator.base.Query
import lupos.operator.physical.partition.POPSplitPartition
import lupos.operator.physical.partition.POPSplitPartitionFromStore
import lupos.operator.physical.partition.POPSplitPartitionFromStoreCount
import lupos.operator.physical.singleinput.POPFilter
import lupos.operator.physical.singleinput.POPProjection
import lupos.operator.physical.singleinput.modifiers.POPReduced
import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.shared.EPartitionModeExt
import lupos.shared.operator.IOPBase
import lupos.triple_store_manager.POPTripleStoreIterator

public class PhysicalOptimizerPartitionExpandPartitionTowardsStore(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerPartitionExpandPartitionTowardsStoreID, "PhysicalOptimizerPartitionExpandPartitionTowardsStore") {
    // this optimizer moved the partitioning towards and into the triple store, but does NOT care if the specific triple store exist ...
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if (query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Thread || query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
            when (node) {
                is POPSplitPartition -> {
// splitting must always split all variables provided by its direct children - if there is a different children, adapt the variables
                    when (val c = node.children[0]) {
                        is POPReduced -> {
                            res = POPReduced(query, c.projectedVariables, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0]))
                            query.removePartitionOperator(node.getUUID(), node.partitionID)
                            query.addPartitionOperator(res.children[0].getUUID(), node.partitionID)
                            onChange()
                        }
                        is POPProjection -> {
                            res = POPProjection(query, c.projectedVariables, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0]))
                            query.removePartitionOperator(node.getUUID(), node.partitionID)
                            query.addPartitionOperator(res.children[0].getUUID(), node.partitionID)
                            onChange()
                        }
                        is POPFilter -> {
                            res = POPFilter(query, c.projectedVariables, c.children[1] as AOPBase, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0]))
                            query.removePartitionOperator(node.getUUID(), node.partitionID)
                            query.addPartitionOperator(res.children[0].getUUID(), node.partitionID)
                            onChange()
                        }
                        is POPTripleStoreIterator -> {
                            val partitionVariable = node.partitionVariable
                            if (partitionVariable != null) {
                                val new_count = c.changeToIndexWithMaximumPartitions(node.partitionCount, partitionVariable)
                                if (new_count > 0) {
                                    c.hasSplitFromStore = true
                                    res = if (node.projectedVariables.isNotEmpty()) {
                                        POPSplitPartitionFromStore(query, node.projectedVariables, partitionVariable, new_count, node.partitionID, c)
                                    } else {
                                        POPSplitPartitionFromStoreCount(query, node.projectedVariables, partitionVariable, new_count, node.partitionID, c)
                                    }
                                    query.removePartitionOperator(node.getUUID(), node.partitionID)
                                    query.addPartitionOperator(res.getUUID(), node.partitionID)
                                    onChange()
                                } else {
//                                    TODO("PhysicalOptimizerPartitionExpandPartitionTowardsStore unable to assign B ${node.getUUID()} ${c.getUUID()}")
// this should be handled by PhysicalOptimizerPartitionAssingPartitionsToRemaining afterwards
                                }
                            } else {
//                                TODO("PhysicalOptimizerPartitionExpandPartitionTowardsStore unable to assign A ${node.getUUID()} ${c.getUUID()}")
// this should be handled by PhysicalOptimizerPartitionAssingPartitionsToRemaining afterwards
                            }
                        }
                    }
                }
            }
        }
        return res
    }
}
