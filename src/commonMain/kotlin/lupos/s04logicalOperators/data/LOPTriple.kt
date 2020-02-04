package lupos.s04logicalOperators.data
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.data.LOPConstant
import lupos.s04logicalOperators.data.LOPExpression
import lupos.s04logicalOperators.data.LOPGraphOperation
import lupos.s04logicalOperators.data.LOPInsertData
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase



class LOPTriple(val s: OPBase, val p: OPBase, val o: OPBase, val graph: String) : LOPBase() {
    override fun getProvidedVariableNames(): List<String> {
        return s.getProvidedVariableNames() + p.getProvidedVariableNames() + o.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return s.getProvidedVariableNames() + p.getProvidedVariableNames() + o.getProvidedVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("LOPTriple")
    }
}
