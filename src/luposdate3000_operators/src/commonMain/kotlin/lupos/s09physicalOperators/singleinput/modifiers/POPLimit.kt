package lupos.s09physicalOperators.singleinput.modifiers

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField

class POPLimit(query: IQuery, projectedVariables: List<String>, @JvmField val limit: Int, child: IOPBase) : POPBase(query, projectedVariables, EOperatorID.POPLimitID, "POPLimit", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int {
        SanityCheck.check { children[0].getPartitionCount(variable) == 1 }
        return 1
    }

    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return sparql.substring(0, sparql.length - 1) + " LIMIT " + limit + "}"
        }
        return "{SELECT * {$sparql} LIMIT $limit}"
    }

    override fun equals(other: Any?): Boolean = other is POPLimit && limit == other.limit && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = POPLimit(query, projectedVariables, limit, children[0].cloneOP())
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate(parent)
        for (variable in variables) {
            val tmp = object : ColumnIterator() {
                @JvmField
                var count = 0

                @JvmField
                val iterator = child.columns[variable]!!

                @JvmField
                var label = 1
                override /*suspend*/ fun next(): Int {
                    return if (label != 0) {
                        if (count == limit) {
                            _close()
                            ResultSetDictionaryExt.nullValue
                        } else {
                            count++
                            iterator.next()
                        }
                    } else {
                        ResultSetDictionaryExt.nullValue
                    }
                }

                /*suspend*/ inline fun _close() {
                    if (label != 0) {
                        label = 0
                        iterator.close()
                    }
                }

                override /*suspend*/ fun close() {
                    _close()
                }
            }
            outMap[variable] = tmp
        }
        return IteratorBundle(outMap)
    }

    override /*suspend*/ fun toXMLElement(): XMLElement = super.toXMLElement().addAttribute("limit", "" + limit)
}
