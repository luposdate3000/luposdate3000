package lupos.s09physicalOperators.singleinput

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPBind


class POPBindUndefined : POPBase {
    override val classname = "POPBindUndefined"
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    val name: AOPVariable
    private val variablesOld: Array<Variable?>
    private val variablesNew: Array<Variable?>
    private val variableBound: Variable
    override fun equals(other: Any?): Boolean {
        if (other !is POPBindUndefined)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (name != other.name)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    constructor(dictionary: ResultSetDictionary, name: AOPVariable, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        children[0] = child
        this.name = name
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        val variableNames = children[0].getProvidedVariableNames()
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

    override fun getProvidedVariableNames() = (children[0].getRequiredVariableNames() + name.name).distinct()

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames()
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPBindUndefined.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                for (rsOld in children[0].channel) {
                    resultFlowConsume({ this@POPBindUndefined }, { children[0] }, { rsOld })
                    var rsNew = resultSet.createResultRow()
                    for (i in variablesOld.indices)
                        rsNew[variablesNew[i]!!] = rsOld[variablesOld[i]!!]
                    resultSet.setUndefValue(rsNew, variableBound)
                    channel.send(resultFlowProduce({ this@POPBindUndefined }, { rsNew }))
                }
                channel.close()
                children[0].channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children[0].channel.close(e)
            }
        }
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPBindUndefined")
        res.addAttribute("uuid", "" + uuid)
        res.addAttribute("name", name.name)
        res.addContent(childrenToXML())
        return res
    }
}
