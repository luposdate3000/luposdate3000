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
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.Query
import lupos.operator.physical.POPBase
import lupos.operator.physical.partition.APOPParallel
import lupos.operator.physical.partition.POPMergePartition
import lupos.operator.physical.partition.POPSplitPartition
import lupos.operator.physical.singleinput.POPDebug
import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.shared.operator.IOPBase

public class PhysicalOptimizerSplitMergePartition(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerSplitMergePartitionID, "PhysicalOptimizerSplitMergePartition") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        when (node) {
            !is APOPParallel -> {
                if (node is POPBase && (
                        parent == null || (
                            parent !is APOPParallel &&
                                parent !is OPBaseCompound &&
                                parent !is POPDebug
                            )
                        )
                ) {
                    val provided = node.getProvidedVariableNames()
                    if (provided.size > 0) {
                        val partitionID = query.getNextPartitionOperatorID()
                        res = POPSplitPartition(query, provided, null, 1, partitionID, node)
                        query.addPartitionOperator(res.uuid, partitionID)
                        res = POPMergePartition(query, provided, null, 1, partitionID, res)
                        query.addPartitionOperator(res.uuid, partitionID)
                        onChange()
                    }
                }
            }
        }
        return res
    }
}
