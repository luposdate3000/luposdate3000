package lupos.s04logicalOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class LOPModifyData(query: Query, @JvmField val type: EModifyType, @JvmField val data: MutableList<LOPTriple> = mutableListOf<LOPTriple>()) : LOPBase(query, EOperatorID.LOPModifyDataID, "LOPModifyData", arrayOf()) {
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

    override fun cloneOP() = LOPModifyData(query, type, data)
}
