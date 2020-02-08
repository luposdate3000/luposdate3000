package lupos.s04logicalOperators.singleinput.modifiers
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.LOPBase

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit


class LOPOffset(val offset: Int) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(offset: Int, child: OPBase) : this(offset) {
        this.children[0] = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPOffset")
        res.addAttribute("offset", "" + offset)
        res.addContent(childrenToXML())
        return res
    }
}
