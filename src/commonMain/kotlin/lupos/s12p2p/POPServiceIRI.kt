package lupos.s12p2p
import lupos.s03resultRepresentation.*
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
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.POPBindUndefined
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s12p2p.P2P
import lupos.s12p2p.P2PLocalDummy


class POPServiceIRI : POPBase {
    override val children: Array<OPBase> = arrayOf()
    val transactionID: Long
    private val resultSet: ResultSet
    val constraint: OPBase?
    val serverName: String
    val silent: Boolean
    var first = true
    val originalConstraint: OPBase
override val dictionary:ResultSetDictionary
    constructor(dictionary:ResultSetDictionary,transactionID: Long, serverName: String, silent: Boolean, constraint: OPBase) : super() {
this.dictionary=dictionary
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
