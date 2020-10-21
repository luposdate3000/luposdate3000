package lupos.s04logicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPLimit(query: Query, @JvmField val limit: Int, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPLimitID, "LOPLimit", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override suspend fun toXMLElement() = super.toXMLElement().addAttribute("limit", "" + limit)
    override fun equals(other: Any?) = other is LOPLimit && limit == other.limit && children[0] == other.children[0]
    override fun cloneOP() = LOPLimit(query, limit, children[0].cloneOP())
    suspend override fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        var childHistogram = children[0].getHistogram()
        res.count = childHistogram.count
        if (res.count > limit) {
            res.count = limit
            var scale = limit.toDouble() / res.count.toDouble()
for((k,v) in            childHistogram.values){
                res.values[k] = (v.toDouble() * scale).toInt()
            }
        } else {
for((k,v) in             childHistogram.values){
                res.values[k] = v
            }
        }
        return res
    }
}
