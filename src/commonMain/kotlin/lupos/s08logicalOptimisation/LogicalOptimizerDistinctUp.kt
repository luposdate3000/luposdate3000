package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerDistinctUp(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary, EOptimizerID.LogicalOptimizerDistinctUpID) {
    override val classname = "LogicalOptimizerDistinctUp"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPDistinct) {
            if (node.children[0] is LOPDistinct) {
                res = node.children[0]
                onChange()
            }
        } else {
            for (i in node.children.indices) {
                val c = node.children[i]
                if (c is LOPDistinct && c.getProvidedVariableNames().containsAll(node.getProvidedVariableNames())) {
                    node.children[i]=c.children[0]
                    res = LOPDistinct(node)
                    onChange()
                    break
                }
            }
        }
        res
    })
}
