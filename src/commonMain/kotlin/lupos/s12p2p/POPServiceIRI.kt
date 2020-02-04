package lupos.s11p2p
import lupos.s07physicalOperators.POPBase

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.data.LOPExpression
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.OPBase
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.POPExpression
import lupos.s07physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s07physicalOperators.singleinput.POPBindUndefined
import lupos.s07physicalOperators.singleinput.POPFilterExact
import lupos.s07physicalOperators.singleinput.POPSingleInputBaseNullableIterator


class POPServiceIRI : POPBase {
    val transactionID: Long
    private val resultSet: ResultSet
    val constraint: OPBase?
    val serverName: String
    val silent: Boolean
    var first = true
    val originalConstraint: OPBase

    constructor(transactionID: Long, serverName: String, silent: Boolean, constraint: OPBase) : super() {
        this.transactionID = transactionID
        this.serverName = serverName
        originalConstraint = constraint
        this.constraint = try {
            P2P.execOnNamedNode(transactionID, serverName, constraint)
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
            val res = constraint!!.next()
            return res
        } catch (e: Throwable) {
            if (silent || constraint == null) {
                val res = resultSet.createResultRow()
                for (n in getProvidedVariableNames())
                    res[resultSet.createVariable(n)] = resultSet.createValue(resultSet.getUndefValue())
                return res
            }
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
        val res = XMLElement("POPServiceIRI")
        res.addAttribute("name", serverName)
        res.addAttribute("silent", "" + silent)
        if (constraint != null)
            res.addContent(constraint.toXMLElement())
        else
            res.addContent(originalConstraint.toXMLElement())
        return res
    }
}
