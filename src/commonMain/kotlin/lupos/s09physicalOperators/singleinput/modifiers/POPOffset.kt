package lupos.s09physicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s09physicalOperators.POPBase

class POPOffset(query: Query, @JvmField val offset: Int, child: OPBase) : POPBase(query, EOperatorID.POPOffsetID, "POPOffset", arrayOf(child)) {
    override fun equals(other: Any?): Boolean {
        if (other !is POPOffset)
            return false
        if (offset != other.offset)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT "))
            return sparql.substring(0, sparql.length - 1) + " OFFSET " + offset + "}"
        return "{SELECT * {" + sparql + "} OFFSET " + offset + "}"
    }

    override fun cloneOP() = POPOffset(query, offset, children[0].cloneOP())
    override suspend fun evaluate(): ColumnIteratorRow {
        val variables = getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate()
        var columns = Array(variables.size) { child.columns[variables[it]] }
        var tmp: Value? = null
        for (i in 0 until offset) {
            for (columnIndex in 0 until columns.size) {
                tmp = columns[columnIndex]!!.next()
                if (tmp == null) {
                    break
                }
            }
        }
        for (variable in variables) {
            if (tmp == null) {
                child.columns[variable]!!.close()
            }
            outMap[variable] = child.columns[variable]!!
        }
        return ColumnIteratorRow(outMap)
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("offset", "" + offset)
}
