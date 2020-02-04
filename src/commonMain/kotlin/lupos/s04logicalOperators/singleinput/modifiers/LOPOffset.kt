package lupos.s03buildOperatorGraph.singleinput.modifiers

import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.singleinput.LOPSingleInputBase
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPDistinct
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPLimit


class LOPOffset(val offset: Int) : LOPSingleInputBase() {
    constructor(offset: Int, child: OPBase) : this(offset) {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPOffset")
        res.addAttribute("offset", "" + offset)
        res.addContent(child.toXMLElement())
        return res
    }
}
