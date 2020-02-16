package lupos.s09physicalOperators.singleinput

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase


class POPBind : POPBase {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    val name: LOPVariable
    val expression: POPExpression
    private val variablesOld: Array<Variable?>
    private val variablesNew: Array<Variable?>
    private val variableBound: Variable

    constructor(dictionary: ResultSetDictionary, name: LOPVariable, expression: POPExpression, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        children[0] = child
        this.name = name
        this.expression = expression
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        val variableNames = children[0].resultSet.getVariableNames()
        variablesOld = Array<Variable?>(variableNames.size, init = fun(_: Int) = (null as Variable?))
        variablesNew = Array<Variable?>(variableNames.size + 1, init = fun(_: Int) = (null as Variable?))
        var i = 0
        variableBound = resultSet.createVariable(name.name)
        for (n in variableNames) {
            variablesOld[i] = children[0].resultSet.createVariable(n)
            variablesNew[i] = resultSet.createVariable(n)
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

    override fun evaluate() = Trace.trace<Unit>({ "POPBind.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            for (rsOld in children[0].channel) {
                var rsNew = resultSet.createResultRow()
                for (i in variablesOld.indices)
                    rsNew[variablesNew[i]!!] = rsOld[variablesOld[i]!!]
                try {
                    val value = expression.evaluate(children[0].resultSet, rsOld)
                    if (value == null)
                        resultSet.setUndefValue(rsNew, variableBound)
                    else
                        rsNew[variableBound] = resultSet.createValue(value)
                } catch (e: Throwable) {
                    resultSet.setUndefValue(rsNew, variableBound)
                    GlobalLogger.log(ELoggerType.DEBUG, { "silent :: " })
                    GlobalLogger.stacktrace(ELoggerType.DEBUG, e)
                }
                channel.send(rsNew)
            }
            channel.close()
            children[0].channel.close()
        }
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPBind")
        res.addAttribute("name", name.name)
        res.addContent(XMLElement("expression").addContent(expression.toXMLElement()))
        res.addContent(childrenToXML())
        return res
    }
}
