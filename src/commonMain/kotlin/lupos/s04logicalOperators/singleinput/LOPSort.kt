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
        val res = XMLElement("LOPSort")
        res.addAttribute("by", by.name)
        if (asc) {
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
override fun getRequiredVariableNames()=listOf(by.name)
    override fun equals(other: Any?) = other is LOPSort && asc == other.asc && by == other.by && children[0] == other.children[0]
    override fun cloneOP() = LOPSort(query, asc, by, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
