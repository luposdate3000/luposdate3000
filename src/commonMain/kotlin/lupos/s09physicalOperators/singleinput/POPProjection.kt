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


class POPProjection : POPBase {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    val variables: MutableList<AOPVariable>
    private val variablesOld: Array<Variable>
    private val variablesNew: Array<Variable>
    override fun equals(other: Any?): Boolean {
        if (other !is POPProjection)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (!variables.equals(other.variables))
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    constructor(dictionary: ResultSetDictionary, variables: MutableList<AOPVariable>, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        this.children[0] = child
        this.variables = variables
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        this.variablesOld = Array<Variable>(variables.size, init = fun(it: Int) = children[0].resultSet.createVariable(variables[it].name))
        this.variablesNew = Array<Variable>(variables.size, init = fun(it: Int) = resultSet.createVariable(variables[it].name))
    }

    override fun getProvidedVariableNames()=MutableList(variables.size){variables[it].name}.distinct()

    override fun getRequiredVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (v in variables)
            res.add(v.name)
        return res
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPProjection.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                for (rsOld in children[0].channel) {
                    resultFlowConsume({ this@POPProjection }, { children[0] }, { rsOld })
                    var rsNew = resultSet.createResultRow()
                    for (i in variablesNew.indices)
                        rsNew[variablesNew[i]] = rsOld[variablesOld[i]]
                    channel.send(resultFlowProduce({ this@POPProjection }, { rsNew }))
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
        val res = XMLElement("POPProjection")
        val vars = XMLElement("variables")
        res.addContent(vars)
        for (v in variables)
            vars.addContent(XMLElement("variable").addAttribute("name", v.name))
        res.addContent(childrenToXML())
        return res
    }
}
