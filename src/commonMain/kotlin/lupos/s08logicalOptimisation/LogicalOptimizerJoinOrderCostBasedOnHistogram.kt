package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase
object LogicalOptimizerJoinOrderCostBasedOnHistogram {
    operator fun invoke(allChilds: List<OPBase>, root: LOPJoin): OPBase? {
Coverage.funStart(9170)
        SanityCheck.check { allChilds.size > 0 }
Coverage.statementStart(9171)
        val nodes = mutableListOf<OPBase>()
Coverage.statementStart(9172)
        nodes.addAll(allChilds)
Coverage.statementStart(9173)
        loop2@ while (nodes.size > 1) {
Coverage.whileLoopStart(9174)
            var x = 0
Coverage.statementStart(9175)
            for (c in nodes) {
Coverage.forLoopStart(9176)
                x++
Coverage.statementStart(9177)
            }
Coverage.statementStart(9178)
            var bestA_1 = 0
Coverage.statementStart(9179)
            var bestB_1 = 1
Coverage.statementStart(9180)
            var h_1: HistogramResult? = null
Coverage.statementStart(9181)
            var r_1 = 1.0
Coverage.statementStart(9182)
            var bestA_2 = 0
Coverage.statementStart(9183)
            var bestB_2 = 1
Coverage.statementStart(9184)
            var h_2: HistogramResult? = null
Coverage.statementStart(9185)
            var r_2 = Int.MAX_VALUE
Coverage.statementStart(9186)
            for (i in 0 until nodes.size) {
Coverage.forLoopStart(9187)
                for (j in i + 1 until nodes.size) {
Coverage.forLoopStart(9188)
                    var p0 = nodes[i].getProvidedVariableNames()
Coverage.statementStart(9189)
                    var p1 = nodes[j].getProvidedVariableNames()
Coverage.statementStart(9190)
                    if (p0.intersect(p1).size > 0) {
Coverage.ifStart(9191)
//prevent any cross-product without any join-variable - except very last joins, where cross-product is unavoidable
Coverage.statementStart(9192)
                        var ch0 = nodes[i].getHistogram()
Coverage.statementStart(9193)
                        var ch1 = nodes[j].getHistogram()
Coverage.statementStart(9194)
                        var h2 = LOPJoin.mergeHistograms(ch0, ch1, false)
Coverage.statementStart(9195)
                        var r2 = h2.count.toDouble() / (ch0.count.toDouble() * ch1.count.toDouble())
Coverage.statementStart(9196)
                        if (nodes[i] is LOPTriple) {
Coverage.ifStart(9197)
                            r2 = r2 * p0.size.toDouble() * 0.3//prefer triples with many constants first
Coverage.statementStart(9198)
                        }
Coverage.statementStart(9199)
                        if (nodes[j] is LOPTriple) {
Coverage.ifStart(9200)
                            r2 = r2 * p1.size.toDouble() * 0.3//prefer triples with many constants first
Coverage.statementStart(9201)
                        }
Coverage.statementStart(9202)
                        if (nodes[i] is LOPValues) {
Coverage.ifStart(9203)
                            r2 = r2 * 0.1//prefer values clause as much as possible, because the result size is very likely to be small
Coverage.statementStart(9204)
                        }
Coverage.statementStart(9205)
                        if (nodes[j] is LOPValues) {
Coverage.ifStart(9206)
                            r2 = r2 * 0.1//prefer values clause as much as possible, because the result size is very likely to be small
Coverage.statementStart(9207)
                        }
Coverage.statementStart(9208)
                        if (h_1 == null || r2 < r_1) {
Coverage.ifStart(9209)
                            bestA_1 = i
Coverage.statementStart(9210)
                            bestB_1 = j
Coverage.statementStart(9211)
                            h_1 = h2
Coverage.statementStart(9212)
                            r_1 = r2
Coverage.statementStart(9213)
                        }
Coverage.statementStart(9214)
                        if (h_2 == null || h2.count < r_2) {
Coverage.ifStart(9215)
                            bestA_2 = i
Coverage.statementStart(9216)
                            bestB_2 = j
Coverage.statementStart(9217)
                            h_2 = h2
Coverage.statementStart(9218)
                            r_2 = h2.count
Coverage.statementStart(9219)
                        }
Coverage.statementStart(9220)
                    }
Coverage.statementStart(9221)
                }
Coverage.statementStart(9222)
            }
Coverage.statementStart(9223)
            var bestA: Int
Coverage.statementStart(9224)
            var bestB: Int
Coverage.statementStart(9225)
            if (r_1 < 0.6) {
Coverage.ifStart(9226)
//prefer the joins with strong result-count-reduction
Coverage.statementStart(9227)
                bestA = bestA_1
Coverage.statementStart(9228)
                bestB = bestB_1
Coverage.statementStart(9229)
            } else {
Coverage.ifStart(9230)
                //otherwise choose join with least amount of expected rows
Coverage.statementStart(9231)
                bestA = bestA_2
Coverage.statementStart(9232)
                bestB = bestB_2
Coverage.statementStart(9233)
            }
Coverage.statementStart(9234)
            var b = nodes.removeAt(bestB)//first remove at the end of list
Coverage.statementStart(9235)
            var a = nodes.removeAt(bestA)//afterwards in front of b otherwise, the index would be wrong
Coverage.statementStart(9236)
            var c = LOPJoin(root.query, a, b, false)
Coverage.statementStart(9237)
            nodes.add(c)
Coverage.statementStart(9238)
        }
Coverage.statementStart(9239)
        return nodes[0]
    }
}
