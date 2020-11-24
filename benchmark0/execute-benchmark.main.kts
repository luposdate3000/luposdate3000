#!/usr/bin/env kotlin
@file:Import("../src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("../src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("../src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("generate-benchmark-data.kt")
@file:CompilerOptions("-Xmulti-platform")

import lupos.s00misc.Platform
import java.lang.ProcessBuilder.Redirect
import java.io.BufferedOutputStream
import java.io.DataOutputStream
import java.io.FileOutputStream
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

val jars = mutableListOf(
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Buffer_Manager_Inmemory-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Dictionary_Inmemory-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Endpoint-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Operators-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Parser-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Result_Format-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Shared-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Test-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Triple_Store_All-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Triple_Store_Id_Triple-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Optimizer-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Endpoint_Java_Sockets-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Jena_Wrapper_Off-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Launch_Benchmark-jvm.jar",
)
val userHome = Platform.getUserHome()
for (f in Platform.findNamedFileInDirectory("${Platform.getUserHome()}${Platform.getPathSeparator()}.gradle${Platform.getPathSeparator()}caches${Platform.getPathSeparator()}modules-2${Platform.getPathSeparator()}files-2.1${Platform.getPathSeparator()}com.soywiz.korlibs.krypto${Platform.getPathSeparator()}krypto-jvm${Platform.getPathSeparator()}1.9.1${Platform.getPathSeparator()}", "krypto-jvm-1.9.1.jar")) {
    jars.add(f)
}
for (f in Platform.findNamedFileInDirectory("${Platform.getUserHome()}${Platform.getPathSeparator()}.m2${Platform.getPathSeparator()}repository${Platform.getPathSeparator()}org${Platform.getPathSeparator()}jetbrains${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}kotlin-stdlib${Platform.getPathSeparator()}1.4.255-SNAPSHOT", "kotlin-stdlib-1.4.255-SNAPSHOT.jar")) {
    jars.add(f)
}
var classpath = ""
for (jar2 in jars) {
    val jar = if (jar2.startsWith("/")) jar2 else "../$jar2"
    if (classpath == "") {
        classpath = jar
    } else {
        classpath = "$classpath:$jar"
    }
}
println("the classpath $classpath")
val trash = args[0].toInt()
val result_rows = args[1].toLong()
val data = "/hadoop/BigSIoT/data"
val join = 1
val predicates = 10
var queriesList = listOf("q2.sparql", "q10.sparql")
var queries = ""
for (q in queriesList) {
    if (queries.length > 0) {
        queries = "$queries;$q"
    } else {
        queries = q
    }
}
println("settings $trash $result_rows micro")
File(data).deleteRecursively()
File(data).mkdirs()
val triples = generateTriples(result_rows, predicates, join, trash, data)
val size = File("$data/intermediate.n3").length()
val data_bnodecount = 0
val data_compressed = size
val fileWriter = FileWriter("all-micro.csv", true)
val benchmark_out = PrintWriter(fileWriter)
for (process in listOf(2, 4, 8, 16)) {
File("$data/intermediate.partitions").printWriter().use{out->
out.println("SPO,1,$process")
out.println("SPO,2,$process")
out.println("SOP,1,$process")
out.println("SOP,2,$process")
out.println("PSO,1,$process")
out.println("PSO,2,$process")
out.println("POS,1,$process")
out.println("POS,2,$process")
out.println("OSP,1,$process")
out.println("OSP,2,$process")
out.println("OPS,1,$process")
out.println("OPS,2,$process")
}
    val p = ProcessBuilder("java", "-Xmx60g", "-cp", classpath, "MainKt", "$data/intermediate.n3", "$queries", "10", "$triples")
            .directory(File("."))
            .redirectOutput(File("x"))
            .redirectError(Redirect.INHERIT)
            .start()
    p.waitFor()
    if (p.exitValue() != 0) {
        throw Exception("executing command failed")
    }
    for (q in queriesList) {
        var no = List(5) { "1" }
        var with = List(5) { "1" }
        File("x").forEachLine() {
            if (it.contains(q)) {
                if (it.contains("NoOptimizer")) {
                    no = it.split(",")
                }
                if (it.contains("WithOptimizer")) {
                    with = it.split(",")
                }
            }
        }
        val no_repetitions = no[3].toInt()
        val no_time = no[4].toDouble()
        val no_time_per_repetition = no_time / no_repetitions
        val no_time_per_result_row = no_time / (no_repetitions * result_rows)
        val no_time_per_triple = no_time / (no_repetitions * triples)
        val with_repetitions = with[3].toInt()
        val with_time = with[4].toDouble()
        val with_time_per_repetition = with_time / with_repetitions
        val with_time_per_result_row = with_time / (with_repetitions * result_rows)
        val with_time_per_triple = with_time / (with_repetitions * triples)
        val time_per_optimizer = with_time_per_repetition - no_time_per_repetition
        var s = "$q"
        s += ",$trash"
        s += ",$join"
        s += ",$process"
        s += ",$predicates"
        s += ",$triples"
        s += ",$result_rows"
        s += ",$no_repetitions"
        s += ",$no_time"
        s += ",$no_time_per_repetition"
        s += ",$no_time_per_result_row"
        s += ",$no_time_per_triple"
        s += ",$with_repetitions"
        s += ",$with_time"
        s += ",$with_time_per_repetition"
        s += ",$with_time_per_result_row"
        s += ",$with_time_per_triple"
        s += ",$time_per_optimizer"
        benchmark_out.println(s)
benchmark_out.flush()
    }
}
benchmark_out.close()
