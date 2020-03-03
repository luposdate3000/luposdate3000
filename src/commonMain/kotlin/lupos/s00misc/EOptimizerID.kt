package lupos.s00misc

enum class EOptimizerID(val optional: Boolean) {
    LogicalOptimizerID(false),
    LogicalOptimizerArithmeticID(true),
    LogicalOptimizerFilterDownID(true),
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
    KeyDistributionOptimizerID(false)
}
