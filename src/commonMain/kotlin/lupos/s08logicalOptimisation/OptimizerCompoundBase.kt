package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase


abstract class OptimizerCompoundBase(transactionID: Long, dictionary: ResultSetDictionary, optimizerID: EOptimizerID) : OptimizerBase(transactionID, dictionary, optimizerID) {
    override val classname = "OptimizerCompoundBase"
    abstract val childrenOptimizers: Array<OptimizerBase>

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = node

    override fun optimizeCall(node: OPBase, onChange: () -> Unit): OPBase {
        node.syntaxVerifyAllVariableExists(listOf(), true)
        var tmp = node
        for (o in childrenOptimizers) {
            var c = true
            while (c) {
                c = false
                tmp = o.optimizeInternal(tmp, null, {
                    c = true
                    onChange()
                })
                c = false
            }
        }
        tmp.syntaxVerifyAllVariableExists(listOf(), false)
        return tmp
    }
}
