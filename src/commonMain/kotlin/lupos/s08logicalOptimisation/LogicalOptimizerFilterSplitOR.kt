package lupos.s08logicalOptimisation
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter


class LogicalOptimizerFilterSplitOR(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary, EOptimizerID.LogicalOptimizerFilterSplitORID) {
    override val classname = "LogicalOptimizerFilterSplitOR"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPFilter) {
            val child = node.children[0]
            val aopcompare = node.children[1]
            if (aopcompare is AOPOr && aopcompare.children[0] is AOPEQ && aopcompare.children[1] is AOPEQ) {
                onChange()
                require(false)/*TODO check this*/
                res = LOPUnion(LOPFilter(aopcompare.children[0] as AOPBase, child.cloneOP()), LOPFilter(aopcompare.children[1] as AOPBase, child.cloneOP()))
            }
        }
        res
    })
}
