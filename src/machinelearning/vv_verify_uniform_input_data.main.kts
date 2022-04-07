#!/usr/bin/env kotlin
import kotlin.random.Random

// -----------------------configure here ->
val label = args[0] // choose one of : setOf("timeFor", "joinResultsFor")
val joinOrders = args[1].toInt() //numeric value
val absOrRel = args[2] == "abs"
val worstCaseFactor=15.0
// -----------------------configure here <-

val absoluteRanks = Array(joinOrders) { DoubleArray(joinOrders) }
val relativeRanks = Array(joinOrders) { DoubleArray(joinOrders) }
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
        for (choice in 0 until joinOrders) {
            var absolute = 0
            for (v in values) {
                if (v < values[choice]) {
                    absolute++
                }
            }
            val relative = ((values[choice] - min) * 14.0 / (max - min)).toInt()
            absoluteRanks[choice][absolute]++
            relativeRanks[choice][relative]++
        }
    }
} while (true)

println(",${List(joinOrders) { "Rank" + it }.joinToString()}")
if (absOrRel) {
    for (i in 0 until joinOrders) {
        println("Choice$i," + absoluteRanks[i].map { it.toDouble() / countLines.toDouble() }.joinToString())
    }
} else {
    for (i in 0 until joinOrders) {
        println("Choice$i," + relativeRanks[i].map { it.toDouble() / countLines.toDouble() }.joinToString())
    }
}
