package lupos.s04logicalOperators.singleinput.modifiers

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset


class LOPPrefix(val name: String, val iri: String) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(name: String, iri: String, child: OPBase) : this(name, iri) {
        this.children[0] = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

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
}
