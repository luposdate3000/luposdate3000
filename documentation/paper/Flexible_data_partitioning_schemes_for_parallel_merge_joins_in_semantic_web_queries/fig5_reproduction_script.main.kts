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
@file:Import("../../../src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ETripleComponentType.kt")
@file:Import("../../../src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ETripleComponentTypeExt.kt")
@file:CompilerOptions("-Xmulti-platform")

import lupos.shared.ETripleComponentTypeExt
import java.io.BufferedOutputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.lang.ProcessBuilder.Redirect
import java.text.DecimalFormat
import kotlin.math.log2
import kotlin.math.pow

// ///////////////////////////////////////////////////////////////////////////////////////////////////////
// config options -> /////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////////////////////////////////////////////
val tmpFolder = "tmp_fig5_data" // point to a folder with enough storage space available
val minimumTime = 10.0 // the minimum time (in seconds) for a single measurement
val resultFolder = "fig5_result_data" // the folder where the results of the measurements are stored
val outputCountList = listOf(512, 2048, 8192, 32768) // number of result rows
val joinCountList = listOf(1, 2, 4, 8, 16) // number of consekutive executed joins
val joinList = listOf(2, 4, 8, 16, 32, 64) // number of raw rows joining together (same number for each input, which comes directly from the store)
val trashList = listOf(0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024) // for simulating low selectivities -> put not joinable trash-rows in between
//
// disable individual steps, if the program crashes in the middle due to "out of memory" followed by the out-of-memory-killer choosing this script instead of the database.
//
val enableCompile = true
val enableMeasuerments = true
val enableGrapic = true
val producePNG = true // if set to false, than an eps is produced as used in the paper - the labels on the figure axis expect latex-interpretation and are broken in the png variant
// ///////////////////////////////////////////////////////////////////////////////////////////////////////
// config options <- /////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////////////////////////////////////////////
val tmpFile = "$tmpFolder/intermediate.n3"
val tmpLogFile = File(File(resultFolder), "database.log")
File(tmpFolder).mkdirs()
File(resultFolder).mkdirs()
// compile
if (enableCompile) {
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
}
// perform the measurements
if (enableMeasuerments) {
    for (output_count in outputCountList) {
        for (join_count in joinCountList) {
            for (join in joinList) {
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
                        .redirectOutput(Redirect.appendTo(tmpLogFile))
                        .redirectError(Redirect.INHERIT)
                        .start()
                        .waitFor()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            for (trash in trashList) {
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
                        .redirectOutput(Redirect.appendTo(tmpLogFile))
                        .redirectError(Redirect.INHERIT)
                        .start()
                        .waitFor()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
// visualize data
if (enableGrapic) {
    for (output_count in outputCountList) {
        extractData(tmpLogFile.getAbsolutePath(), "$output_count")
        File(File(resultFolder), "fig5_$output_count.gnuplot").printWriter().use { out ->
            if (producePNG) {
                out.println("set terminal png size 1920,1080")
                out.println("set output 'fig5_$output_count.png'")
            } else {
                out.println("set terminal epslatex size 20cm,8cm")
                out.println("set output 'fig5_$output_count.tex'")
            }
            out.println("set xlabel \"mergejoins\"")
            out.println("set ylabel \"selectivity\" offset 4,0,0")
            out.println("set cblabel \"optimal partitions\"")
            out.println("set style textbox opaque noborder")
            out.println("set datafile separator ','")
            out.println("set xrange [-0.5:4.5]")
            out.println("set yrange [-0.5:17.5]")
            out.println("unset xtics")
            out.println("set xtics format \" \"")
            out.println("set xtics (${File(File(resultFolder), "plot.XLabels").readText()})")
            out.println("unset ytics")
            out.println("set ytics format \" \"")
            out.println("set ytics (${File(File(resultFolder), "plot.YLabels").readText()})")
            out.println("set palette model RGB maxcolors 5")
            out.println("set palette defined ( 0 0.5 0.5 0.5, 1 1 1 1 )")
            out.println("set logscale cb 2")
            out.println("set cbrange [0.75:24]")
            out.println("plot 'plot.map' matrix with image notitle, 'plot.csv' u 1:2:3 w labels notitle")
        }
        ProcessBuilder("gnuplot", File(File(resultFolder), "fig5_$output_count.gnuplot").getAbsolutePath())
            .directory(File(resultFolder))
            .redirectOutput(Redirect.appendTo(tmpLogFile))
            .redirectError(Redirect.INHERIT)
            .start()
            .waitFor()
    }
}
// remove temorary folders
File(tmpFolder).deleteRecursively()
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

fun extractData(filename: String, output_count: String) {
    val data = mutableMapOf<Int/*trash or join*/, MutableMap<Int/*joincount*/, MutableMap<Int/*partitions*/, Double>>>()
    val data_trash_or_join = mutableSetOf<Int>(
        -64,
        -32,
        -16,
        -8,
        -4,
        -2,
        -1,
        1,
        2,
        4,
        8,
        16,
        32,
        64,
        128,
        256,
        512,
        1024,
    )
    val data_joincount = mutableSetOf<Int>()
    val data_partitions = mutableSetOf<Int>()
    File(filename).forEachLine {
        if (it.contains("NoOptimizer")) {
            val row = it.split(",")
            if (row.size > 10 && row[1] == output_count) {
                val trash = row[7].toInt()
                val join = row[8].toInt()
                val trash_or_join = if (trash == 0) -join else trash
                data_trash_or_join.add(trash_or_join)
                val joincount = row[9].toInt()
                data_joincount.add(joincount)
                val partitions = row[10].toInt()
                data_partitions.add(partitions)
                val repetitions = row[3].toInt()
                val time = row[4].toDouble() / 1000.0/* ms to s */
                val repetition_per_second = repetitions / time
                var a = data[trash_or_join]
                if (a == null) {
                    a = mutableMapOf<Int/*joincount*/, MutableMap<Int/*partitions*/, Double>>()
                    data[trash_or_join] = a
                }
                var b = a[joincount]
                if (b == null) {
                    b = mutableMapOf<Int/*partitions*/, Double>()
                    a[joincount] = b
                }
                b[partitions] = repetition_per_second
            }
        }
    }
    var row = "\t\t\t&  "
    for (joincount in data_joincount) {
        row = "$row&${joincount.toString().padStart(4, ' ')}"
    }
    println(row)
    for (trash_or_join in data_trash_or_join.sorted()) { // y-axis
        if (trash_or_join < 0) {
            row = "2^{${log2(-trash_or_join.toDouble()).toInt()}}\t\t\t"
        } else {
            row = "\\\\frac{1}{1+2^{${log2(trash_or_join.toDouble()).toInt()}}}\t"
        }
        for (partitions in data_partitions.sorted()) { // y-axis-part
            row = "$row&${partitions.toString().padStart(2, ' ')}"
            for (joincount in data_joincount.sorted()) { // x-axis
                val tt = data[trash_or_join]?.get(joincount)
                var t = tt?.get(partitions)
                if (t != null) {
                    var max = 0.0
                    for ((k, v) in tt!!) {
                        if (v > max) {
                            max = v
                        }
                    }
                    if (t == max) {
                        row = "$row&1.00"
                    } else {
                        row = "$row&${(t / max).toString().take(4).padStart(4, ' ')}"
                    }
                } else {
                    row = "$row&    "
                }
            }
            println(row)
            row = "\t\t\t"
        }
    }
    println("-------------------")
    row = "\t\t\t  "
    for (joincount in data_joincount) {
        row = "$row,${joincount.toString().padStart(4, ' ')}"
    }
    println(row)
    for (trash_or_join in data_trash_or_join.sorted()) { // y-axis
        if (trash_or_join < 0) {
            row = "2^{${log2(-trash_or_join.toDouble())}}\t\t\t"
        } else {
            row = "\\\\frac{1}{1+2^{${log2(trash_or_join.toDouble()).toInt()}}}\t"
        }
        for (joincount in data_joincount.sorted()) { // x-axis
            val tt = data[trash_or_join]?.get(joincount)
            if (tt != null) {
                var max = 0.0
                var maxi = -1
                for ((k, v) in tt) {
                    if (v > max) {
                        max = v
                        maxi = k
                    }
                }
                row = "$row,${maxi.toString().padStart(4, ' ')}"
            } else {
                row = "$row,    "
            }
        }
        println(row)
    }
    println("-------------------")
    var i = 0
    File(File(resultFolder), "plot.XLabels").printWriter().use { out ->
        for (joincount in data_joincount.sorted()) {
            if (i > 0) {
                out.print(",")
            }
            out.print("\"$joincount\" $i")
            i++
        }
    }
    File(File(resultFolder), "plot.YLabels").printWriter().use { out ->
        i = 0
        for (trash_or_join in data_trash_or_join.sorted()) {
            println(trash_or_join)
            if (i > 0) {
                out.print(",")
            }
            if (trash_or_join < 0) {
                out.print("\"\$2^{${log2(-trash_or_join.toDouble()).toInt()}}\$\" $i")
            } else {
                val xx = log2(trash_or_join.toDouble()).toInt()
                if (xx % 2 == 1) {
                    out.print("\"\$\\\\frac{1}{1+2^{$xx}}\$\" $i")
                } else {
                    out.print("\"\" $i")
                }
            }
            i++
        }
    }
    i = 0
    File(File(resultFolder), "plot.map").printWriter().use { outMap ->
        File(File(resultFolder), "plot.csv").printWriter().use { outCsv ->
            for (trash_or_join in data_trash_or_join.sorted()) { // y-axis
                row = ""
                var j = 0
                for (joincount in data_joincount.sorted()) { // x-axis
                    val tt = data[trash_or_join]?.get(joincount)
                    if (tt != null) {
                        var max = 0.0
                        var maxi = -1
                        var seq = -1.0
                        for ((k, v) in tt) {
                            if (k == 1) {
                                seq = v
                            }
                            if (v > max * 0.9) {
                                max = v
                                maxi = k
                            }
                        }
                        if (seq < 0.0) {
                            seq = max
                        }
                        row = "$row,${maxi.toString().padStart(4, ' ')}"
                        var max_seq = if (max / seq < 1.0) 1.0 else max / seq
                        outCsv.println("$j,$i,$maxi; ${doubleToString(max_seq)}; ${doubleToString(seq)}/s")
                    } else {
                        row = "$row,   0"
                    }
                    j++
                }
                if (row.length > 0) {
                    outMap.println(row.substring(1))
                }
                i++
            }
        }
    }
    println("-------------------")
    i = 0
    for (trash_or_join in data_trash_or_join.sorted()) { // y-axis
        for (joincount in data_joincount.sorted()) { // x-axis
            val tt = data[trash_or_join]?.get(joincount)
            if (tt != null) {
                var max = 0.0
                var maxi = -1
                for ((k, v) in tt) {
                    if (v > max) {
                        max = v
                        maxi = k
                    }
                }
                println("$i $joincount $maxi")
            } else {
                println("$i $joincount 0")
            }
        }
        i++
    }
}

fun doubleToString(d: Double): String {
    return DecimalFormat("#.##").format(d)
}
