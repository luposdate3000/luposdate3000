package lupos.s04logicalOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPTriple(query: Query, s: AOPBase, p: AOPBase, o: AOPBase, @JvmField val graph: String, @JvmField val graphVar: Boolean) : LOPBase(query, EOperatorID.LOPTripleID, "LOPTriple", arrayOf<OPBase>(s, p, o)) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("graph", graph).addAttribute("graphVar", "" + graphVar)
    override fun getRequiredVariableNames() = listOf<String>()
    override fun getProvidedVariableNames(): List<String> {
        var res = mutableListOf<String>()
        for (c in children)
            res.addAll(c.getRequiredVariableNames())
        return res.distinct()
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {}
    override fun equals(other: Any?): Boolean {
        if (other !is LOPTriple)
            return false
        if (graph != other.graph)
            return false
        if (graphVar != other.graphVar)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPTriple(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase, children[2].cloneOP() as AOPBase, graph, graphVar)
}
