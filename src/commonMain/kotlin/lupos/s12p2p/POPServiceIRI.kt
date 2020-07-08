package lupos.s12p2p
import lupos.s00misc.Partition

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.ServiceNotImplementedException
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPServiceIRI(query: Query, projectedVariables: List<String>, @JvmField val serverName: String, @JvmField val silent: Boolean, @JvmField val constraint: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPServiceIRIID, "POPServiceIRI", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun equals(other: Any?) = other is POPServiceIRI && silent == other.silent && serverName == other.serverName && constraint == other.constraint
    override fun cloneOP() = POPServiceIRI(query, projectedVariables, serverName, silent, constraint)
    override fun getProvidedVariableNamesInternal() = constraint.getProvidedVariableNames().distinct()
    override suspend fun evaluate(parent:Partition): IteratorBundle {
        throw ServiceNotImplementedException()
/*Coverage Unreachable*/
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPServiceIRI")
        res.addAttribute("name", serverName)
        res.addAttribute("silent", "" + silent)
        res.addContent(constraint.toXMLElement())
        return res
    }
}
