package lupos.s04logicalOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPMakeBooleanResult(query: Query, child: OPBase) : LOPBase(query, EOperatorID.LOPMakeBooleanResultID, "LOPMakeBooleanResult", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun getProvidedVariableNames(): List<String> {
Coverage.funStart(5171)
        return mutableListOf("?boolean")
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5172)
        if (other !is LOPMakeBooleanResult) {
Coverage.ifStart(5173)
            return false
        }
Coverage.statementStart(5174)
        for (i in children.indices) {
Coverage.forLoopStart(5175)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5176)
                return false
            }
Coverage.statementStart(5177)
        }
Coverage.statementStart(5178)
        return true
    }
    override fun cloneOP() = LOPMakeBooleanResult(query, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5179)
        var res = HistogramResult()
Coverage.statementStart(5180)
        res.values["?boolean"] = 1
Coverage.statementStart(5181)
        res.count = 1
Coverage.statementStart(5182)
        return res
    }
}
