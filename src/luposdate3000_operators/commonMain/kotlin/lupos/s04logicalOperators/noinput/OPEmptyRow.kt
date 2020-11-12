package lupos.s04logicalOperators.noinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class OPEmptyRow(query: IQuery) : LOPBase(query, EOperatorID.OPEmptyRowID, "OPEmptyRow", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun toSparql() = "{}"
    override fun equals(other: Any?) = other is OPEmptyRow
    override fun cloneOP(): IOPBase = this
    override suspend fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        res.count = 1
        return res
    }
}
