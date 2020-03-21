package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class LogicalOptimizer(query: Query) : OptimizerCompoundBase(query, EOptimizerID.LogicalOptimizerID) {
    override val classname = "LogicalOptimizer"
    override val childrenOptimizers = arrayOf(//
            LogicalOptimizerRemoveProjection(query),//
            LogicalOptimizerRemovePrefix(query),//
            LogicalOptimizerArithmetic(query),//
            LogicalOptimizerDistinctUp(query),//
            LogicalOptimizerOptional(query),//
            LogicalOptimizerRemoveNOOP(query),//
            LogicalOptimizerBindToFilter(query),//
            LogicalOptimizerFilterSplitAND(query),//
            LogicalOptimizerFilterDown(query),//
            LogicalOptimizerFilterUp(query),//
            LogicalOptimizerUnionUp(query),//
            LogicalOptimizerJoinOrder(query),//
            LogicalOptimizerFilterIntoTriple(query))
}
