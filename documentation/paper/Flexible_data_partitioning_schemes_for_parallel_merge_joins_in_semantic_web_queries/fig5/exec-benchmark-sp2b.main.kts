#!/usr/bin/env kotlin
@file:Import("../src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("../src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/_Platform.kt")
@file:Import("../src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/_Platform.kt")
@file:Import("generate-data.kt")
@file:CompilerOptions("-Xmulti-platform")
import lupos.s00misc.Platform
import java.io.File
import java.lang.ProcessBuilder.Redirect
import kotlin.math.pow
val triplesFiles = "${Platform.getBenchmarkHome()}/luposdate-testdata/bench_4/intermediate.n3"
val minimumTime = 10.0
File("log").mkdirs()
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
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Launch_Benchmark_fig5-jvm.jar",
)
val userHome = Platform.getUserHome()
for (f in Platform.findNamedFileInDirectory("${Platform.getGradleCache()}modules-2${Platform.getPathSeparator()}files-2.1${Platform.getPathSeparator()}com.soywiz.korlibs.krypto${Platform.getPathSeparator()}krypto-jvm${Platform.getPathSeparator()}1.9.1${Platform.getPathSeparator()}", "krypto-jvm-1.9.1.jar")) {
    jars.add(f)
}
for (f in Platform.findNamedFileInDirectory("${Platform.getMavenCache()}org${Platform.getPathSeparator()}jetbrains${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}kotlin-stdlib${Platform.getPathSeparator()}1.4.255-SNAPSHOT", "kotlin-stdlib-1.4.255-SNAPSHOT.jar")) {
    jars.add(f)
}
var classpath = ""
for (jar in jars) {
    if (classpath == "") {
        classpath = jar
    } else {
        classpath = "$classpath:$jar"
    }
}
for (output_count in listOf(512, 2048, 8192, 32768)) {
    for (join_count in listOf(1, 2, 4, 8, 16)) {
        for (join in listOf(2, 4, 8, 16, 32, 64)) {
            try {
                val count2 = (output_count / (join.toDouble().pow(1 + join_count.toDouble()))).toInt()
                if (count2 == 0) {
                    continue
                }
                generateTriples("${Platform.getBenchmarkHome()}/luposdate-testdata/bench_4", count2, 0, join, join_count)
                val cmd = mutableListOf("java", "-Xmx${Platform.getAvailableRam()}g", "-cp", classpath, "MainKt", triplesFiles, "$minimumTime", "$output_count", "0", "$join", "$join_count")
                cmd.addAll(args)
                ProcessBuilder(cmd)
                    .directory(File("."))
                    .redirectOutput(Redirect.INHERIT)
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        for (trash in listOf(0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024)) {
            try {
                generateTriples("${Platform.getBenchmarkHome()}/luposdate-testdata/bench_4", output_count, trash, 1, join_count)
                val cmd = mutableListOf("java", "-Xmx${Platform.getAvailableRam()}g", "-cp", classpath, "MainKt", triplesFiles, "$minimumTime", "$output_count", "$trash", "1", "$join_count")
                cmd.addAll(args)
                ProcessBuilder(cmd)
                    .directory(File("."))
                    .redirectOutput(Redirect.INHERIT)
                    .redirectError(Redirect.INHERIT)
                    .start()
                    .waitFor()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
