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
class POPLimit(query: Query, projectedVariables: List<String>, @JvmField val limit: Int, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPLimitID, "POPLimit", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun toSparql(): String {
Coverage.funStart(11430)
        val sparql = children[0].toSparql()
Coverage.statementStart(11431)
        if (sparql.startsWith("{SELECT ")) {
Coverage.ifStart(11432)
            return sparql.substring(0, sparql.length - 1) + " LIMIT " + limit + "}"
        }
Coverage.statementStart(11433)
        return "{SELECT * {" + sparql + "} LIMIT " + limit + "}"
    }
    override fun equals(other: Any?): Boolean = other is POPLimit && limit == other.limit && children[0] == other.children[0]
    override fun cloneOP() = POPLimit(query, projectedVariables, limit, children[0].cloneOP())
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(11434)
        val variables = getProvidedVariableNames()
Coverage.statementStart(11435)
        var count = 0
Coverage.statementStart(11436)
        val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(11437)
        val child = children[0].evaluate()
Coverage.statementStart(11438)
        for (variable in variables) {
Coverage.forLoopStart(11439)
            val iterator = child.columns[variable]!!
Coverage.statementStart(11440)
            val tmp = ColumnIterator()
Coverage.statementStart(11441)
            tmp.next = {
Coverage.statementStart(11442)
                var res: Value?
Coverage.statementStart(11443)
                if (count == limit) {
Coverage.ifStart(11444)
                    tmp.close()
Coverage.statementStart(11445)
                    res = null
Coverage.statementStart(11446)
                } else {
Coverage.ifStart(11447)
                    count++
Coverage.statementStart(11448)
                    res = iterator.next()
Coverage.statementStart(11449)
                }
Coverage.statementStart(11450)
/*return*/res
            }
Coverage.statementStart(11451)
            tmp.close = {
Coverage.statementStart(11452)
                tmp._close()
Coverage.statementStart(11453)
                child.columns[variable]!!.close()
Coverage.statementStart(11454)
            }
Coverage.statementStart(11455)
            outMap[variable] = ColumnIteratorDebug(uuid, variable, tmp)
Coverage.statementStart(11456)
        }
Coverage.statementStart(11457)
        return IteratorBundle(outMap)
    }
    override fun toXMLElement() = super.toXMLElement().addAttribute("limit", "" + limit)
}
