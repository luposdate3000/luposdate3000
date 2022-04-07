#!/usr/bin/env kotlin
import kotlin.random.Random

// -----------------------configure here ->
val label = args[0] // choose one of : setOf("timeFor", "joinResultsFor")
val joinOrders = args[1].toInt() //numeric value
val choiceColumn = args[2] // choose one of : setOf("luposdateWouldChoose", "random")
val worstCaseFactor=15.0
// -----------------------configure here <-

val absoluteRanks = IntArray(joinOrders)
val relativeRanks = IntArray(joinOrders)
var countLines = 0
val header = mutableListOf<String>()
val random = Random(0)
do {
    val line = readLine()
    if (line == null) {
        break
    }
    val ll = line.replace(" ", "").replace("//", "/")
    val columns = ll.split(",")
    if (header.size == 0) {
        header.addAll(columns)
    } else {
        countLines++
        val values = DoubleArray(joinOrders) { columns[header.indexOf("$label($it)")].toDouble() }
        var absolute = 0
        var min = values[0]
        var max = values[0]
        for (v in values) {
            if (min > v) {
                min = v
            }
            if (max < v) {
                max = v
            }
        }
val worstcase=min*worstCaseFactor
for (choice in 0 until joinOrders) {
if(values[choice]>worstcase){
values[choice]=worstcase
}
}
if(max>worstcase){
max=worstcase
}
        val choice = when (choiceColumn) {
            "random" -> {
                random.nextInt(0, joinOrders)
            }
            else -> {
                columns[header.indexOf(choiceColumn)].toInt()
            }
        }
        for (v in values) {
            if (v < values[choice]) {
                absolute++
            }
        }
        val relative = ((values[choice] - min) * 14.0 / (max - min)).toInt()
        absoluteRanks[absolute]++
        relativeRanks[relative]++
    }
} while (true)

var absoluteAccumulator = 0
var relativeAccumulator = 0
println("rank,absolute,relative,absolute-accumulated,relative-accumulated")
println("0,0.0,0.0,0.0,0.0")
for (i in 0 until joinOrders) {
    absoluteAccumulator += absoluteRanks[i]
    relativeAccumulator += relativeRanks[i]
    val a = absoluteRanks[i].toDouble() / countLines.toDouble()
    val b = relativeRanks[i].toDouble() / countLines.toDouble()
    val c = absoluteAccumulator.toDouble() / countLines.toDouble()
    val d = relativeAccumulator.toDouble() / countLines.toDouble()
    println("${i + 1},${a},${b},${c},${d}")
}
