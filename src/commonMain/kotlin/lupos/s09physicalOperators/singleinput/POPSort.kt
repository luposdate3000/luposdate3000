package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMerge
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s04logicalOperators.iterator.RowIteratorMerge
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPSort(query: Query, projectedVariables: List<String>, @JvmField val sortBy: Array<AOPVariable>, @JvmField val sortOrder: Boolean, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPSortID, "POPSort", arrayOf(child), ESortPriority.SORT) {
override fun getPartitionCount(variable:String):Int{
SanityCheck.check{children[0].getPartitionCount(variable)==1}
return 1
}
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

    override suspend fun toXMLElement(): XMLElement {
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

    override suspend fun evaluate(parent: Partition): IteratorBundle {
        val child = children[0].evaluate(parent)
        val variablesOut = getProvidedVariableNames()
        var comparator: Comparator<Value>
        if (sortOrder) {
            comparator = ValueComparatorASC(query)
        } else {
            comparator = ValueComparatorDESC(query)
        }
        if (variablesOut.size == 0) {
            return child
        } else if (variablesOut.size == 1) {
            if (sortBy.size == 1) {
                return IteratorBundle(mapOf(variablesOut[0] to ColumnIteratorMerge(child.columns[variablesOut[0]]!!, comparator)))
            } else {
                return IteratorBundle(mapOf(variablesOut[0] to ColumnIteratorMerge(child.columns[variablesOut[0]]!!)))
            }
/*Coverage Unreachable*/
        } else {
            val columnNamesTmp = mutableListOf<String>()
            for (v in sortBy) {
                columnNamesTmp.add(v.name)
            }
            for (v in mySortPriority.map { it.variableName }) {
                if (variablesOut.contains(v)) {
                    if (!columnNamesTmp.contains(v)) {
                        columnNamesTmp.add(v)
                    }
                }
            }
            for (v in variablesOut) {
                if (!columnNamesTmp.contains(v)) {
                    columnNamesTmp.add(v)
                }
            }
            val columnNames = columnNamesTmp.toTypedArray()
            return IteratorBundle(RowIteratorMerge(child.rows, comparator, sortBy.size, columnNames))
        }
/*Coverage Unreachable*/
    }
}
