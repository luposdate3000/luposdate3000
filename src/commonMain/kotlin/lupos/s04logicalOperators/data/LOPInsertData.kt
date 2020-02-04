package lupos.s04logicalOperators.data
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.data.LOPConstant
import lupos.s04logicalOperators.data.LOPExpression
import lupos.s04logicalOperators.data.LOPGraphOperation
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase



class LOPInsertData() : LOPBase() {
    val data = mutableListOf<List<String>>()
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPInsertData")
        for (t in data) {
            res.addContent(XMLElement("RawTriple").addAttribute("s", t[0]).addAttribute("p", t[1]).addAttribute("o", t[2]).addAttribute("graph", t[3]))
        }
        return res
    }
}
