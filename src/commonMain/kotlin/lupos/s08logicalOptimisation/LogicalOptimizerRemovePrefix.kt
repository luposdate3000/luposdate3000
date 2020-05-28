package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase
class LogicalOptimizerRemovePrefix(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerRemovePrefixID) {
    override val classname = "LogicalOptimizerRemovePrefix"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9841)
        var res = node
Coverage.statementStart(9842)
        if (node is LOPPrefix) {
Coverage.ifStart(9843)
            node.children[0].applyPrefix(node.name, node.iri)
Coverage.statementStart(9844)
            res = node.children[0]
Coverage.statementStart(9845)
            onChange()
Coverage.statementStart(9846)
        }
Coverage.statementStart(9847)
        return res
    }
}
