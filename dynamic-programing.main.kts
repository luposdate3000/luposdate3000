#!/usr/bin/env kotlin
//https://db.in.tum.de/teaching/ws1415/queryopt/chapter3.pdf page 160

import kotlin.math.log2
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow


val histograms = arrayOf(
    4 to mapOf("x1" to 2),
    8 to mapOf("x1" to 3, "x2" to 4),
    8 to mapOf("x2" to 4),
    2 to mapOf("x2" to 3),
)


var SS: Int = 2.0.pow(histograms.size).toInt() - 1
val arrCost = DoubleArray(SS + 1) { -1.0 }
val arrLeft = IntArray(SS + 1)
val arrRight = IntArray(SS + 1)
val globalFactorList = mutableListOf<Pair<Int,Double>>()

fun initialize() {
    val mapVarnameToInput = mutableMapOf<String, MutableSet<Int>>()
    var x = 1
    val mapIntToBitmask = IntArray(histograms.size)
    for (i in 0 until histograms.size) {
        arrCost[x] = histograms[i].first.toDouble()
        mapIntToBitmask[i] = x
        for (k in histograms[i].second.keys) {
            mapVarnameToInput.getOrPut(k, { mutableSetOf(i) }).add(i)
        }
        x *= 2
    }
    for ((inputVariable, inputSet) in mapVarnameToInput) {
        if (inputSet.size > 1) {
            val inputList = inputSet.toIntArray()
            for (i in 0 until inputList.size) {
                for (j in i + 1 until inputList.size) {
                    val ii = inputList[i]
                    val jj = inputList[j]
                    val count1 = histograms[ii].first.toDouble()
                    val count2 = histograms[jj].first.toDouble()
                    val distinct1 = histograms[ii].second[inputVariable]!!.toDouble()
                    val distinct2 = histograms[jj].second[inputVariable]!!.toDouble()
                    val factor = (count1 / distinct1) * (count2 / distinct2) * (min(distinct1, distinct2) / max(distinct1, distinct2))
                    globalFactorList.add(mapIntToBitmask[i] + mapIntToBitmask[j] to factor)
                }
            }
        }
    }
}

fun enumerateThem(S: Int, factorList: List<Pair<Int,Double>>) {
    var S1 = S and (-S)
    do {
        var S2 = S - S1
        println("working with S1 = ${S1.toString(2)}, S2 = ${S2.toString(2)}")
        if (arrCost[S1] < 0.0) {
            enumerateThem(S1, factorList.filter { it.first == it.first and S1 })
        }
        if (arrCost[S2] < 0.0) {
            enumerateThem(S2, factorList.filter { it.first == it.first and S2 })
        }
        var cost = arrCost[S1] * arrCost[S2]
        for (factor in factorList.filter { it.first != it.first and S1 }) {
            cost *= factor.second
        }
        if (arrCost[S] < 0 || cost < arrCost[S]) {
            arrCost[S] = cost
            arrLeft[S] = S1
            arrRight[S] = S2
        }
        S1 = S and (S1 - S)
    } while (S1 != S)
}

initialize()
enumerateThem(SS, globalFactorList)
