package lupos.s03buildOperatorGraph.multiinput

import lupos.s03buildOperatorGraph.singleinput.LOPSingleInputBase
import lupos.s03buildOperatorGraph.OPBase

import lupos.s00misc.XMLElement


class LOPJoin(first: OPBase, val second: OPBase, val optional: Boolean) : LOPSingleInputBase(first) {
    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames() + second.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames() + second.getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPJoin")
        res.addAttribute("optional", "" + optional)
        res.addContent(child.toXMLElement())
        res.addContent(second.toXMLElement())
        return res
    }
}
