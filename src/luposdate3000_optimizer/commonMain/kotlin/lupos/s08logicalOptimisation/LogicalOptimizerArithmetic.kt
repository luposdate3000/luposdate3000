package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallExists
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallNotExists
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerArithmetic(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerArithmeticID) {
    override val classname = "LogicalOptimizerArithmetic"
    fun hasAggregation(node: IOPBase): Boolean {
        for (n in node.getChildren()) {
            if (hasAggregation(n)) {
                return true
            }
        }
        return node is AOPAggregationBase
    }

    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if (node is AOPBase && node !is AOPValue && node !is AOPBuildInCallNotExists && node !is AOPBuildInCallExists && node !is AOPVariable) {
            if (node.getChildren().size > 0 && node.getRequiredVariableNamesRecoursive().size == 0 && !hasAggregation(node)) {
                var value = node.evaluateID(IteratorBundle(0))()
                if (value == ResultSetDictionaryExt.errorValue) {
                    value = ResultSetDictionaryExt.undefValue
                }
                res = AOPConstant(query, value)
                onChange()
            }
        }
        return res
    }
}
