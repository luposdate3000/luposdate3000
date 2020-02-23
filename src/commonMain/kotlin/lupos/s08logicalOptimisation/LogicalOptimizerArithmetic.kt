package lupos.s08logicalOptimisation

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerArithmetic(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary) {
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        if (node is AOPBase && parent !is AOPBase && parent !is LOPExpression && parent !is LOPValues) {
            onChange()
            return LOPExpression(node)
        }
        return node
    }
}
