package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizer(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerCompoundBase(transactionID, dictionary, EOptimizerID.LogicalOptimizerID) {
    override val classname = "LogicalOptimizer"
    override val childrenOptimizers = arrayOf(//
            LogicalOptimizerRemovePrefix(transactionID, dictionary),//
            LogicalOptimizerRemoveNOOP(transactionID, dictionary),//
            LogicalOptimizerArithmetic(transactionID, dictionary),//
            LogicalOptimizerBindToFilter(transactionID, dictionary),//
            LogicalOptimizerFilterDown(transactionID, dictionary),//
            LogicalOptimizerFilterIntoTriple(transactionID, dictionary))
}
