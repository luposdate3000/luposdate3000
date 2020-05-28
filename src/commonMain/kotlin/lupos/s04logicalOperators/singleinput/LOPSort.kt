package lupos.s04logicalOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPSort(query: Query, @JvmField val asc: Boolean, @JvmField var by: AOPVariable, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPSortID, "LOPSort", arrayOf(child), ESortPriority.SORT) {
    override fun toXMLElement(): XMLElement {
Coverage.funStart(5275)
        val res = XMLElement("LOPSort")
Coverage.statementStart(5276)
        res.addAttribute("by", by.name)
Coverage.statementStart(5277)
        if (asc) {
Coverage.ifStart(5278)
            res.addAttribute("order", "ASC")
Coverage.statementStart(5279)
        } else {
Coverage.ifStart(5280)
            res.addAttribute("order", "DESC")
Coverage.statementStart(5281)
        }
Coverage.statementStart(5282)
        res.addAttribute("providedVariables", getProvidedVariableNames().toString())
Coverage.statementStart(5283)
        res.addAttribute("providedSort", getPossibleSortPriorities().toString())
Coverage.statementStart(5284)
        res.addAttribute("filteredSort", sortPriorities.toString())
Coverage.statementStart(5285)
        res.addAttribute("selectedSort", mySortPriority.toString())
Coverage.statementStart(5286)
        res.addContent(childrenToXML())
Coverage.statementStart(5287)
        return res
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5288)
        if (other !is LOPSort) {
Coverage.ifStart(5289)
            return false
        }
Coverage.statementStart(5290)
        if (asc != other.asc) {
Coverage.ifStart(5291)
            return false
        }
Coverage.statementStart(5292)
        if (by != other.by) {
Coverage.ifStart(5293)
            return false
        }
Coverage.statementStart(5294)
        for (i in children.indices) {
Coverage.forLoopStart(5295)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5296)
                return false
            }
Coverage.statementStart(5297)
        }
Coverage.statementStart(5298)
        return true
    }
    override fun cloneOP() = LOPSort(query, asc, by, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5299)
        return children[0].getHistogram()
    }
}
