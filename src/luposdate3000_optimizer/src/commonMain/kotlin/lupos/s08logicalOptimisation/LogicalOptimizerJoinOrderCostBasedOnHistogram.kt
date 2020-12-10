package lupos.s08logicalOptimisation

import lupos.s00misc.HistogramNotImplementedException
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues

object LogicalOptimizerJoinOrderCostBasedOnHistogram {
    /*suspend*/ operator fun invoke(allChilds: List<IOPBase>, root: LOPJoin): IOPBase? {
        SanityCheck.check { allChilds.isNotEmpty() }
        try {
            val nodes = mutableListOf<IOPBase>()
            nodes.addAll(allChilds)
            loop2@ while (nodes.size > 1) {
                var besta1 = 0
                var bestb1 = 1
                var h1: HistogramResult? = null
                var r1 = 1.0
                var besta2 = 0
                var bestb2 = 1
                var h21: HistogramResult? = null
                var r21 = Int.MAX_VALUE
                for (i in 0 until nodes.size) {
                    for (j in i + 1 until nodes.size) {
                        val p0 = nodes[i].getProvidedVariableNames()
                        val p1 = nodes[j].getProvidedVariableNames()
                        if (p0.intersect(p1).isNotEmpty()) {
// prevent any cross-product without any join-variable - except very last joins, where cross-product is unavoidable
                            val ch0 = nodes[i].getHistogram()
                            val ch1 = nodes[j].getHistogram()
                            val h2 = LOPJoin.mergeHistograms(ch0, ch1, false)
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
                                besta1 = i
                                bestb1 = j
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
                var bestA: Int
                var bestB: Int
                if (r1 < 0.6) {
// prefer the joins with strong result-count-reduction
                    bestA = besta1
                    bestB = bestb1
                } else {
                    // otherwise choose join with least amount of expected rows
                    bestA = besta2
                    bestB = bestb2
                }
                val b = nodes.removeAt(bestB) // first remove at the end of list
                val a = nodes.removeAt(bestA) // afterwards in front of b otherwise, the index would be wrong
                val c = LOPJoin(root.query, a, b, false)
                nodes.add(c)
            }
            return nodes[0]
        } catch (e: HistogramNotImplementedException) {
            return null
        }
/*Coverage Unreachable*/
    }
}
