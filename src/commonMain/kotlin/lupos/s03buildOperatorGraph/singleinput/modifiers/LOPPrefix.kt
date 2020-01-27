package lupos.s03buildOperatorGraph.singleinput.modifiers

import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPOffset
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPLimit
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPDistinct
import lupos.s03buildOperatorGraph.singleinput.LOPSingleInputBase
import lupos.s03buildOperatorGraph.OPBase

import lupos.s00misc.XMLElement


class LOPPrefix(val name: String, val iri: String) : LOPSingleInputBase() {
    constructor(name: String, iri: String, child: OPBase) : this(name, iri) {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPPrefix")
        res.addAttribute("name", name)
        res.addAttribute("iri", iri)
        res.addContent(child.toXMLElement())
        return res
    }
}
