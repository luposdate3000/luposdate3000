package lupos.s5physicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.Variable
import lupos.s06resultRepresentation.ResultSet


import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s5physicalOperators.POPBase

class POPBindUndefined : POPSingleInputBase {
    val name: LOPVariable
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variablesOld: Array<Variable?>
    private val variablesNew: Array<Variable?>
    private val variableBound: Variable

    constructor(name: LOPVariable, child: POPBase) : super(child) {
        this.name = name
        resultSetOld = child.getResultSet()
        val variableNames = resultSetOld.getVariableNames()
        variablesOld = Array<Variable?>(variableNames.size, init = fun(_: Int) = (null as Variable?))
        variablesNew = Array<Variable?>(variableNames.size + 1, init = fun(_: Int) = (null as Variable?))
        var i = 0
        variableBound = resultSetNew.createVariable(name.name)
        for (n in variableNames) {
            variablesOld[i] = resultSetOld.createVariable(n)
            variablesNew[i] = resultSetNew.createVariable(n)
            i++
        }
        variablesNew[i] = variableBound
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(name.name) + child.getRequiredVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
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
        for (i in variablesOld.indices) {
            // TODO reuse resultSet
            rsNew[variablesNew[i]!!] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variablesOld[i]!!]))
        }
        rsNew[variableBound] = resultSetNew.createValue(resultSetNew.getUndefValue())
        return rsNew
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPBindUndefined")
        res.addAttribute("name", name.name)
        res.addContent(child.toXMLElement())
        return res
    }
}
