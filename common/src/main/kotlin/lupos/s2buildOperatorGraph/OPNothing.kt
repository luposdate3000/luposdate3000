package lupos.s2buildOperatorGraph

import lupos.misc.XMLElement

import lupos.s2buildOperatorGraph.LOPBase
import lupos.s2buildOperatorGraph.OPBase

class OPNothing() : OPBase() {

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("OPNothing")
    }
}
