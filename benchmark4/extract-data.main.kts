#!/usr/bin/env kotlin
import java.io.File

var data = mutableListOf<Pair<Double, String>>()
File("results").forEachLine {
    if (it.contains("NoOptimizer")) {
        val row = it.split(",")
        if (row.size > 5) {
            data.add(Pair(row[5].toDouble(), row[0]))
        }
    }
}
data.sortBy {
    it.first
}
for (d in data) {
    println(d)
}
