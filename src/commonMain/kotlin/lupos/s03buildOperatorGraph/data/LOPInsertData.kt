package lupos.s03buildOperatorGraph.data

import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.data.LOPConstant
import lupos.s03buildOperatorGraph.data.LOPExpression
import lupos.s03buildOperatorGraph.LOPBase
import lupos.s03buildOperatorGraph.OPBase


class LOPInsertData() : LOPBase() {
    val data = mutableListOf<LOPTriple>()
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("LOPInsertData")
    }
}
