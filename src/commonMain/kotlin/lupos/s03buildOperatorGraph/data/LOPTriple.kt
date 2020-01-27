package lupos.s2buildOperatorGraph.data
import lupos.s03buildOperatorGraph.data.LOPConstant
import lupos.s00misc.XMLElement


import lupos.s2buildOperatorGraph.LOPBase
import lupos.s2buildOperatorGraph.OPBase

class LOPTriple(val s: OPBase, val p: OPBase, val o: OPBase) : LOPBase() {
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
