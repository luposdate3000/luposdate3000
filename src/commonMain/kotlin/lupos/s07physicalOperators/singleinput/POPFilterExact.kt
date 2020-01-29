package lupos.s07physicalOperators.singleinput

import lupos.s00misc.*

import lupos.s07physicalOperators.singleinput.POPSingleInputBaseNullableIterator
import lupos.s07physicalOperators.singleinput.POPBindUndefined
import lupos.s07physicalOperators.singleinput.POPBind
import lupos.s07physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s07physicalOperators.POPExpression
import lupos.s07physicalOperators.POPBase
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.data.LOPExpression

import lupos.s00misc.XMLElement
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.Variable
import lupos.s06resultRepresentation.ResultSet


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
        try {
            Trace.start("POPFilterExact.nnext")
            while (true) {
                if (!child.hasNext()) {
                    return null
                }
                val nextRow = child.next()
                if (resultSet.getValue(nextRow[filterVariable]) == value) {
                    return nextRow!!
                }
            }
        } finally {
            Trace.stop("POPFilterExact.nnext")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPFilterExact")
        res.addAttribute("name", variable.name)
        res.addAttribute("value", value)
        res.addContent(XMLElement("child").addContent(child.toXMLElement()))
        return res
    }
companion object{
        fun fromXMLElement(xml:XMLElement):POPFilterExact{
                return POPFilterExact(LOPVariable(xml.attributes["name"]!!),xml.attributes["value"]!!,XMLElement.convertToPOPBase(xml["child"]!!))
        }
    }
}
