package lupos.s09physicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import lupos.s00misc.SanityCheck
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPOffset(query: Query, projectedVariables: List<String>, @JvmField val offset: Int, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPOffsetID, "POPOffset", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
override fun getPartitionCount(variable:String):Int{
SanityCheck.check{children[0].getPartitionCount(variable)==1}
return 1
}
    override fun equals(other: Any?) = other is POPOffset && offset == other.offset && children[0] == other.children[0]
    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return sparql.substring(0, sparql.length - 1) + " OFFSET " + offset + "}"
        }
        return "{SELECT * {" + sparql + "} OFFSET " + offset + "}"
    }

    override fun cloneOP() = POPOffset(query, projectedVariables, offset, children[0].cloneOP())
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        val variables = getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate(parent)
        var columns = Array(variables.size) { child.columns[variables[it]] }
        var tmp: Value = ResultSetDictionary.nullValue
        loop@ for (i in 0 until offset) {
            for (columnIndex in 0 until columns.size) {
                tmp = columns[columnIndex]!!.next()
                if (tmp == ResultSetDictionary.nullValue) {
                    for (closeIndex in 0 until columns.size) {
                        columns[closeIndex]!!.close()
                    }
                    break@loop
                }
            }
        }
        for (variable in variables) {
            if (tmp == ResultSetDictionary.nullValue) {
                child.columns[variable]!!.close()
            }
            outMap[variable] = child.columns[variable]!!
        }
        return IteratorBundle(outMap)
    }

    override suspend fun toXMLElement() = super.toXMLElement().addAttribute("offset", "" + offset)
}
