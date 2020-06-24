package lupos.s04logicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPOffset(query: Query, @JvmField val offset: Int, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPOffsetID, "LOPOffset", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("offset", "" + offset)
    override fun equals(other: Any?) = other is LOPOffset && offset == other.offset && children[0] == other.children[0]
    override fun cloneOP() = LOPOffset(query, offset, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        var childHistogram = children[0].getHistogram()
        res.count = childHistogram.count - offset
        if (res.count < 0) {
            res.count = 0
            childHistogram.values.forEach { k, v ->
                res.values[k] = 0
            }
        } else {
            childHistogram.values.forEach { k, v ->
                if (v > childHistogram.count - offset) {
                    res.values[k] = childHistogram.count - offset
                } else {
                    res.values[k] = v
                }
            }
        }
        return res
    }
}
