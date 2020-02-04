package lupos.s09physicalOperators.singleinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.LOPExpression
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPBindUndefined
import lupos.s09physicalOperators.singleinput.POPSingleInputBaseNullableIterator


class POPFilterExact : POPSingleInputBaseNullableIterator {

    val variable: LOPVariable
    val value: String
    private val resultSet: ResultSet
    private val filterVariable: Variable

    constructor(variable: LOPVariable, value: String, child: OPBase) : super(child) {
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
}
