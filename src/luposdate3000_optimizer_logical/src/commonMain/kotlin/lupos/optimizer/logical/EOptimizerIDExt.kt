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
    public const val LogicalOptimizerArithmeticID: EOptimizerID = 0 // 0x00000000
    public const val LogicalOptimizerBindToFilterID: EOptimizerID = 1 // 0x00000001
    public const val LogicalOptimizerBindUpID: EOptimizerID = 2 // 0x00000002
    public const val LogicalOptimizerColumnSortOrderID: EOptimizerID = 3 // 0x00000003
    public const val LogicalOptimizerDetectMinusID: EOptimizerID = 4 // 0x00000004
    public const val LogicalOptimizerDetectMinusStep2ID: EOptimizerID = 5 // 0x00000005
    public const val LogicalOptimizerDistinctSplitID: EOptimizerID = 6 // 0x00000006
    public const val LogicalOptimizerDistinctUpID: EOptimizerID = 7 // 0x00000007
    public const val LogicalOptimizerExistsID: EOptimizerID = 8 // 0x00000008
    public const val LogicalOptimizerFilterDownID: EOptimizerID = 9 // 0x00000009
    public const val LogicalOptimizerFilterEQID: EOptimizerID = 10 // 0x0000000a
    public const val LogicalOptimizerFilterIntoTripleID: EOptimizerID = 11 // 0x0000000b
    public const val LogicalOptimizerFilterMergeANDID: EOptimizerID = 12 // 0x0000000c
    public const val LogicalOptimizerFilterOptionalID: EOptimizerID = 13 // 0x0000000d
    public const val LogicalOptimizerFilterOptionalStep2ID: EOptimizerID = 14 // 0x0000000e
    public const val LogicalOptimizerFilterSplitANDID: EOptimizerID = 15 // 0x0000000f
    public const val LogicalOptimizerFilterSplitORID: EOptimizerID = 16 // 0x00000010
    public const val LogicalOptimizerFilterUpID: EOptimizerID = 17 // 0x00000011
    public const val LogicalOptimizerID: EOptimizerID = 18 // 0x00000012
    public const val LogicalOptimizerJoinOrderID: EOptimizerID = 19 // 0x00000013
    public const val LogicalOptimizerMinusAddSortID: EOptimizerID = 20 // 0x00000014
    public const val LogicalOptimizerOptionalID: EOptimizerID = 21 // 0x00000015
    public const val LogicalOptimizerProjectionDownID: EOptimizerID = 22 // 0x00000016
    public const val LogicalOptimizerProjectionUpID: EOptimizerID = 23 // 0x00000017
    public const val LogicalOptimizerReducedDownID: EOptimizerID = 24 // 0x00000018
    public const val LogicalOptimizerRemoveBindVariableID: EOptimizerID = 25 // 0x00000019
    public const val LogicalOptimizerRemoveNOOPID: EOptimizerID = 26 // 0x0000001a
    public const val LogicalOptimizerRemovePrefixID: EOptimizerID = 27 // 0x0000001b
    public const val LogicalOptimizerRemoveProjectionID: EOptimizerID = 28 // 0x0000001c
    public const val LogicalOptimizerSortDownID: EOptimizerID = 29 // 0x0000001d
    public const val LogicalOptimizerStoreToValuesID: EOptimizerID = 30 // 0x0000001e
    public const val LogicalOptimizerUnionUpID: EOptimizerID = 31 // 0x0000001f
    public const val PhysicalOptimizerDebugID: EOptimizerID = 32 // 0x00000020
    public const val PhysicalOptimizerID: EOptimizerID = 33 // 0x00000021
    public const val PhysicalOptimizerJoinTypeID: EOptimizerID = 34 // 0x00000022
    public const val PhysicalOptimizerNaiveID: EOptimizerID = 35 // 0x00000023
    public const val PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperatorID: EOptimizerID = 36 // 0x00000024
    public const val PhysicalOptimizerPartitionAssingPartitionsToRemainingID: EOptimizerID = 37 // 0x00000025
    public const val PhysicalOptimizerPartitionExpandPartitionTowardsStoreID: EOptimizerID = 38 // 0x00000026
    public const val PhysicalOptimizerPartitionExpandTowardsRootID: EOptimizerID = 39 // 0x00000027
    public const val PhysicalOptimizerPartitionJoinTopologyID: EOptimizerID = 40 // 0x00000028
    public const val PhysicalOptimizerPartitionMaxSplitID: EOptimizerID = 41 // 0x00000029
    public const val PhysicalOptimizerPartitionRemoveUselessPartitionsID: EOptimizerID = 42 // 0x0000002a
    public const val PhysicalOptimizerPartitionRespectMaxPartitionsID: EOptimizerID = 43 // 0x0000002b
    public const val PhysicalOptimizerSplitMergePartitionID: EOptimizerID = 44 // 0x0000002c
    public const val PhysicalOptimizerTripleIndexID: EOptimizerID = 45 // 0x0000002d
    public const val PhysicalOptimizerVisualisationID: EOptimizerID = 46 // 0x0000002e
    public const val values_size: Int = 47
    public const val values_mask: Int = 63 // 0x0000003f
    public const val values_mask_inversed: Int = 2147483584 // 0x7fffffc0

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
        "PhysicalOptimizerPartitionJoinTopologyID",
        "PhysicalOptimizerPartitionMaxSplitID",
        "PhysicalOptimizerPartitionRemoveUselessPartitionsID",
        "PhysicalOptimizerPartitionRespectMaxPartitionsID",
        "PhysicalOptimizerSplitMergePartitionID",
        "PhysicalOptimizerTripleIndexID",
        "PhysicalOptimizerVisualisationID",
    )
}
