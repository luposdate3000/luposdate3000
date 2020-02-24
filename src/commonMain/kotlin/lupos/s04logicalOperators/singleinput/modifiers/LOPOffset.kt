package lupos.s04logicalOperators.singleinput.modifiers

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPOffset(val offset: Int) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(offset: Int, child: OPBase) : this(offset) {
        this.children[0] = child
    }

    override fun getProvidedVariableNames() = children[0].getProvidedVariableNames().distinct()

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPOffset")
        res.addAttribute("offset", "" + offset)
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPOffset)
            return false
        if (offset != other.offset)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
