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
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(8736)
        var res: OPBase = node
Coverage.statementStart(8737)
        if (node is LOPBind) {
Coverage.ifStart(8738)
            var v = node.children[0].getProvidedVariableNames()
Coverage.statementStart(8739)
            if (v.contains(node.name.name)) {
Coverage.ifStart(8740)
                val v2 = mutableListOf<String>()
Coverage.statementStart(8741)
                v2.addAll(v)
Coverage.statementStart(8742)
                v2.remove(node.name.name)
Coverage.statementStart(8743)
                node.children[0] = LOPProjection(query, v2.map { AOPVariable(query, it) }.toMutableList(), LOPFilter(query, AOPEQ(query, AOPVariable(query, node.name.name), node.children[1] as AOPBase), node.children[0]))
Coverage.statementStart(8744)
                onChange()
Coverage.statementStart(8745)
            }
Coverage.statementStart(8746)
        }
Coverage.statementStart(8747)
        return res
    }
}
