package lupos.s08logicalOptimisation

import lupos.s00misc.*
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerRemovePrefix(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary) {
    override val classname = "LogicalOptimizerRemovePrefix"
    override val optional = false
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
        if (node is LOPPrefix) {
            onChange()
            node.children[0].applyPrefix(node.name, node.iri)
            res = node.children[0]
        }
        res
    })

}
