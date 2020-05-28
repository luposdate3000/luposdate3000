package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s08logicalOptimisation.OptimizerBase
class LogicalOptimizerDistinctUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerDistinctUpID) {
    override val classname = "LogicalOptimizerDistinctUp"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(8951)
        var res: OPBase = node
Coverage.statementStart(8952)
        if (node is LOPDistinct) {
Coverage.ifStart(8953)
            if (node.children[0] is LOPDistinct) {
Coverage.ifStart(8954)
                res = node.children[0]
Coverage.statementStart(8955)
                onChange()
Coverage.statementStart(8956)
            }
Coverage.statementStart(8957)
        } else if (node !is LOPUnion && node !is OPBaseCompound && node !is LOPLimit && node !is LOPOffset) {
Coverage.ifStart(8958)
            for (i in node.children.indices) {
Coverage.forLoopStart(8959)
                val c = node.children[i]
Coverage.statementStart(8960)
                if (c is LOPDistinct && c.getProvidedVariableNames().containsAll(node.getProvidedVariableNames())) {
Coverage.ifStart(8961)
                    node.children[i] = c.children[0]
Coverage.statementStart(8962)
                    res = LOPDistinct(query, node)
Coverage.statementStart(8963)
                    onChange()
Coverage.statementStart(8964)
                    break
                }
Coverage.statementStart(8965)
            }
Coverage.statementStart(8966)
        }
Coverage.statementStart(8967)
        return res
    }
}
