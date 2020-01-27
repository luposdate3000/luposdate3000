package lupos.s5physicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.Variable
import lupos.s06resultRepresentation.ResultSet


import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s2buildOperatorGraph.data.LOPExpression
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.POPExpression

class POPFilter : POPSingleInputBaseNullableIterator {

    val filter: POPExpression
    private val resultSet: ResultSet

    constructor(filter: POPExpression, child: POPBase) : super(child) {
        this.filter = filter
        resultSet = child.getResultSet()
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames() + filter.getRequiredVariableNames()
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun nnext(): ResultRow? {
        while (true) {
            if (!child.hasNext())
                return null
            val nextRow = child.next()
            if (filter.evaluateBoolean(resultSet, nextRow))
                return nextRow!!
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPFilter")
        res.addContent(filter.toXMLElement())
        res.addContent(child.toXMLElement())
        return res
    }
}
