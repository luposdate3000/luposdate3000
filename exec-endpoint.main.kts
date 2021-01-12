#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystemExt.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:CompilerOptions("-Xmulti-platform")
import lupos.s00misc.EOperatingSystem
import lupos.s00misc.EOperatingSystemExt
import lupos.s00misc.Platform
import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.net.InetAddress
var releaseMode = "Disable"
var suspendMode = "Disable"
var inlineMode = "Disable"
var cleanedArgs = mutableListOf<String>()
for (arg in args) {
    if (arg.startsWith("--releaseMode=")) {
        releaseMode = arg.substring("--releaseMode=".length)
    } else if (arg.startsWith("--suspendMode=")) {
        suspendMode = arg.substring("--suspendMode=".length)
    } else if (arg.startsWith("--inlineMode=")) {
        inlineMode = arg.substring("--inlineMode=".length)
    } else {
        cleanedArgs.add(arg)
    }
}
var appendix = ""
if (suspendMode == "Enable") {
    appendix += "_Coroutines"
} else {
    appendix += "_Threads"
}
if (inlineMode == "Enable") {
    appendix += "_Inline"
} else {
    appendix += "_NoInline"
}
if (releaseMode == "Enable") {
    appendix += "_Release"
} else {
    appendix += "_Debug"
}
File("log").mkdirs()
val jars = mutableListOf(
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Buffer_Manager_Inmemory-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Dictionary_Inmemory-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Endpoint-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Operators-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Parser-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Result_Format-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Shared-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Test-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Triple_Store_All-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Triple_Store_Id_Triple-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Optimizer-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Endpoint_Java_Sockets-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Jena_Wrapper_Off-jvm.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Launch_Endpoint-jvm.jar",
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
        if (Platform.getOperatingSystem() == EOperatingSystemExt.Windows) {
            classpath = "$classpath;$jar"
        } else {
            classpath = "$classpath:$jar"
        }
    }
}
val cmd = mutableListOf("java", "-Xmx${Platform.getAvailableRam()}g", "-cp", classpath, "MainKt", InetAddress.getLocalHost().getHostName())
cmd.addAll(cleanedArgs)
ProcessBuilder(cmd)
    .redirectOutput(Redirect.INHERIT)
    .redirectError(Redirect.INHERIT)
    .start()
    .waitFor()

// curl -H "Content-Type: application/x-www-form-urlencoded" --data-binary "${Platform.getBenchmarkHome()}/luposdate-testdata/sp2b/1024/data0.n3" $(hostname):2324/import/turtle
// curl -H "Content-Type: application/x-www-form-urlencoded" --data-binary "@resources/sp2b/q10.sparql" $(hostname):2324/sparql/query
