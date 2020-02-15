package lupos.s09physicalOperators.singleinput

import kotlinx.coroutines.*
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPBind


class POPBindUndefined : POPBase {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    val name: LOPVariable
    private val variablesOld: Array<Variable?>
    private val variablesNew: Array<Variable?>
    private val variableBound: Variable

    constructor(dictionary: ResultSetDictionary, name: LOPVariable, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        children[0] = child
        this.name = name
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
        return mutableListOf<String>(name.name) + children[0].getRequiredVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames()
    }

    override fun evaluate() {
        for (c in children)
            c.evaluate()
        runBlocking {
            for (rsOld in children[0].channel) {
                var rsNew = resultSet.createResultRow()
                for (i in variablesOld.indices) {
                    // TODO reuse resultSet
                    rsNew[variablesNew[i]!!] = rsOld[variablesOld[i]!!]
                }
                resultSet.setUndefValue(rsNew, variableBound)
                channel.send(rsNew)
            }
            channel.close()
            for (c in children)
                c.channel.close()
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPBindUndefined")
        res.addAttribute("name", name.name)
        res.addContent(childrenToXML())
        return res
    }
}
