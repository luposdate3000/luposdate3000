package lupos.s00misc

enum class EOptimizerID(val optional: Boolean) {
    LogicalOptimizerArithmeticID(true),
    LogicalOptimizerFilterDownID(true),
    LogicalOptimizerRemoveNOOPID(false),
    LogicalOptimizerRemovePrefixID(false),
    PhysicalOptimizerID(false),
    KeyDistributionOptimizerID(false),
    LogicalOptimizerID(false)
}
