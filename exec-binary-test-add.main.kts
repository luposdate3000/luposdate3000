#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:CompilerOptions("-Xmulti-platform")

import lupos.s00misc.Platform
import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
var releaseMode = ReleaseMode.Disable
var suspendMode = SuspendMode.Disable
var inlineMode = InlineMode.Disable

for (arg in args) {
    if (arg.startsWith("--releaseMode=")) {
        releaseMode = ReleaseMode.valueOf(arg.substring("--releaseMode=".length))
    } else if (arg.startsWith("--suspendMode=")) {
        suspendMode = SuspendMode.valueOf(arg.substring("--suspendMode=".length))
    } else if (arg.startsWith("--inlineMode=")) {
        inlineMode = InlineMode.valueOf(arg.substring("--inlineMode=".length))
    }
}
var appendix = ""
        if (suspendMode == SuspendMode.Enable) {
            appendix += "_Coroutines"
        } else {
            appendix += "_Threads"
        }
        if (inlineMode == InlineMode.Enable) {
            appendix += "_Inline"
        } else {
            appendix += "_NoInline"
        }
        if (releaseMode == ReleaseMode.Enable) {
            appendix += "_Release"
        } else {
            appendix += "_Debug"
        }
File("log").mkdirs()
val inputdata = args[0]
val sparql = args[1]
val targetdata = args[2]
val outputfoldername = args[3]
val testname = args[4]
val mode = args[5]
val jars = mutableListOf(
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Buffer_Manager_Inmemory$appendix-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Dictionary_Inmemory$appendix-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Endpoint$appendix-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Operators$appendix-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Parser$appendix-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Result_Format$appendix-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Shared$appendix-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Test$appendix-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Triple_Store_All$appendix-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Triple_Store_Id_Triple$appendix-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Optimizer$appendix-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Endpoint_None$appendix-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Jena_Wrapper_Off$appendix-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Launch_Binary_Test_Suite$appendix-jvm.jar",
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
println(classpath)
ProcessBuilder("java", "-Xmx${Platform.getAvailableRam()}g", "-cp", classpath, "MainKt", "--generate", inputdata, sparql, targetdata, "resources${Platform.getPathSeparator()}binary${Platform.getPathSeparator()}$outputfoldername", testname, mode)
    .redirectOutput(Redirect.INHERIT)
    .redirectError(Redirect.INHERIT)
    .start()
    .waitFor()
Files.write(Paths.get("resources${Platform.getPathSeparator()}binary${Platform.getPathSeparator()}config"), "$outputfoldername=enabled\n".toByteArray(), StandardOpenOption.APPEND)
