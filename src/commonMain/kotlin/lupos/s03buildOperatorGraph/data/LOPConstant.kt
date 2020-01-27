package lupos.s03buildOperatorGraph.data

import lupos.s03buildOperatorGraph.LOPBase

import lupos.s00misc.XMLElement


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
