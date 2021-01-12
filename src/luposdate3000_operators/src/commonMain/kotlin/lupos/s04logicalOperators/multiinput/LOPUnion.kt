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
import lupos.s04logicalOperators.singleinput.LOPProjection
public class LOPUnion public constructor(query: IQuery, first: IOPBase, second: IOPBase) : LOPBase(query, EOperatorID.LOPUnionID, "LOPUnion", arrayOf(first, second), ESortPriority.UNION) {
    override fun equals(other: Any?): Boolean = other is LOPUnion && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = LOPUnion(query, children[0].cloneOP(), children[1].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        val childHistogram0 = children[0].getHistogram()
        val childHistogram1 = children[1].getHistogram()
        res.count = childHistogram0.count + childHistogram1.count
        val providedA = children[0].getProvidedVariableNames()
        val providedB = children[1].getProvidedVariableNames()
        val provided = providedA.intersect(providedB)
        var p = getProvidedVariableNames()
        if (provided.containsAll(p)) {
            children[0] = LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), children[0])
            children[1] = LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), children[1])
            p = getProvidedVariableNames()
            SanityCheck.check { provided.containsAll(p) }
        }
        try {
            for (v in p) {
                val a = childHistogram0.values[v] ?: 1
                val b = childHistogram1.values[v] ?: 1
                res.values[v] = a + b
            }
        } catch (e: Throwable) {
            SanityCheck.println { "TODO exception 10" }
            e.printStackTrace()
            throw BugException(classname, "calculateHistogram column missing")
        }
        return res
    }
}
