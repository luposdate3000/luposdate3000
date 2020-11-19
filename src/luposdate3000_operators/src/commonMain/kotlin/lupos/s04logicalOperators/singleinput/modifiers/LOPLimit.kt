package lupos.s04logicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow

class LOPLimit(query: IQuery, @JvmField val limit: Int, child: IOPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPLimitID, "LOPLimit", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override /*suspend*/ fun toXMLElement(): XMLElement = super.toXMLElement().addAttribute("limit", "" + limit)
    override fun equals(other: Any?): Boolean = other is LOPLimit && limit == other.limit && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPLimit(query, limit, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        val childHistogram = children[0].getHistogram()
        res.count = childHistogram.count
        if (res.count > limit) {
            res.count = limit
            val scale = limit.toDouble() / res.count.toDouble()
            for ((k, v) in childHistogram.values) {
                res.values[k] = (v.toDouble() * scale).toInt()
            }
        } else {
            for ((k, v) in childHistogram.values) {
                res.values[k] = v
            }
        }
        return res
    }
}
