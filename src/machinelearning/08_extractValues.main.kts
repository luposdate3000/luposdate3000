#!/usr/bin/env kotlin

val possibleFilters = setOf("timeFor", "joinResultsFor", "networkTrafficFor")

fun printUsage() {
    println("usage ./08_extractValues.main.kts $possibleFilters")
    System.exit(1)
}
if (args.size != 1) {
    printUsage()
}

val label = args[0]

if (!possibleFilters.contains(label)) {
    printUsage()
}

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
        var joinOrder = 0
        var index = header.indexOf("$label($joinOrder)")
        while (index != -1) {
            val time = columns[index]
            println("$filename $joinOrder $time")
            joinOrder++
            index = header.indexOf("$label($joinOrder)")
        }
    }
} while (true)
