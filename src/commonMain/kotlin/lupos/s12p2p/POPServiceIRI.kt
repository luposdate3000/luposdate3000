package lupos.s12p2p
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable

import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s12p2p.P2P


class POPServiceIRI(query: Query, val serverName: String, val silent: Boolean, val constraint: OPBase) : POPBase(query, EOperatorID.POPServiceIRIID, "POPServiceIRI", arrayOf()) {
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

    override fun cloneOP() = POPServiceIRI(query, serverName, silent, constraint)
    override fun getProvidedVariableNames() = constraint.getProvidedVariableNames().distinct()
    override suspend fun evaluate(): ColumnIteratorRow {
        TODO("not implemented")
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPServiceIRI")
        res.addAttribute("name", serverName)
        res.addAttribute("silent", "" + silent)
        if (constraint != null)
            res.addContent(constraint.toXMLElement())
        return res
    }
}
