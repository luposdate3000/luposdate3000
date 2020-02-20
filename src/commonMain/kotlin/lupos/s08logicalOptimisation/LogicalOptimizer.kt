package lupos.s08logicalOptimisation

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizer(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerCompoundBase(transactionID, dictionary) {
    override val childrenOptimizers = arrayOf(//
            LogicalOptimizerRemovePrefix(transactionID, dictionary),//
            LogicalOptimizerRemoveNOOP(transactionID, dictionary),//
            LogicalOptimizerArithmetic(transactionID, dictionary),//
            LogicalOptimizerFilterDown(transactionID, dictionary))
}
