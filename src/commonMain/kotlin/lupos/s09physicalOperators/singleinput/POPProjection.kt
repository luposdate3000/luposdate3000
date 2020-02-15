package lupos.s09physicalOperators.singleinput

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


class POPProjection : POPBase {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    val variables: MutableList<LOPVariable>
    private val variablesOld: Array<Variable>
    private val variablesNew: Array<Variable>

    constructor(dictionary: ResultSetDictionary, variables: MutableList<LOPVariable>, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        this.children[0] = child
        this.variables = variables
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        this.variablesOld = Array<Variable>(variables.size, init = fun(it: Int) = children[0].resultSet.createVariable(variables[it].name))
        this.variablesNew = Array<Variable>(variables.size, init = fun(it: Int) = resultSet.createVariable(variables[it].name))
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

    override fun hasNext(): Boolean = Trace.trace({ "POPProjection.hasNext" }, {
        val res = children[0].hasNext()
        return res
    }) as Boolean

    override fun next(): ResultRow = Trace.trace({ "POPProjection.next" }, {
        var rsNew = resultSet.createResultRow()
        val rsOld = children[0].next()
        for (i in variablesNew.indices) {
            // TODO reuse resultSet
            rsNew[variablesNew[i]] = rsOld[variablesOld[i]]
        }
        return rsNew
    }) as ResultRow

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
