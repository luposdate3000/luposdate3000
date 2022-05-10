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

import lupos.operator.base.Query
import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.optimizer.logical.OptimizerCompoundBase

public class PhysicalOptimizer public constructor(query: Query) : OptimizerCompoundBase(query, EOptimizerIDExt.PhysicalOptimizerID, "PhysicalOptimizer") {
    override val childrenOptimizers: Array<Array<OptimizerBase>> = arrayOf(
        //
        arrayOf(
            PhysicalOptimizerJoinType(query), //
            PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator(query), //
        ),
        arrayOf(
            PhysicalOptimizerTripleIndex(query), //
            PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator(query), //
        ),
        arrayOf(
            PhysicalOptimizerNaive(query), //
            PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator(query), //
        ),
        arrayOf(
            PhysicalOptimizerPartitionExpandPartitionTowardsStore(query), //
            PhysicalOptimizerPartitionExpandTowardsRoot(query), //
            PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator(query), //
        ),
        arrayOf(
            PhysicalOptimizerPartitionRemoveUselessPartitions(query), //
            PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator(query), //
        ),
        arrayOf(
            PhysicalOptimizerPartitionAssingPartitionsToRemaining(query), //
            PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator(query), //
        ),
        arrayOf(
if(inSimulator){
            PhysicalOptimizerPartitionRespectMaxSplit(query) //
}else{
            PhysicalOptimizerPartitionRespectMaxPartitions(query) //
},
            PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator(query), //
        ),
        arrayOf(
            PhysicalOptimizerDebug(query), //
        ),
    )
}
