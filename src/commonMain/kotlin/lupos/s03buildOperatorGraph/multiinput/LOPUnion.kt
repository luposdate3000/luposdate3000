package lupos.s2buildOperatorGraph.multiinput

import lupos.s00misc.XMLElement


import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.singleinput.LOPSingleInputBase

class LOPUnion(first: OPBase, val second: OPBase) : LOPSingleInputBase(first) {
    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames() + second.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames() + second.getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPUnion")
        res.addContent(child.toXMLElement())
        res.addContent(second.toXMLElement())
        return res
    }
}
