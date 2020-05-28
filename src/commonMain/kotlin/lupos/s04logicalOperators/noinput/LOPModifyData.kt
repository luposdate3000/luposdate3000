package lupos.s04logicalOperators.noinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPModifyData(query: Query, @JvmField val type: EModifyType, @JvmField val data: MutableList<LOPTriple> = mutableListOf<LOPTriple>()) : LOPBase(query, EOperatorID.LOPModifyDataID, "LOPModifyData", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun toXMLElement(): XMLElement {
Coverage.funStart(4490)
        val res = XMLElement("LOPModifyData")
Coverage.statementStart(4491)
        res.addAttribute("type", "" + type)
Coverage.statementStart(4492)
        for (t in data) {
Coverage.forLoopStart(4493)
            res.addContent(t.toXMLElement())
Coverage.statementStart(4494)
        }
Coverage.statementStart(4495)
        return res
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(4496)
        if (other !is LOPModifyData) {
Coverage.ifStart(4497)
            return false
        }
Coverage.statementStart(4498)
        return data == other.data
    }
    override fun cloneOP() = LOPModifyData(query, type, data)
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(4499)
        var res = HistogramResult()
Coverage.statementStart(4500)
        res.count = 1
Coverage.statementStart(4501)
        return res
    }
}
