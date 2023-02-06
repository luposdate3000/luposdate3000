/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.operator.logical.noinput

import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.operator.logical.LOPBase
import lupos.shared.EIndexPattern
import lupos.shared.EIndexPatternExt
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.TripleStoreManager
import lupos.shared.XMLElement
import lupos.shared.operator.HistogramResult
import lupos.shared.operator.IAOPBase
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class LOPTriple public constructor(
    query: IQuery,
    s: IAOPBase,
    p: IAOPBase,
    o: IAOPBase,
    @JvmField public val graph: String,
    @JvmField public val graphVar: Boolean
) : LOPBase(query, EOperatorIDExt.LOPTripleID, "LOPTriple", arrayOf(s, p, o), ESortPriorityExt.ANY_PROVIDED_VARIABLE) {
    override fun toSparql(): String {
        if (graph == TripleStoreManager.DEFAULT_GRAPH_NAME) {
            return children[0].toSparql() + " " + children[1].toSparql() + " " + children[2].toSparql() + "."
        }
        return "GRAPH <$graph> {" + children[0].toSparql() + " " + children[1].toSparql() + " " + children[2].toSparql() + "}."
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement = super.toXMLElement(partial, partition).addAttribute("graph", graph).addAttribute("graphVar", "" + graphVar)
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
                if (SanityCheck.enabled) { if (!(s != "_")) { throw Exception("SanityCheck failed") } }
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
            if (SanityCheck.enabled) { if (!(resString.length == 3 || (resString.length == 4 && resString.contains("_")))) { throw Exception("SanityCheck failed") } }
            return EIndexPatternExt.names.indexOf(resString)
        }
    }

    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        if (graphVar) {
            TODO()
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
            val store = (query as Query).instance.tripleStoreManager!!.getGraph(graph)
            val childHistogram = store.getHistogram(query, params, idx)
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
        if (SanityCheck.enabled) { if (!(res.count != -1)) { throw Exception("SanityCheck failed") } }
        return res
    }
}
