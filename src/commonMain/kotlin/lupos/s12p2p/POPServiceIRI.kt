package lupos.s12p2p
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
class POPServiceIRI(query: Query, projectedVariables: List<String>, val serverName: String, val silent: Boolean, val constraint: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPServiceIRIID, "POPServiceIRI", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun equals(other: Any?): Boolean {
Coverage.funStart(12697)
        if (other !is POPServiceIRI) {
Coverage.ifStart(12698)
            return false
        }
Coverage.statementStart(12699)
        if (silent != other.silent) {
Coverage.ifStart(12700)
            return false
        }
Coverage.statementStart(12701)
        if (serverName != other.serverName) {
Coverage.ifStart(12702)
            return false
        }
Coverage.statementStart(12703)
        for (i in children.indices) {
Coverage.forLoopStart(12704)
            if (!children[i].equals(other.children[i])) {
Coverage.ifStart(12705)
                return false
            }
Coverage.statementStart(12706)
        }
Coverage.statementStart(12707)
        return true
    }
    override fun cloneOP() = POPServiceIRI(query, projectedVariables, serverName, silent, constraint)
    override fun getProvidedVariableNamesInternal() = constraint.getProvidedVariableNames().distinct()
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(12708)
        TODO("not implemented")
Coverage.statementStart(12709)
    }
    override fun toXMLElement(): XMLElement {
Coverage.funStart(12710)
        val res = XMLElement("POPServiceIRI")
Coverage.statementStart(12711)
        res.addAttribute("name", serverName)
Coverage.statementStart(12712)
        res.addAttribute("silent", "" + silent)
Coverage.statementStart(12713)
        res.addContent(constraint.toXMLElement())
Coverage.statementStart(12714)
        return res
    }
}
