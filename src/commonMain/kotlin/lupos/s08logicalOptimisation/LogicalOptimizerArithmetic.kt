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
import lupos.s04logicalOperators.iterator.IteratorBundle
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

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res = node
        if (node is AOPBase && node !is AOPValue) {
            if (node.children.size > 0 && node.getRequiredVariableNamesRecoursive().size == 0 && !hasAggregation(node)) {
                var value = node.evaluate(IteratorBundle(0))()
                if (value is ValueError) {
                    value = ValueUndef()
                }
                res = AOPConstant(query, value)
                onChange()
            }
        }
        return res
    }
}
