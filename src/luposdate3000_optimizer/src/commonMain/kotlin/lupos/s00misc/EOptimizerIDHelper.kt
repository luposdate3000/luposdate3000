package lupos.s00misc
import kotlin.jvm.JvmField
public object EOptimizerIDHelper {
    @JvmField public val optional: BooleanArray = BooleanArray(EOptimizerIDExt.values_size) {
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
            EOptimizerIDExt.PhysicalOptimizerPartition1ID -> false
            EOptimizerIDExt.PhysicalOptimizerPartition2ID -> false
            EOptimizerIDExt.PhysicalOptimizerPartition3ID -> false
            EOptimizerIDExt.PhysicalOptimizerPartition4ID -> false
            EOptimizerIDExt.PhysicalOptimizerPartition5ID -> false
            EOptimizerIDExt.PhysicalOptimizerPartition6ID -> false
            EOptimizerIDExt.LogicalOptimizerDistinctUpID -> false
            EOptimizerIDExt.LogicalOptimizerStoreToValuesID -> true
            else -> throw UnreachableException()
        }
    }
    @JvmField public val repeatOnChange: BooleanArray = BooleanArray(EOptimizerIDExt.values_size) {
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
            EOptimizerIDExt.PhysicalOptimizerPartition1ID -> true
            EOptimizerIDExt.PhysicalOptimizerPartition2ID -> true
            EOptimizerIDExt.PhysicalOptimizerPartition3ID -> true
            EOptimizerIDExt.PhysicalOptimizerPartition4ID -> true
            EOptimizerIDExt.PhysicalOptimizerPartition5ID -> false
            EOptimizerIDExt.PhysicalOptimizerPartition6ID -> false
            EOptimizerIDExt.LogicalOptimizerDistinctUpID -> true
            EOptimizerIDExt.LogicalOptimizerStoreToValuesID -> false
            else -> throw UnreachableException()
        }
    }
}
