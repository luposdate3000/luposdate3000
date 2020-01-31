package lupos.s11p2p

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.data.LOPExpression
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.OPBase
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


class POPServiceIRI : POPBase {
    private val resultSet: ResultSet
    val constraint: OPBase?
    val serverName: String
    val silent: Boolean
    var first = true
    val originalConstraint: OPBase

    constructor(serverName: String, silent: Boolean, constraint: OPBase) : super() {
        this.serverName = serverName
        originalConstraint = constraint
        this.constraint = try {
            P2P.execOnNamedNode(serverName, constraint)
        } catch (e: Throwable) {
            null
        }
        this.silent = silent
        //todo ... handle the case if the target node is not part of this p2p network
        resultSet = if (this.constraint != null)
            this.constraint.getResultSet()
        else
            ResultSet()
    }

    override fun getProvidedVariableNames(): List<String> {
        return originalConstraint.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return originalConstraint.getRequiredVariableNames()
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun next(): ResultRow {
        try {
            return constraint!!.next()
        } catch (e: Throwable) {
            if (silent)
                return resultSet.createResultRow()
            throw e
        }
    }

    override fun hasNext(): Boolean {
        if (first && constraint == null) {
            first = false
            return true
        } else if (constraint == null) {
            return false
        } else
            return constraint.hasNext()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPService")
        res.addAttribute("serverName", serverName)
        res.addContent(originalConstraint.toXMLElement())
        return res
    }
}
