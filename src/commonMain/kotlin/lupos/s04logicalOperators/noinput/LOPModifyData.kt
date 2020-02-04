package lupos.s04logicalOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.LOPConstant
import lupos.s04logicalOperators.noinput.LOPExpression
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.OPBase

enum class ModifyDataType{
INSERT,DELETE
}

class LOPModifyData(val type:ModifyDataType) : LOPBase() {
    val data = mutableListOf<List<String>>()
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPModifyData")
	res.addAttribute("type",""+type)
        for (t in data) {
            res.addContent(XMLElement("RawTriple").addAttribute("s", t[0]).addAttribute("p", t[1]).addAttribute("o", t[2]).addAttribute("graph", t[3]))
        }
        return res
    }
}
