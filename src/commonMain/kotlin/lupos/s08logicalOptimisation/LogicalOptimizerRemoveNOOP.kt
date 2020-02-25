package lupos.s08logicalOptimisation

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerRemoveNOOP(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary) {
    override val classname="LogicalOptimizerRemoveNOOP"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        when (node) {
            is LOPNOOP -> {
                onChange()
                return node.children[0]
            }
            else -> return node
        }
    }

}
