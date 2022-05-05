#!/usr/bin/env kotlin
/*
 * This File is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
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
import java.lang.ProcessBuilder.Redirect
import java.time.LocalDateTime

File("simulator_output").deleteRecursively()
File("simulator_output").mkdirs()
var skipfirst = false

inline fun execute(args: List<String>): List<String> {
    println(args.joinToString(" "))
    val p = ProcessBuilder(args)
        .redirectError(Redirect.INHERIT)
    val it = p.start()
    val res = it.getInputStream().bufferedReader().use { it.readText() }.split("\n")
    it.waitFor()
    if (it.exitValue() != 0) {
        println("exit-code:: " + it.exitValue())
    }
    return res
}

val baseCmd = execute(listOf("./launcher.main.kts", "--run", "--mainClass=Launch_Simulator_Config", "--dryMode=Enable")).filter { it.contains("exec") }.first().replace("exec :: ", "").split(" ")

val headerLine = mutableListOf<String>()
val contentLines = mutableListOf<MutableList<String>>()
val attributeLines = mutableListOf<MutableList<String>>()
val specializedCmdHeaders = listOf("campus", "networkTopology", "databaseTopology", "query", "dataDistribution", "evaluation", "luposdate3000", "queryDistribution", "multicast", "routing","localExecution")
headerLine.addAll(specializedCmdHeaders)
var printCounter = 0

var first = true
val BASE_PATH = "src/luposdate3000_simulator_db/src/jvmMain/resources"
val json_evaluation = "$BASE_PATH/evaluation.json"
val json_luposdate3000 = "$BASE_PATH/luposdate3000.json"

    fun printStatus() {
        println()
        println("printing status csv '$printCounter'")
        println()
        printCounter++
        println(headerLine.joinToString())
        for (i in 0 until contentLines.size) {
            while (contentLines[i].size < headerLine.size - specializedCmdHeaders.size) {
                contentLines[i].add("0.0")
            }
            println(attributeLines[i].joinToString() + "," + contentLines[i].joinToString())
        }
    }
//for (ontologyVersion in listOf(2, 0)) { // 0=noSOSA, 1=SOSA with INSERT-WHERE, 2=SOSA with luposdate3000-knowledge
for (ontologyVersion in listOf(2)) { // 0=noSOSA, 1=SOSA with INSERT-WHERE, 2=SOSA with luposdate3000-knowledge

    val campusList = when (ontologyVersion) {
        2 -> listOf(
            "ontology/campusSOSANoSamplesInternalID.json",
            "ontology/campusSOSAInternalID.json",
        )
        1 -> listOf(
            "ontology/campusSOSANoSamples.json",
            "ontology/campusSOSA.json",
        )
        0 -> listOf(
            "ontology/campusNoSamples.json",
            "ontology/campus.json",
        )
        else -> TODO()
    }
    val routingList = listOf(
        "routing/routing_RPL_Fast.json",
        "routing/routing_AllShortestPath.json",
    )
    val query =         when (ontologyVersion) {
            2 -> "queries/SOSA_Queries.json"
            1 -> "queries/SOSA_Queries.json"
            0 -> "queries/Queries.json"
            else -> TODO()
        }
    val databaseTopologyList = listOf(
        "programDistribution/distributed.json",
        "programDistribution/distributedWithQueryHops.json",
//    "programDistribution/central.json",
    )
    val dataDistributionList = listOf(
        "dataDistribution/luposdate3000_by_id_S_all_collations.json",
        "dataDistribution/luposdate3000_by_key.json",
//        "dataDistribution/luposdate3000_by_id_1_all_collations.json",
//        "dataDistribution/luposdate3000_by_id_2_all_collations.json",
//        "dataDistribution/luposdate3000_by_id_O_all_collations.json",
//        "dataDistribution/luposdate3000_by_id_twice_all_collations.json",
//        "dataDistribution/luposdate3000_by_simple.json",
    )
    val multicastList = listOf(
        "multicast/luposdate3000MulticastDisabled.json",
        "multicast/luposdate3000MulticastEnabled.json",
    )
    val queryDistributionList = listOf(
        "luposdate3000_distribution_routing.json",
"luposdate3000_distribution_centralized.json",
    )
    val networkTopologyList = mutableListOf<String>(
        "topology/Uniform4DB.json",
        "topology/Random4DB.json",
        "topology/Ring4DB.json",
        "topology/Full4DB.json",
        "topology/Uniform16DB.json",
        "topology/Random16DB.json",
        "topology/Ring16DB.json",
        "topology/Full16DB.json",
        "topology/Uniform128DB.json",
        "topology/Random128DB.json",
        "topology/Ring128DB.json",
        "topology/Full128DB.json",
    )
val localExecutionList=listOf("luposdate3000_local_execution_enabled.json","luposdate3000_local_execution_disabled.json")
for(localExecution in localExecutionList){
val json_localExecution="$BASE_PATH/$localExecution"
    for (campus in campusList) {
        val json_campus = "$BASE_PATH/$campus"
        for (networkTopology in networkTopologyList) {
            val json_networkTopology = "$BASE_PATH/$networkTopology"
                val json_query = "$BASE_PATH/$query"
                for (routing in routingList) {
                    val json_routing = "$BASE_PATH/$routing"
                    for (databaseTopology in databaseTopologyList) {
                        val json_database_topology = "$BASE_PATH/$databaseTopology"
                        for (dataDistribution in dataDistributionList) {
                            val json_dataDistribution = "$BASE_PATH/$dataDistribution"
                            for (multicast in multicastList) {
                                val json_multicast = "$BASE_PATH/$multicast"
                                for (queryDistribution in queryDistributionList) {
                                    try {
                                        val json_queryDistribution = "$BASE_PATH/$queryDistribution"
                                        if (databaseTopology == "programDistribution/central.json" && query !in listOf("Q0.json", "Q_SOSA_0.json")) {
                                            // centralized has only traffic during initialization, afterwards all zero
                                            continue
                                        }
                                        if (multicast == "multicast/luposdate3000MulticastEnabled.json" && query !in listOf("Q0.json", "Q_SOSA_0.json")) {
                                            // multicast is only relevant for insert, everything else is the same
                                            continue
                                        }
                                        if (campus in listOf("ontology/campusNoSamples.json", "ontology/campusSOSANoSamples.json", "ontology/campusSOSANoSamplesInternalID.json") && (
                                                multicast != "multicast/luposdate3000MulticastEnabled.json" ||
                                                    databaseTopology != "programDistribution/distributedWithQueryHops.json" ||
                                                    routing != "routing_RPL_Fast.json" ||
                                                    dataDistribution != "dataDistribution/luposdate3000_by_id_S_all_collations.json" ||
                                                    query !in listOf("Q0.json", "Q_SOSA_0.json")
                                                )
                                        ) {
                                            continue
                                        }
                                        val specializedCmd = listOf(json_campus, json_networkTopology, json_database_topology, json_query, json_dataDistribution, json_evaluation, json_luposdate3000, json_queryDistribution, json_multicast, json_routing,json_localExecution)
                                        val cmd = baseCmd + specializedCmd
//println(cmd.joinToString(" "))
//continue
                                        val result = if (skipfirst) {
                                            skipfirst = false
                                            listOf<String>()
                                        } else {
                                            execute(cmd)
                                        }
                                        val measurementFile = result.filter { it.contains("outputdirectory=") }.first().replace("outputdirectory=", "") + "/measurement.csv"
                                        for (l in result) {
                                            println(l)
                                        }
                                        var firstLine = listOf<String>()
                                        val attributeLine = specializedCmd.map { it.substring(it.lastIndexOf("/") + 1, it.length - 5) }.toMutableList()
                                        File(measurementFile).forEachLine {
                                            if (firstLine.isEmpty()) {
                                                firstLine = it.split(",")
                                            } else {
                                        var contentLine = mutableListOf<String>()
                                                val data = it.split(",")
                                                for (i in 0 until firstLine.size) {
                                                    var idx = headerLine.indexOf(firstLine[i])
                                                    if (idx < 0) {
                                                        idx = headerLine.size
                                                        headerLine.add(firstLine[i])
                                                    }
                                                    idx -= specializedCmdHeaders.size
                                                    while (contentLine.size <= idx) {
                                                        contentLine.add("0.0")
                                                    }
                                                    contentLine[idx] = data[i]
                                                }
                                        attributeLines.add(attributeLine)
                                        contentLines.add(contentLine)
                                            }
                                        }
                                    } catch (e: Throwable) {
                                        e.printStackTrace()
                                    }
                                }
                            }
                        }
                    printStatus()
                    }
                }
            }
    }
}
}
printStatus()
