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
    class Plan : Comparable<Plan> {
        val child: OPBase?
        val childs: Pair<Int, Int>?
        val histogram: HistogramResult

        constructor(child: OPBase) {
            this.child = child
            childs = null
            histogram = child.getHistogram()
        }

        constructor(plans: Array<Plan?>, childA: Int, childB: Int) {
            child = null
            childs = Pair(childA, childB)
            histogram = LOPJoin.mergeHistograms(plans[childA]!!.histogram, plans[childB]!!.histogram, false)
        }

        override operator fun compareTo(other: Plan): Int {
            var res = histogram.count.compareTo(other.histogram.count)
            return res
        }

        fun toOPBase(plans: Array<Plan?>): OPBase {
            if (child != null) {
                return child
            } else if (childs != null) {
                val ca = plans[childs.first]!!.toOPBase(plans)
                return LOPJoin(ca.query, ca, plans[childs.second]!!.toOPBase(plans), false)
            } else {
                throw Exception("unreachable")
            }
        }
    }

    fun enumerate(length: Int, bits: Int, action: (Int, Int) -> Unit) {
        /* 
         * this function enumerates all distinct combinations of 2 numbers, which together have *bits* set within a total range of *length* bits
         */
        require(bits > 0)
        require(bits <= length)
        if (bits == 1) {
            for (i in 0 until length) {
                action(0, 1 shl i)
            }
        } else if (bits == 2) {
            for (i in 0 until length) {
                for (j in i + 1 until length) {
                    action(1 shl i, 1 shl j)
                }
            }
        } else if (bits == 3) {
            for (i in 0 until length) {
                for (j in i + 1 until length) {
                    for (k in j + 1 until length) {
                        action((1 shl i), (1 shl j) or (1 shl k))
                        action((1 shl j), (1 shl i) or (1 shl k))
                        action((1 shl i) or (1 shl j), (1 shl k))
                    }
                }
            }
        } else {
            /* this is generic, but slower due to lots of loop iterations, which are eliminated later */
            for (bitsA in 1 until bits) {
                val bitsB = bits - bitsA
                var lastA = 0
                var a = 0
                var b = 0
                enumerate(length, bitsA, { a1, a2 ->
                    a = a1 or a2
                    if (a != lastA) {
                        var lastB = 0
                        enumerate(length, bitsB, { b1, b2 ->
                            b = b1 or b2
                            if (lastB != b) {
                                if (a < b && a and b == 0) {
                                    lastA = a
                                    lastB = b
                                    action(a, b)
                                }
                            }
                        })
                    }
                })
            }
        }
    }

    operator fun invoke(allChilds: List<OPBase>, root: LOPJoin): OPBase? {
        SanityCheck.check { allChilds.size > 2 }
        if (allChilds.size < 30) {
            val plans = arrayOfNulls<Plan?>(1 shl allChilds.size)
            var key = 1
            for (i in allChilds.indices) {
                plans[key] = Plan(allChilds[i])
                key *= 2
            }
            for (i in 2 until allChilds.size + 1) {
                enumerate(allChilds.size, i, {a,b->
require(a and b == 0)
require(a!=0)
require(b!=0)
val target=a or b
require(target<plans.size)
                    val newPlan = Plan(plans, a, b)
                    if (plans[target] == null) {
                        plans[target] = newPlan
                    } else if (newPlan < plans[target]!!) {
                        plans[target] = newPlan
                    }
                })
            }
            val bestPlan = plans[plans.size - 1]!!
            return bestPlan.toOPBase(plans)
        }
        return null
    }
}
