#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystemExt.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/PlatformAlias.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:CompilerOptions("-Xmulti-platform")
import lupos.s00misc.EOperatingSystem
import lupos.s00misc.EOperatingSystemExt
import lupos.s00misc.Platform
import java.io.File
import java.lang.ProcessBuilder.Redirect
var releaseMode = "Disable"
var suspendMode = "Disable"
var inlineMode = "Disable"
var noPartitionsFlag = ""
var persistentFlag = "_Inmemory"
var proguardFlag = ""

var cleanedArgs = mutableListOf<String>()

for (arg in args) {
    if (arg.startsWith("--releaseMode=")) {
        releaseMode = arg.substring("--releaseMode=".length)
    } else if (arg.startsWith("--suspendMode=")) {
        suspendMode = arg.substring("--suspendMode=".length)
    } else if (arg.startsWith("--inlineMode=")) {
        inlineMode = arg.substring("--inlineMode=".length)
    } else if (arg == "--noPartitions") {
        noPartitionsFlag = "_NoPartitions"
    } else if (arg == "--persistent") {
        persistentFlag = "_Persistent"
    } else if (arg == "--proguard") {
        proguardFlag = "-pro"
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
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Buffer_Manager$persistentFlag-jvm$proguardFlag.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Dictionary_Inmemory-jvm$proguardFlag.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Endpoint-jvm$proguardFlag.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Operators-jvm$proguardFlag.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Parser-jvm$proguardFlag.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Result_Format-jvm$proguardFlag.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Shared-jvm$proguardFlag.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Test-jvm$proguardFlag.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Triple_Store_All$noPartitionsFlag-jvm$proguardFlag.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Triple_Store_Id_Triple-jvm$proguardFlag.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Optimizer$noPartitionsFlag-jvm$proguardFlag.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Endpoint_None-jvm$proguardFlag.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Jena_Wrapper_Off-jvm$proguardFlag.jar",
    "build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}Luposdate3000_Launch_Binary_Test_Suite-jvm$proguardFlag.jar",
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
val cmd = mutableListOf("java", "-Xmx${Platform.getAvailableRam()}g", "-cp", classpath, "MainKt")
cmd.addAll(cleanedArgs)
val p = ProcessBuilder(cmd)
    .redirectOutput(Redirect.INHERIT)
    .redirectError(Redirect.INHERIT)
    .start()
p.waitFor()
if (p.exitValue() != 0) {
    throw Exception("exit-code:: " + p.exitValue())
}
val configOld = mutableMapOf<String, String>()
File("resources${Platform.getPathSeparator()}binary${Platform.getPathSeparator()}config").forEachLine {
    val a = it.split("=")
    configOld[a[0]] = a[1]
}
val configNew = mutableMapOf<String, String>()
File("resources${Platform.getPathSeparator()}binary${Platform.getPathSeparator()}config2").forEachLine {
    val a = it.split("=")
    configNew[a[0]] = a[1]
}
for ((k, v) in configOld) {
    val v2 = configNew[k]
    if (v != v2) {
        println("$k $v -> $v2")
    }
}
