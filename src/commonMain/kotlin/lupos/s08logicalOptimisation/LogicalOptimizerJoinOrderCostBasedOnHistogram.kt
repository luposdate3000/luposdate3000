package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase

object LogicalOptimizerJoinOrderCostBasedOnHistogram {
    operator fun invoke(allChilds: List<OPBase>, root: LOPJoin): OPBase? {
        SanityCheck.check { allChilds.size > 0 }
        val nodes = mutableListOf<OPBase>()
        nodes.addAll(allChilds)
        loop2@ while (nodes.size > 1) {
            var x = 0
            for (c in nodes) {
                x++
            }
            var bestA_1 = 0
            var bestB_1 = 1
            var h_1: HistogramResult? = null
            var r_1 = 1.0
            var bestA_2 = 0
            var bestB_2 = 1
            var h_2: HistogramResult? = null
            var r_2 = Int.MAX_VALUE
            for (i in 0 until nodes.size) {
                for (j in i + 1 until nodes.size) {
var p0=nodes[i].getProvidedVariableNames()
var p1=nodes[j].getProvidedVariableNames()
if(p0.intersect(p1).size>0){
//prevent any cross-product without any join-variable - except very last joins, where cross-product is unavoidable
                    var ch0 = nodes[i].getHistogram()
                    var ch1 = nodes[j].getHistogram()
                    var h2 = LOPJoin.mergeHistograms(ch0, ch1, false)
                    var r2 = h2.count.toDouble() / (ch0.count.toDouble() * ch1.count.toDouble())
                    if (h_1 == null || r2 < r_1) {
                        bestA_1 = i
                        bestB_1 = j
                        h_1 = h2
                        r_1 = r2
                    }
                    if (h_2 == null || h2.count<r_2) {
                        bestA_2 = i
                        bestB_2 = j
                        h_2 = h2
                        r_2 = h2.count
                    }
}
                }
            }
var bestA:Int
var bestB:Int
if(r_1<0.6){
//prefer the joins with strong result-count-reduction
bestA=bestA_1
bestB=bestB_1
}else{
//otherwise choose join with least amount of expected rows
bestA=bestA_2
bestB=bestB_2
}
            var b = nodes.removeAt(bestB)//first remove at the end of list
            var a = nodes.removeAt(bestA)//afterwards in front of b otherwise, the index would be wrong
            var c = LOPJoin(root.query, a, b, false)
            nodes.add(c)
        }
        return nodes[0]
    }
}
