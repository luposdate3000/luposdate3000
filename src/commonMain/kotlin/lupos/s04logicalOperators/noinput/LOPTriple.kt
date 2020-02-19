package lupos.s04logicalOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class LOPTriple(val s: OPBase, val p: OPBase, val o: OPBase, val graph: String) : LOPBase() {
    override val children: Array<OPBase> = arrayOf()
    override fun getProvidedVariableNames(): List<String> {
        return s.getProvidedVariableNames() + p.getProvidedVariableNames() + o.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return s.getProvidedVariableNames() + p.getProvidedVariableNames() + o.getProvidedVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("LOPTriple")
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPTriple)
            return false
        if (graph != other.graph)
            return false
        if (!s.equals(other.s))
            return false
        if (!p.equals(other.p))
            return false
        if (!o.equals(other.o))
            return false
        return true
    }
}
