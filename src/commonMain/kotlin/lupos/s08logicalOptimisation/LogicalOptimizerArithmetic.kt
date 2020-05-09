package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase



class LogicalOptimizerArithmetic(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerArithmeticID) {
    override val classname = "LogicalOptimizerArithmetic"
    fun hasAggregation(node: OPBase): Boolean {
        for (n in node.children) {
            if (hasAggregation(n)) {
                return true
            }
        }
        return node is AOPAggregationBase
    }

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
        if (node is AOPBase && node !is AOPValue) {
            if (node.children.size > 0 && node.getRequiredVariableNamesRecoursive().size == 0 && !hasAggregation(node)) {
                val outMap = mutableMapOf<String, ColumnIterator>()
                println("evaluating constant ::")
                var value = node.evaluate(ColumnIteratorRow(outMap))()
                println("evaluating constant :: A $value")
                if (value is ValueError) {
                    value = ValueUndef()
                }
                println("evaluating constant :: B $value")
                res = AOPConstant(query, value)
                println("evaluating constant :: C $res")
                onChange()
            }
        }
/*return*/res
    })
}
