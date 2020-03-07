package lupos.s08logicalOptimisation
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerRemovePrefix(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary, EOptimizerID.LogicalOptimizerRemovePrefixID) {
    override val classname = "LogicalOptimizerRemovePrefix"
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
