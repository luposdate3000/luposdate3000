package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.Query

class LogicalOptimizer(query: Query) : OptimizerCompoundBase(query, EOptimizerID.LogicalOptimizerID) {
    override val classname = "LogicalOptimizer"
    override val childrenOptimizers = arrayOf(//
            arrayOf<OptimizerBase>(
                    LogicalOptimizerRemoveProjection(query),//
                    LogicalOptimizerRemovePrefix(query),//
                    LogicalOptimizerRemoveNOOP(query),//
                    LogicalOptimizerArithmetic(query),//
                    LogicalOptimizerDistinctUp(query),//
                    LogicalOptimizerOptional(query),//
                    LogicalOptimizerBindToFilter(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerUnionUp(query),//
                    LogicalOptimizerFilterSplitAND(query),//
                    LogicalOptimizerFilterDown(query),//
                    LogicalOptimizerFilterUp(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerJoinOrder(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerProjectionDown(query),//
                    LogicalOptimizerFilterIntoTriple(query)
            )
    )
}
