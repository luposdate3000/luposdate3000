package lupos.s03buildOperatorGraph.data

import lupos.s03buildOperatorGraph.LOPBase
import lupos.s03buildOperatorGraph.data.LOPValues
import lupos.s03buildOperatorGraph.data.LOPTriple
import lupos.s03buildOperatorGraph.data.LOPExpression

import lupos.s03buildOperatorGraph.data.LOPConstant
import lupos.s00misc.XMLElement


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
