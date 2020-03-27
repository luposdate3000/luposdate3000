package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter

class LogicalOptimizerFilterSplitAND(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterSplitANDID) {
    override val classname = "LogicalOptimizerFilterSplitAND"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPFilter) {
            val child = node.children[0]
            val aopcompare = node.children[1]
            if (aopcompare is AOPAnd) {
                onChange()
                res = LOPFilter(query, aopcompare.children[0] as AOPBase, LOPFilter(query, aopcompare.children[1] as AOPBase, child))
            }
        }
        res
    })
}
