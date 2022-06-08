#!/usr/bin/env kotlin

val possibleFilters=setOf("timeFor", "joinResultsFor", "networkTrafficFor")

fun printUsage(){
println("usage ./08_extractValues.main.kts 15 $possibleFilters")
System.exit(1)
}
if(args.size!=2){
printUsage()
}

val label = args[1] // choose one of : setOf("timeFor", "joinResultsFor", "networkTrafficFor")
val joinOrders = args[0].toInt()

if(!possibleFilters.contains(label)){
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
        for (joinOrder in 0 until joinOrders) {
            val time = columns[header.indexOf("$label($joinOrder)")]
            println("$filename $joinOrder $time")
        }
    }
} while (true)
