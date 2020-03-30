package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerFilterUp(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterUpID) {
    override val classname = "LogicalOptimizerFilterUp"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node !is LOPFilter) {
            for (i in node.children.indices) {
                val filter = node.children[i]
                if (filter is LOPFilter && node.getProvidedVariableNames().containsAll(filter.getRequiredVariableNames())) {
                    val d = filter.children[1] as AOPBase
                    if (d is AOPNEQ && (d.children[0] is AOPVariable || d.children[0] is AOPConstant) && (d.children[1] is AOPVariable || d.children[1] is AOPConstant)) {
                        node.children[i] = filter.children[0]
                        filter.children[0] = node
                        res = filter
                        onChange()
                        break
                    }
                }
            }
        }
        res
    })
}
