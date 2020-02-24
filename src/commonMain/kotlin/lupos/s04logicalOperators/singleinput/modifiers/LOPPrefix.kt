package lupos.s04logicalOperators.singleinput.modifiers

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPPrefix(val name: String, val iri: String) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(name: String, iri: String, child: OPBase) : this(name, iri) {
        this.children[0] = child
    }

    override fun getProvidedVariableNames()=children[0].getProvidedVariableNames().distinct()

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPPrefix")
        res.addAttribute("name", name)
        res.addAttribute("iri", iri)
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPPrefix)
            return false
        if (name != other.name)
            return false
        if (iri != other.iri)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
