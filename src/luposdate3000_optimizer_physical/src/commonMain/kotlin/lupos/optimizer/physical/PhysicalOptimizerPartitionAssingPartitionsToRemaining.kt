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
import lupos.operator.physical.partition.POPMergePartitionCount
import lupos.operator.physical.partition.POPMergePartitionOrderedByIntId
import lupos.operator.physical.partition.POPSplitPartitionFromStore
import lupos.operator.physical.partition.POPSplitPartitionFromStoreCount
import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.shared.EPartitionModeExt
import lupos.shared.operator.IOPBase
import lupos.triple_store_manager.POPTripleStoreIterator

public class PhysicalOptimizerPartitionAssingPartitionsToRemaining(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerPartitionAssingPartitionsToRemainingID, "PhysicalOptimizerPartitionAssingPartitionsToRemaining") {
    // this store introduces fixes, if the desired triple store does not participate in any partitioning at all, but it is required to do so
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if (query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Thread || query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
            when (node) {
                is POPTripleStoreIterator -> {
                    if (!node.hasSplitFromStore) {
                        var partitionVariableMax = ""
                        var new_countMax = -1
                        for (c in node.children) {
                            if (c is AOPVariable) {
                                try {
                                    val partitionVariable = c.name
                                    val new_count = node.changeToIndexWithMaximumPartitions(null, partitionVariable)
                                    if (new_count> new_countMax) {
                                        new_countMax = new_count
                                        partitionVariableMax = partitionVariable
                                    }
                                } catch (e: Throwable) {
                                    // e.printStackTrace() this is handled correctly
                                }
                            }
                        }
                        if (new_countMax > 1) {
                            val partitionID = query.getNextPartitionOperatorID()
                            if (node.projectedVariables.size > 0) {
                                res = POPSplitPartitionFromStore(query, node.projectedVariables, partitionVariableMax, new_countMax, partitionID, node)
                            } else {
                                res = POPSplitPartitionFromStoreCount(query, node.projectedVariables, partitionVariableMax, new_countMax, partitionID, node)
                            }
                            query.addPartitionOperator(res.getUUID(), partitionID)
                            if (node.projectedVariables.size > 0) {
                                res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, partitionVariableMax, new_countMax, partitionID, res)
                            } else {
                                res = POPMergePartitionCount(query, node.projectedVariables, partitionVariableMax, new_countMax, partitionID, res)
                            }
                            query.addPartitionOperator(res.getUUID(), partitionID)
                            node.hasSplitFromStore = true
                            onChange()
                        }
                    }
                }
            }
        }
        return res
    }
}
