package lupos.s00misc
import kotlin.jvm.JvmField
internal object EOptimizerIDExt {
    internal const val LogicalOptimizerArithmeticID: EOptimizerID = 0
    internal const val LogicalOptimizerBindToFilterID: EOptimizerID = 1
    internal const val LogicalOptimizerBindUpID: EOptimizerID = 2
    internal const val LogicalOptimizerColumnSortOrderID: EOptimizerID = 3
    internal const val LogicalOptimizerDetectMinusID: EOptimizerID = 4
    internal const val LogicalOptimizerDetectMinusStep2ID: EOptimizerID = 5
    internal const val LogicalOptimizerDistinctSplitID: EOptimizerID = 6
    internal const val LogicalOptimizerDistinctUpID: EOptimizerID = 7
    internal const val LogicalOptimizerExistsID: EOptimizerID = 8
    internal const val LogicalOptimizerFilterDownID: EOptimizerID = 9
    internal const val LogicalOptimizerFilterEQID: EOptimizerID = 10
    internal const val LogicalOptimizerFilterIntoTripleID: EOptimizerID = 11
    internal const val LogicalOptimizerFilterMergeANDID: EOptimizerID = 12
    internal const val LogicalOptimizerFilterOptionalID: EOptimizerID = 13
    internal const val LogicalOptimizerFilterOptionalStep2ID: EOptimizerID = 14
    internal const val LogicalOptimizerFilterSplitANDID: EOptimizerID = 15
    internal const val LogicalOptimizerFilterSplitORID: EOptimizerID = 16
    internal const val LogicalOptimizerFilterUpID: EOptimizerID = 17
    internal const val LogicalOptimizerID: EOptimizerID = 18
    internal const val LogicalOptimizerJoinOrderID: EOptimizerID = 19
    internal const val LogicalOptimizerMinusAddSortID: EOptimizerID = 20
    internal const val LogicalOptimizerOptionalID: EOptimizerID = 21
    internal const val LogicalOptimizerProjectionDownID: EOptimizerID = 22
    internal const val LogicalOptimizerProjectionUpID: EOptimizerID = 23
    internal const val LogicalOptimizerReducedDownID: EOptimizerID = 24
    internal const val LogicalOptimizerRemoveBindVariableID: EOptimizerID = 25
    internal const val LogicalOptimizerRemoveNOOPID: EOptimizerID = 26
    internal const val LogicalOptimizerRemovePrefixID: EOptimizerID = 27
    internal const val LogicalOptimizerRemoveProjectionID: EOptimizerID = 28
    internal const val LogicalOptimizerSortDownID: EOptimizerID = 29
    internal const val LogicalOptimizerStoreToValuesID: EOptimizerID = 30
    internal const val LogicalOptimizerUnionUpID: EOptimizerID = 31
    internal const val PhysicalOptimizerDebugID: EOptimizerID = 32
    internal const val PhysicalOptimizerID: EOptimizerID = 33
    internal const val PhysicalOptimizerJoinTypeID: EOptimizerID = 34
    internal const val PhysicalOptimizerNaiveID: EOptimizerID = 35
    internal const val PhysicalOptimizerPartition1ID: EOptimizerID = 36
    internal const val PhysicalOptimizerPartition2ID: EOptimizerID = 37
    internal const val PhysicalOptimizerPartition3ID: EOptimizerID = 38
    internal const val PhysicalOptimizerPartition4ID: EOptimizerID = 39
    internal const val PhysicalOptimizerPartition5ID: EOptimizerID = 40
    internal const val PhysicalOptimizerPartition6ID: EOptimizerID = 41
    internal const val PhysicalOptimizerTripleIndexID: EOptimizerID = 42
    internal const val values_size: Int = 43
    @JvmField internal val names: Array<String> = arrayOf(
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
        "PhysicalOptimizerPartition1ID",
        "PhysicalOptimizerPartition2ID",
        "PhysicalOptimizerPartition3ID",
        "PhysicalOptimizerPartition4ID",
        "PhysicalOptimizerPartition5ID",
        "PhysicalOptimizerPartition6ID",
        "PhysicalOptimizerTripleIndexID",
    )
}
