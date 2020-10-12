package lupos.s09physicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPLimit(query: Query, projectedVariables: List<String>, @JvmField val limit: Int, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPLimitID, "POPLimit", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int {
        SanityCheck.check { children[0].getPartitionCount(variable) == 1 }
        return 1
    }

    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return sparql.substring(0, sparql.length - 1) + " LIMIT " + limit + "}"
        }
        return "{SELECT * {" + sparql + "} LIMIT " + limit + "}"
    }

    override fun equals(other: Any?): Boolean = other is POPLimit && limit == other.limit && children[0] == other.children[0]
    override fun cloneOP() = POPLimit(query, projectedVariables, limit, children[0].cloneOP())
    override suspend fun evaluate(parent: Partition): IteratorBundle {
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
                override suspend fun next(): Value {
                    if (label != 0) {
                        if (count == limit) {
                            _close()
                            return ResultSetDictionary.nullValue
                        } else {
                            count++
                            return iterator.next()
                        }
                    } else {
                        return ResultSetDictionary.nullValue
                    }
                }

                suspend inline fun _close() {
                    if (label != 0) {
                        label = 0
                        iterator.close()
                    }
                }

                override suspend fun close() {
                    _close()
                }
            }
            outMap[variable] = tmp
        }
        return IteratorBundle(outMap)
    }

    override suspend fun toXMLElement() = super.toXMLElement().addAttribute("limit", "" + limit)
}
