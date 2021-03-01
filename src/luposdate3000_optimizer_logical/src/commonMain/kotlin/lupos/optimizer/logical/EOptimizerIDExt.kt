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
package lupos.optimizer.logical

import kotlin.jvm.JvmField

public object EOptimizerIDExt {
    public const val LogicalOptimizerArithmeticID: EOptimizerID = 0
    public const val LogicalOptimizerBindToFilterID: EOptimizerID = 1
    public const val LogicalOptimizerBindUpID: EOptimizerID = 2
    public const val LogicalOptimizerColumnSortOrderID: EOptimizerID = 3
    public const val LogicalOptimizerDetectMinusID: EOptimizerID = 4
    public const val LogicalOptimizerDetectMinusStep2ID: EOptimizerID = 5
    public const val LogicalOptimizerDistinctSplitID: EOptimizerID = 6
    public const val LogicalOptimizerDistinctUpID: EOptimizerID = 7
    public const val LogicalOptimizerExistsID: EOptimizerID = 8
    public const val LogicalOptimizerFilterDownID: EOptimizerID = 9
    public const val LogicalOptimizerFilterEQID: EOptimizerID = 10
    public const val LogicalOptimizerFilterIntoTripleID: EOptimizerID = 11
    public const val LogicalOptimizerFilterMergeANDID: EOptimizerID = 12
    public const val LogicalOptimizerFilterOptionalID: EOptimizerID = 13
    public const val LogicalOptimizerFilterOptionalStep2ID: EOptimizerID = 14
    public const val LogicalOptimizerFilterSplitANDID: EOptimizerID = 15
    public const val LogicalOptimizerFilterSplitORID: EOptimizerID = 16
    public const val LogicalOptimizerFilterUpID: EOptimizerID = 17
    public const val LogicalOptimizerID: EOptimizerID = 18
    public const val LogicalOptimizerJoinOrderID: EOptimizerID = 19
    public const val LogicalOptimizerMinusAddSortID: EOptimizerID = 20
    public const val LogicalOptimizerOptionalID: EOptimizerID = 21
    public const val LogicalOptimizerProjectionDownID: EOptimizerID = 22
    public const val LogicalOptimizerProjectionUpID: EOptimizerID = 23
    public const val LogicalOptimizerReducedDownID: EOptimizerID = 24
    public const val LogicalOptimizerRemoveBindVariableID: EOptimizerID = 25
    public const val LogicalOptimizerRemoveNOOPID: EOptimizerID = 26
    public const val LogicalOptimizerRemovePrefixID: EOptimizerID = 27
    public const val LogicalOptimizerRemoveProjectionID: EOptimizerID = 28
    public const val LogicalOptimizerSortDownID: EOptimizerID = 29
    public const val LogicalOptimizerStoreToValuesID: EOptimizerID = 30
    public const val LogicalOptimizerUnionUpID: EOptimizerID = 31
    public const val PhysicalOptimizerDebugID: EOptimizerID = 32
    public const val PhysicalOptimizerID: EOptimizerID = 33
    public const val PhysicalOptimizerJoinTypeID: EOptimizerID = 34
    public const val PhysicalOptimizerNaiveID: EOptimizerID = 35
    public const val PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperatorID: EOptimizerID = 36
    public const val PhysicalOptimizerPartitionAssingPartitionsToRemainingID: EOptimizerID = 37
    public const val PhysicalOptimizerPartitionExpandPartitionTowardsStoreID: EOptimizerID = 38
    public const val PhysicalOptimizerPartitionExpandTowardsRootID: EOptimizerID = 39
    public const val PhysicalOptimizerPartitionRemoveUselessPartitionsID: EOptimizerID = 40
    public const val PhysicalOptimizerPartitionRespectMaxPartitionsID: EOptimizerID = 41
    public const val PhysicalOptimizerTripleIndexID: EOptimizerID = 42
    public const val values_size: Int = 43

    @JvmField
    public val names: Array<String> = arrayOf(
        "LogicalOptimizerArithmeticID",
        "LogicalOptimizerBindToFilterID",
        "LogicalOptimizerBindUpID",
        "LogicalOptimizerColumnSortOrderID",
        "LogicalOptimizerDetectMinusID",
        "LogicalOptimizerDetectMinusStep2ID",
        "LogicalOptimizerDistinctSplitID",
        "LogicalOptimizerDistinctUpID",
        "LogicalOptimizerExistsID",
        "LogicalOptimizerFilterDownID",
        "LogicalOptimizerFilterEQID",
        "LogicalOptimizerFilterIntoTripleID",
        "LogicalOptimizerFilterMergeANDID",
        "LogicalOptimizerFilterOptionalID",
        "LogicalOptimizerFilterOptionalStep2ID",
        "LogicalOptimizerFilterSplitANDID",
        "LogicalOptimizerFilterSplitORID",
        "LogicalOptimizerFilterUpID",
        "LogicalOptimizerID",
        "LogicalOptimizerJoinOrderID",
        "LogicalOptimizerMinusAddSortID",
        "LogicalOptimizerOptionalID",
        "LogicalOptimizerProjectionDownID",
        "LogicalOptimizerProjectionUpID",
        "LogicalOptimizerReducedDownID",
        "LogicalOptimizerRemoveBindVariableID",
        "LogicalOptimizerRemoveNOOPID",
        "LogicalOptimizerRemovePrefixID",
        "LogicalOptimizerRemoveProjectionID",
        "LogicalOptimizerSortDownID",
        "LogicalOptimizerStoreToValuesID",
        "LogicalOptimizerUnionUpID",
        "PhysicalOptimizerDebugID",
        "PhysicalOptimizerID",
        "PhysicalOptimizerJoinTypeID",
        "PhysicalOptimizerNaiveID",
        "PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperatorID",
        "PhysicalOptimizerPartitionAssingPartitionsToRemainingID",
        "PhysicalOptimizerPartitionExpandPartitionTowardsStoreID",
        "PhysicalOptimizerPartitionExpandTowardsRootID",
        "PhysicalOptimizerPartitionRemoveUselessPartitionsID",
        "PhysicalOptimizerPartitionRespectMaxPartitionsID",
        "PhysicalOptimizerTripleIndexID",
    )
}
