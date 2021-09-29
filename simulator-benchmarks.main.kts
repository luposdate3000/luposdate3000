#!/usr/bin/env kotlin
/*
 * This }le is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or }TNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

import java.io.File

File("simulator_output").deleteRecursively()
File("simulator_output").mkdirs()

inline fun execute(args: List<String>): List<String> {
    val p = ProcessBuilder(args)
    val it = p.start()
    val res = it.getInputStream().bufferedReader().use { it.readText() }.split("\n")
    it.waitFor()
    if (it.exitValue() != 0) {
        throw Exception("exit-code:: " + it.exitValue())
    }
    return res
}


val baseCmd = execute(listOf("./launcher.main.kts", "--run", "--mainClass=Launch_Simulator_Config", "--dryMode=Enable")).filter { it.contains("exec") }.first().replace("exec :: ", "").split(" ")

var first = true
val BASE_PATH = "src/luposdate3000_simulator_iot/src/jvmMain/resources"
val json_evaluation = "${BASE_PATH}/evaluation.json"
val json_luposdate3000 = "${BASE_PATH}/luposdate3000.json"

val campusList = listOf(
    "campusNoSamples",
    "campus")
val routingList = listOf(
    "RPL_Fast",
    "AllShortestPath")
val queryList = listOf(
    "Q0")
val topologyList = listOf(
    "distributed",
    "distributedWithQueryHops",
    "central")
val dataDistributionList = listOf(
    "luposdate3000_by_key")
val multicastList = listOf(
    "Disabled",
    "Enabled")
val queryDistributionList = listOf(
    "luposdate3000_distribution_routing")


val headerLine = mutableListOf<String>()
val contentLines = mutableListOf<MutableList<Double>>()

for (campus in campusList) {
    val json_campus = "${BASE_PATH}/${campus}.json"
    for (routing in routingList) {
        val json_routing = "${BASE_PATH}/routing_$routing.json"
        for (query in queryList) {
            val json_query = "${BASE_PATH}/$query.json"
            for (topology in topologyList) {
                val json_topology = "${BASE_PATH}/$topology.json"
                for (dataDistribution in dataDistributionList) {
                    val json_dataDistribution = "${BASE_PATH}/$dataDistribution.json"
                    for (multicast in multicastList) {
                        val json_multicast = "${BASE_PATH}/luposdate3000Multicast${multicast}.json"
                        for (queryDistribution in queryDistributionList) {
                            val json_queryDistribution = "${BASE_PATH}/$queryDistribution.json"
                            if (topology == "central" && query != "Q0") {
                                //centralized has only traffic during initialization, afterwards all zero
                                continue
                            }
                            if (campus == "campusNoSamples" && query != "Q0") {
                                //there is no data at all, any query get all zero data
                                continue
                            }
                            if (multicast == "Enabled" && query != "Q0") {
                                //multicast is only relevant for insert, everything else is the same
                                continue
                            }
                            val cmd = baseCmd + listOf(json_campus, json_topology, json_query, json_dataDistribution, json_evaluation, json_luposdate3000, json_queryDistribution, json_multicast, json_routing)
                            val measurementFile = execute(cmd).filter { it.contains("outputdirectory=") }.first().replace("outputdirectory=", "") + "/measurement.csv"
                            var firstLine = listOf<String>()
                            var contentLine = mutableListOf<Double>()
                            println("measurementFile:$measurementFile")
                            File(measurementFile).forEachLine {
                                if (firstLine.isEmpty()) {
                                    firstLine = it.split(",")
                                } else if (contentLine.isEmpty()) {
                                    val data = it.split(",")
                                    for (i in 0 until firstLine.size) {
                                        var idx = headerLine.indexOf(firstLine[i])
                                        if (idx < 0) {
                                            idx = headerLine.size
                                            headerLine.add(firstLine[i])
                                        }
                                        while (contentLine.size <= idx) {
                                            contentLine.add(0.0)
                                        }
                                        contentLine[i] = data[i].toDouble()
                                    }
                                }
                            }
                            contentLines.add(contentLine)
                        }
                    }
                }
            }
        }
    }
}
println(headerLine.joinToString())
for (line in contentLines) {
    while (line.size < headerLine.size) {
        line.add(0.0)
    }
    println(line.joinToString())
}
