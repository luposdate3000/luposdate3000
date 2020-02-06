package lupos.s09physicalOperators.singleinput
import lupos.s03resultRepresentation.*

import lupos.s00misc.kotlinStacktrace
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct


class POPBind : POPBase {
override val dictionary:ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    val name: LOPVariable
    val expression: POPExpression
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variablesOld: Array<Variable?>
    private val variablesNew: Array<Variable?>
    private val variableBound: Variable

    constructor(dictionary:ResultSetDictionary,name: LOPVariable, expression: POPExpression, child: OPBase) : super() {
this.dictionary=dictionary
         children[0] = child
        this.name = name
        this.expression = expression
        resultSetOld = children[0].getResultSet()
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
        return mutableListOf<String>(name.name) + children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return expression.getRequiredVariableNames()
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPBind.hasNext")
            val res = children[0].hasNext()
            return res
        } finally {
            Trace.stop("POPBind.hasNext")
        }
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPBind.next")
            var rsNew = resultSetNew.createResultRow()
            val rsOld = children[0].next()
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
        res.addContent(childrenToXML())
        return res
    }
}
