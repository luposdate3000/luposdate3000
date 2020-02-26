package lupos.s08logicalOptimisation
import lupos.s00misc.EOperatorID

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizer(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerCompoundBase(transactionID, dictionary) {
    override val classname = "LogicalOptimizer"
    override val optional = false
    override val childrenOptimizers = arrayOf(//
            LogicalOptimizerRemovePrefix(transactionID, dictionary),//
            LogicalOptimizerRemoveNOOP(transactionID, dictionary),//
            LogicalOptimizerArithmetic(transactionID, dictionary),//
            LogicalOptimizerFilterDown(transactionID, dictionary))
}
