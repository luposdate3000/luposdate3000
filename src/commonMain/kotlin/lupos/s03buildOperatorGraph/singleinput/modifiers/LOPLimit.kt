package lupos.s03buildOperatorGraph.singleinput.modifiers

import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.singleinput.LOPSingleInputBase
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPDistinct


class LOPLimit(val limit: Int) : LOPSingleInputBase() {
    constructor(limit: Int, child: OPBase) : this(limit) {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPLimit")
        res.addAttribute("limit", "" + limit)
        res.addContent(child.toXMLElement())
        return res
    }
}
