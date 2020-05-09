package lupos.s12p2p
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase




class POPServiceIRI(query: Query, projectedVariables: List<String>, val serverName: String, val silent: Boolean, val constraint: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPServiceIRIID, "POPServiceIRI", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun equals(other: Any?): Boolean {
        if (other !is POPServiceIRI) {
            return false
        }
        if (silent != other.silent) {
            return false
        }
        if (serverName != other.serverName) {
            return false
        }
        for (i in children.indices) {
            if (!children[i].equals(other.children[i])) {
                return false
            }
        }
        return true
    }

    override fun cloneOP() = POPServiceIRI(query, projectedVariables, serverName, silent, constraint)
    override fun getProvidedVariableNamesInternal() = constraint.getProvidedVariableNames().distinct()
    override suspend fun evaluate(): ColumnIteratorRow {
        TODO("not implemented")
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPServiceIRI")
        res.addAttribute("name", serverName)
        res.addAttribute("silent", "" + silent)
        res.addContent(constraint.toXMLElement())
        return res
    }
}
