package lupos.s5physicalOperators.singleinput

import lupos.misc.XMLElement

import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s2buildOperatorGraph.data.LOPExpression
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.POPExpression

class POPFilterExact : POPSingleInputBaseNullableIterator {

    val variable: LOPVariable
    val value: String
    private val resultSet: ResultSet
    private val filterVariable: Variable

    constructor(variable: LOPVariable, value: String, child: POPBase) : super(child) {
        this.variable = variable
        this.value = value
        resultSet = child.getResultSet()
        filterVariable = resultSet.createVariable(variable.name)
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames() + variable.name
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun nnext(): ResultRow? {
        while (true) {
            if (!child.hasNext())
                return null
            val nextRow = child.next()
            if (resultSet.getValue(nextRow[filterVariable]) == value)
                return nextRow!!
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPFilterExact")
        res.addAttribute("name", variable.name)
        res.addAttribute("value", value)
        res.addContent(child.toXMLElement())
        return res
    }
}
