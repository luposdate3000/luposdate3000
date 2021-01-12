package lupos.s09physicalOperators.singleinput
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIteratorMerge
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIteratorMerge
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField
public class POPSort public constructor(query: IQuery, projectedVariables: List<String>, @JvmField public val sortBy: Array<AOPVariable>, @JvmField public val sortOrder: Boolean, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPSortID, "POPSort", arrayOf(child), ESortPriorityExt.SORT) {
    override fun getPartitionCount(variable: String): Int {
        SanityCheck.check { children[0].getPartitionCount(variable) == 1 }
        return 1
    }
    override fun equals(other: Any?): Boolean = other is POPSort && sortBy.contentEquals(other.sortBy) && sortOrder == other.sortOrder && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = POPSort(query, projectedVariables, sortBy, sortOrder, children[0].cloneOP())
    override fun getRequiredVariableNames(): List<String> = sortBy.map { it.name }
    override fun toSparql(): String {
        val variables = Array(sortBy.size) { sortBy[it].name }
        val child = children[0]
        SanityCheck.check { child !is POPSort }
        val sparql = child.toSparql()
        var res: String = if (sparql.startsWith("{SELECT ")) {
            sparql.substring(0, sparql.length - 1)
        } else {
            "{SELECT *{$sparql}"
        }
        res += " ORDER BY "
        res += if (sortOrder) {
            "ASC("
        } else {
            "DESC("
        }
        for (v in variables) {
            res += AOPVariable(query, v).toSparql() + " "
        }
        res += ")"
        res += "}"
        return res
    }
    override /*suspend*/ fun toXMLElement(): XMLElement {
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
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val child = children[0].evaluate(parent)
        val variablesOut = getProvidedVariableNames()
        val comparator: Comparator<Int> = if (sortOrder) {
            ValueComparatorASC(query)
        } else {
            ValueComparatorDESC(query)
        }
        when {
            variablesOut.isEmpty() -> {
                return child
            }
            variablesOut.size == 1 -> {
                return if (sortBy.size == 1) {
                    IteratorBundle(mapOf(variablesOut[0] to ColumnIteratorMerge(child.columns[variablesOut[0]]!!, comparator)))
                } else {
                    IteratorBundle(mapOf(variablesOut[0] to ColumnIteratorMerge(child.columns[variablesOut[0]]!!)))
                }
            }
            else -> {
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
        }
    }
}
