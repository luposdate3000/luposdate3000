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
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s09physicalOperators.singleinput.POPSingleInputBaseNullableIterator



class POPFilter : POPSingleInputBaseNullableIterator {

    val filter: POPExpression
    private val resultSet: ResultSet

    constructor(filter: POPExpression, child: OPBase) : super(child) {
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
        res.addContent(XMLElement("filter").addContent(filter.toXMLElement()))
        res.addContent(XMLElement("child").addContent(child.toXMLElement()))
        return res
    }
}
