package lupos.s00misc

enum class EOptimizerID(val optional: Boolean) {
    LogicalOptimizerArithmeticID(true),
    LogicalOptimizerFilterDownID(true),
    LogicalOptimizerRemoveNOOPID(false),
    LogicalOptimizerRemovePrefixID(false),
    PhysicalOptimizerID(false),
    PhysicalOptimizerTripleIndexID(false),
    PhysicalOptimizerNaiveID(false),
    KeyDistributionOptimizerID(false),
    LogicalOptimizerID(false)
}
