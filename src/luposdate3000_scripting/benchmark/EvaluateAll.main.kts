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
@file:Import("DatabaseHandleVirtuoso.kt")
@file:Import("Config.kt")
@file:Import("DatabaseHandleBlazegraph.kt")
@file:Import("DatabaseHandleJena.kt")
@file:Import("DatabaseHandle.kt")
@file:Import("DatabaseHandleLuposdate3000Thread.kt")
@file:Import("DatabaseHandleLuposdate3000.kt")
@file:Import("DatabaseHandleLuposdate3000NoPartition.kt")
@file:Import("DatabaseHandleLuposdateMemory.kt")
@file:Import("DatabaseHandleLuposdateRDF3X.kt")
@file:Import("../../luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EOperatingSystem.kt")
@file:Import("../../luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EOperatingSystemExt.kt")
@file:Import("../../luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ETripleComponentType.kt")
@file:Import("../../luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ETripleComponentTypeExt.kt")
@file:Import("../../luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared_inline/Platform.kt")
@file:Import("../../luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/shared_inline/Platform.kt")
@file:Import("../../luposdate3000_shared/src/jvmMain/kotlin/lupos/shared/DateHelperRelative.kt")
@file:Import("../../luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DateHelperRelative.kt")
@file:CompilerOptions("-Xmulti-platform")

import lupos.benchmark.DatabaseHandleJena
import lupos.benchmark.DatabaseHandleLuposdate3000NoPartition
import lupos.benchmark.DatabaseHandleLuposdate3000Thread
import lupos.benchmark.DatabaseHandleVirtuoso
import lupos.shared.DateHelperRelative
import java.io.File

// configure databases
val allDatabases = listOf(
//    DatabaseHandleBlazegraph("/mnt/db/benchmark/"), //empty results --- something wrong with query prefixes
//    DatabaseHandleLuposdateMemory(port = 8080),
//    DatabaseHandleLuposdateRDF3X(workDir = "/mnt/db/benchmark/", port = 8080), //all queries working on yago1
    DatabaseHandleVirtuoso(workDir = "/mnt/db/benchmark/"),
    DatabaseHandleJena(port = 8080),
    DatabaseHandleLuposdate3000NoPartition(workDir = "/mnt/db/benchmark/", port = 8080)
        .setBufferManager("Inmemory"),
    DatabaseHandleLuposdate3000Thread(workDir = "/mnt/db/benchmark/", port = 8080, threadCount = 2)
        .setBufferManager("Inmemory"),
    DatabaseHandleLuposdate3000Thread(workDir = "/mnt/db/benchmark/", port = 8080, threadCount = 4)
        .setBufferManager("Inmemory"),
    DatabaseHandleLuposdate3000Thread(workDir = "/mnt/db/benchmark/", port = 8080, threadCount = 8)
        .setBufferManager("Inmemory"),
    DatabaseHandleLuposdate3000Thread(workDir = "/mnt/db/benchmark/", port = 8080, threadCount = 16)
        .setBufferManager("Inmemory"),
    DatabaseHandleLuposdate3000NoPartition(workDir = "/mnt/db/benchmark/", port = 8080)
        .setBufferManager("Persistent_Cached"),
    DatabaseHandleLuposdate3000Thread(workDir = "/mnt/db/benchmark/", port = 8080, threadCount = 2)
        .setBufferManager("Persistent_Cached"),
    DatabaseHandleLuposdate3000Thread(workDir = "/mnt/db/benchmark/", port = 8080, threadCount = 4)
        .setBufferManager("Persistent_Cached"),
    DatabaseHandleLuposdate3000Thread(workDir = "/mnt/db/benchmark/", port = 8080, threadCount = 8)
        .setBufferManager("Persistent_Cached"),
    DatabaseHandleLuposdate3000Thread(workDir = "/mnt/db/benchmark/", port = 8080, threadCount = 16)
        .setBufferManager("Persistent_Cached"),
)
// configure dataset locations
val allDatasets = mapOf(
    "yago1" to "/mnt/luposdate-testdata/yago1/yago-1.0.0-turtle.ttl",
    "yago2" to "/mnt/luposdate-testdata/yago2/yago-2.n3",
    "yago2s" to "/mnt/luposdate-testdata/yago2s/yago-2.5.3-turtle-simple.ttl",
    "barton" to "/mnt/luposdate-testdata/barton/barton.nt",
)
// configure file containing all queries
val literaturFile = "/src/benjamin/uni_luebeck/_00_papers/gelesenePaper/literatur.n3"
// blacklist some queries, comments show why these are blacklisted
val blacklistedQueries = mapOf(
    "LuposdateRDF3X" to mapOf(
        "yago1" to setOf(
            "_:26" // timeout
        )
    )
)

// obtaining tasks from file
val allTasks = mutableMapOf<String, MutableMap<String, String>>()
File(literaturFile).forEachLine { line ->
    val idx = line.indexOf(" <query_sparql_")
    if (idx > 0) {
        if (!line.contains("_original>")) {
            val queryName = line.substring(0, idx)
            val idx2 = line.indexOf(">", idx)
            val datasetname = line.substring(idx + " <query_sparql_".length, idx2)
            val query = line.substring(idx2 + 3, line.length - 3)
            var tmp = allTasks[datasetname]
            if (tmp == null) {
                tmp = mutableMapOf<String, String>()
                allTasks[datasetname] = tmp
            }
            tmp[queryName] = query
        }
    }
}
// printing all tasks
for ((k, v) in allTasks) {
    println("dataset :: $k")
    for ((k2, v2) in v) {
        println("  query :: $k2 :: $v2")
    }
}

// evaluating all tasks
val outputFolder = "/mnt/db/benchmark-results/"
File(outputFolder).mkdirs()
File("$outputFolder/log.txt").printWriter().use { logger ->
    for ((datasetName, allQueries) in allTasks) {
        val datasetFile = allDatasets[datasetName]!!
        for (databaseIdx in 0 until allDatabases.size) {
            val database = allDatabases[databaseIdx]
            try {
                var abortSignal = false
                val startTime = DateHelperRelative.markNow()
                database.launch(
                    datasetFile,
                    {
                        abortSignal = true
                    },
                    {
                        try {
                            if (!abortSignal) {
                                val importTime = DateHelperRelative.elapsedSeconds(startTime)
                                logger.println("import,$datasetName,${database.getName()},_,$importTime")
                                logger.flush()
                                for ((queryname, query) in allQueries) {
                                    val flag = blacklistedQueries[database.getName()]?.get(datasetName)?.contains(queryname)
                                    if (flag == null || !flag) {
                                        val startTime2 = DateHelperRelative.markNow()
                                        val response = database.runQuery(query)
                                        val querytime = DateHelperRelative.elapsedSeconds(startTime2)
                                        logger.println("evaluate,$datasetName,${database.getName()},$queryname,$querytime")
                                        logger.flush()
                                        File("$outputFolder$datasetName/$queryname").mkdirs()
                                        File("$outputFolder$datasetName/$queryname/${database.getName()}.xml").printWriter().use { out ->
                                            out.println(response)
                                        }
                                    }
                                }
                            }
                        } catch (e: Throwable) {
                            e.printStackTrace()
                        }
                    }
                )
            } catch (e: Throwable) {
                e.printStackTrace()
                println("errored import ${database.getName()} $datasetName")
            }
        }
    }
}
