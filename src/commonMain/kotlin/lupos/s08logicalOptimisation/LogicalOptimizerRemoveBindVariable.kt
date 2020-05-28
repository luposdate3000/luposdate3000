package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase
class LogicalOptimizerRemoveBindVariable(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerRemoveBindVariableID) {
    override val classname = "LogicalOptimizerRemoveBindVariable"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9763)
        var res = node
Coverage.statementStart(9764)
        if (node is LOPProjection) {
Coverage.ifStart(9765)
            val child = node.children[0]
Coverage.statementStart(9766)
            if (child is LOPBind) {
Coverage.ifStart(9767)
                var exp = child.children[1]
Coverage.statementStart(9768)
                if (exp is AOPVariable) {
Coverage.ifStart(9769)
                    var provided = node.getProvidedVariableNames()
Coverage.statementStart(9770)
                    if (!provided.contains(exp.name)) {
Coverage.ifStart(9771)
                        node.replaceVariableWithAnother(child.children[0], exp.name, child.name.name, child, 0)
Coverage.statementStart(9772)
                        node.children[0] = child.children[0]
Coverage.statementStart(9773)
                        onChange()
Coverage.statementStart(9774)
                    }
Coverage.statementStart(9775)
                }
Coverage.statementStart(9776)
            }
Coverage.statementStart(9777)
        }
Coverage.statementStart(9778)
        return res
    }
}
