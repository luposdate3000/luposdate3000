package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

abstract class OptimizerCompoundBase(query: Query, optimizerID: EOptimizerID) : OptimizerBase(query, optimizerID) {
    override val classname = "OptimizerCompoundBase"
    abstract val childrenOptimizers: Array<Array<OptimizerBase>>
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = node
    override fun optimizeCall(node: OPBase, onChange: () -> Unit): OPBase {
if(query.filtersMovedUpFromOptionals){
        node.syntaxVerifyAllVariableExists(listOf(), true)
}
        var tmp = node
        var d: Boolean
        for (opt in childrenOptimizers) {
            d = true
            while (d) {
                d = false
                for (o in opt) {
                    SanityCheck.println({ "debug ${o.optimizerID}" })
                    var c = true
                    while (c) {
                        c = false
                        tmp = o.optimizeInternal(tmp, null, {
                            if (o.optimizerID.repeatOnChange) {
                                c = true
                                d = true
                                onChange()
                            }
                        })
                    }
                    SanityCheck {
if(query.filtersMovedUpFromOptionals){
                        tmp.syntaxVerifyAllVariableExists(listOf(), false)
}
                    }
                }
            }
        }
if(query.filtersMovedUpFromOptionals){
        tmp.syntaxVerifyAllVariableExists(listOf(), false)
}
        return tmp
    }
}
