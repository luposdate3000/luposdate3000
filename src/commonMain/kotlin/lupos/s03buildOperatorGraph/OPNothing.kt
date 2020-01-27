package lupos.s03buildOperatorGraph

import lupos.s03buildOperatorGraph.OperatorGraphVisitor
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.LOPBase

import lupos.s00misc.XMLElement


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
