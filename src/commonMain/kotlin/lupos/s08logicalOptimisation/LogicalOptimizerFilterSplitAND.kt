package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter


class LogicalOptimizerFilterSplitAND(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary, EOptimizerID.LogicalOptimizerFilterSplitANDID) {
    override val classname = "LogicalOptimizerFilterSplitAND"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPFilter) {
            val child = node.children[0]
            val aopcompare = node.children[1]
            if (aopcompare is AOPAnd) {
                onChange()
                res = LOPFilter(aopcompare.children[0] as AOPBase, LOPFilter(aopcompare.children[1] as AOPBase, child))
            }
        }
        res
    })
}
