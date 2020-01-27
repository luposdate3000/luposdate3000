package lupos.s2buildOperatorGraph.singleinput.modifiers
import lupos.s00misc.XMLElement


import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.singleinput.LOPSingleInputBase

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
