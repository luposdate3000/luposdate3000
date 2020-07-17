package lupos.s04logicalOperators.singleinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPFilter(query: Query, filter: AOPBase, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPFilterID, "LOPFilter", arrayOf(child, filter), ESortPriority.SAME_AS_CHILD) {
var dontSplitFilter=false
    override fun childrenToVerifyCount() = 1
    override fun getProvidedVariableNames(): List<String> = children[0].getProvidedVariableNames().distinct()
    override fun getRequiredVariableNames(): List<String> = children[1].getRequiredVariableNamesRecoursive()
    override fun equals(other: Any?) = other is LOPFilter && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP() = LOPFilter(query, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
