package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s04logicalOperators.Query


enum class EOptimizerID(@JvmField val optional: Boolean) {
    LogicalOptimizerID(false),
    LogicalOptimizerRemoveProjectionID(true),
    LogicalOptimizerJoinOrderID(true),
    LogicalOptimizerUnionUpID(true),
    LogicalOptimizerArithmeticID(false),
    LogicalOptimizerFilterDownID(true),
    LogicalOptimizerFilterUpID(true),
    LogicalOptimizerFilterIntoTripleID(true),
    LogicalOptimizerFilterSplitANDID(true),
    LogicalOptimizerFilterSplitORID(true),
    LogicalOptimizerBindToFilterID(false),
    LogicalOptimizerRemoveNOOPID(false),
    LogicalOptimizerOptionalID(false),
    LogicalOptimizerRemovePrefixID(false),
    PhysicalOptimizerID(false),
    PhysicalOptimizerTripleIndexID(true),
    PhysicalOptimizerNaiveID(false),
    KeyDistributionOptimizerID(false),
    LogicalOptimizerDistinctUpID(false)
}
