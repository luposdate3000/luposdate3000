package lupos.s09physicalOperators.singleinput.modifiers
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
class POPOffset(query: Query, projectedVariables: List<String>, @JvmField val offset: Int, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPOffsetID, "POPOffset", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun equals(other: Any?): Boolean {
Coverage.funStart(11458)
        if (other !is POPOffset) {
Coverage.ifStart(11459)
            return false
        }
Coverage.statementStart(11460)
        if (offset != other.offset) {
Coverage.ifStart(11461)
            return false
        }
Coverage.statementStart(11462)
        for (i in children.indices) {
Coverage.forLoopStart(11463)
            if (!children[i].equals(other.children[i])) {
Coverage.ifStart(11464)
                return false
            }
Coverage.statementStart(11465)
        }
Coverage.statementStart(11466)
        return true
    }
    override fun toSparql(): String {
Coverage.funStart(11467)
        val sparql = children[0].toSparql()
Coverage.statementStart(11468)
        if (sparql.startsWith("{SELECT ")) {
Coverage.ifStart(11469)
            return sparql.substring(0, sparql.length - 1) + " OFFSET " + offset + "}"
        }
Coverage.statementStart(11470)
        return "{SELECT * {" + sparql + "} OFFSET " + offset + "}"
    }
    override fun cloneOP() = POPOffset(query, projectedVariables, offset, children[0].cloneOP())
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(11471)
        val variables = getProvidedVariableNames()
Coverage.statementStart(11472)
        val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(11473)
        val child = children[0].evaluate()
Coverage.statementStart(11474)
        var columns = Array(variables.size) { child.columns[variables[it]] }
Coverage.statementStart(11475)
        var tmp: Value? = null
Coverage.statementStart(11476)
        for (i in 0 until offset) {
Coverage.forLoopStart(11477)
            for (columnIndex in 0 until columns.size) {
Coverage.forLoopStart(11478)
                tmp = columns[columnIndex]!!.next()
Coverage.statementStart(11479)
                if (tmp == null) {
Coverage.ifStart(11480)
                    break
                }
Coverage.statementStart(11481)
            }
Coverage.statementStart(11482)
        }
Coverage.statementStart(11483)
        for (variable in variables) {
Coverage.forLoopStart(11484)
            if (tmp == null) {
Coverage.ifStart(11485)
                child.columns[variable]!!.close()
Coverage.statementStart(11486)
            }
Coverage.statementStart(11487)
            outMap[variable] = ColumnIteratorDebug(uuid, variable, child.columns[variable]!!)
Coverage.statementStart(11488)
        }
Coverage.statementStart(11489)
        return IteratorBundle(outMap)
    }
    override fun toXMLElement() = super.toXMLElement().addAttribute("offset", "" + offset)
}
