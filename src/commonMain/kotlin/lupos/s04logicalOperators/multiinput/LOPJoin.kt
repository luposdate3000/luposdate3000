package lupos.s04logicalOperators.multiinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPJoin : LOPBase {
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    val optional: Boolean

    constructor(first: OPBase, second: OPBase, optional: Boolean) : super() {
        children[0] = first
        children[1] = second
        this.optional = optional
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames() + children[1].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return getProvidedVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPJoin")
        res.addAttribute("optional", "" + optional)
        res.addContent(childrenToXML())
        return res
    }
}
