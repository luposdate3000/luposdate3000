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
class LOPLimit(query: Query, @JvmField val limit: Int, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPLimitID, "LOPLimit", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("limit", "" + limit)
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5316)
        if (other !is LOPLimit) {
Coverage.ifStart(5317)
            return false
        }
Coverage.statementStart(5318)
        if (limit != other.limit) {
Coverage.ifStart(5319)
            return false
        }
Coverage.statementStart(5320)
        for (i in children.indices) {
Coverage.forLoopStart(5321)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5322)
                return false
            }
Coverage.statementStart(5323)
        }
Coverage.statementStart(5324)
        return true
    }
    override fun cloneOP() = LOPLimit(query, limit, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5325)
        var res = HistogramResult()
Coverage.statementStart(5326)
        var childHistogram = children[0].getHistogram()
Coverage.statementStart(5327)
        res.count = childHistogram.count
Coverage.statementStart(5328)
        if (res.count > limit) {
Coverage.ifStart(5329)
            res.count = limit
Coverage.statementStart(5330)
            var scale = limit.toDouble() / res.count.toDouble()
Coverage.statementStart(5331)
            childHistogram.values.forEach { k, v ->
Coverage.statementStart(5332)
                res.values[k] = (v.toDouble() * scale).toInt()
Coverage.statementStart(5333)
            }
Coverage.statementStart(5334)
        } else {
Coverage.ifStart(5335)
            childHistogram.values.forEach { k, v ->
Coverage.statementStart(5336)
                res.values[k] = v
Coverage.statementStart(5337)
            }
Coverage.statementStart(5338)
        }
Coverage.statementStart(5339)
        return res
    }
}
