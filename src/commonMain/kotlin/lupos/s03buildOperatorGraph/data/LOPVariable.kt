package lupos.s2buildOperatorGraph.data
import lupos.s00misc.XMLElement


import lupos.s2buildOperatorGraph.LOPBase

class LOPVariable(var name: String) : LOPBase() {
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(name)
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>(name)
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("LOPVariable")
    }
}
