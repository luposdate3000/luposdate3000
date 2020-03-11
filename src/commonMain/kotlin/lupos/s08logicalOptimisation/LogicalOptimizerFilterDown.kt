package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerFilterDown(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterDownID) {
    override val classname = "LogicalOptimizerFilterDown"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPFilter) {
            val d = node.children[1] as AOPBase
            if (d !is AOPNEQ || !(d.children[0] is AOPVariable || d.children[0] is AOPConstant) || !(d.children[1] is AOPVariable || d.children[1] is AOPConstant)) {
                val c = node.children[0]
                if (c.children.size == 1) {
                    val cc = c.children[0]
                    if (cc.getProvidedVariableNames().containsAll(node.getRequiredVariableNames())) {
                        c.children[0] = node
                        node.children[0] = cc
                        onChange()
                        res = c
                    }
                } else if (c !is LOPGroup) {
                    var moved = false
                    for (ci in c.children.indices) {
                        val cc = c.children[ci]
                        if (cc.getProvidedVariableNames().containsAll(node.getRequiredVariableNames())) {
                            c.children[ci] = LOPFilter(query, node.children[1] as AOPBase, c.children[ci])
                            moved = true
                        }
                    }
                    if (moved)
                        res = c
                }
            }
        }
        res
    })
}
