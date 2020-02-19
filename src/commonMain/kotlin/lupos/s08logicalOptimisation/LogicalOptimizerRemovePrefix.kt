package lupos.s08logicalOptimisation

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerRemovePrefix(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary) {
    override fun optimize(node: OPBase, parent: OPBase?): OPBase {
        when (node) {
            is LOPPrefix -> return node.children[0]
            else -> return node
        }
    }

}
