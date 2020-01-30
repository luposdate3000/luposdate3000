package lupos.s11p2p

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.data.LOPExpression
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.*
import lupos.s07physicalOperators.POPExpression
import lupos.s07physicalOperators.singleinput.*
import lupos.s07physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s07physicalOperators.singleinput.POPBindUndefined
import lupos.s07physicalOperators.singleinput.POPFilterExact
import lupos.s07physicalOperators.singleinput.POPSingleInputBaseNullableIterator
import lupos.s11p2p.*

class POPService : POPBase {
    private val resultSet: ResultSet
    val child: POPBase
    val serverName: String

    constructor(serverName: String, child: POPBase) : super() {
        this.serverName = serverName
        this.child = P2P.execOnNamedNode(serverName, child)
//todo ... handle the case if the target node is not part of this p2p network
        resultSet = this.child.getResultSet()
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun next(): ResultRow {
        return child.next()
    }

    override fun hasNext(): Boolean {
        return child.hasNext()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPService")
        res.addAttribute("serverName", serverName)
        res.addContent(child.toXMLElement())
        return res
    }
}
