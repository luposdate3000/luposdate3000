package lupos.s09physicalOperators.singleinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPExpression
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPBindUndefined
import lupos.s09physicalOperators.singleinput.POPFilterExact


class POPFilter : POPBaseNullableIterator {
    override val children: Array<OPBase> = arrayOf(OPNothing())
    val filter: POPExpression
    private val resultSet: ResultSet

    constructor(filter: POPExpression, child: OPBase) : super() {
        children[0] = child
        this.filter = filter
        resultSet = children[0].getResultSet()
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return filter.getRequiredVariableNames()
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun nnext(): ResultRow? {
        try {
            Trace.start("POPFilter.nnext")
            while (children[0].hasNext()) {
                val nextRow = children[0].next()
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
        res.addContent(XMLElement("filter").addContent(filter.toXMLElement()))
        res.addContent(childrenToXML())
        return res
    }
}
