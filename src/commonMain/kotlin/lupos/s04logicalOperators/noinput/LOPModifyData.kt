package lupos.s04logicalOperators.noinput

import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class LOPModifyData(val type: EModifyType, val data: MutableList<LOPTriple> = mutableListOf<LOPTriple>()) : LOPBase() {
    override val operatorID = EOperatorID.LOPModifyDataID
    override val classname = "LOPModifyData"
    override val children: Array<OPBase> = arrayOf()

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPModifyData")
        res.addAttribute("type", "" + type)
        for (t in data)
            res.addContent(t.toXMLElement())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPModifyData)
            return false
        return data == other.data
    }

    override fun cloneOP() = LOPModifyData(type, data)
}
