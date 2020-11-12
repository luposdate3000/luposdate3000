package lupos.s04logicalOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPMakeBooleanResult(query: IQuery, child: IOPBase) : LOPBase(query, EOperatorID.LOPMakeBooleanResultID, "LOPMakeBooleanResult", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf("?boolean")
    }

    override fun equals(other: Any?) = other is LOPMakeBooleanResult && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPMakeBooleanResult(query, children[0].cloneOP())
    override suspend fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        res.values["?boolean"] = 1
        res.count = 1
        return res
    }
}
