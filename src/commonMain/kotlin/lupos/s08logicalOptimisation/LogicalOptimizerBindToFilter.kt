package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID

import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerBindToFilter(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerBindToFilterID) {
    override val classname = "LogicalOptimizerBindToFilter"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) :OPBase{
        var res: OPBase = node
        if (node is LOPBind) {
            var v = node.children[0].getProvidedVariableNames()
            if (v.contains(node.name.name)) {
                val v2 = mutableListOf<String>()
                v2.addAll(v)
                v2.remove(node.name.name)
                node.children[0] = LOPProjection(query, v2.map { AOPVariable(query, it) }.toMutableList(), LOPFilter(query, AOPEQ(query, AOPVariable(query, node.name.name), node.children[1] as AOPBase), node.children[0]))
                onChange()
            }
        }
return res
    }
}
