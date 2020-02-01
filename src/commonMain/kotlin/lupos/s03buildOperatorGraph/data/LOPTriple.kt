package lupos.s03buildOperatorGraph.data

import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.data.LOPConstant
import lupos.s03buildOperatorGraph.data.LOPExpression
import lupos.s03buildOperatorGraph.LOPBase
import lupos.s03buildOperatorGraph.OPBase


class LOPTriple(val s: OPBase, val p: OPBase, val o: OPBase,val graph:String) : LOPBase() {
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
