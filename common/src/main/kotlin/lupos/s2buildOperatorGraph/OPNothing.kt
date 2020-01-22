package lupos.s2buildOperatorGraph

import lupos.s2buildOperatorGraph.LOPBase
import lupos.s2buildOperatorGraph.OPBase
import lupos.misc.*

class OPNothing() : OPBase() {

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}"
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
