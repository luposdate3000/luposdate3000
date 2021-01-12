package lupos.s00misc
import kotlin.jvm.JvmField
internal object EOptimizerIDExt {
    internal  val LogicalOptimizerArithmeticID: EOptimizerID = EOptimizerID(0)
    internal  val LogicalOptimizerBindToFilterID: EOptimizerID = EOptimizerID(1)
    internal  val LogicalOptimizerBindUpID: EOptimizerID = EOptimizerID(2)
    internal  val LogicalOptimizerColumnSortOrderID: EOptimizerID = EOptimizerID(3)
    internal  val LogicalOptimizerDetectMinusID: EOptimizerID = EOptimizerID(4)
    internal  val LogicalOptimizerDetectMinusStep2ID: EOptimizerID = EOptimizerID(5)
    internal  val LogicalOptimizerDistinctSplitID: EOptimizerID = EOptimizerID(6)
    internal  val LogicalOptimizerDistinctUpID: EOptimizerID = EOptimizerID(7)
    internal  val LogicalOptimizerExistsID: EOptimizerID = EOptimizerID(8)
    internal  val LogicalOptimizerFilterDownID: EOptimizerID = EOptimizerID(9)
    internal  val LogicalOptimizerFilterEQID: EOptimizerID = EOptimizerID(10)
    internal  val LogicalOptimizerFilterIntoTripleID: EOptimizerID = EOptimizerID(11)
    internal  val LogicalOptimizerFilterMergeANDID: EOptimizerID = EOptimizerID(12)
    internal  val LogicalOptimizerFilterOptionalID: EOptimizerID = EOptimizerID(13)
    internal  val LogicalOptimizerFilterOptionalStep2ID: EOptimizerID = EOptimizerID(14)
    internal  val LogicalOptimizerFilterSplitANDID: EOptimizerID = EOptimizerID(15)
    internal  val LogicalOptimizerFilterSplitORID: EOptimizerID = EOptimizerID(16)
    internal  val LogicalOptimizerFilterUpID: EOptimizerID = EOptimizerID(17)
    internal  val LogicalOptimizerID: EOptimizerID = EOptimizerID(18)
    internal  val LogicalOptimizerJoinOrderID: EOptimizerID = EOptimizerID(19)
    internal  val LogicalOptimizerMinusAddSortID: EOptimizerID = EOptimizerID(20)
    internal  val LogicalOptimizerOptionalID: EOptimizerID = EOptimizerID(21)
    internal  val LogicalOptimizerProjectionDownID: EOptimizerID = EOptimizerID(22)
    internal  val LogicalOptimizerProjectionUpID: EOptimizerID = EOptimizerID(23)
    internal  val LogicalOptimizerReducedDownID: EOptimizerID = EOptimizerID(24)
    internal  val LogicalOptimizerRemoveBindVariableID: EOptimizerID = EOptimizerID(25)
    internal  val LogicalOptimizerRemoveNOOPID: EOptimizerID = EOptimizerID(26)
    internal  val LogicalOptimizerRemovePrefixID: EOptimizerID = EOptimizerID(27)
    internal  val LogicalOptimizerRemoveProjectionID: EOptimizerID = EOptimizerID(28)
    internal  val LogicalOptimizerSortDownID: EOptimizerID = EOptimizerID(29)
    internal  val LogicalOptimizerStoreToValuesID: EOptimizerID = EOptimizerID(30)
    internal  val LogicalOptimizerUnionUpID: EOptimizerID = EOptimizerID(31)
    internal  val PhysicalOptimizerDebugID: EOptimizerID = EOptimizerID(32)
    internal  val PhysicalOptimizerID: EOptimizerID = EOptimizerID(33)
    internal  val PhysicalOptimizerJoinTypeID: EOptimizerID = EOptimizerID(34)
    internal  val PhysicalOptimizerNaiveID: EOptimizerID = EOptimizerID(35)
    internal  val PhysicalOptimizerPartition1ID: EOptimizerID = EOptimizerID(36)
    internal  val PhysicalOptimizerPartition2ID: EOptimizerID = EOptimizerID(37)
    internal  val PhysicalOptimizerPartition3ID: EOptimizerID = EOptimizerID(38)
    internal  val PhysicalOptimizerPartition4ID: EOptimizerID = EOptimizerID(39)
    internal  val PhysicalOptimizerPartition5ID: EOptimizerID = EOptimizerID(40)
    internal  val PhysicalOptimizerPartition6ID: EOptimizerID = EOptimizerID(41)
    internal  val PhysicalOptimizerTripleIndexID: EOptimizerID = EOptimizerID(42)
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
