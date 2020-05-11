package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorMergeSort
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s04logicalOperators.iterator.RowIteratorBuf
import lupos.s04logicalOperators.iterator.RowIteratorMerge
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPSort(query: Query, projectedVariables: List<String>, @JvmField val sortBy: Array<AOPVariable>, @JvmField val sortOrder: Boolean, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPSortID, "POPSort", arrayOf(child), ESortPriority.SORT) {
    override fun equals(other: Any?): Boolean = other is POPSort && sortBy == other.sortBy && sortOrder == other.sortOrder && children[0] == other.children[0]
    override fun cloneOP() = POPSort(query, projectedVariables, sortBy, sortOrder, children[0].cloneOP())
    override fun getRequiredVariableNames(): List<String> = sortBy.map { it.name }
    override fun toSparql(): String {
        val variables = Array(sortBy.size) { sortBy[it].name }
        var child = children[0]
        SanityCheck.check({ child !is POPSort })
        val sparql = child.toSparql()
        var res: String
        if (sparql.startsWith("{SELECT ")) {
            res = sparql.substring(0, sparql.length - 1)
        } else {
            res = "{SELECT *{" + sparql + "}"
        }
        res += " ORDER BY "
        if (sortOrder) {
            res += "ASC("
        } else {
            res += "DESC("
        }
        for (v in variables) {
            res += AOPVariable(query, v).toSparql() + " "
        }
        res += ")"
        res += "}"
        return res
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPSort")
        val projectedXML = XMLElement("projectedVariables")
        res.addContent(projectedXML)
        for (variable in projectedVariables) {
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        res.addAttribute("uuid", "" + uuid)
        val sortByXML = XMLElement("by")
        res.addContent(sortByXML)
        for (v in sortBy) {
            sortByXML.addContent(XMLElement("variable").addAttribute("name", v.name))
        }
        if (sortOrder) {
            res.addAttribute("order", "ASC")
        } else {
            res.addAttribute("order", "DESC")
        }
        res.addAttribute("providedVariables", getProvidedVariableNames().toString())
        res.addAttribute("providedSort", getPossibleSortPriorities().toString())
        res.addAttribute("filteredSort", sortPriorities.toString())
        res.addAttribute("selectedSort", mySortPriority.toString())
        res.addContent(childrenToXML())
        return res
    }

    override suspend fun evaluate(): IteratorBundle {
/*if sortBy.size==0, than only use a fastComparator. This may happen, if there is a Distinct clause following */
        val child = children[0].evaluate()
        val variablesOut = getProvidedVariableNames()
        if (variablesOut.size == 0) {
            return child
        } else {
            val columnNamesTmp = mutableListOf<String>()
            val columnIteratorsTmp = mutableListOf<ColumnIterator>()
            for (v in sortBy) {
                columnNamesTmp.add(v.name)
                columnIteratorsTmp.add(child.columns[v.name]!!)
            }
            for (v in variablesOut) {
                if (!columnNamesTmp.contains(v)) {
                    columnNamesTmp.add(v)
                    columnIteratorsTmp.add(child.columns[v]!!)
                }
            }
            val columnNames = columnNamesTmp.toTypedArray()
            val columnIterators = columnIteratorsTmp.toTypedArray()
            var comparator: Comparator<Value>
            if (sortOrder) {
                comparator = ValueComparatorASC(query)
            } else {
                comparator = ValueComparatorDESC(query)
            }
            var buf1 = IntArray(columnNames.size * MERGE_SORT_MIN_ROWS)
            var buf2 = IntArray(columnNames.size * MERGE_SORT_MIN_ROWS)
            var done = false
            var resultList = mutableListOf<RowIterator?>()
            while (!done) {
                var i = 0
                loop@ while (i < MERGE_SORT_MIN_ROWS) {
                    for (columnIndex in 0 until columnNames.size) {
                        var tmp = columnIterators[columnIndex].next()
                        if (tmp == null) {
                            require(columnIndex == 0)
                            done = true
                            break@loop
                        } else {
                            buf1[i++] = tmp
                        }
                    }
                }
                var total = i / columnNames.size
                var off = 0
                var shift = 0
                var size = 1 shl shift
                var count = 0
                var mid = 0
                while (size / 2 < total) {
                    off = 0
                    shift++
                    size = 1 shl shift
                    while (off < total) {
                        if (off + size <= total) {
                            count = size
                        } else {
                            count = total - off
                        }
                        mid = size / 2
                        val aEnd = (off + mid) * columnNames.size
                        val bEnd = (off + count) * columnNames.size
                        var a = off * columnNames.size
                        var b = aEnd
                        var c = a
                        if (count < mid) {
                            b = a
                            a = aEnd
                        }
                        loop@ while (a < aEnd && b < bEnd) {
                            for (i in 0 until columnNames.size) {
                                var cmp = 0
                                var j = 0
                                while (j < sortBy.size) {
                                    cmp = comparator.compare(buf1[a + i], buf1[b + i])
                                    if (cmp < 0) {
                                        for (j in 0 until columnNames.size) {
                                            buf2[c++] = buf1[a++]
                                        }
                                        continue@loop
                                    } else if (cmp > 0) {
                                        for (j in 0 until columnNames.size) {
                                            buf2[c++] = buf1[b++]
                                        }
                                        continue@loop
                                    }
                                    j++
                                }
                                while (j < columnNames.size) {
                                    cmp = buf1[a + i] - buf1[b + i]
                                    if (cmp < 0) {
                                        for (j in 0 until columnNames.size) {
                                            buf2[c++] = buf1[a++]
                                        }
                                        continue@loop
                                    } else if (cmp > 0) {
                                        for (j in 0 until columnNames.size) {
                                            buf2[c++] = buf1[b++]
                                        }
                                        continue@loop
                                    }
                                    j++
                                }
                            }
                            for (j in 0 until columnNames.size) {
                                buf2[c++] = buf1[a++]
                            }
                            for (j in 0 until columnNames.size) {
                                buf2[c++] = buf1[b++]
                            }
                        }
                        while (a < aEnd) {
                            buf2[c++] = buf1[a++]
                        }
                        while (b < bEnd) {
                            buf2[c++] = buf1[b++]
                        }
                        off += size
                    }
                    var t = buf1
                    buf1 = buf2
                    buf2 = t
                }
                var it = RowIteratorBuf(buf1, columnNames, i)
                if (resultList.size == 0) {
                    resultList.add(it)
                } else if (resultList[0] == null) {
                    resultList[0] = it
                } else {
                    resultList[0] = RowIteratorMerge(resultList[0]!!, it, comparator, sortBy.size)
                    if (resultList[resultList.size - 1] != null) {
                        resultList.add(null)
                    }
                    var j = 1
                    while (j < resultList.size) {
                        if (resultList[j] == null) {
                            resultList[j] = resultList[j - 1]
                            resultList[j - 1] = null
                            break
                        } else {
                            resultList[j] = RowIteratorMerge(resultList[j]!!, resultList[j - 1]!!, comparator, sortBy.size)
                            resultList[j - 1] = null
                        }
                        j++
                    }
                }
                buf1 = IntArray(columnNames.size * MERGE_SORT_MIN_ROWS)
            }
            var j = 1
            while (j < resultList.size) {
                if (resultList[j] == null) {
                    resultList[j] = resultList[j - 1]
                } else {
                    resultList[j] = RowIteratorMerge(resultList[j]!!, resultList[j - 1]!!, comparator, sortBy.size)
                }
                j++
            }
            require(resultList.size > 0)
            return IteratorBundle(resultList[resultList.size - 1]!!)
        }
    }
}
