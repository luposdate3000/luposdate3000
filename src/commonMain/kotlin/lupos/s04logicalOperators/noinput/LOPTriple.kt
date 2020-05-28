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
Coverage.funStart(4502)
        if (graph == PersistentStoreLocal.defaultGraphName) {
Coverage.ifStart(4503)
            return children[0].toSparql() + " " + children[1].toSparql() + " " + children[2].toSparql() + "."
        }
Coverage.statementStart(4504)
        return "GRAPH <$graph> {" + children[0].toSparql() + " " + children[1].toSparql() + " " + children[2].toSparql() + "}."
    }
    override fun toXMLElement() = super.toXMLElement().addAttribute("graph", graph).addAttribute("graphVar", "" + graphVar)
    override fun getRequiredVariableNames() = listOf<String>()
    override fun getProvidedVariableNames(): List<String> {
Coverage.funStart(4505)
        var res = mutableListOf<String>()
Coverage.statementStart(4506)
        for (c in children) {
Coverage.forLoopStart(4507)
            res.addAll(c.getRequiredVariableNames())
Coverage.statementStart(4508)
        }
Coverage.statementStart(4509)
        res.remove("_")
Coverage.statementStart(4510)
        res.remove("_")
Coverage.statementStart(4511)
        res.remove("_")
Coverage.statementStart(4512)
        return res.distinct()
    }
    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {}
    override fun equals(other: Any?): Boolean {
Coverage.funStart(4513)
        if (other !is LOPTriple) {
Coverage.ifStart(4514)
            return false
        }
Coverage.statementStart(4515)
        if (graph != other.graph) {
Coverage.ifStart(4516)
            return false
        }
Coverage.statementStart(4517)
        if (graphVar != other.graphVar) {
Coverage.ifStart(4518)
            return false
        }
Coverage.statementStart(4519)
        for (i in children.indices) {
Coverage.forLoopStart(4520)
            if (children[i] != other.children[i]) {
Coverage.ifStart(4521)
                return false
            }
Coverage.statementStart(4522)
        }
Coverage.statementStart(4523)
        return true
    }
    override fun cloneOP() = LOPTriple(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase, children[2].cloneOP() as AOPBase, graph, graphVar)
    companion object {
        fun getIndex(children: Array<OPBase>, sortPriority: List<String>): EIndexPattern {
Coverage.funStart(4524)
            /*
Coverage.statementStart(4525)
             * always prefer P over S over O to access the best compressed triple store, which should be the fastest
Coverage.statementStart(4526)
             */
Coverage.statementStart(4527)
            var resString = ""
Coverage.statementStart(4528)
            val c0 = children[0]
Coverage.statementStart(4529)
            val c1 = children[1]
Coverage.statementStart(4530)
            val c2 = children[2]
Coverage.statementStart(4531)
            //constants first
Coverage.statementStart(4532)
            if (c1 is AOPConstant) {
Coverage.ifStart(4533)
                resString += "P"
Coverage.statementStart(4534)
            }
Coverage.statementStart(4535)
            if (c0 is AOPConstant) {
Coverage.ifStart(4536)
                resString += "S"
Coverage.statementStart(4537)
            }
Coverage.statementStart(4538)
            if (c2 is AOPConstant) {
Coverage.ifStart(4539)
                resString += "O"
Coverage.statementStart(4540)
            }
Coverage.statementStart(4541)
            if (resString.length > 0 && resString.length < 3) {
Coverage.ifStart(4542)
                resString += "_"
Coverage.statementStart(4543)
            }
Coverage.statementStart(4544)
            //than sort order
Coverage.statementStart(4545)
            for (s in sortPriority) {
Coverage.forLoopStart(4546)
                SanityCheck.check { s != "_" }
Coverage.statementStart(4547)
                if (c0 is AOPVariable && c0.name == s) {
Coverage.ifStart(4548)
                    resString += "S"
Coverage.statementStart(4549)
                } else if (c1 is AOPVariable && c1.name == s) {
Coverage.ifStart(4550)
                    resString += "P"
Coverage.statementStart(4551)
                } else if (c2 is AOPVariable && c2.name == s) {
Coverage.ifStart(4552)
                    resString += "O"
Coverage.statementStart(4553)
                }
Coverage.statementStart(4554)
            }
Coverage.statementStart(4555)
            //than columns which are used
Coverage.statementStart(4556)
            if (c1 is AOPVariable && c1.name != "_" && !resString.contains("P")) {
Coverage.ifStart(4557)
                resString += "P"
Coverage.statementStart(4558)
            }
Coverage.statementStart(4559)
            if (c0 is AOPVariable && c0.name != "_" && !resString.contains("S")) {
Coverage.ifStart(4560)
                resString += "S"
Coverage.statementStart(4561)
            }
Coverage.statementStart(4562)
            if (c2 is AOPVariable && c2.name != "_" && !resString.contains("O")) {
Coverage.ifStart(4563)
                resString += "O"
Coverage.statementStart(4564)
            }
Coverage.statementStart(4565)
            //at last fill the remaining columns
Coverage.statementStart(4566)
            if (!resString.contains("P")) {
Coverage.ifStart(4567)
                resString += "P"
Coverage.statementStart(4568)
            }
Coverage.statementStart(4569)
            if (!resString.contains("S")) {
Coverage.ifStart(4570)
                resString += "S"
Coverage.statementStart(4571)
            }
Coverage.statementStart(4572)
            if (!resString.contains("O")) {
Coverage.ifStart(4573)
                resString += "O"
Coverage.statementStart(4574)
            }
Coverage.statementStart(4575)
            SanityCheck.check({ resString.length == 3 || (resString.length == 4 && resString.contains("_")) }, { "${resString} ${children.map { it.toSparql() }} $sortPriority" })
Coverage.statementStart(4576)
            return EIndexPattern.valueOf(resString)
        }
    }
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(4577)
        SanityCheck { !graphVar }
Coverage.statementStart(4578)
        var res = HistogramResult()
Coverage.statementStart(4579)
        res.count = -1
Coverage.statementStart(4580)
        var store = DistributedTripleStore.getNamedGraph(query, graph)
Coverage.statementStart(4581)
        for (v in getProvidedVariableNames()) {
Coverage.forLoopStart(4582)
            val params = Array(3) {
Coverage.statementStart(4583)
                var t = children[it]
Coverage.statementStart(4584)
                if (t is AOPVariable && t.name != v) {
Coverage.ifStart(4585)
                    t = AOPVariable(query, "_")
Coverage.statementStart(4586)
                }
Coverage.statementStart(4587)
                /*return*/t as AOPBase
            }
Coverage.statementStart(4588)
            var idx = getIndex(params.map { it as OPBase }.toTypedArray(), listOf<String>())
Coverage.statementStart(4589)
            var childHistogram = store.getHistogram(params, idx)
Coverage.statementStart(4590)
            SanityCheck.check { res.count == -1 || res.count == childHistogram.first }
Coverage.statementStart(4591)
            res.count = childHistogram.first
Coverage.statementStart(4592)
            res.values[v] = childHistogram.second
Coverage.statementStart(4593)
        }
Coverage.statementStart(4594)
        if (res.count == -1) {
Coverage.ifStart(4595)
            res.count = 0
Coverage.statementStart(4596)
        }
Coverage.statementStart(4597)
        SanityCheck.check { res.count != -1 }
Coverage.statementStart(4598)
        return res
    }
}
