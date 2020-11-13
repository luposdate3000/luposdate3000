package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPProjection

class LogicalOptimizerBindToFilter(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerBindToFilterID) {
    override val classname: String = "LogicalOptimizerBindToFilter"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        if (node is LOPBind) {
            val v = node.getChildren()[0].getProvidedVariableNames()
            if (v.contains(node.name.name)) {
                val v2 = mutableListOf<String>()
                v2.addAll(v)
                v2.remove(node.name.name)
                node.getChildren()[0] = LOPProjection(query, v2.map { AOPVariable(query, it) }.toMutableList(), LOPFilter(query, AOPEQ(query, AOPVariable(query, node.name.name), node.getChildren()[1] as AOPBase), node.getChildren()[0]))
                onChange()
            }
        }
        return node
    }
}
