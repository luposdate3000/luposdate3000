package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase
class LogicalOptimizerArithmetic(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerArithmeticID) {
    override val classname = "LogicalOptimizerArithmetic"
    fun hasAggregation(node: OPBase): Boolean {
Coverage.funStart(8719)
        for (n in node.children) {
Coverage.forLoopStart(8720)
            if (hasAggregation(n)) {
Coverage.ifStart(8721)
                return true
            }
Coverage.statementStart(8722)
        }
Coverage.statementStart(8723)
        return node is AOPAggregationBase
    }
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(8724)
        var res = node
Coverage.statementStart(8725)
        if (node is AOPBase && node !is AOPValue) {
Coverage.ifStart(8726)
            if (node.children.size > 0 && node.getRequiredVariableNamesRecoursive().size == 0 && !hasAggregation(node)) {
Coverage.ifStart(8727)
                var value = node.evaluate(IteratorBundle(0))()
Coverage.statementStart(8728)
                if (value is ValueError) {
Coverage.ifStart(8729)
                    value = ValueUndef()
Coverage.statementStart(8730)
                }
Coverage.statementStart(8731)
                res = AOPConstant(query, value)
Coverage.statementStart(8732)
                onChange()
Coverage.statementStart(8733)
            }
Coverage.statementStart(8734)
        }
Coverage.statementStart(8735)
        return res
    }
}
