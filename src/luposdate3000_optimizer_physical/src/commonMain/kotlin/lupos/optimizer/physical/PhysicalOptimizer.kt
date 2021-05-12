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
import lupos.shared.LUPOS_VISUALIZE

public class PhysicalOptimizer public constructor(query: Query) : OptimizerCompoundBase(query, EOptimizerIDExt.PhysicalOptimizerID, "PhysicalOptimizer") {
    override val childrenOptimizers: Array<Array<OptimizerBase>> = arrayOf(
        //
        arrayOf(
            PhysicalOptimizerJoinType(query), //
        ),
        arrayOf(
            PhysicalOptimizerTripleIndex(query), //
        ),
        arrayOf(
            PhysicalOptimizerNaive(query), //
        ),
        arrayOf(
            PhysicalOptimizerPartitionExpandPartitionTowardsStore(query), //
            PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator(query), //
            PhysicalOptimizerPartitionExpandTowardsRoot(query), //
        ),
        arrayOf(
            PhysicalOptimizerPartitionRespectMaxPartitions(query), //
        ),
        arrayOf(
            PhysicalOptimizerPartitionRemoveUselessPartitions(query), //
        ),
        arrayOf(
            PhysicalOptimizerPartitionAssingPartitionsToRemaining(query), //
        ),
        arrayOf(
            // PhysicalOptimizerDebug(query), //
        ),
        if (LUPOS_VISUALIZE) {
            arrayOf<OptimizerBase>(PhysicalOptimizerVisualisation(query))
        } else {
            arrayOf<OptimizerBase>()
        },

    )
}
