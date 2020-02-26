package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase


abstract class OptimizerBase(val transactionID: Long, val dictionary: ResultSetDictionary, val optimizerID: EOptimizerID) {
    abstract val classname: String

    abstract fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase

    fun optimizeInternal(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        for (i in node.children.indices) {
            val tmp = optimizeInternal(node.children[i], node, onChange)
            node.updateChildren(i, tmp)
        }
        return optimize(node, parent, onChange)
    }

    open fun optimizeCall(node: OPBase, onChange: () -> Unit = {}): OPBase {
        node.syntaxVerifyAllVariableExists(listOf<String>(), true)
        val res = optimizeInternal(node, null, onChange)
        res.syntaxVerifyAllVariableExists(listOf<String>(), false)
        return res
    }
}
