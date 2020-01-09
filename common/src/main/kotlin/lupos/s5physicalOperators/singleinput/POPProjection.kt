package lupos.s5physicalOperators.singleinput

import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase

class POPProjection : POPSingleInputBase {
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variablesOld: Array<Variable>
    private val variablesNew: Array<Variable>

    constructor(variables: MutableList<LOPVariable>, child: POPBase) : super(child) {
        resultSetOld = child.getResultSet()
        this.variablesOld = Array<Variable>(variables.size, init = fun(it: Int) = resultSetOld.createVariable(variables[it].name))
        this.variablesNew = Array<Variable>(variables.size, init = fun(it: Int) = resultSetNew.createVariable(variables[it].name))
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        val res = child.hasNext()
        return res
    }

    override fun next(): ResultRow {
        var rsNew = resultSetNew.createResultRow()
        val rsOld = child.next()
        for (i in variablesNew.indices) {
            // TODO reuse resultSet
            rsNew[variablesNew[i]] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variablesOld[i]]))
        }
        return rsNew
    }

    override fun toString(indentation: String): String {
        var res = "${indentation}${this::class.simpleName}\n${indentation}\tvariables:\n"
        for (i in variablesNew.indices) {
            res += "${indentation}\t\t${resultSetOld.getVariable(variablesNew[i])}\n"
        }
        res += "${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
        return res
    }
}
