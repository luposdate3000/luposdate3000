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
import lupos.operator.logical.IOPBase
import lupos.operator.logical.Query
import lupos.operator.physical.partition.POPMergePartitionCount
import lupos.operator.physical.partition.POPMergePartitionOrderedByIntId
import lupos.operator.physical.partition.POPSplitPartitionFromStore
import lupos.operator.physical.partition.POPSplitPartitionFromStoreCount
import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.shared.EPartitionModeExt
import lupos.triple_store_id_triple.POPTripleStoreIterator
import lupos.triple_store_id_triple.tripleStoreManager

public class PhysicalOptimizerPartitionAssingPartitionsToRemaining(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerPartitionAssingPartitionsToRemainingID, "PhysicalOptimizerPartitionAssingPartitionsToRemaining") {
    // this store introduces fixes, if the desired triple store does not participate in any partitioning at all, but it is required to do so
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if ((tripleStoreManager.getPartitionMode() == EPartitionModeExt.Thread || tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process)) {
            when (node) {
                is POPTripleStoreIterator -> {
                    if (!node.hasSplitFromStore) {
                        var partitionVariable = ""
                        var new_count = -1
                        for (c in node.children) {
                            if (c is AOPVariable) {
                                try {
                                    partitionVariable = c.name
                                    println("PhysicalOptimizerPartitionAssingPartitionsToRemaining : initialize specific ${node.getUUID()}")
                                    new_count = node.changeToIndexWithMaximumPartitions(null, partitionVariable)
                                    break
                                } catch (e: Throwable) {
                                    e.printStackTrace()
                                }
                            }
                        }
                        if (new_count > 1) {
                            val partitionID = query.getNextPartitionOperatorID()
                            println("PhysicalOptimizerPartitionAssingPartitionsToRemaining : initialize specific ${node.getUUID()}")
                            if (node.projectedVariables.size > 0) {
                                res = POPSplitPartitionFromStore(query, node.projectedVariables, partitionVariable, new_count, partitionID, node)
                            } else {
                                res = POPSplitPartitionFromStoreCount(query, node.projectedVariables, partitionVariable, new_count, partitionID, node)
                            }
                            query.addPartitionOperator(res.getUUID(), partitionID)
                            if (node.projectedVariables.size > 0) {
                                res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, partitionVariable, new_count, partitionID, res)
                            } else {
                                res = POPMergePartitionCount(query, node.projectedVariables, partitionVariable, new_count, partitionID, res)
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
