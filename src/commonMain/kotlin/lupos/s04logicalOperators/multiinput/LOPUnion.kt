package lupos.s04logicalOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPProjection
class LOPUnion(query: Query, first: OPBase, second: OPBase) : LOPBase(query, EOperatorID.LOPUnionID, "LOPUnion", arrayOf(first, second), ESortPriority.UNION) {
    override fun equals(other: Any?): Boolean {
Coverage.funStart(4447)
        if (other !is LOPUnion) {
Coverage.ifStart(4448)
            return false
        }
Coverage.statementStart(4449)
        for (i in children.indices) {
Coverage.forLoopStart(4450)
            if (children[i] != other.children[i]) {
Coverage.ifStart(4451)
                return false
            }
Coverage.statementStart(4452)
        }
Coverage.statementStart(4453)
        return true
    }
    override fun cloneOP() = LOPUnion(query, children[0].cloneOP(), children[1].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(4454)
        var res = HistogramResult()
Coverage.statementStart(4455)
        var childHistogram0 = children[0].getHistogram()
Coverage.statementStart(4456)
        var childHistogram1 = children[1].getHistogram()
Coverage.statementStart(4457)
        res.count = childHistogram0.count + childHistogram1.count
Coverage.statementStart(4458)
        var providedA = children[0].getProvidedVariableNames()
Coverage.statementStart(4459)
        var providedB = children[1].getProvidedVariableNames()
Coverage.statementStart(4460)
        var provided = providedA.intersect(providedB)
Coverage.statementStart(4461)
        var p = getProvidedVariableNames()
Coverage.statementStart(4462)
        if (provided.containsAll(p)) {
Coverage.ifStart(4463)
            children[0] = LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), children[0])
Coverage.statementStart(4464)
            children[1] = LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), children[1])
Coverage.statementStart(4465)
            p = getProvidedVariableNames()
Coverage.statementStart(4466)
            SanityCheck.check { provided.containsAll(p) }
Coverage.statementStart(4467)
        }
Coverage.statementStart(4468)
        for (v in p) {
Coverage.forLoopStart(4469)
            res.values[v] = childHistogram0.values[v]!! + childHistogram1.values[v]!!
Coverage.statementStart(4470)
        }
Coverage.statementStart(4471)
        return res
    }
}
