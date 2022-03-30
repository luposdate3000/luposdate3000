#!/usr/bin/env kotlin

// -----------------------configure here ->
val label = "timeFor" // choose one of : setOf("timeFor", "joinResultsFor", "networkTrafficFor")
val joinOrders = 15
// -----------------------configure here <-

val header = mutableListOf<String>()
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
        val filename = columns[header.indexOf("queryFile")]
        for (joinOrder in 0 until joinOrders) {
            val time = columns[header.indexOf("$label($joinOrder)")]
            println("$filename $joinOrder $time")
        }
    }
} while (true)
