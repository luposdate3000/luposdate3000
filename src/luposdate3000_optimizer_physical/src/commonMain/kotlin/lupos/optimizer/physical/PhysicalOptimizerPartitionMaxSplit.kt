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
import lupos.operator.physical.multiinput.POPUnion
import lupos.operator.physical.partition.POPChangePartitionOrderedByIntId
import lupos.operator.physical.partition.POPMergePartition
import lupos.operator.physical.partition.POPMergePartitionCount
import lupos.operator.physical.partition.POPMergePartitionOrderedByIntId
import lupos.operator.physical.partition.POPSplitPartition
import lupos.operator.physical.partition.POPSplitPartitionFromStore
import lupos.operator.physical.partition.POPSplitPartitionFromStoreCount
import lupos.operator.physical.singleinput.POPBind
import lupos.operator.physical.singleinput.POPFilter
import lupos.operator.physical.singleinput.POPProjection
import lupos.operator.physical.singleinput.modifiers.POPReduced
import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.shared.EPartitionModeExt
import lupos.shared.operator.IOPBase
import lupos.triple_store_manager.POPTripleStoreIterator
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

public class PhysicalOptimizerPartitionMaxSplit(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerPartitionMaxSplitID, "PhysicalOptimizerPartitionMaxSplit") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if (query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Thread || query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
            when (node) {
                is POPSplitPartition -> {
                    when (val c = node.children[0]) {
                        is POPMergePartition -> {
                            val res2 = POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0])
                            res = POPMergePartition(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, res2)
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                        is POPMergePartitionOrderedByIntId -> {
                            val res2 = POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0])
                            res = POPMergePartitionOrderedByIntId(query, node.projectedVariables, c.partitionVariable, c.partitionCount2, c.partitionID, res2)
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            res.setMySortPriority(c.mySortPriority, node.projectedVariables)
                            onChange()
                        }
                        is POPMergePartitionCount -> {
                            val res2 = POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0])
                            res = POPMergePartitionCount(query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, res2)
                            query.removePartitionOperator(c.getUUID(), c.partitionID)
                            query.addPartitionOperator(res.getUUID(), c.partitionID)
                            query.partitionOperatorCount.clear()
                            onChange()
                        }
                    }
                }
            }
        }
        return res
    }
}
