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

import lupos.s00misc.DontCareWhichException
import lupos.s00misc.EOptimizerIDExt
import lupos.s00misc.EPartitionModeExt
import lupos.s00misc.USE_PARTITIONS
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.POPTripleStoreIterator
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.modifiers.POPReduced

public class PhysicalOptimizerPartition1(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerPartition1ID, "PhysicalOptimizerPartition1") {
    // this optimizer moved the partitioning towards and into the triple store, but does NOT care if the specific triple store exist ...
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if ((USE_PARTITIONS == EPartitionModeExt.Thread || USE_PARTITIONS == EPartitionModeExt.Process)) {
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
                            try {
                                println("PhysicalOptimizerPartition1 : initialize specific ${c.getUUID()}")
                                val new_count = c.changeToIndexWithMaximumPartitions(node.partitionCount, node.partitionVariable)
                                c.hasSplitFromStore = true
                                println("PhysicalOptimizerPartition1 : initialize specific ${c.getUUID()}")
                                res = POPSplitPartitionFromStore(query, node.projectedVariables, node.partitionVariable, node.partitionCount, node.partitionID, c)
                                query.removePartitionOperator(node.getUUID(), node.partitionID)
                                query.addPartitionOperator(res.getUUID(), node.partitionID)
                                onChange()
                            } catch (e: DontCareWhichException) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
        }
        return res
    }
}
