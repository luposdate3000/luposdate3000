package lupos.s04logicalOperators.noinput
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import kotlin.jvm.JvmField
class LOPModifyData(query: IQuery, @JvmField val type: EModifyType, @JvmField val data: MutableList<LOPTriple> = mutableListOf()) : LOPBase(query, EOperatorID.LOPModifyDataID, "LOPModifyData", arrayOf(), ESortPriority.PREVENT_ANY) {
    override /*suspend*/ fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPModifyData")
        res.addAttribute("type", "" + type)
        for (t in data) {
            res.addContent(t.toXMLElement())
        }
        return res
    }
    override fun equals(other: Any?): Boolean = other is LOPModifyData && type == other.type && data == other.data
    override fun cloneOP(): IOPBase = LOPModifyData(query, type, data)
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        res.count = 1
        return res
    }
}
