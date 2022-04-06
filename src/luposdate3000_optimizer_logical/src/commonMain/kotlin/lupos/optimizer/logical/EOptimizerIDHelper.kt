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
import lupos.shared.myPrintStackTraceAndThrowAgain
import lupos.shared.myPrintStackTrace

import kotlin.jvm.JvmField

public class EOptimizerIDHelper {
    @JvmField
    public var optional: BooleanArray

    @JvmField
    public var repeatOnChange: BooleanArray

    init {
        try {
            optional = BooleanArray(EOptimizerIDExt.values_size) {
                when (it) {
                    EOptimizerIDExt.LogicalOptimizerMinusAddSortID -> true
                    EOptimizerIDExt.LogicalOptimizerDistinctSplitID -> true
                    EOptimizerIDExt.LogicalOptimizerSortDownID -> true
                    EOptimizerIDExt.LogicalOptimizerReducedDownID -> true
                    EOptimizerIDExt.LogicalOptimizerID -> false
                    EOptimizerIDExt.LogicalOptimizerDetectMinusID -> false
                    EOptimizerIDExt.LogicalOptimizerDetectMinusStep2ID -> false
                    EOptimizerIDExt.LogicalOptimizerColumnSortOrderID -> true
                    EOptimizerIDExt.LogicalOptimizerRemoveProjectionID -> true
                    EOptimizerIDExt.LogicalOptimizerJoinOrderID -> true
                    EOptimizerIDExt.LogicalOptimizerUnionUpID -> true
                    EOptimizerIDExt.LogicalOptimizerExistsID -> true
                    EOptimizerIDExt.LogicalOptimizerArithmeticID -> false
                    EOptimizerIDExt.LogicalOptimizerFilterDownID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterEQID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterUpID -> true
                    EOptimizerIDExt.LogicalOptimizerBindUpID -> true
                    EOptimizerIDExt.LogicalOptimizerProjectionDownID -> true
                    EOptimizerIDExt.LogicalOptimizerProjectionUpID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterIntoTripleID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterOptionalID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterOptionalStep2ID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterSplitANDID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterMergeANDID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterSplitORID -> true
                    EOptimizerIDExt.LogicalOptimizerBindToFilterID -> false
                    EOptimizerIDExt.LogicalOptimizerRemoveNOOPID -> false
                    EOptimizerIDExt.LogicalOptimizerOptionalID -> false
                    EOptimizerIDExt.LogicalOptimizerRemovePrefixID -> false
                    EOptimizerIDExt.LogicalOptimizerRemoveBindVariableID -> true
                    EOptimizerIDExt.PhysicalOptimizerID -> false
                    EOptimizerIDExt.PhysicalOptimizerTripleIndexID -> true
                    EOptimizerIDExt.PhysicalOptimizerJoinTypeID -> false
                    EOptimizerIDExt.PhysicalOptimizerNaiveID -> false
                    EOptimizerIDExt.PhysicalOptimizerDebugID -> false
                    EOptimizerIDExt.PhysicalOptimizerPartitionExpandPartitionTowardsStoreID -> false
                    EOptimizerIDExt.PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperatorID -> false
                    EOptimizerIDExt.PhysicalOptimizerPartitionExpandTowardsRootID -> false
                    EOptimizerIDExt.PhysicalOptimizerPartitionRespectMaxPartitionsID -> false
                    EOptimizerIDExt.PhysicalOptimizerPartitionRemoveUselessPartitionsID -> false
                    EOptimizerIDExt.PhysicalOptimizerPartitionAssingPartitionsToRemainingID -> false
                    EOptimizerIDExt.LogicalOptimizerDistinctUpID -> false
                    EOptimizerIDExt.LogicalOptimizerStoreToValuesID -> true
                    EOptimizerIDExt.PhysicalOptimizerVisualisationID -> true
                    EOptimizerIDExt.PhysicalOptimizerSplitMergePartitionID -> true
                    else -> TODO("optional $it")
                }
            }

            repeatOnChange = BooleanArray(EOptimizerIDExt.values_size) {
                when (it) {
                    EOptimizerIDExt.LogicalOptimizerMinusAddSortID -> true
                    EOptimizerIDExt.LogicalOptimizerDistinctSplitID -> true
                    EOptimizerIDExt.LogicalOptimizerSortDownID -> true
                    EOptimizerIDExt.LogicalOptimizerReducedDownID -> true
                    EOptimizerIDExt.LogicalOptimizerID -> true
                    EOptimizerIDExt.LogicalOptimizerDetectMinusID -> true
                    EOptimizerIDExt.LogicalOptimizerDetectMinusStep2ID -> true
                    EOptimizerIDExt.LogicalOptimizerColumnSortOrderID -> true
                    EOptimizerIDExt.LogicalOptimizerRemoveProjectionID -> true
                    EOptimizerIDExt.LogicalOptimizerJoinOrderID -> false
                    EOptimizerIDExt.LogicalOptimizerUnionUpID -> true
                    EOptimizerIDExt.LogicalOptimizerExistsID -> true
                    EOptimizerIDExt.LogicalOptimizerArithmeticID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterDownID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterEQID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterUpID -> true
                    EOptimizerIDExt.LogicalOptimizerBindUpID -> true
                    EOptimizerIDExt.LogicalOptimizerProjectionDownID -> true
                    EOptimizerIDExt.LogicalOptimizerProjectionUpID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterIntoTripleID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterOptionalID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterOptionalStep2ID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterSplitANDID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterMergeANDID -> true
                    EOptimizerIDExt.LogicalOptimizerFilterSplitORID -> true
                    EOptimizerIDExt.LogicalOptimizerBindToFilterID -> true
                    EOptimizerIDExt.LogicalOptimizerRemoveNOOPID -> true
                    EOptimizerIDExt.LogicalOptimizerOptionalID -> true
                    EOptimizerIDExt.LogicalOptimizerRemovePrefixID -> true
                    EOptimizerIDExt.LogicalOptimizerRemoveBindVariableID -> true
                    EOptimizerIDExt.PhysicalOptimizerID -> true
                    EOptimizerIDExt.PhysicalOptimizerTripleIndexID -> true
                    EOptimizerIDExt.PhysicalOptimizerJoinTypeID -> true
                    EOptimizerIDExt.PhysicalOptimizerNaiveID -> true
                    EOptimizerIDExt.PhysicalOptimizerDebugID -> true
                    EOptimizerIDExt.PhysicalOptimizerPartitionExpandPartitionTowardsStoreID -> true
                    EOptimizerIDExt.PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperatorID -> true
                    EOptimizerIDExt.PhysicalOptimizerPartitionExpandTowardsRootID -> true
                    EOptimizerIDExt.PhysicalOptimizerPartitionRespectMaxPartitionsID -> true
                    EOptimizerIDExt.PhysicalOptimizerPartitionRemoveUselessPartitionsID -> false
                    EOptimizerIDExt.PhysicalOptimizerPartitionAssingPartitionsToRemainingID -> false
                    EOptimizerIDExt.LogicalOptimizerDistinctUpID -> true
                    EOptimizerIDExt.LogicalOptimizerStoreToValuesID -> false
                    EOptimizerIDExt.PhysicalOptimizerSplitMergePartitionID -> false
                    EOptimizerIDExt.PhysicalOptimizerVisualisationID -> true
                    else -> TODO("repeatOnChange $it")
                }
            }
        } catch (e: Throwable) {
optional = BooleanArray(0){true}
repeatOnChange=BooleanArray(0){true}
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_logical/src/commonMain/kotlin/lupos/optimizer/logical/EOptimizerIDHelper.kt:135"/*SOURCE_FILE_END*/ )
        }
    }
}
