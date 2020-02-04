package lupos.s04logicalOperators
import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.OperatorGraphVisitor
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase



class OPNothing() : LOPBase() {

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
