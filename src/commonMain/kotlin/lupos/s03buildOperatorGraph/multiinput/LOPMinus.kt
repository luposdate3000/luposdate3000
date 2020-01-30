package lupos.s03buildOperatorGraph.multiinput

import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.multiinput.LOPJoin
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.singleinput.LOPSingleInputBase


class LOPMinus(first: OPBase, var second: OPBase) : LOPSingleInputBase(first) {
    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames() + second.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames() + second.getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPMinus")
        res.addContent(child.toXMLElement())
        res.addContent(second.toXMLElement())
        return res
    }
}
