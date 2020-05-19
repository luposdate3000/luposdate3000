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
println("LogicalOptimizerJoinOrderCostBasedOnHistogram loop")
var x=0
for(c in nodes){
println("$x ${c.getHistogram().count} ${c.getHistogram().values}")
x++
}
            var bestA = -1
            var bestB = -1
            var h: HistogramResult? = null
            var r = 1.0
            for (i in 0 until nodes.size) {
                for (j in i + 1 until nodes.size) {
                    var ch0 = nodes[i].getHistogram()
                    var ch1 = nodes[j].getHistogram()
                    var h2 = LOPJoin.mergeHistograms(ch0, ch1, false)
                    var r2 = h2.count.toDouble() / (ch0.count.toDouble() * ch1.count.toDouble())
println("merge $i $j -> ${h2.count}, $r2")
                    if (h == null || r2 < r) {
                        bestA = i
                        bestB = j
                        h = h2
                        r = r2
                    }
                }
            }
            var b = nodes.removeAt(bestB)//first remove at the end of list
            var a = nodes.removeAt(bestA)//afterwards in front of b otherwise, the index would be wrong
            var c = LOPJoin(root.query, a, b, false)
            nodes.add(c)
        }
        return nodes[0]
    }
}
