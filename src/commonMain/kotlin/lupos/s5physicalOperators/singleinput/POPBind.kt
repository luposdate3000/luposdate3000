package lupos.s5physicalOperators.singleinput

import lupos.misc.XMLElement

import lupos.misc.kotlinStacktrace

import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.POPExpression

class POPBind : POPSingleInputBase {
    val name: LOPVariable
    val expression: POPExpression
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variablesOld: Array<Variable?>
    private val variablesNew: Array<Variable?>
    private val variableBound: Variable

    constructor(name: LOPVariable, expression: POPExpression, child: POPBase) : super(child) {
        this.name = name
        this.expression = expression
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
        return mutableListOf<String>(name.name)
    }

    override fun getRequiredVariableNames(): List<String> {
        return expression.getRequiredVariableNames()
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
        try {
            rsNew[variableBound] = resultSetNew.createValue(expression.evaluate(resultSetOld, rsOld))
        } catch (e: Throwable) {
            rsNew[variableBound] = resultSetNew.createValue(resultSetNew.getUndefValue())
            print("silent :: ")
            e.kotlinStacktrace()
        }
        return rsNew
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPBind")
        res.addAttribute("name", name.name)
        res.addContent(XMLElement("LocalValue").addContent(expression.toXMLElement()))
        res.addContent(child.toXMLElement())
        return res
    }
}
