package lupos.s04logicalOperators.multiinput

import lupos.s00misc.BugException
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPProjection

class LOPUnion(query: IQuery, first: IOPBase, second: IOPBase) : LOPBase(query, EOperatorID.LOPUnionID, "LOPUnion", arrayOf(first, second), ESortPriority.UNION) {
    override fun equals(other: Any?) = other is LOPUnion && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = LOPUnion(query, children[0].cloneOP(), children[1].cloneOP())
    suspend override fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        var childHistogram0 = children[0].getHistogram()
        var childHistogram1 = children[1].getHistogram()
        res.count = childHistogram0.count + childHistogram1.count
        var providedA = children[0].getProvidedVariableNames()
        var providedB = children[1].getProvidedVariableNames()
        var provided = providedA.intersect(providedB)
        var p = getProvidedVariableNames()
        if (provided.containsAll(p)) {
            children[0] = LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), children[0])
            children[1] = LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), children[1])
            p = getProvidedVariableNames()
            SanityCheck.check { provided.containsAll(p) }
        }
        try {
            for (v in p) {
                res.values[v] = childHistogram0.values[v]!! + childHistogram1.values[v]!!
            }
        } catch (e: Throwable) {
            SanityCheck.println({ "TODO exception 10" })
            e.printStackTrace()
            throw BugException(classname, "calculateHistogram column missing")
        }
        return res
    }
}
