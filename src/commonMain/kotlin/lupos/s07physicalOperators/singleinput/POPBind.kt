package lupos.s07physicalOperators.singleinput

import lupos.s00misc.kotlinStacktrace
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.OPBase
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.POPBase
import lupos.s07physicalOperators.POPExpression
import lupos.s07physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s07physicalOperators.singleinput.POPSingleInputBase


class POPBind : POPSingleInputBase {
    val name: LOPVariable
    val expression: POPExpression
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variablesOld: Array<Variable?>
    private val variablesNew: Array<Variable?>
    private val variableBound: Variable

    constructor(name: LOPVariable, expression: POPExpression, child: OPBase) : super(child) {
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
        return mutableListOf<String>(name.name) + child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return expression.getRequiredVariableNames() + child.getRequiredVariableNames()
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPBind.hasNext")
            val res = child.hasNext()
            return res
        } finally {
            Trace.stop("POPBind.hasNext")
        }
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPBind.next")
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
        } finally {
            Trace.stop("POPBind.next")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPBind")
        res.addAttribute("name", name.name)
        res.addContent(XMLElement("expression").addContent(expression.toXMLElement()))
        res.addContent(XMLElement("child").addContent(child.toXMLElement()))
        return res
    }
}
