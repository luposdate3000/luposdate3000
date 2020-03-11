package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


abstract class OptimizerBase(@JvmField val query: Query, @JvmField val optimizerID: EOptimizerID) {
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
        node.syntaxVerifyAllVariableExists(listOf(), true)
        val res = optimizeInternal(node, null, onChange)
        res.syntaxVerifyAllVariableExists(listOf(), false)
        return res
    }
}
