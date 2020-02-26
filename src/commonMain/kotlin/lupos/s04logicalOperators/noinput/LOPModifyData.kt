package lupos.s04logicalOperators.noinput
import lupos.s00misc.EOperatorID

import lupos.s00misc.EModifyType
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class LOPModifyData(val type: EModifyType) : LOPBase() {
    override val operatorID=EOperatorID.LOPModifyDataID
    override val classname = "LOPModifyData"
    override val children: Array<OPBase> = arrayOf()
    val data = mutableListOf<List<Pair<String, Boolean>>>()

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPModifyData")
        res.addAttribute("type", "" + type)
        for (t in data) {
            res.addContent(XMLElement("RawTriple")
                    .addAttribute("sv", t[0].first)
                    .addAttribute("pv", t[1].first)
                    .addAttribute("ov", t[2].first)
                    .addAttribute("sconst", "" + t[0].second)
                    .addAttribute("pconst", "" + t[1].second)
                    .addAttribute("oconst", "" + t[2].second)
                    .addAttribute("graph", t[3].first))
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPModifyData)
            return false
        return data == other.data
    }
}
