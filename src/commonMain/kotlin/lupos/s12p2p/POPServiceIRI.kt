package lupos.s12p2p

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase
import lupos.s12p2p.P2P


class POPServiceIRI : POPBase {
    @JvmField
    val constraint: OPBase?
    @JvmField
    val serverName: String
    @JvmField
    val silent: Boolean
    @JvmField
    var first = true
    @JvmField
    val originalConstraint: OPBase

    override fun equals(other: Any?): Boolean {
        if (other !is POPServiceIRI)
            return false
        if (silent != other.silent)
            return false
        if (serverName != other.serverName)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun cloneOP() = POPServiceIRI(query, serverName, silent, originalConstraint)

    constructor(query: Query, serverName: String, silent: Boolean, constraint: OPBase) : super(query, EOperatorID.POPServiceIRIID, "POPServiceIRI", ResultSet(query.dictionary), arrayOf()) {
        this.serverName = serverName
        originalConstraint = constraint
        this.constraint = try {
            P2P.execOnNamedNode(query, serverName, constraint)
        } catch (e: Throwable) {
            null
        }
        this.silent = silent
        //todo ... handle the case if the target node is not part of this p2p network
    }

    override fun getProvidedVariableNames() = originalConstraint.getProvidedVariableNames().distinct()

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPServiceIRI.evaluate" }, {
        for (n in getProvidedVariableNames())
            resultSet.createVariable(n)
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            Trace.trace({ "POPServiceIRI.next" }, {
                try {
                    if (constraint == null) {
                        if (silent) {
                            val res = resultSet.createResultRow()
                            channel.send(res)
                        }
                    } else {
                        val variables = mutableListOf<Pair<Variable, Variable>>()
                        for (n in getProvidedVariableNames())
                            variables.add(Pair(resultSet.createVariable(n), constraint.resultSet.createVariable(n)))
                        val constraintChannel = constraint.evaluate()
                        constraintChannel.forEach { oldRow ->
                            val res = resultSet.createResultRow()
                            for (n in variables)
                                resultSet.setValue(res, n.first, constraint.resultSet.getValue(oldRow, n.second))
                            channel.send(res)
                        }
                    }
                    channel.close()
                } catch (e: Throwable) {
                    channel.close(e)
                }
            })
        }
        return ResultIterator(next = {
            channel.receive()
        }, close = {
            channel.close()
        })
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
