package lupos.s04logicalOperators.singleinput.modifiers
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPOffset(query: Query, @JvmField val offset: Int, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPOffsetID, "LOPOffset", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("offset", "" + offset)
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5340)
        if (other !is LOPOffset) {
Coverage.ifStart(5341)
            return false
        }
Coverage.statementStart(5342)
        if (offset != other.offset) {
Coverage.ifStart(5343)
            return false
        }
Coverage.statementStart(5344)
        for (i in children.indices) {
Coverage.forLoopStart(5345)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5346)
                return false
            }
Coverage.statementStart(5347)
        }
Coverage.statementStart(5348)
        return true
    }
    override fun cloneOP() = LOPOffset(query, offset, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5349)
        var res = HistogramResult()
Coverage.statementStart(5350)
        var childHistogram = children[0].getHistogram()
Coverage.statementStart(5351)
        res.count = childHistogram.count - offset
Coverage.statementStart(5352)
        if (res.count < 0) {
Coverage.ifStart(5353)
            res.count = 0
Coverage.statementStart(5354)
            childHistogram.values.forEach { k, v ->
Coverage.statementStart(5355)
                res.values[k] = 0
Coverage.statementStart(5356)
            }
Coverage.statementStart(5357)
        } else {
Coverage.ifStart(5358)
            childHistogram.values.forEach { k, v ->
Coverage.statementStart(5359)
                if (v > childHistogram.count - offset) {
Coverage.ifStart(5360)
                    res.values[k] = childHistogram.count - offset
Coverage.statementStart(5361)
                } else {
Coverage.ifStart(5362)
                    res.values[k] = v
Coverage.statementStart(5363)
                }
Coverage.statementStart(5364)
            }
Coverage.statementStart(5365)
        }
Coverage.statementStart(5366)
        return res
    }
}
