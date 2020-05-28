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
Coverage.funStart(9985)
        node.syntaxVerifyAllVariableExists(listOf(), true)
Coverage.statementStart(9986)
        var tmp = node
Coverage.statementStart(9987)
        var d: Boolean
Coverage.statementStart(9988)
        for (opt in childrenOptimizers) {
Coverage.forLoopStart(9989)
            d = true
Coverage.statementStart(9990)
            while (d) {
Coverage.whileLoopStart(9991)
                d = false
Coverage.statementStart(9992)
                for (o in opt) {
Coverage.forLoopStart(9993)
                    SanityCheck {
Coverage.statementStart(9994)
                        println("debug ${o.optimizerID}")
Coverage.statementStart(9995)
                    }
Coverage.statementStart(9996)
                    var c = true
Coverage.statementStart(9997)
                    while (c) {
Coverage.whileLoopStart(9998)
                        c = false
Coverage.statementStart(9999)
                        tmp = o.optimizeInternal(tmp, null, {
Coverage.statementStart(10000)
                            if (o.optimizerID.repeatOnChange) {
Coverage.ifStart(10001)
                                c = true
Coverage.statementStart(10002)
                                d = true
Coverage.statementStart(10003)
                                onChange()
Coverage.statementStart(10004)
                            }
Coverage.statementStart(10005)
                        })
Coverage.statementStart(10006)
                    }
Coverage.statementStart(10007)
                    SanityCheck {
Coverage.statementStart(10008)
                        tmp.syntaxVerifyAllVariableExists(listOf(), false)
Coverage.statementStart(10009)
                    }
Coverage.statementStart(10010)
                }
Coverage.statementStart(10011)
            }
Coverage.statementStart(10012)
        }
Coverage.statementStart(10013)
        tmp.syntaxVerifyAllVariableExists(listOf(), false)
Coverage.statementStart(10014)
        return tmp
    }
}
