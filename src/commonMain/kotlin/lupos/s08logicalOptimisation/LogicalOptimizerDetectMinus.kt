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
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) :OPBase{
        var res: OPBase = node
        if (node is LOPFilter) {
            val node1 = node.children[1]
            if (node1 is AOPNot) {
                val node10 = node1.children[0]
                if (node10 is AOPBuildInCallBOUND) {
                    //there exists a filter, such that the variable is NOT bound.
                    //now search for_ an optional join, where this variable is bound only in the optional part
                    val variableName = (node10.children[0] as AOPVariable).name
                    searchForOptionalJoin(node, variableName, { p, i ->
                        val a = p.children[i].children[0]
                        val b = p.children[i].children[1]
                        val tmpFakeVariables = b.getProvidedVariableNames().toMutableList()
                        tmpFakeVariables.removeAll(a.getProvidedVariableNames())
                        if (b.getProvidedVariableNames().containsAll(a.getProvidedVariableNames())) {
                            p.children[i] = LOPMinus(query, a, b, tmpFakeVariables)
                        } else {
                            p.children[i] = LOPMinus(query, a, LOPJoin(query, a.cloneOP(), b, false), tmpFakeVariables)//put all the variables into the subtracting child too - to be able to process the filters
                        }
                        res = node.children[0] // remove the !bound part
                        onChange()
                    })
                }
            }
        }
return res
    }

    fun searchForOptionalJoin(node: OPBase, variableName: String, action: (OPBase, Int) -> Unit) {
        for (c in 0 until node.children.size) {
            val child = node.children[c]
            if (child is LOPJoin && child.optional && !child.children[0].getProvidedVariableNames().contains(variableName) && child.children[1].getProvidedVariableNames().contains(variableName)) {
                action(node, c)
            } else {
                searchForOptionalJoin(child, variableName, action)
            }
        }
    }
}
