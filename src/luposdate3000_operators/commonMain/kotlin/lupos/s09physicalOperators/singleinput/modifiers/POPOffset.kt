package lupos.s09physicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s09physicalOperators.POPBase

class POPOffset(query: IQuery, projectedVariables: List<String>, @JvmField val offset: Int, child: IOPBase) : POPBase(query, projectedVariables, EOperatorID.POPOffsetID, "POPOffset", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int {
        SanityCheck.check { children[0].getPartitionCount(variable) == 1 }
        return 1
    }

    override fun equals(other: Any?) = other is POPOffset && offset == other.offset && children[0] == other.children[0]
    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return sparql.substring(0, sparql.length - 1) + " OFFSET " + offset + "}"
        }
        return "{SELECT * {$sparql} OFFSET $offset}"
    }

    override fun cloneOP(): IOPBase = POPOffset(query, projectedVariables, offset, children[0].cloneOP())
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate(parent)
        val columns = Array(variables.size) { child.columns[variables[it]] }
        var tmp: Int = ResultSetDictionaryExt.nullValue
        loop@ for (i in 0 until offset) {
            for (element in columns) {
                tmp = element!!.next()
                if (tmp == ResultSetDictionaryExt.nullValue) {
                    for (element in columns) {
                        element!!.close()
                    }
                    break@loop
                }
            }
        }
        for (variable in variables) {
            if (tmp == ResultSetDictionaryExt.nullValue) {
                child.columns[variable]!!.close()
            }
            outMap[variable] = child.columns[variable]!!
        }
        return IteratorBundle(outMap)
    }

    override /*suspend*/ fun toXMLElement() = super.toXMLElement().addAttribute("offset", "" + offset)
}
