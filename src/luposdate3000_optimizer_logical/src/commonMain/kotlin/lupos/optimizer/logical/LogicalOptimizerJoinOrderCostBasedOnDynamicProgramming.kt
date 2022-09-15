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
import lupos.operator.base.Query
import lupos.operator.logical.multiinput.LOPJoin
import lupos.shared.SanityCheck
import lupos.shared.operator.IOPBase
import kotlin.math.log2
import kotlin.math.min
import kotlin.math.pow
// https://db.in.tum.de/teaching/ws1415/queryopt/chapter3.pdf page 160

public object LogicalOptimizerJoinOrderCostBasedOnDynamicProgramming {
    public /*suspend*/ operator fun invoke(allChilds: List<IOPBase>, root: LOPJoin): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_logical/src/commonMain/kotlin/lupos/optimizer/logical/LogicalOptimizerJoinOrderCostBasedOnDynamicProgramming.kt:28"/*SOURCE_FILE_END*/ }, { allChilds.isNotEmpty() })

        if (allChilds.size> 18) {
            (root.query as Query).machineLearningAbort = true
            throw Exception("unable to use dynmic programming")
        }

        val histograms = mutableListOf<Pair<Int, MutableMap<String, Int>>>()
        var ctrrrr = 0
        for (c in allChilds) {
            val hist = c.getHistogram()
            histograms.add(hist.count to hist.values)
            println("finished histogram $ctrrrr")
            ctrrrr++
        }

        var SS: Int = 2.0.pow(histograms.size).toInt() - 1
        val arrCostLast = DoubleArray(SS + 1) { -1.0 }
        val arrCostSum = DoubleArray(SS + 1) { -1.0 }
        val arrLeft = IntArray(SS + 1)
        val arrRight = IntArray(SS + 1)

        val variables = mutableListOf<String>()
        var counterMap = Array(1) { IntArray(1) }

        fun initialize() {
            var x = 1
            var variablesSet = mutableSetOf<String>()
            for (i in 0 until histograms.size) {
                variablesSet.addAll(histograms[i].second.keys)
                arrCostLast[x] = histograms[i].first.toDouble()
                arrCostSum[x] = histograms[i].first.toDouble()
                x *= 2
            }
            variables.addAll(variablesSet)
            counterMap = Array(variables.size) { IntArray(histograms.size) { -1 } }
            for (i in 0 until histograms.size) {
                for ((variable, distinct) in histograms[i].second) {
                    val j = variables.indexOf(variable)
                    counterMap[j][i] = distinct
                }
            }
        }

        fun joinOrderToString(S: Int): IOPBase {
            if (arrLeft[S] == 0) {
                return allChilds[log2(S.toDouble()).toInt()]
            } else {
                return LOPJoin(root.query, joinOrderToString(arrLeft[S]), joinOrderToString(arrRight[S]), false)
            }
        }

        initialize()

        var ctr = 0

        fun enumerateThem(S: Int) {
            var S1 = S and (-S)
            do {
                var S2 = S - S1
                if (arrCostSum[S1] < 0.0) {
                    enumerateThem(S1)
                }
                if (arrCostSum[S2] < 0.0) {
                    enumerateThem(S2)
                }
                var cost = arrCostLast[S1] * arrCostLast[S2]
                for (v in 0 until variables.size) {
                    var min1 = -1.0
                    var min2 = -1.0
                    var i = S1
                    var ii = 0
                    while (i > 0) {
                        if (i and 0x1 != 0) {
                            val x = counterMap[v][ii]
                            if ((min1 < 0 || x < min1) && (x >= 0)) {
                                min1 = 0.0 + x
                            }
                        }
                        i = i / 2
                        ii++
                    }
                    i = S2
                    ii = 0
                    while (i > 0) {
                        if (i and 0x1 != 0) {
                            val x = counterMap[v][ii]
                            if ((min1 < 0 || x < min1) && (x >= 0)) {
                                min2 = 0.0 + x
                            }
                        }
                        i = i / 2
                        ii++
                    }
                    if (min1 >= 0 && min2 >= 0) {
                        val cost2 = min(min1, min2) * ((arrCostLast[S1] / min1) * (arrCostLast[S2] / min2))
                        cost = min(cost, cost2)
                    }
                }
                val costSum = cost + arrCostSum[S1] + arrCostSum[S2]
                if (arrCostSum[S] < 0 || costSum < arrCostSum[S]) {
                    arrCostLast[S] = cost
                    arrCostSum[S] = costSum
                    arrLeft[S] = S1
                    arrRight[S] = S2
                }
                S1 = S and (S1 - S)
            } while (S1 < S2)
            ctr++
        }

        enumerateThem(SS)

        return joinOrderToString(SS)

/*Coverage Unreachable*/
    }
}
