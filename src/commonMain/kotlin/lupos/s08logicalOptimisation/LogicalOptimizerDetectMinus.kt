package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBOUND
import lupos.s04arithmetikOperators.singleinput.AOPNot
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.*
class LogicalOptimizerDetectMinus(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerDetectMinusID) {
    override val classname = "LogicalOptimizerDetectMinus"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(8887)
        var res: OPBase = node
Coverage.statementStart(8888)
        if (node is LOPFilter) {
Coverage.ifStart(8889)
            val node1 = node.children[1]
Coverage.statementStart(8890)
            if (node1 is AOPNot) {
Coverage.ifStart(8891)
                val node10 = node1.children[0]
Coverage.statementStart(8892)
                if (node10 is AOPBuildInCallBOUND) {
Coverage.ifStart(8893)
                    //there exists a filter, such that the variable is NOT bound.
Coverage.statementStart(8894)
                    //now search for_ an optional join, where this variable is bound only in the optional part
Coverage.statementStart(8895)
                    val variableName = (node10.children[0] as AOPVariable).name
Coverage.statementStart(8896)
                    searchForOptionalJoin(node, variableName, { p, i ->
Coverage.statementStart(8897)
                        val a = p.children[i].children[0]
Coverage.statementStart(8898)
                        val b = p.children[i].children[1]
Coverage.statementStart(8899)
                        val tmpFakeVariables = b.getProvidedVariableNames().toMutableList()
Coverage.statementStart(8900)
                        tmpFakeVariables.removeAll(a.getProvidedVariableNames())
Coverage.statementStart(8901)
                        if (b.getProvidedVariableNames().containsAll(a.getProvidedVariableNames())) {
Coverage.ifStart(8902)
                            p.children[i] = LOPMinus(query, a, b, tmpFakeVariables)
Coverage.statementStart(8903)
                        } else {
Coverage.ifStart(8904)
                            p.children[i] = LOPMinus(query, a, LOPJoin(query, a.cloneOP(), b, false), tmpFakeVariables)//put all the variables into the subtracting child too - to be able to process the filters
Coverage.statementStart(8905)
                        }
Coverage.statementStart(8906)
                        res = node.children[0] // remove the !bound part
Coverage.statementStart(8907)
                        onChange()
Coverage.statementStart(8908)
                    })
Coverage.statementStart(8909)
                }
Coverage.statementStart(8910)
            }
Coverage.statementStart(8911)
        }
Coverage.statementStart(8912)
        return res
    }
    fun searchForOptionalJoin(node: OPBase, variableName: String, action: (OPBase, Int) -> Unit) {
Coverage.funStart(8913)
        for (c in 0 until node.children.size) {
Coverage.forLoopStart(8914)
            val child = node.children[c]
Coverage.statementStart(8915)
            if (child is LOPJoin && child.optional && !child.children[0].getProvidedVariableNames().contains(variableName) && child.children[1].getProvidedVariableNames().contains(variableName)) {
Coverage.ifStart(8916)
                action(node, c)
Coverage.statementStart(8917)
            } else {
Coverage.ifStart(8918)
                searchForOptionalJoin(child, variableName, action)
Coverage.statementStart(8919)
            }
Coverage.statementStart(8920)
        }
Coverage.statementStart(8921)
    }
}
