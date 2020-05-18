package lupos.s04logicalOperators.singleinput.modifiers

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPReduced(query: Query, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPReducedID, "LOPReduced", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    var hadPushDown = false
    override fun equals(other: Any?): Boolean {
        if (other !is LOPReduced) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun cloneOP() = LOPReduced(query, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        var childHistogram = children[0].getHistogram()
        res.variableNames.addAll(childHistogram.variableNames)
        res.distinct.addAll(childHistogram.distinct)
        res.count = childHistogram.count
        return res
    }
}
