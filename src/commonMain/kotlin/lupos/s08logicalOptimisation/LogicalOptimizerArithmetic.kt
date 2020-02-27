package lupos.s08logicalOptimisation

import lupos.s00misc.EOperatorID
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPUndef
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerArithmetic(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary, EOptimizerID.LogicalOptimizerArithmeticID) {
    override val classname = "LogicalOptimizerArithmetic"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
var res=node
        if (node is AOPBase && node !is AOPValue&& node.children.size > 0 && node.getRequiredVariableNamesRecoursive().size==0) {
val resultSet=ResultSet(ResultSetDictionary())
val resultRow=resultSet.createResultRow()
println("xxxLogicalOptimizerArithmetic")
println(node)
try{
		res=node.calculate(resultSet,resultRow)
}catch(e:Throwable){
res=AOPUndef()
}
println(res)
            onChange()
        }
        res
    })
}
