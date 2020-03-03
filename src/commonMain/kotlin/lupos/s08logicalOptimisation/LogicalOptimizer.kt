package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s03resultRepresentation.ResultSetDictionary


class LogicalOptimizer(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerCompoundBase(transactionID, dictionary, EOptimizerID.LogicalOptimizerID) {
    override val classname = "LogicalOptimizer"
    override val childrenOptimizers = arrayOf(//
            LogicalOptimizerRemovePrefix(transactionID, dictionary),//
            LogicalOptimizerOptional(transactionID, dictionary),//
            LogicalOptimizerRemoveNOOP(transactionID, dictionary),//
            LogicalOptimizerBindToFilter(transactionID, dictionary),//
            LogicalOptimizerFilterSplitAND(transactionID, dictionary),//
            LogicalOptimizerArithmetic(transactionID, dictionary),//
            LogicalOptimizerFilterDown(transactionID, dictionary),//
            LogicalOptimizerFilterIntoTriple(transactionID, dictionary))
}
