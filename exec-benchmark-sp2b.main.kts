#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:CompilerOptions("-Xmulti-platform")

import lupos.s00misc.Platform

import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.nio.file.StandardOpenOption
import java.nio.file.Files
import java.nio.file.Paths

val triplesFiles="/mnt/luposdate-testdata/sp2b/1048576/complete.n3"
val queryFiles="resources/sp2b/q10.sparql;resources/sp2b/q11.sparql;resources/sp2b/q12a.sparql;resources/sp2b/q12b.sparql;resources/sp2b/q12c.sparql;resources/sp2b/q1.sparql;resources/sp2b/q2.sparql;resources/sp2b/q3a.sparql;resources/sp2b/q3b.sparql;resources/sp2b/q3c.sparql;resources/sp2b/q4.sparql;resources/sp2b/q5a.sparql;resources/sp2b/q5b.sparql;resources/sp2b/q6.sparql;resources/sp2b/q7.sparql;resources/sp2b/q8.sparql;resources/sp2b/q9.sparql"
val minimumTime=10.0
val numberOfTriples=1048576

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
for (jar in jars) {
    if (classpath == "") {
        classpath = jar
    } else {
        classpath = "$classpath:$jar"
    }
}
val cmd = mutableListOf("java", "-Xmx60g", "-cp", classpath, "MainKt",triplesFiles,queryFiles,"$minimumTime","$numberOfTriples")
cmd.addAll(args)
ProcessBuilder(cmd)
        .redirectOutput(Redirect.INHERIT)
        .redirectError(Redirect.INHERIT)
        .start()
        .waitFor()
