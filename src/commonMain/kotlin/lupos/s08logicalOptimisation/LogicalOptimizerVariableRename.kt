package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.AOPUndef
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerVariableRename(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerVariableRenameID) {
    override val classname = "LogicalOptimizerVariableRename"

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
val variables=node.getProvidedVariableNames()
for (v in variables){
val oldMap=query.variableNameMapping[v]
if(oldMap==null){
val newName="#var${query.variableNameMapping.keys.size}"
query.variableNameMapping[v]=newName
node.renameVariable(v,newName)
}
}
        res
    })
}
