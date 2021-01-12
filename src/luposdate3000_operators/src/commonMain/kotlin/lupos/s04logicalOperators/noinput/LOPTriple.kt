package lupos.s04logicalOperators.noinput
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.GraphVarHistogramsNotImplementedException
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s05tripleStore.PersistentStoreLocalExt
import lupos.s15tripleStoreDistributed.distributedTripleStore
import kotlin.jvm.JvmField
public class LOPTriple public constructor(query: IQuery, s: IAOPBase, p: IAOPBase, o: IAOPBase, @JvmField public val graph: String, @JvmField public val graphVar: Boolean) : LOPBase(query, EOperatorIDExt.LOPTripleID, "LOPTriple", arrayOf(s, p, o), ESortPriorityExt.ANY_PROVIDED_VARIABLE) {
    override fun toSparql(): String {
        if (graph == PersistentStoreLocalExt.defaultGraphName) {
            return children[0].toSparql() + " " + children[1].toSparql() + " " + children[2].toSparql() + "."
        }
        return "GRAPH <$graph> {" + children[0].toSparql() + " " + children[1].toSparql() + " " + children[2].toSparql() + "}."
    }
    override /*suspend*/ fun toXMLElement(): XMLElement = super.toXMLElement().addAttribute("graph", graph).addAttribute("graphVar", "" + graphVar)
    override fun getRequiredVariableNames(): List<String> = listOf()
    override fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (c in children) {
            res.addAll(c.getRequiredVariableNames())
        }
        res.remove("_")
        res.remove("_")
        res.remove("_")
        return res.distinct()
    }
    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {}
    override fun equals(other: Any?): Boolean = other is LOPTriple && graph == other.graph && graphVar == other.graphVar && children[0] == other.children[0] && children[1] == other.children[1] && children[2] == other.children[2]
    override fun cloneOP(): IOPBase = LOPTriple(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase, children[2].cloneOP() as AOPBase, graph, graphVar)
    public companion object {
        public fun getIndex(children: Array<IOPBase>, sortPriority: List<String>): EIndexPattern {
            /*
             * always prefer P over S over O to access the best compressed triple store, which should be the fastest
             */
            var resString = ""
            val c0 = children[0]
            val c1 = children[1]
            val c2 = children[2]
            // constants first
            if (c1 is AOPConstant) {
                resString += "P"
            }
            if (c0 is AOPConstant) {
                resString += "S"
            }
            if (c2 is AOPConstant) {
                resString += "O"
            }
            if (resString.isNotEmpty() && resString.length < 3) {
                resString += "_"
            }
            // than sort order
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
            // than columns which are used
            if (c1 is AOPVariable && c1.name != "_" && !resString.contains("P")) {
                resString += "P"
            }
            if (c0 is AOPVariable && c0.name != "_" && !resString.contains("S")) {
                resString += "S"
            }
            if (c2 is AOPVariable && c2.name != "_" && !resString.contains("O")) {
                resString += "O"
            }
            // at last fill the remaining columns
            if (!resString.contains("P")) {
                resString += "P"
            }
            if (!resString.contains("S")) {
                resString += "S"
            }
            if (!resString.contains("O")) {
                resString += "O"
            }
            SanityCheck.check({ resString.length == 3 || (resString.length == 4 && resString.contains("_")) }, { "$resString ${children.map { it.toSparql() }} $sortPriority" })
SanityCheck.println{"GENERATED :: $resString ${EIndexPatternExt.names.indexOf(resString)}"}
            return EIndexPattern(EIndexPatternExt.names.indexOf(resString))
        }
    }
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        if (graphVar) {
            throw GraphVarHistogramsNotImplementedException()
        }
        val res = HistogramResult()
        res.count = -1
        for (v in getProvidedVariableNames()) {
            val params = Array(3) {
                var t = children[it]
                if (t is AOPVariable && t.name != v) {
                    t = AOPVariable(query, "_")
                }
                t as IAOPBase
            }
            val idx = getIndex(params.map { it }.toTypedArray(), listOf())
            val store = distributedTripleStore.getNamedGraph(query, graph)
            val childHistogram = store.getHistogram(params, idx)
            if (childHistogram.first < res.count || res.count == -1) {
                res.count = childHistogram.first
            }
            res.values[v] = childHistogram.second
        }
        for (v in getProvidedVariableNames()) {
            if (res.values[v]!! > res.count) {
                res.values[v] = res.count
            }
        }
        if (res.count == -1) {
            res.count = 0
        }
        SanityCheck.check { res.count != -1 }
        return res
    }
}
