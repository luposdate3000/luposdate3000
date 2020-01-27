package lupos.s03buildOperatorGraph.data
import lupos.s00misc.XMLElement


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
