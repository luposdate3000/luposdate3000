package lupos.s04logicalOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPTriple(query: Query, s: AOPBase, p: AOPBase, o: AOPBase, @JvmField val graph: String, @JvmField val graphVar: Boolean) : LOPBase(query, EOperatorID.LOPTripleID, "LOPTriple", arrayOf<OPBase>(s, p, o), ESortPriority.ANY_PROVIDED_VARIABLE) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("graph", graph).addAttribute("graphVar", "" + graphVar)
    override fun getRequiredVariableNames() = listOf<String>()
    override fun getProvidedVariableNames(): List<String> {
        var res = mutableListOf<String>()
        for (c in children) {
            res.addAll(c.getRequiredVariableNames())
        }
        res.remove("_")
        res.remove("_")
        res.remove("_")
        return res.distinct()
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {}
    override fun equals(other: Any?): Boolean {
        if (other !is LOPTriple) {
            return false
        }
        if (graph != other.graph) {
            return false
        }
        if (graphVar != other.graphVar) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun cloneOP() = LOPTriple(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase, children[2].cloneOP() as AOPBase, graph, graphVar)

    companion object {
        fun getIntex(children: Array<OPBase>, sortPriority: List<String>): EIndexPattern {
            var res = EIndexPattern.SPO
            var count = 0
            for (n in children) {
                if (n is AOPConstant) {
                    count++
                }
            }
            when (count) {
                1 -> {
                    if (children[0] is AOPConstant) {
                        if (sortPriority.size == 0 || (children[1] as AOPVariable).name == sortPriority[0]) {
                            res = EIndexPattern.S_0
                        } else {
                            res = EIndexPattern.S_1
                        }
                    } else if (children[1] is AOPConstant) {
                        if (sortPriority.size == 0 || (children[0] as AOPVariable).name == sortPriority[0]) {
                            res = EIndexPattern.P_0
                        } else {
                            res = EIndexPattern.P_1
                        }
                    } else {
                        require(children[2] is AOPConstant)
                        if (sortPriority.size == 0 || (children[0] as AOPVariable).name == sortPriority[0]) {
                            res = EIndexPattern.O_0
                        } else {
                            res = EIndexPattern.O_1
                        }
                    }
                }
                2 -> {
                    if (children[0] !is AOPConstant) {
                        res = EIndexPattern.PO
                    } else if (children[1] !is AOPConstant) {
                        res = EIndexPattern.SO
                    } else {
                        require(children[2] !is AOPConstant)
                        res = EIndexPattern.SP
                    }
                }
                else -> {
                    require(count == 3 || count == 0)
                    res = EIndexPattern.SPO
                }
            }
            return res
        }
    }
}
