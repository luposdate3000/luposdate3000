package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.*

import lupos.s04logicalOperators.singleinput.*
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerArithmetic(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerArithmeticID) {
    override val classname = "LogicalOptimizerArithmetic"
    fun hasAggregation(node: OPBase): Boolean {
        for (n in node.children)
            if (hasAggregation(n))
                return true
        return node is AOPAggregationBase
    }

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
        if (node is AOPBase && node !is AOPValue) {
            if (node.children.size > 0 && node.getRequiredVariableNamesRecoursive().size == 0 && !hasAggregation(node)) {
                val resultSet = ResultSet(query.dictionary)
                val resultRow = resultSet.createResultRow()
                val resultChunk = ResultChunk(resultSet)
                resultChunk.skipSize(1)
                val rVektor = node.calculate(resultChunk)
                res = AOPConstant(query, rVektor.data[0])
                onChange()
            }
        }
        if (node is LOPBind) {
            val child = node.children[0]
            val expression = node.children[1]
            if (expression is AOPVariable && !child.getProvidedVariableNames().contains(expression.name)) {
                node.children[1] = AOPConstant(query, ValueUndef())
                onChange()
            }
        }
        res
    })
}
