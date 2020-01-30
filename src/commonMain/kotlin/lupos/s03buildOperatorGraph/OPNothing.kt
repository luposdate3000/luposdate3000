package lupos.s03buildOperatorGraph

import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.LOPBase
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.OperatorGraphVisitor


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
