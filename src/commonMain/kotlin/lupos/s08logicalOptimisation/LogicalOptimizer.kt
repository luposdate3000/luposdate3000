package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.Query


class LogicalOptimizer(query: Query) : OptimizerCompoundBase(query, EOptimizerID.LogicalOptimizerID) {
    override val classname = "LogicalOptimizer"
    override val childrenOptimizers = arrayOf(//
            LogicalOptimizerRemovePrefix(query),//
            LogicalOptimizerDistinctUp(query),//
            LogicalOptimizerOptional(query),//
            LogicalOptimizerRemoveNOOP(query),//
            LogicalOptimizerBindToFilter(query),//
            LogicalOptimizerFilterSplitAND(query),//
            LogicalOptimizerArithmetic(query),//
            LogicalOptimizerFilterDown(query),//
            LogicalOptimizerFilterIntoTriple(query))
}
