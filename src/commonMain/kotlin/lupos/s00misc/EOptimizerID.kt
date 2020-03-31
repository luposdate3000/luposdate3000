package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s04logicalOperators.Query

enum class EOptimizerID(@JvmField val optional: Boolean, @JvmField val repeatOnChange: Boolean) {
    LogicalOptimizerID(false, true),
    LogicalOptimizerRemoveProjectionID(true, true),
    LogicalOptimizerJoinOrderID(true, false),
    LogicalOptimizerUnionUpID(true, true),
    LogicalOptimizerArithmeticID(false, true),
    LogicalOptimizerFilterDownID(true, true),
    LogicalOptimizerFilterUpID(true, true),
    LogicalOptimizerFilterIntoTripleID(true, true),
    LogicalOptimizerFilterSplitANDID(true, true),
    LogicalOptimizerFilterSplitORID(true, true),
    LogicalOptimizerBindToFilterID(false, true),
    LogicalOptimizerRemoveNOOPID(false, true),
    LogicalOptimizerOptionalID(false, true),
    LogicalOptimizerRemovePrefixID(false, true),
    PhysicalOptimizerID(false, true),
    PhysicalOptimizerTripleIndexID(true, true),
    PhysicalOptimizerNaiveID(false, true),
    KeyDistributionOptimizerID(false, true),
    LogicalOptimizerDistinctUpID(false, true)
}
