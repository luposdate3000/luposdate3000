package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.Query

class LogicalOptimizer(query: Query) : OptimizerCompoundBase(query, EOptimizerID.LogicalOptimizerID) {
    override val classname = "LogicalOptimizer"
    override val childrenOptimizers = arrayOf(//
            arrayOf<OptimizerBase>(
                    LogicalOptimizerRemovePrefix(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerArithmetic(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerRemoveProjection(query),//
                    LogicalOptimizerRemoveNOOP(query),//
                    LogicalOptimizerDistinctUp(query),//
                    LogicalOptimizerOptional(query),//
                    LogicalOptimizerBindToFilter(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerUnionUp(query),//
                    LogicalOptimizerFilterUp(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerJoinOrder(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerFilterSplitAND(query),//
                    LogicalOptimizerFilterDown(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerFilterMergeAND(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerProjectionDown(query),//
                    LogicalOptimizerRemoveProjection(query),//
                    LogicalOptimizerFilterIntoTriple(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerColumnSortOrder(query)//
            )
    )
}
