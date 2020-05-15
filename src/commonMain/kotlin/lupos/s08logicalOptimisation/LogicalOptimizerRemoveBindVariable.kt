package lupos.s08logicalOptimisation
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s04logicalOperators.singleinput.*
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerRemoveBindVariable(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerRemoveBindVariableID) {
    override val classname = "LogicalOptimizerRemoveBindVariable"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
        if (node is LOPProjection) {
            val child = node.children[0]
            if (child is LOPBind) {
                var exp = child.children[1]
                if (exp is AOPVariable) {
var provided=node.getProvidedVariableNames()
if(!provided.contains(exp.name)){
node.replaceVariableWithAnother(child.children[0],exp.name,child.name.name,child,0)
node.children[0]=child.children[0]
onChange()
}
                }
            }
        }
/*return*/res
    })
}
