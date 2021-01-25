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
@file:Import("../../../../src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ETripleComponentType.kt")
@file:Import("../../../../src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ETripleComponentTypeExt.kt")
@file:CompilerOptions("-Xmulti-platform")
import lupos.s00misc.ETripleComponentTypeExt
import kotlin.math.pow
import java.lang.ProcessBuilder.Redirect
import java.io.BufferedOutputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter

val tmpFolder = "tmp_fig5_data" // point to a folder with enough storage space available
val tmpFile = "$tmpFolder/intermediate.n3"
val minimumTime = 0.0001	// the minimum time for a single measurement
val resultFolder="fig5_result_data" //the folder where the results of the measurements are stored

// compile
/*
val p = ProcessBuilder(
    "./launcher.main.kts",
    "--compileAll",
    "--releaseMode=Enable",
    "--inlineMode=Enable",
    "--compileArgument_Luposdate3000_Endpoint:QueryResultToStream=lupos.s11outputResult.QueryResultToEmptyStream",
)
    .directory(File("."))
    .redirectOutput(Redirect.INHERIT)
    .redirectError(Redirect.INHERIT)
    .start()
p.waitFor()
if (p.exitValue() != 0) {
    throw Exception("exit-code:: " + p.exitValue())
}
*/
File(tmpFolder).mkdirs()
File(resultFolder).mkdirs()
// perform the measurements
for (output_count in listOf(512, 2048, 8192, 32768)) {
    for (join_count in listOf(1, 2, 4, 8, 16)) {
        for (join in listOf(2, 4, 8, 16, 32, 64)) {
            try {
                val count2 = (output_count / (join.toDouble().pow(1 + join_count.toDouble()))).toInt()
                if (count2 == 0) {
                    continue
                }
                generateTriples(tmpFolder, count2, 0, join, join_count)
                ProcessBuilder(
                    "./launcher.main.kts",
                    "--run",
                    "--releaseMode=Enable",
                    "--inlineMode=Enable",
"--proguardMode=On",
                    "--mainClass=Benchmark_Fig5",
                    "--runArgument_Luposdate3000_Launch_Benchmark_Fig5:datasource_files=$tmpFile",
                    "--runArgument_Luposdate3000_Launch_Benchmark_Fig5:minimum_time=$minimumTime",
                    "--runArgument_Luposdate3000_Launch_Benchmark_Fig5:number_of_triples=$output_count",
                    "--runArgument_Luposdate3000_Launch_Benchmark_Fig5:trash=0",
                    "--runArgument_Luposdate3000_Launch_Benchmark_Fig5:join=$join",
                    "--runArgument_Luposdate3000_Launch_Benchmark_Fig5:join_count=$join_count",
                )
                    .directory(File("."))
                    .redirectOutput(Redirect.appendTo(File(File(resultFolder),"database.log")))
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        for (trash in listOf(0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024)) {
            try {
                generateTriples(tmpFolder, output_count, trash, 1, join_count)
                ProcessBuilder(
                    "./launcher.main.kts",
                    "--run",
                    "--releaseMode=Enable",
                    "--inlineMode=Enable",
"--proguardMode=On",
                    "--mainClass=Benchmark_Fig5",
                    "--runArgument_Luposdate3000_Launch_Benchmark_Fig5:datasource_files=$tmpFile",
                    "--runArgument_Luposdate3000_Launch_Benchmark_Fig5:minimum_time=$minimumTime",
                    "--runArgument_Luposdate3000_Launch_Benchmark_Fig5:number_of_triples=$output_count",
                    "--runArgument_Luposdate3000_Launch_Benchmark_Fig5:trash=$trash",
                    "--runArgument_Luposdate3000_Launch_Benchmark_Fig5:join=1",
                    "--runArgument_Luposdate3000_Launch_Benchmark_Fig5:join_count=$join_count",
                )
                    .directory(File("."))
.redirectOutput(Redirect.appendTo(File(File(resultFolder),"database.log")))
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
// evaluate

// helper functions
fun generateTriples(folderName: String, count: Int, trash_block: Int, join_block: Int, join_count: Int): Int {
    val byteBuf = ByteArray(1)
    File(folderName).mkdirs()
    var outN3: PrintWriter = File(folderName + "/intermediate.n3").printWriter()
    var outIntermediateDictionary = DataOutputStream(BufferedOutputStream(FileOutputStream(folderName + "/intermediate.n3.dictionary")))
    var outIntermediateTriples = DataOutputStream(BufferedOutputStream(FileOutputStream(folderName + "/intermediate.n3.triples")))
    var outIntermediateDictionaryStat = File(folderName + "/intermediate.n3.stat")
    var dictCounterBnode = 0
    var dictCounterIri = 0
    var outIntermediateTriplesStatCounter = 0
    var idMapping = mutableMapOf<String, Int>()
    var idMappingCounter = 0
    fun writeToDict(s: String): Int {
        if (idMapping[s] != null) {
            return idMapping[s]!!
        }
        val tmp: ByteArray
        if (s.startsWith("_:")) {
            dictCounterBnode++
            byteBuf[0] = ETripleComponentTypeExt.BLANK_NODE.toByte()
            tmp = s.encodeToByteArray()
        } else {
            dictCounterIri++
            byteBuf[0] = ETripleComponentTypeExt.IRI.toByte()
            tmp = s.substring(1, s.length - 1).encodeToByteArray()
        }
        outIntermediateDictionary.writeInt(tmp.size)
        outIntermediateDictionary.write(byteBuf)
        outIntermediateDictionary.write(tmp)
        idMapping[s] = idMappingCounter++
        return idMapping[s]!!
    }
    fun appendTriple(s: String, p: String, o: String) {
        val si = writeToDict(s)
        val pi = writeToDict(p)
        val oi = writeToDict(o)
        outN3.println("$s $p $o .")
        outIntermediateTriples.writeInt(si)
        outIntermediateTriples.writeInt(pi)
        outIntermediateTriples.writeInt(oi)
        outIntermediateTriplesStatCounter++
    }
    for (i in 0 until count) {
        for (c in 0 until join_count + 1) {
            val cc = 'a' + c
            for (j in 0 until join_block) {
                appendTriple("_:$i", "<$cc>", "_:$j")
            }
        }
        for (c in 0 until join_count + 1) {
            val cc = 'a' + c
            for (j in 0 until trash_block) {
                appendTriple("_:${cc}_${i}_$j", "<$cc>", "_:$j")
            }
        }
    }
    outIntermediateTriples.close()
    File("$folderName/intermediate.n3.partitions").printWriter().use { out ->
        out.println("PSO,-1,1")
        for (i in listOf(2, 4, 8, 16)) {
            out.println("PSO,1,$i")
        }
    }
    outIntermediateDictionaryStat.printWriter().use { out ->
        out.println("total=${dictCounterBnode + dictCounterIri}")
        for (t in ETripleComponentTypeExt.names) {
            if (t == "BLANK_NODE") {
                out.println("$t=$dictCounterBnode")
            } else if (t == "IRI") {
                out.println("$t=$dictCounterIri")
            } else {
                out.println("$t=0")
            }
        }
    }
    outN3.close()
    outIntermediateDictionary.close()
    outIntermediateTriples.close()
    return outIntermediateTriplesStatCounter
}
