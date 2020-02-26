package lupos.s04logicalOperators.noinput

import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class LOPTriple(val s: OPBase, val p: OPBase, val o: OPBase, val graph: String?, val graphVar: Boolean) : LOPBase() {
    override val operatorID = EOperatorID.LOPTripleID
    override val classname = "LOPTriple"
    override val children: Array<OPBase> = arrayOf()

    override fun getProvidedVariableNames(): List<String> {
        val res = (s.getRequiredVariableNames() + p.getRequiredVariableNames() + o.getRequiredVariableNames()).distinct()
        return res
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
        if (graphVar != other.graphVar)
            return false
        return true
    }
}
