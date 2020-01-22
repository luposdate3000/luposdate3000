package lupos.s5physicalOperators.singleinput

import lupos.misc.*
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase

class POPProjection : POPSingleInputBase {
    val variables: MutableList<LOPVariable>
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variablesOld: Array<Variable>
    private val variablesNew: Array<Variable>

    constructor(variables: MutableList<LOPVariable>, child: POPBase) : super(child) {
        this.variables = variables
        resultSetOld = child.getResultSet()
        this.variablesOld = Array<Variable>(variables.size, init = fun(it: Int) = resultSetOld.createVariable(variables[it].name))
        this.variablesNew = Array<Variable>(variables.size, init = fun(it: Int) = resultSetNew.createVariable(variables[it].name))
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (v in variables)
            res.add(v.name)
        return res
    }

    override fun getRequiredVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (v in variables)
            res.add(v.name)
        return res
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

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPProjection")
        var vars = ""
        for (v in variables)
            vars = vars + "," + v.name
        res.addAttribute("variables", vars)
        res.addContent(child.toXMLElement())
        return res
    }
}
