package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.LOPBind


class LogicalOptimizerOptional(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary, EOptimizerID.LogicalOptimizerOptionalID) {
    override val classname = "LogicalOptimizerOptional"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPOptional) {
            res = LOPJoin(OPNothing(), node.children[0], true)
        }
        res
    })
}
