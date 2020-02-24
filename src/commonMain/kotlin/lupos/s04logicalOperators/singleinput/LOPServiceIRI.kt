package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class LOPServiceIRI(val name: String, val silent: Boolean, child: OPBase) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(child)

    override fun getProvidedVariableNames() = children[0].getProvidedVariableNames().distinct()

    override fun getRequiredVariableNames(): List<String> {
        return getProvidedVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPService")
        res.addAttribute("name", name)
        res.addAttribute("silent", "" + silent)
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPServiceIRI)
            return false
        if (name != other.name)
            return false
        if (silent != other.silent)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
