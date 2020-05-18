package lupos.s04logicalOperators.multiinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPUnion(query: Query, first: OPBase, second: OPBase) : LOPBase(query, EOperatorID.LOPUnionID, "LOPUnion", arrayOf(first, second), ESortPriority.UNION) {
    override fun equals(other: Any?): Boolean {
        if (other !is LOPUnion) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun cloneOP() = LOPUnion(query, children[0].cloneOP(), children[1].cloneOP())
    override fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        var childHistogram0 = children[0].getHistogram()
        var childHistogram1 = children[1].getHistogram()
        res.count = childHistogram0.count + childHistogram1.count
        for (v in getProvidedVariableNames()) {
            res.values[v] = childHistogram0.values[v]!! + childHistogram1.values[v]!!
        }
        return res
    }
}
