package lupos.s04logicalOperators.multiinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase


class LOPJoin(first: OPBase, second: OPBase, val optional: Boolean) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(first, second)
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
