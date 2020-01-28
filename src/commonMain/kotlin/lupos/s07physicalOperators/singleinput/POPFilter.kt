package lupos.s07physicalOperators.singleinput

import lupos.s00misc.*

import lupos.s07physicalOperators.singleinput.POPSingleInputBaseNullableIterator
import lupos.s07physicalOperators.singleinput.POPFilterExact
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
        try {
            Trace.start("POPFilter.nnext")
            while (child.hasNext()) {
                val nextRow = child.next()
                if (filter.evaluateBoolean(resultSet, nextRow)) {
                    return nextRow!!
                }
            }
            return null
        } finally {
            Trace.stop("POPFilter.nnext")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPFilter")
        res.addContent(filter.toXMLElement())
        res.addContent(child.toXMLElement())
        return res
    }
}
