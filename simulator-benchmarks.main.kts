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

fun execute(args: List<String>): List<String> {
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

val campusList = listOf("campusNoSamples", "campus")
val routingList = listOf("RPL_Fast", "AllShortestPath")
val queryList = listOf("Q0")
val topologyList = listOf("distributed", "distributedWithQueryHops", "central")
val dataDistributionList = listOf("luposdate3000_by_key")
val multicastList = listOf("Disabled", "Enabled")
val distopologyList = listOf("luposdate3000_distribution_routing")

for (campus in campusList) {
    val json_campus = "${BASE_PATH}/${campus}.json"
    for (r in routingList) {
        val json_routing = "${BASE_PATH}/routing_$r.json"
        for (q in queryList) {
            val json_query = "${BASE_PATH}/$q.json"
            for (t in topologyList) {
                val json_topology = "${BASE_PATH}/$t.json"
                for (d in dataDistributionList) {
                    val json_dataDistribution = "${BASE_PATH}/$d.json"
                    for (m in multicastList) {
                        val json_multicast = "${BASE_PATH}/luposdate3000Multicast${m}.json"
                        for (dist in distopologyList) {
                            val json_dataDistributionist = "${BASE_PATH}/$dist.json"
                            if (t == "central" && q != "Q0") {
	//centralized has only traffic during initialization, afterwards all zero
                                continue
                            }
                            if (campus == "campusNoSamples" && q != "Q0") {
	//there is no data at all, any query get all zero data
                                continue
                            }
                            if (m == "Enabled" && q != "Q0") {
	//multicast is only relevant for insert, everything else is the same
                                continue
                            }
                            val measurementFile = "simulator_output/_${campus}_${t}_${q}_${d}_evaluation_luposdate3000_${dist}_luposdate3000Multicast${m}_routing_${r}/measurement.csv"
                            val cmd = baseCmd + listOf(json_campus, json_topology, json_query, json_dataDistribution, json_evaluation, json_luposdate3000, json_dataDistributionist, json_multicast, json_routing)
                            execute(cmd)


                        }
                    }
                }
            }
        }
    }
}
