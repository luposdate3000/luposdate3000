package lupos.s00misc

enum class EOptimizerID(val optional: Boolean) {
    LogicalOptimizerArithmeticID(true),
    LogicalOptimizerFilterDownID(true),
    LogicalOptimizerBindToFilterID(false),
    LogicalOptimizerRemoveNOOPID(false),
    LogicalOptimizerRemovePrefixID(false),
    PhysicalOptimizerID(false),
    PhysicalOptimizerTripleIndexID(true),
    PhysicalOptimizerNaiveID(false),
    KeyDistributionOptimizerID(false),
    LogicalOptimizerID(false)
}
