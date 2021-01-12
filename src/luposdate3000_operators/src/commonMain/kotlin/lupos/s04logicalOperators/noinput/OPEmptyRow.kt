package lupos.s04logicalOperators.noinput
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
public class OPEmptyRow public constructor(query: IQuery) : LOPBase(query, EOperatorIDExt.OPEmptyRowID, "OPEmptyRow", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
    override fun toSparql(): String = "{}"
    override fun equals(other: Any?): Boolean = other is OPEmptyRow
    override fun cloneOP(): IOPBase = this
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        res.count = 1
        return res
    }
}
