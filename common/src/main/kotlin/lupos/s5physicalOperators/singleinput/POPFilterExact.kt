package lupos.s5physicalOperators.singleinput

import lupos.misc.*
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase

class POPFilterExact : POPSingleInputBaseNullableIterator {

    val variable: LOPVariable
    val value: String

    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variablesOld: Array<Variable?>
    private val variablesNew: Array<Variable?>
    private val filterVariable: Variable
    private val filterValue: String

    constructor(variable: LOPVariable, value: String, child: POPBase) : super(child) {
        this.variable = variable
        this.value = value
        resultSetOld = child.getResultSet()
        val variableNames = resultSetOld.getVariableNames()
        variablesOld = Array<Variable?>(variableNames.size, init = fun(_: Int) = (null as Variable?))
        variablesNew = Array<Variable?>(variableNames.size, init = fun(_: Int) = (null as Variable?))
        var i = 0
        for (name in variableNames) {
            variablesOld[i] = resultSetOld.createVariable(name)
            variablesNew[i] = resultSetNew.createVariable(name)
            i++
        }
        filterVariable = resultSetOld.createVariable(variable.name)
        filterValue = value
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames() + variable.name
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun nnext(): ResultRow? {
        var nextRow: ResultRow?
        while (true) {
            if (!child.hasNext())
                return null
            nextRow = child.next()
            if (resultSetOld.getValue(nextRow[filterVariable]) == filterValue) {
                break
            }
        }
        var rsNew = resultSetNew.createResultRow()

        val rsOld = nextRow!!
        for (i in variablesNew.indices) {
            // TODO reuse resultSet
            rsNew[variablesNew[i]!!] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variablesOld[i]!!]))
        }
        return rsNew
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n${indentation}\tvariable:\n${indentation}\t\t${resultSetOld.getVariable(filterVariable)}\n${indentation}\tvalue:\n${indentation}\t\t${filterValue}\n${indentation}\tchild:\n${child.toString("${indentation}\t\t")}"
    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPFilterExact")
        res.addAttribute("name", variable.name)
        res.addAttribute("value", value)
        res.addContent(child.toXMLElement())
        return res
    }
}
