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
    override fun equals(other: Any?): Boolean {
        if (other !is LOPSortAny) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun cloneOP() = LOPSortAny(query, possibleSortOrder, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        var childHistogram = children[0].getHistogram()
        res.variableNames.addAll(childHistogram.variableNames)
        res.distinct.addAll(childHistogram.distinct)
        res.count = childHistogram.count
        return res
    }
}
