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
package lupos.optimizer.logical

import lupos.operator.base.multiinput.LOPJoin_Helper
import lupos.operator.logical.multiinput.LOPJoin
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.logical.noinput.LOPValues
import lupos.shared.SanityCheck
import lupos.shared.operator.HistogramResult
import lupos.shared.operator.IOPBase

public object LogicalOptimizerJoinOrderCostBasedOnHistogram {
    public /*suspend*/ operator fun invoke(allChilds: List<IOPBase>, root: LOPJoin): IOPBase {
        if (SanityCheck.enabled) { if (!(allChilds.isNotEmpty())) { throw Exception("SanityCheck failed") } }
        val nodes = mutableListOf<IOPBase>()
        nodes.addAll(allChilds)
        loop2@ while (nodes.size > 1) {
            var h1: HistogramResult? = null
            var r1 = 1.0
            var besta2 = 0
            var bestb2 = 1
            var h21: HistogramResult? = null
            var r21 = Int.MAX_VALUE
            for (i in 0 until nodes.size) {
                for (j in i + 1 until nodes.size) {
                    val p0 = nodes[i].getProvidedVariableNames().toMutableSet()
                    val p1 = nodes[j].getProvidedVariableNames().toMutableSet()
                    p0.remove("_")
                    p1.remove("_")
                    if (p0.intersect(p1).isNotEmpty()) {
// prevent any cross-product without any join-variable - except very last joins, where cross-product is unavoidable
                        val ch0 = nodes[i].getHistogram()
                        val ch1 = nodes[j].getHistogram()
                        val h2 = LOPJoin_Helper.mergeHistograms(ch0, ch1, false)
                        var r2 = h2.count.toDouble() / (ch0.count.toDouble() * ch1.count.toDouble())
                        if (nodes[i] is LOPTriple) {
                            r2 *= p0.size.toDouble() * 0.3 // prefer triples with many constants first
                        }
                        if (nodes[j] is LOPTriple) {
                            r2 *= p1.size.toDouble() * 0.3 // prefer triples with many constants first
                        }
                        if (nodes[i] is LOPValues) {
                            r2 *= 0.1 // prefer values clause as much as possible, because the result size is very likely to be small
                        }
                        if (nodes[j] is LOPValues) {
                            r2 *= 0.1 // prefer values clause as much as possible, because the result size is very likely to be small
                        }
                        if (h1 == null || r2 < r1) {
                            h1 = h2
                            r1 = r2
                        }
                        if (h21 == null || h2.count < r21) {
                            besta2 = i
                            bestb2 = j
                            h21 = h2
                            r21 = h2.count
                        }
                    }
                }
            }
            val bestA: Int = besta2
            val bestB: Int = bestb2
            val b = nodes.removeAt(bestB) // first remove at the end of list
            val a = nodes.removeAt(bestA) // afterwards in front of b otherwise, the index would be wrong
            val c = LOPJoin(root.query, a, b, false)
            nodes.add(c)
        }
        return nodes[0]
/*Coverage Unreachable*/
    }
}
