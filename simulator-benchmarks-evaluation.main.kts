#!/usr/bin/env kotlin

val keyColumnsToCompare = arrayOf(
    "topology",
    "database",
    "multicast",
    "routing-protocol",
    "dist",
    "readwrite",
)
val valueColumnsToCompare = arrayOf(
    "number of virtually sent operatorGraph packages",
    "number of virtually sent intermediate results packages",
    "number of virtually sent graph update packages",
    "number of virtually sent dictionary packages",
    "number of forwarded packages",
    "number of sent packages",
    "virtual network traffic operatorGraph(Bytes)",
    "virtual network traffic intermediate results(Bytes)",
    "virtual network traffic graph update(Bytes)",
    "network traffic forwarded(Bytes)",
    "network traffic total (Bytes)",
)
val clusterColumn = "query"

/////////////
/////////////
/////////////
/////////////

val header = readLine()!!.split(",").toTypedArray()
val columnCount = header.size
val data = mutableListOf<Array<String>>()


val valueColumnsToCompareIdx = valueColumnsToCompare.map { header.indexOf(it) }.toIntArray()
val clusterColumnIdx = header.indexOf(clusterColumn)
val clusterColumnRange = mutableSetOf<String>()

var line = readLine()
while (line != null) {
    val row = line!!.split(",").toTypedArray()
    data.add(row)
    clusterColumnRange.add(row[clusterColumnIdx])
    line = readLine()
}

for (cluster in clusterColumnRange) {
    println()
    println()
    println("clustering by '$cluster'")
    println()

    for (compareColumn in keyColumnsToCompare) {
        val compareColumnIdx = header.indexOf(compareColumn)

        val localDataCounters = mutableMapOf<String, Int>() //the value -> current aggregatesCount
        val localData = mutableMapOf<String, DoubleArray>() //the value -> current aggregates


        for (d in data.filter { it[clusterColumnIdx] == cluster }) {
            val key = d[compareColumnIdx]
            localDataCounters[key] = (localDataCounters[key] ?: 0) + 1
            val row = (localData[key] ?: DoubleArray(columnCount))
            localData[key] = row
            for (i in valueColumnsToCompareIdx) {
                row[i] += d[i].toDouble()
            }
        }
        if (localData.size > 1) {

            println()
            println()
            println("comparing '$compareColumn'")
            println()
            println("x,${valueColumnsToCompare.joinToString()}")
            for ((k, v) in localData) {
                val row = Array(v.size) { v[it] / localDataCounters[k]!! }
                println("$k,${valueColumnsToCompareIdx.map { row[it] }.joinToString()}")
            }
        }
    }
}
