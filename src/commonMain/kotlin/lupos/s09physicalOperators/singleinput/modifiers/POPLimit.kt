package lupos.s09physicalOperators.singleinput.modifiers
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase



class POPLimit(query: Query, projectedVariables: List<String>, @JvmField val limit: Int, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPLimitID, "POPLimit", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return sparql.substring(0, sparql.length - 1) + " LIMIT " + limit + "}"
        }
        return "{SELECT * {" + sparql + "} LIMIT " + limit + "}"
    }

    override fun equals(other: Any?): Boolean = other is POPLimit && limit == other.limit && children[0] == other.children[0]
    override fun cloneOP() = POPLimit(query, projectedVariables, limit, children[0].cloneOP())
    override suspend fun evaluate(): ColumnIteratorRow {
        val variables = getProvidedVariableNames()
        var count = 0
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate()
        for (variable in variables) {
            val iterator = child.columns[variable]!!
            val tmp = ColumnIterator()
            tmp.next = {
                var res: Value?
                if (count == limit) {
                    tmp.close()
                    res = null
                } else {
                    count++
                    res = iterator.next()
                }
/*return*/res
            }
            tmp.close = {
                tmp._close()
                child.columns[variable]!!.close()
            }
            outMap[variable] = ColumnIteratorDebug(uuid, variable, tmp)
        }
        return ColumnIteratorRow(outMap)
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("limit", "" + limit)
}
