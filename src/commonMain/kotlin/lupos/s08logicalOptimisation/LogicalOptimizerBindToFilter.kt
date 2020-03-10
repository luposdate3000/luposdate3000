package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerBindToFilter(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerBindToFilterID) {
    override val classname = "LogicalOptimizerBindToFilter"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPBind && (node.children[0].getProvidedVariableNames().contains(node.name.name))) {
            res = LOPFilter(query, AOPEQ(query, AOPVariable(query, node.name.name), node.children[1] as AOPBase), node.children[0])
            onChange()
        }
        res
    })
}
