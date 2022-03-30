#!/usr/bin/env kotlin

// -----------------------configure here ->
val label = "timeFor" // choose one of : setOf("timeFor", "joinResultsFor")
val joinOrders = 15
// -----------------------configure here <-


val header = mutableListOf<String>()
val data = mutableMapOf<String,DoubleArray>()
do {
    val line = readLine()
    if (line == null) {
        break
    }
    val ll = line.replace(" ", "").replace("//", "/")
    val columns = ll.split(",")
    if (columns.contains("phase")) {
header.clear()
        header.addAll(columns)
    } else {
        val phase = columns[header.indexOf("phase")].split("#")
if(phase.size==2){
val filename=phase[0]
val joinorder=phase[1].toInt()
var arr=data[filename]
if(arr==null){
arr=DoubleArray(joinOrders)
data[filename]=arr
}
arr[joinorder]=columns[header.indexOf("network traffic total (Bytes)")].toDouble()
}
    }
} while (true)

println("queryFile,${List(joinOrders){"networkTrafficFor($it)"}.joinToString()}")
for((f,v) in data){
println("$f,${v.joinToString()}")
}
