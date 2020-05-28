package lupos.s08logicalOptimisation
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
abstract class OptimizerBase(@JvmField val query: Query, @JvmField val optimizerID: EOptimizerID) {
    abstract val classname: String
    abstract fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase
    fun optimizeInternal(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9965)
        SanityCheck {
Coverage.statementStart(9966)
            if (parent != null) {
Coverage.ifStart(9967)
                var found = false
Coverage.statementStart(9968)
                for (c in parent.children) {
Coverage.forLoopStart(9969)
                    if (c === node) {
Coverage.ifStart(9970)
                        found = true
Coverage.statementStart(9971)
                        break
                    }
Coverage.statementStart(9972)
                }
Coverage.statementStart(9973)
                require(found)
Coverage.statementStart(9974)
            }
Coverage.statementStart(9975)
        }
Coverage.statementStart(9976)
        for (i in node.children.indices) {
Coverage.forLoopStart(9977)
            val tmp = optimizeInternal(node.children[i], node, onChange)
Coverage.statementStart(9978)
            node.updateChildren(i, tmp)
Coverage.statementStart(9979)
        }
Coverage.statementStart(9980)
        return optimize(node, parent, onChange)
    }
    open fun optimizeCall(node: OPBase, onChange: () -> Unit = {}): OPBase {
Coverage.funStart(9981)
        node.syntaxVerifyAllVariableExists(listOf(), true)
Coverage.statementStart(9982)
        val res = optimizeInternal(node, null, onChange)
Coverage.statementStart(9983)
        res.syntaxVerifyAllVariableExists(listOf(), false)
Coverage.statementStart(9984)
        return res
    }
}
