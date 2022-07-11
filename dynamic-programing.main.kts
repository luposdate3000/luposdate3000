#!/usr/bin/env kotlin
//https://db.in.tum.de/teaching/ws1415/queryopt/chapter3.pdf page 160

import kotlin.math.log2
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow


val histograms = arrayOf(
    812 to mapOf("x1" to 200), //0
    821 to mapOf("x1" to 300, "x2" to 423),//1
    823 to mapOf("x2" to 423),//2
    853 to mapOf("x3" to 850),//3
    834 to mapOf("x2" to 300),//4
    945 to mapOf("x2" to 930),//5
    834 to mapOf("x1" to 30),//6
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
                    val factorOld = (count1 / distinct1) * (count2 / distinct2) * (min(distinct1, distinct2) / max(distinct1, distinct2))
val factor=1-((max(distinct1, distinct2)-min(distinct1, distinct2))/max(distinct1, distinct2))
                    globalFactorList.add(mapIntToBitmask[ii] + mapIntToBitmask[jj] to factor)
println("globalFactorList ${inputVariable} ${mapIntToBitmask[ii].toString(2).padStart(8,'0')} ${mapIntToBitmask[jj].toString(2).padStart(8,'0')} = $factor")
                }
            }
        }
    }
}


fun joinOrderToString(S:Int):String{
if(arrLeft[S]==0){
return "${log2(S.toDouble()).toInt()}"
}else{
return "(${joinOrderToString(arrLeft[S])},${joinOrderToString(arrRight[S])})"
}
}

var starttime=System.nanoTime()
initialize()
println("initialization done                                    in ${(System.nanoTime()-starttime).toDouble()/1_000_000_000.0}s")

var ctr=0
fun enumerateThem(S: Int, factorList: List<Pair<Int,Double>>) {
    var S1 = S and (-S)
    do {
        var S2 = S - S1
        if (arrCost[S1] < 0.0) {
            enumerateThem(S1, factorList.filter { it.first == it.first and S1 })
        }
        if (arrCost[S2] < 0.0) {
            enumerateThem(S2, factorList.filter { it.first == it.first and S2 })
        }
        var cost = arrCost[S1] * arrCost[S2]
        for (factor in factorList.filter { (it.first != (it.first and S1) ) && (0!=(it.first and S1))}) {
            cost *= factor.second
        }
        if (arrCost[S] < 0 || cost < arrCost[S]) {
            arrCost[S] = cost
            arrLeft[S] = S1
            arrRight[S] = S2
        }
        S1 = S and (S1 - S)
    } while (S1 != S)
println("step ${ctr.toString().padStart(6,'0')} of ${SS.toString().padStart(6,'0')} (${S.toString(2).padStart(30,'0')}) in ${(System.nanoTime()-starttime).toDouble()/1_000_000_000.0}s")
ctr++
}





enumerateThem(SS, globalFactorList)

for(i in 0 until SS+1){
println("${i.toString(2).padStart(8,'0')} => ${arrCost[i]}")
}


println(joinOrderToString(SS))
