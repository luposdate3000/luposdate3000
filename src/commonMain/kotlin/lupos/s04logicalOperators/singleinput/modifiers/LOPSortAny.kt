package lupos.s04logicalOperators.singleinput.modifiers

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SortHelper
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPSortAny(query: Query, val possibleSortOrder: List<SortHelper>, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPSortAnyID, "LOPSortAny", arrayOf(child), ESortPriority.SORT) {
    override fun equals(other: Any?) = other is LOPSortAny && possibleSortOrder == other.possibleSortOrder && children[0] == other.children[0]
    override fun cloneOP() = LOPSortAny(query, possibleSortOrder, children[0].cloneOP())
    suspend override fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
