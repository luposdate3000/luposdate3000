package lupos.s07physicalOperators.singleinput

import lupos.s07physicalOperators.singleinput.POPSingleInputBase
import lupos.s07physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s07physicalOperators.singleinput.POPGroup
import lupos.s07physicalOperators.singleinput.POPFilter
import lupos.s07physicalOperators.singleinput.POPFilterExact
import lupos.s07physicalOperators.singleinput.POPBindUndefined
import lupos.s07physicalOperators.singleinput.POPBind
import lupos.s07physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s07physicalOperators.POPBase
import lupos.s03buildOperatorGraph.data.LOPVariable

import lupos.s00misc.XMLElement
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.Variable
import lupos.s06resultRepresentation.ResultSet


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

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPProjection")
        val vars = XMLElement("LocalVariables")
        res.addContent(vars)
        for (v in variables)
            vars.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
        res.addContent(child.toXMLElement())
        return res
    }
}
