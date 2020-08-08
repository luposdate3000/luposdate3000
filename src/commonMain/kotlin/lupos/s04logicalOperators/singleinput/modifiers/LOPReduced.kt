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
    override fun equals(other: Any?) = other is LOPReduced && children[0] == other.children[0]
    override fun cloneOP() = LOPReduced(query, children[0].cloneOP())
    suspend override fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
