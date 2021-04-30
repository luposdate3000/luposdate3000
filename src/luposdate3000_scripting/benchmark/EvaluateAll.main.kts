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
@file:CompilerOptions("-Xmulti-platform")

import lupos.benchmark.DatabaseHandleBlazegraph
import lupos.benchmark.DatabaseHandleJena
import lupos.benchmark.DatabaseHandleLuposdate3000NoPartition
import lupos.benchmark.DatabaseHandleLuposdate3000Thread
import lupos.benchmark.DatabaseHandleLuposdateMemory
import lupos.benchmark.DatabaseHandleLuposdateRDF3X
import lupos.benchmark.DatabaseHandleVirtuoso
import java.io.File

val allDatabases = listOf(
    DatabaseHandleJena(port = 8080),
    DatabaseHandleBlazegraph(),
    DatabaseHandleLuposdateMemory(port = 8080),
    DatabaseHandleLuposdateRDF3X(workDir = "/mnt/db/benchmark/", port = 8080),
    DatabaseHandleVirtuoso(workDir = "/mnt/db/benchmark/"),
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
val allQueries = mapOf(
    "_:11" to "select distinct ?n1 ?n2 where { ?p1 <hasFamilyName> ?n1 . ?p2 <hasFamilyName> ?n2 . ?p1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_scientist_110560637> . ?p2 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_scientist_110560637> . ?c1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_site_108651247> . ?p1 ?x1 ?c1 . ?p2 ?y1 ?c1 . filter (?p1 != ?p2) . }",
    "_:13" to "select distinct ?n1 where { ?p1 <hasGivenName> ?n1 . ?c2 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_site_108651247> . ?c1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_village_108672738> . ?c1 <http://www.w3.org/2000/01/rdf-schema#label> 'London' . ?c2 <http://www.w3.org/2000/01/rdf-schema#label> 'Paris' . ?p1 ?x1 ?c1 . ?p1 ?y1 ?c2 . }",
    "_:15" to "select ?gn ?fn where { ?p1 <bornIn> ?c1 . ?a1 <bornIn> ?c2 . ?p1 <hasAcademicAdvisor> ?a1 . ?p1 <hasFamilyName> ?fn . ?p1 <hasGivenName> ?gn . ?p1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_scientist_110560637> . }",
    "_:17" to "select ?n1 where { ?a1 <actedIn> ?m1 . ?a1 <directed> ?m2 . ?a1 <hasGivenName> ?n1 . ?a1 <livesIn> ?c1 . ?a1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_actor_109765278> . ?m1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_movie_106613686> . ?m2 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_movie_106613686> . }",
    "_:21" to "select distinct ?n1 ?n2 where { ?a2 <actedIn> ?m1 . ?a1 <actedIn> ?m1 . ?a1 <hasGivenName> ?n1 . ?a2 <hasGivenName> ?n2 . ?a1 <livesIn> ?c1 . ?a2 <livesIn> ?c2 . filter (?a1 != ?a2) . filter (?c1 = ?c2) . }",
    "_:24" to "select distinct ?n1 ?n2 where { ?p1 <bornIn> ?c1 . ?p2 <bornIn> ?c1 . ?p1 <hasFamilyName> ?n1 . ?p2 <hasFamilyName> ?n2 . ?p1 <hasWonPrize> ?aw . ?p2 <hasWonPrize> ?aw. ?p1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_scientist_110560637> . ?p2 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_scientist_110560637> . filter (?p1 != ?p2) . }",
    "_:26" to "select distinct ?n1 ?n2 where { ?p1 <hasFamilyName> ?n1 . ?p2 <hasFamilyName> ?n2 . ?p1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_scientist_110560637> . ?p2 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_scientist_110560637> . ?p1 ?x1 ?c1 . ?p2 ?y1 ?c1 . filter (?p1 != ?p2) . }",
    "_:30" to "select ?fn ?gn where { ?p1 <bornIn> ?c1 . ?a1 <bornIn> ?c2 . ?p1 <hasAcademicAdvisor> ?a1 . ?p1 <hasFamilyName> ?fn . ?p1 <hasGivenName> ?gn . ?p1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_scientist_110560637> . ?c2 <locatedIn> <Germany> . ?c1 <locatedIn> <Switzerland> . }",
    "_:40" to "select ?gn ?fn where { ?p1 <bornIn> ?c1 . ?a1 <bornIn> ?c1 . ?p1 <hasAcademicAdvisor> ?a1 . ?p1 <hasFamilyName> ?fn . ?p1 <hasGivenName> ?gn .  }",
    "_:42" to "select ?gn ?fn where { ?p1 <hasAcademicAdvisor> ?a1 . ?p1 <hasFamilyName> ?fn . ?p1 <hasGivenName> ?gn . ?p1 <isMarriedTo> ?p2 . }",
    "_:44" to "select ?n1 ?n2 where { ?a1 <actedIn> ?m1 . ?a2 <actedIn> ?m1 . ?a1 <hasGivenName> ?n1 . ?a2 <hasGivenName> ?n2 . }",
    "_:46" to "select ?n1 ?n2 where { ?p1 <bornIn> ?c1 . ?p2 <bornIn> ?c1 . ?p1 <hasGivenName> ?n1 . ?p2 <hasGivenName> ?n2 . ?p1 <isMarriedTo> ?p2 .  }",
    "_:48" to "select ?x where { ?x <livesIn> <Athens> . }",
    "_:50" to "select ?y ?u where { <Albert_Einstein> <graduatedFrom> ?u . ?y <graduatedFrom> ?u . }",
    "_:6" to "select ?n1 where { ?a1 <actedIn> ?m1 . ?a1 <directed> ?m2 . ?a1 <hasGivenName> ?n1 . ?a1 <livesIn> ?c1 . ?c1 <locatedIn> ?s . ?m1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wikicategory_2000_films> . ?m2 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wikicategory_2000_films> . ?a1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <wordnet_actor_109765278> . }",
    "_:7" to "select distinct ?n1 ?n2 where { ?a1 <actedIn> ?m1 . ?a2 <actedIn> ?m1. ?a1 <hasGivenName> ?n1 . ?a2 <hasGivenName> ?n2 . ?a1 <livesIn> ?c1 . ?a2 <livesIn> ?c1 . filter (?a1 != ?a2) . }",
    "_:9" to "select ?n1 ?n2 where { ?p1 <bornIn> ?c1 . ?p2 <bornIn> ?c1 . ?p1 <hasGivenName> ?n1 . ?p2 <hasGivenName> ?n2 . ?p1 <isMarriedTo> ?p2 . }",
)

val inputFileName = "/mnt/luposdate-testdata/yago1/yago-1.0.0-turtle.ttl"
val outputFolder = "/mnt/db/benchmark-results/"

for (databaseIdx in 0 until allDatabases.size) {
    val database = allDatabases[databaseIdx]
    try {
        var abortSignal = false
        database.launch(
            inputFileName,
            {
                abortSignal = true
            },
            {
                try {
                    if (!abortSignal) {
                        for ((queryname, query) in allQueries) {
                            val response = database.runQuery(query)
                            File("$outputFolder$queryname").mkdirs()
                            File("$outputFolder$queryname/${database.getName()}.xml").printWriter().use { out ->
                                out.println(response)
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
        println("errored import ${database.getName()} $inputFileName")
    }
}
