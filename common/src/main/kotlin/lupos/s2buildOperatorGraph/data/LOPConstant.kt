package lupos.s2buildOperatorGraph.data

import lupos.misc.XMLElement

import lupos.s2buildOperatorGraph.LOPBase

class LOPConstant(var value: String) : LOPBase() {
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("LOPConstant")
    }
}
