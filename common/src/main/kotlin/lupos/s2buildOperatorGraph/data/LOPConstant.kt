package lupos.s2buildOperatorGraph.data

import lupos.misc.*
import lupos.s2buildOperatorGraph.LOPBase

class LOPConstant(var value: String) : LOPBase() {
    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName} '$value'\n"
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
