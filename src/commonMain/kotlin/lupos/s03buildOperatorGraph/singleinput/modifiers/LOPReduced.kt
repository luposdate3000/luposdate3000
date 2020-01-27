package lupos.s03buildOperatorGraph.singleinput.modifiers

import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPPrefix
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPOffset
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPLimit
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPDistinct
import lupos.s03buildOperatorGraph.singleinput.LOPSingleInputBase
import lupos.s03buildOperatorGraph.OPBase

import lupos.s00misc.XMLElement


class LOPReduced() : LOPSingleInputBase() {
    constructor(child: OPBase) : this() {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPReduced")
        res.addContent(child.toXMLElement())
        return res
    }
}
