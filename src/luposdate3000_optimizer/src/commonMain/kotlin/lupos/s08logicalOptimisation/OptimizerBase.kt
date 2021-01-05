package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import kotlin.jvm.JvmField
public abstract class OptimizerBase(@JvmField public val query: Query, @JvmField public val optimizerID: EOptimizerID) {
   public  abstract val classname: String
   public  abstract /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase
   public  /*suspend*/ fun optimizeInternal(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        SanityCheck {
            if (parent != null) {
                var found = false
                for (c in parent.getChildren()) {
                    if (c === node) {
                        found = true
                        break
                    }
                }
                SanityCheck.check { found }
            }
        }
        for (i in node.getChildren().indices) {
            val tmp = optimizeInternal(node.getChildren()[i], node, onChange)
            node.updateChildren(i, tmp)
        }
        return optimize(node, parent, onChange)
    }
public    open /*suspend*/ fun optimizeCall(node: IOPBase, onChange: () -> Unit = {}): IOPBase {
        if (query.filtersMovedUpFromOptionals) {
            node.syntaxVerifyAllVariableExists(listOf(), true)
        }
        val res = optimizeInternal(node, null, onChange)
        if (query.filtersMovedUpFromOptionals) {
            res.syntaxVerifyAllVariableExists(listOf(), false)
        }
        return res
    }
}
