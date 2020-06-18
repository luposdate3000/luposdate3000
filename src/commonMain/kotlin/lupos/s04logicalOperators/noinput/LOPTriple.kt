package lupos.s04logicalOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s15tripleStoreDistributed.DistributedTripleStore

class LOPTriple(query: Query, s: AOPBase, p: AOPBase, o: AOPBase, @JvmField val graph: String, @JvmField val graphVar: Boolean) : LOPBase(query, EOperatorID.LOPTripleID, "LOPTriple", arrayOf<OPBase>(s, p, o), ESortPriority.ANY_PROVIDED_VARIABLE) {
    override fun toSparql(): String {
        if (graph == PersistentStoreLocal.defaultGraphName) {
            return children[0].toSparql() + " " + children[1].toSparql() + " " + children[2].toSparql() + "."
        }
        return "GRAPH <$graph> {" + children[0].toSparql() + " " + children[1].toSparql() + " " + children[2].toSparql() + "}."
    }

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
    override fun equals(other: Any?) = other is LOPTriple && graph == other.graph && graphVar == other.graphVar && children[0] == other.children[0] && children[1] == other.children[1] && children[2] == other.children[2]
    override fun cloneOP() = LOPTriple(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase, children[2].cloneOP() as AOPBase, graph, graphVar)

    companion object {
        fun getIndex(children: Array<OPBase>, sortPriority: List<String>): EIndexPattern {
            /*
             * always prefer P over S over O to access the best compressed triple store, which should be the fastest
             */
            var resString = ""
            val c0 = children[0]
            val c1 = children[1]
            val c2 = children[2]
            //constants first
            if (c1 is AOPConstant) {
                resString += "P"
            }
            if (c0 is AOPConstant) {
                resString += "S"
            }
            if (c2 is AOPConstant) {
                resString += "O"
            }
            if (resString.length > 0 && resString.length < 3) {
                resString += "_"
            }
            //than sort order
            for (s in sortPriority) {
                SanityCheck.check { s != "_" }
                if (c0 is AOPVariable && c0.name == s) {
                    resString += "S"
                } else if (c1 is AOPVariable && c1.name == s) {
                    resString += "P"
                } else if (c2 is AOPVariable && c2.name == s) {
                    resString += "O"
                }
            }
            //than columns which are used
            if (c1 is AOPVariable && c1.name != "_" && !resString.contains("P")) {
                resString += "P"
            }
            if (c0 is AOPVariable && c0.name != "_" && !resString.contains("S")) {
                resString += "S"
            }
            if (c2 is AOPVariable && c2.name != "_" && !resString.contains("O")) {
                resString += "O"
            }
            //at last fill the remaining columns
            if (!resString.contains("P")) {
                resString += "P"
            }
            if (!resString.contains("S")) {
                resString += "S"
            }
            if (!resString.contains("O")) {
                resString += "O"
            }
            SanityCheck.check({ resString.length == 3 || (resString.length == 4 && resString.contains("_")) }, { "${resString} ${children.map { it.toSparql() }} $sortPriority" })
            return EIndexPattern.valueOf(resString)
        }
    }

    override fun calculateHistogram(): HistogramResult {
        SanityCheck { !graphVar }
        var res = HistogramResult()
        res.count = -1
        var store = DistributedTripleStore.getNamedGraph(query, graph)
        for (v in getProvidedVariableNames()) {
            val params = Array(3) {
                var t = children[it]
                if (t is AOPVariable && t.name != v) {
                    t = AOPVariable(query, "_")
                }
                /*return*/t as AOPBase
            }
            var idx = getIndex(params.map { it as OPBase }.toTypedArray(), listOf<String>())
            var childHistogram = store.getHistogram(params, idx)
            SanityCheck.check ({ res.count == -1 || res.count == childHistogram.first },{"expected ${childHistogram.first} but found ${res.count}"})
            res.count = childHistogram.first
            res.values[v] = childHistogram.second
        }
        if (res.count == -1) {
            res.count = 0
        }
        SanityCheck.check { res.count != -1 }
        return res
    }
}
