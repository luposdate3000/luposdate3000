package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
class LogicalOptimizerFilterIntoTriple(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterIntoTripleID) {
    override val classname = "LogicalOptimizerFilterIntoTriple"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9093)
        var res: OPBase = node
Coverage.statementStart(9094)
        if (node is LOPFilter) {
Coverage.ifStart(9095)
            val loptriple = node.children[0]
Coverage.statementStart(9096)
            val aopcompare = node.children[1]
Coverage.statementStart(9097)
            if (loptriple is LOPTriple && aopcompare is AOPEQ) {
Coverage.ifStart(9098)
                for (c in 0 until 2) {
Coverage.forLoopStart(9099)
                    val compareVar = aopcompare.children[1 - c]
Coverage.statementStart(9100)
                    val compareVal = aopcompare.children[c]
Coverage.statementStart(9101)
                    if (compareVal is AOPConstant && compareVar is AOPVariable) {
Coverage.ifStart(9102)
                        for (i in 0 until 3) {
Coverage.forLoopStart(9103)
                            val tmp = loptriple.children[i]
Coverage.statementStart(9104)
                            if (tmp is AOPVariable) {
Coverage.ifStart(9105)
                                if (tmp.name == compareVar.name) {
Coverage.ifStart(9106)
                                    onChange()
Coverage.statementStart(9107)
                                    loptriple.children[i] = compareVal
Coverage.statementStart(9108)
                                    res = LOPBind(query, compareVar, compareVal, loptriple)
Coverage.statementStart(9109)
                                }
Coverage.statementStart(9110)
                            }
Coverage.statementStart(9111)
                        }
Coverage.statementStart(9112)
                    }
Coverage.statementStart(9113)
                }
Coverage.statementStart(9114)
            }
Coverage.statementStart(9115)
        }
Coverage.statementStart(9116)
        return res
    }
}
