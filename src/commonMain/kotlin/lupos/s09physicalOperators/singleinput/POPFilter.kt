package lupos.s09physicalOperators.singleinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator


class POPFilter : POPBaseNullableIterator {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    val filter: POPExpression
    private val resultSet: ResultSet

    constructor(dictionary: ResultSetDictionary, filter: POPExpression, child: OPBase) : super() {
        this.dictionary = dictionary
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

    override fun nnext(): ResultRow? =            Trace.trace("POPFilter.nnext"){
            while (children[0].hasNext()) {
                val nextRow = children[0].next()
                if (filter.evaluateBoolean(resultSet, nextRow)) {
                    return nextRow
                }
            }
            return null
    }as ResultRow?

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPFilter")
        res.addContent(XMLElement("filter").addContent(filter.toXMLElement()))
        res.addContent(childrenToXML())
        return res
    }
}
