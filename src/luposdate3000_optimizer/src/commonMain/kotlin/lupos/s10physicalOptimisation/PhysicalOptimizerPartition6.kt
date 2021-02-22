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
package lupos.s10physicalOptimisation

import lupos.s00misc.EOptimizerIDExt
import lupos.s00misc.EPartitionModeExt
import lupos.s00misc.USE_PARTITIONS
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.POPTripleStoreIterator
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore

public class PhysicalOptimizerPartition6(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerPartition6ID, "PhysicalOptimizerPartition6") {
    // this store introduces fixes, if the desired triple store does not participate in any partitioning at all, but it is required to do so
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if ((USE_PARTITIONS == EPartitionModeExt.Thread || USE_PARTITIONS == EPartitionModeExt.Process)) {
            when (node) {
                is POPTripleStoreIterator -> {
                    if (!node.hasSplitFromStore) {
                        var partitionVariable = ""
                        var new_count = -1
                        for (c in node.children) {
                            if (c is AOPVariable) {
                                try {
                                    partitionVariable = c.name
                                    println("PhysicalOptimizerPartition6 : initialize specific ${node.getUUID()}")
                                    new_count = node.changeToIndexWithMaximumPartitions(null, partitionVariable)
                                    break
                                } catch (e: Throwable) {
                                }
                            }
                        }
                        if (new_count > 1) {
                            val partitionID = query.getNextPartitionOperatorID()
                            println("PhysicalOptimizerPartition6 : initialize specific ${node.getUUID()}")
                            res = POPSplitPartitionFromStore(query, node.projectedVariables, partitionVariable, new_count, partitionID, node)
                            query.addPartitionOperator(res.getUUID(), partitionID)
                            res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, partitionVariable, new_count, partitionID, res)
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
