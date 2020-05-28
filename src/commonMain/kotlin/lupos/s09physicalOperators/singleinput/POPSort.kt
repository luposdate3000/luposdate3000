package lupos.s09physicalOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
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
import lupos.s04logicalOperators.iterator.ColumnIteratorMerge
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
Coverage.funStart(12083)
        val variables = Array(sortBy.size) { sortBy[it].name }
Coverage.statementStart(12084)
        var child = children[0]
Coverage.statementStart(12085)
        SanityCheck.check({ child !is POPSort })
Coverage.statementStart(12086)
        val sparql = child.toSparql()
Coverage.statementStart(12087)
        var res: String
Coverage.statementStart(12088)
        if (sparql.startsWith("{SELECT ")) {
Coverage.ifStart(12089)
            res = sparql.substring(0, sparql.length - 1)
Coverage.statementStart(12090)
        } else {
Coverage.ifStart(12091)
            res = "{SELECT *{" + sparql + "}"
Coverage.statementStart(12092)
        }
Coverage.statementStart(12093)
        res += " ORDER BY "
Coverage.statementStart(12094)
        if (sortOrder) {
Coverage.ifStart(12095)
            res += "ASC("
Coverage.statementStart(12096)
        } else {
Coverage.ifStart(12097)
            res += "DESC("
Coverage.statementStart(12098)
        }
Coverage.statementStart(12099)
        for (v in variables) {
Coverage.forLoopStart(12100)
            res += AOPVariable(query, v).toSparql() + " "
Coverage.statementStart(12101)
        }
Coverage.statementStart(12102)
        res += ")"
Coverage.statementStart(12103)
        res += "}"
Coverage.statementStart(12104)
        return res
    }
    override fun toXMLElement(): XMLElement {
Coverage.funStart(12105)
        val res = XMLElement("POPSort")
Coverage.statementStart(12106)
        val projectedXML = XMLElement("projectedVariables")
Coverage.statementStart(12107)
        res.addContent(projectedXML)
Coverage.statementStart(12108)
        for (variable in projectedVariables) {
Coverage.forLoopStart(12109)
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
Coverage.statementStart(12110)
        }
Coverage.statementStart(12111)
        res.addAttribute("uuid", "" + uuid)
Coverage.statementStart(12112)
        val sortByXML = XMLElement("by")
Coverage.statementStart(12113)
        res.addContent(sortByXML)
Coverage.statementStart(12114)
        for (v in sortBy) {
Coverage.forLoopStart(12115)
            sortByXML.addContent(XMLElement("variable").addAttribute("name", v.name))
Coverage.statementStart(12116)
        }
Coverage.statementStart(12117)
        if (sortOrder) {
Coverage.ifStart(12118)
            res.addAttribute("order", "ASC")
Coverage.statementStart(12119)
        } else {
Coverage.ifStart(12120)
            res.addAttribute("order", "DESC")
Coverage.statementStart(12121)
        }
Coverage.statementStart(12122)
        res.addAttribute("providedVariables", getProvidedVariableNames().toString())
Coverage.statementStart(12123)
        res.addAttribute("providedSort", getPossibleSortPriorities().toString())
Coverage.statementStart(12124)
        res.addAttribute("filteredSort", sortPriorities.toString())
Coverage.statementStart(12125)
        res.addAttribute("selectedSort", mySortPriority.toString())
Coverage.statementStart(12126)
        res.addContent(childrenToXML())
Coverage.statementStart(12127)
        return res
    }
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(12128)
        val child = children[0].evaluate()
Coverage.statementStart(12129)
        val variablesOut = getProvidedVariableNames()
Coverage.statementStart(12130)
        var comparator: Comparator<Value>
Coverage.statementStart(12131)
        if (sortOrder) {
Coverage.ifStart(12132)
            comparator = ValueComparatorASC(query)
Coverage.statementStart(12133)
        } else {
Coverage.ifStart(12134)
            comparator = ValueComparatorDESC(query)
Coverage.statementStart(12135)
        }
Coverage.statementStart(12136)
        if (variablesOut.size == 0) {
Coverage.ifStart(12137)
            return child
        } else if (variablesOut.size == 1) {
Coverage.statementStart(12138)
            if (sortBy.size == 1) {
Coverage.ifStart(12139)
                return IteratorBundle(mapOf(variablesOut[0] to ColumnIteratorMerge(child.columns[variablesOut[0]]!!, comparator)))
            } else {
Coverage.statementStart(12140)
                return IteratorBundle(mapOf(variablesOut[0] to ColumnIteratorMerge(child.columns[variablesOut[0]]!!)))
            }
Coverage.statementStart(12141)
        } else {
Coverage.ifStart(12142)
            val columnNamesTmp = mutableListOf<String>()
Coverage.statementStart(12143)
            for (v in sortBy) {
Coverage.forLoopStart(12144)
                columnNamesTmp.add(v.name)
Coverage.statementStart(12145)
            }
Coverage.statementStart(12146)
            for (v in mySortPriority.map { it.variableName }) {
Coverage.forLoopStart(12147)
                if (variablesOut.contains(v)) {
Coverage.ifStart(12148)
                    if (!columnNamesTmp.contains(v)) {
Coverage.ifStart(12149)
                        columnNamesTmp.add(v)
Coverage.statementStart(12150)
                    }
Coverage.statementStart(12151)
                }
Coverage.statementStart(12152)
            }
Coverage.statementStart(12153)
            for (v in variablesOut) {
Coverage.forLoopStart(12154)
                if (!columnNamesTmp.contains(v)) {
Coverage.ifStart(12155)
                    columnNamesTmp.add(v)
Coverage.statementStart(12156)
                }
Coverage.statementStart(12157)
            }
Coverage.statementStart(12158)
            val columnNames = columnNamesTmp.toTypedArray()
Coverage.statementStart(12159)
            return IteratorBundle(RowIteratorMerge(child.rows, comparator, sortBy.size, columnNames))
        }
Coverage.statementStart(12160)
    }
}
