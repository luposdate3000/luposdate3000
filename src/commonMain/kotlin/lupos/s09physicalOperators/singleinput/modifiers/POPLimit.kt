package lupos.s09physicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPLimit(query: Query, @JvmField val limit: Int, child: OPBase) : POPBase(query, EOperatorID.POPLimitID, "POPLimit", arrayOf(child)) {
    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return sparql.substring(0, sparql.length - 1) + " LIMIT " + limit + "}"
        }
        return "{SELECT * {" + sparql + "} LIMIT " + limit + "}"
    }

    override fun equals(other: Any?): Boolean = other is POPLimit && limit == other.limit && children[0] == other.children[0]
    override fun cloneOP() = POPLimit(query, limit, children[0].cloneOP())
    override suspend fun evaluate(): ColumnIteratorRow {
        val variables = getProvidedVariableNames()
        var count = 0
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate()
        for (variable in variables) {
            val iterator = child.columns[variable]!!
            val tmp = ColumnIterator()
            tmp.next = {
                if (count == limit) {
                    tmp.close()
                    /*return*/            null
                } else {
                    count++
/*return*/                    iterator.next()
                }
            }
            tmp.close = {
                tmp._close()
                for (variable2 in variables) {
                    child.columns[variable2]!!.close()
                    outMap[variable2]!!.close()
                }
            }
            outMap[variable] = tmp
        }
        return ColumnIteratorRow(outMap)
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("limit", "" + limit)
}
