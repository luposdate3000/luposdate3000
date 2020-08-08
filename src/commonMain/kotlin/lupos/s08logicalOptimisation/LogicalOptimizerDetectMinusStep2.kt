package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind

class LogicalOptimizerDetectMinusStep2(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerDetectMinusStep2ID) {
    override val classname = "LogicalOptimizerDetectMinusStep2"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res: OPBase = node
        if (node is LOPMinus) {
            val tmp = node.tmpFakeVariables.toMutableSet()
            tmp.removeAll(node.children[0].getProvidedVariableNames())
            if (tmp.size > 0) {
                for (v in tmp) {
                    res = LOPBind(query, AOPVariable(query, v), AOPConstant(query, ValueUndef()), res)
                }
                onChange()
                node.tmpFakeVariables = listOf<String>()
            }
        }
        return res
    }
}
