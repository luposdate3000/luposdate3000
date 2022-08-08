#!/usr/bin/env kotlin
//https://db.in.tum.de/teaching/ws1415/queryopt/chapter3.pdf page 160

import kotlin.math.log2
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow


val histograms = arrayOf(
    812 to mapOf("x1" to 200), //0
    853 to mapOf("x1" to 850),//1
    821 to mapOf("x1" to 300, "x2" to 423),//2
    823 to mapOf("x1" to 423),//3
    834 to mapOf("x1" to 300),//4
    945 to mapOf("x1" to 930),//5
    834 to mapOf("x3" to 30),//6
    834 to mapOf("x3" to 30),//7
    834 to mapOf("x3" to 30),//8
    834 to mapOf("x3" to 30),//9
    834 to mapOf("x3" to 30),//10
    834 to mapOf("x3" to 30),//11
    834 to mapOf("x3" to 30),//12
    834 to mapOf("x3" to 30),//13
    834 to mapOf("x3" to 30),//14
    834 to mapOf("x3" to 30),//15
//    834 to mapOf("x3" to 30),//16
//    834 to mapOf("x3" to 30),//17
//    834 to mapOf("x3" to 30),//18
//    834 to mapOf("x3" to 30),//19
)


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


fun joinOrderToString(S: Int): String {
    if (arrLeft[S] == 0) {
        return "${log2(S.toDouble()).toInt()}"
    } else {
        return "(${joinOrderToString(arrLeft[S])},${joinOrderToString(arrRight[S])})"
    }
}

var starttime = System.nanoTime()
initialize()
println("initialization done                                    in ${(System.nanoTime() - starttime).toDouble() / 1_000_000_000.0}s")

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
//    } while (S1 != S)
    } while (S1 < S2)
if(ctr%10000==0){
println("step ${ctr.toString().padStart(6,'0')} of ${SS.toString().padStart(6,'0')} (${S.toString(2).padStart(30,'0')}) in ${(System.nanoTime()-starttime).toDouble()/1_000_000_000.0}s")
}
    ctr++
}





enumerateThem(SS)
println("real ctr = $ctr")
for (i in 0 until SS + 1) {
//    println("${i.toString(2).padStart(8, '0')} => ${arrCostSum[i]} -- ${arrCostLast[i]} .. ${joinOrderToString(i)}")
}


println(joinOrderToString(SS))
