package lupos.s12p2p

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s12p2p.P2P


class POPServiceIRI : POPBase {
    override val classname = "POPServiceIRI"
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    val transactionID: Long
    val constraint: OPBase?
    val serverName: String
    val silent: Boolean
    var first = true
    val originalConstraint: OPBase
    val variables = mutableListOf<Pair<Variable, Variable>>()

    override fun equals(other: Any?): Boolean {
        if (other !is POPServiceIRI)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (silent != other.silent)
            return false
        if (serverName != other.serverName)
            return false
        if (transactionID != other.transactionID)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    constructor(dictionary: ResultSetDictionary, transactionID: Long, serverName: String, silent: Boolean, constraint: OPBase) : super() {
        this.dictionary = dictionary
        this.transactionID = transactionID
        this.serverName = serverName
        originalConstraint = constraint
        this.constraint = try {
            P2P.execOnNamedNode(dictionary, transactionID, serverName, constraint)
        } catch (e: Throwable) {
            null
        }
        this.silent = silent
        //todo ... handle the case if the target node is not part of this p2p network
        resultSet = ResultSet(dictionary)
        for (n in getProvidedVariableNames()) {
            variables.add(Pair(resultSet.createVariable(n), constraint.resultSet.createVariable(n)))
        }
    }

    override fun getProvidedVariableNames() = originalConstraint.getProvidedVariableNames().distinct()

    override fun getRequiredVariableNames(): List<String> {
        return originalConstraint.getProvidedVariableNames()
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPServiceIRI.evaluate" }, {
        CoroutinesHelper.run {
            try {
                if (constraint == null) {
                    if (silent) {
                        val res = resultSet.createResultRow()
                        for (n in getProvidedVariableNames())
                            resultSet.setUndefValue(res, resultSet.createVariable(n))
                        channel.send(res)
                    }
                } else {
                    constraint.evaluate()
                    for (value in constraint.channel) {
                        val res = resultSet.createResultRow()
                        for (n in variables) {
                            res[n.first] = resultSet.createValue(constraint.resultSet.getValue(value[n.second]))
                        }
                        channel.send(res)
                    }
                }
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
    })

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
