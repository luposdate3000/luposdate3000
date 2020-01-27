package lupos.s2buildOperatorGraph.singleinput

import lupos.s00misc.XMLElement


import lupos.s2buildOperatorGraph.OPBase

class LOPMakeBooleanResult() : LOPSingleInputBase() {
    constructor(child: OPBase) : this() {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return listOf("?boolean")
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPMakeBooleanResult")
        res.addContent(child.toXMLElement())
        return res
    }
}
