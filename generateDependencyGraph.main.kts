#!/usr/bin/env kotlin
/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

println("\\documentclass[a0paper,landscape]{article}")
println("\\usepackage{geometry}")
println("\\geometry{noheadfoot, margin=0.5in}")
println("\\usepackage{prerex}")
println("\\begin{document}")
println("\\thispagestyle{empty}")
println("\\begin{chart}")
val allNodes = mutableMapOf<String, MutableSet<String>>()
val layers = mutableMapOf<String, Int>()
val path = Paths.get("src")
Files.walk(path, 1).forEach { it ->
    val name = it.toString()
    if (name.length > 3 && !name.startsWith("src/luposdate3000_launch_")) {
        val tmp = mutableSetOf<String>()
        for (target in listOf("jvm", "js", "common")) {
            val f = File(it.toString() + "/${target}Dependencies")
            if (f.exists()) {
                f.forEachLine {
                    var dep = it.toLowerCase()
                    if (dep.startsWith("luposdate3000:")) {
                        if (dep.contains("#")) {
                            dep = dep.substring(0, dep.indexOf("#")) + ":0.0.1"
                        }
                        tmp.add(dep)
                        if (allNodes[dep] == null) {
                            allNodes[dep] = mutableSetOf<String>()
                        }
                    }
                }
            }
        }
        allNodes["luposdate3000:" + name.substring(4).toLowerCase() + ":0.0.1"] = tmp
    }
}
for ((k, v) in allNodes) {
    layers[k] = 0
}
var changed = true
while (changed) {
    changed = false
    for ((k, v) in allNodes) {
        for (d in v) {
            if (layers[d]!! >= layers[k]!!) {
                layers[k] = layers[d]!! + 1
                changed = true
            }
        }
    }
}
var maxLayer = 0
for ((k, v) in layers) {
    if (v > maxLayer) {
        maxLayer = v
    }
}
var nodesPerLayer = IntArray(maxLayer + 1)
val allCoordinates = mutableMapOf<String, String>()
for (node in allNodes.keys) {
    val id = ""
    val name = node.replace("_", "\\_").replace(":0.0.1", "").replace("luposdate3000:luposdate3000\\_", "")
    val timetable = ""
    val layer = layers[node]!!
    val coordinates = "${layer * 20},${nodesPerLayer[layer] * 7}"
    nodesPerLayer[layer]++
    allCoordinates[node] = coordinates
    println("\\reqfullcourse $coordinates:{$id}{$name}{$timetable}")
}
for ((k, v) in allNodes) {
    for (d in v) {
        println("\\prereq ${allCoordinates[k]},${allCoordinates[d]}:")
    }
}

println("\\end{chart}")
println("\\end{document}")
// ---------------------------------------------
for ((k, v) in allNodes) {
    println("${layers[k]!!} :: $k")
}
// ---------------------------------------------
