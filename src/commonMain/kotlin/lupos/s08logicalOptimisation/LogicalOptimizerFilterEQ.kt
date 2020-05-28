package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s08logicalOptimisation.OptimizerBase
class LogicalOptimizerFilterEQ(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterEQID) {
    override val classname = "LogicalOptimizerFilterEQ"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9069)
        var res: OPBase = node
Coverage.statementStart(9070)
        if (node is LOPFilter) {
Coverage.ifStart(9071)
            val filter = node.children[1]
Coverage.statementStart(9072)
            if (filter is AOPEQ) {
Coverage.ifStart(9073)
                val v1 = filter.children[0]
Coverage.statementStart(9074)
                val v2 = filter.children[1]
Coverage.statementStart(9075)
                if (v1 is AOPVariable && v2 is AOPVariable) {
Coverage.ifStart(9076)
                    if (parent != null) {
Coverage.ifStart(9077)
                        if (parent is LOPProjection && parent.variables.map { it.name }.contains(v1.name)) {
Coverage.ifStart(9078)
                            node.replaceVariableWithAnother(node.children[0], v2.name, v1.name, node, 0)
Coverage.statementStart(9079)
                            res = LOPBind(query, v2, v1, node.children[0])
Coverage.statementStart(9080)
                        } else {
Coverage.ifStart(9081)
                            node.replaceVariableWithAnother(node.children[0], v1.name, v2.name, node, 0)
Coverage.statementStart(9082)
                            res = LOPBind(query, v1, v2, node.children[0])
Coverage.statementStart(9083)
                        }
Coverage.statementStart(9084)
                    } else {
Coverage.ifStart(9085)
                        node.replaceVariableWithAnother(node.children[0], v1.name, v2.name, node, 0)
Coverage.statementStart(9086)
                        res = LOPBind(query, v1, v2, node.children[0])
Coverage.statementStart(9087)
                    }
Coverage.statementStart(9088)
                    onChange()
Coverage.statementStart(9089)
                }
Coverage.statementStart(9090)
            }
Coverage.statementStart(9091)
        }
Coverage.statementStart(9092)
        return res
    }
}
