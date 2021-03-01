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
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystemExt.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/modulename/_Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/modulename/_Platform.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-inline.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-suspend.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-module.kt")
@file:Import("src/luposdate3000_scripting/parsergenerator.kt")
@file:CompilerOptions("-Xmulti-platform")

import lupos.s00misc.EOperatingSystemExt
import lupos.s00misc.Platform
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.ProcessBuilder.Redirect
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption.REPLACE_EXISTING
import java.util.jar.JarFile

var compileModuleArgs = mutableMapOf<String, MutableMap<String, String>>()
var jsBrowserMode = true
var releaseMode = ""
var suspendMode = ""
var inlineMode = ""
var partitionMode = ""
var memoryMode = ""
var proguardMode = ""
var mainClass = ""
var jenaWrapper = ""
var endpointMode = ""
var dryMode = ""
var target = ""
var intellijMode = ""
var runArgs = mutableListOf<String>()
var skipArgs = false
var compileSpecific: String? = null
var threadCount = 1
var processUrls = ""

enum class ExecMode { RUN, COMPILE, HELP, COMPILE_AND_RUN, GENERATE_PARSER, GENERATE_LAUNCHER, GENERATE_ENUMS, SETUP_INTELLIJ_IDEA, SETUP_JS, ALL_TEST, UNKNOWN }

var execMode = ExecMode.UNKNOWN

enum class ParamClassMode { VALUES, NO_VALUE, FREE_VALUE }

fun getAllModuleConfigurations(): List<CreateModuleArgs> {
    var releaseMode2 = ReleaseMode.valueOf(releaseMode)
    var suspendMode2 = SuspendMode.valueOf(suspendMode)
    var inlineMode2 = InlineMode.valueOf(inlineMode)
    var dryMode2 = DryMode.valueOf(dryMode)
    var target2 = TargetMode.valueOf(target)
    var intellijMode2 = IntellijMode.valueOf(intellijMode)
    val localArgs = CreateModuleArgs()
        .ssetReleaseMode(releaseMode2)
        .ssetSuspendMode(suspendMode2)
        .ssetInlineMode(inlineMode2)
        .ssetDryMode(dryMode2)
        .ssetTarget(target2)
        .ssetIdeaBuildfile(intellijMode2)
        .ssetCodegenKSP(false)
        .ssetCodegenKAPT(false)
    var modules = mutableMapOf<String, CreateModuleArgs>()
    val dependencyMap = mutableMapOf<String, Set<String>>()
    Files.walk(Paths.get("src"), 1).forEach { it ->
        val filename = it.toString()
        val f = File(filename + "/module_config")
        if (f.exists()) {
            var pkg = ""
            var name = ""
            var ksp = false
            var kapt = false
            var enabledFunc: () -> Boolean = { true }
            var enabledRunFunc: () -> Boolean = { true }
            f.forEachLine { line ->
                when {
                    line == "codegenKAPT=true" -> {
                        kapt = true
                    }
                    line == "codegenKSP=true" -> {
                        ksp = true
                    }
                    line.startsWith("name=") -> {
                        name = line.substring("name=".length)
                    }
                    line.startsWith("package=") -> {
                        pkg = line.substring("package=".length)
                    }
                    line.startsWith("enabled=") -> {
                        when (line) {
                            "enabled=always" -> {
                                enabledFunc = { true }
                            }
                            "enabled=intellijOnly" -> {
                                enabledFunc = { intellijMode == "Enable" }
                            }
                            else -> {
                                throw Exception("unknown value")
                            }
                        }
                    }
                    line.startsWith("enabledRun=") -> {
                        when (line) {
                            "enabledRun=always" -> {
                                enabledRunFunc = { true }
                            }
                            "enabledRun=never" -> {
                                enabledRunFunc = { false }
                            }
                            "enabledRun=endpointMode:Java_Sockets" -> {
                                enabledRunFunc = { endpointMode == "Java_Sockets" }
                            }
                            "enabledRun=jsBrowserMode:true" -> {
                                enabledRunFunc = { jsBrowserMode && target == "JS" }
                            }
                            "enabledRun=jsBrowserMode:false" -> {
                                enabledRunFunc = { !jsBrowserMode && target == "JS" }
                            }
                            "enabledRun=endpointMode:None" -> {
                                enabledRunFunc = { endpointMode == "None" }
                            }
                            "enabledRun=jenaWrapper:Off" -> {
                                enabledRunFunc = { jenaWrapper == "Off" }
                            }
                            "enabledRun=jenaWrapper:On" -> {
                                enabledRunFunc = { jenaWrapper == "On" }
                            }
                            "enabledRun=mainClass:Benchmark" -> {
                                enabledRunFunc = { mainClass == "Benchmark" }
                            }
                            "enabledRun=mainClass:Binary_Test_Suite" -> {
                                enabledRunFunc = { mainClass == "Binary_Test_Suite" }
                            }
                            "enabledRun=mainClass:Code_Gen_Example_KAPT" -> {
                                enabledRunFunc = { mainClass == "Code_Gen_Example_KAPT" }
                            }
                            "enabledRun=mainClass:Code_Gen_Example_KSP" -> {
                                enabledRunFunc = { mainClass == "Code_Gen_Example_KSP" }
                            }
                            "enabledRun=mainClass:Endpoint" -> {
                                enabledRunFunc = { mainClass == "Endpoint" }
                            }
                            "enabledRun=mainClass:Generate_Binary_Test_Suite_Multi" -> {
                                enabledRunFunc = { mainClass == "Generate_Binary_Test_Suite_Multi" }
                            }
                            "enabledRun=mainClass:Generate_Binary_Test_Suite_Single" -> {
                                enabledRunFunc = { mainClass == "Generate_Binary_Test_Suite_Single" }
                            }
                            "enabledRun=mainClass:Import" -> {
                                enabledRunFunc = { mainClass == "Import" }
                            }
                            "enabledRun=mainClass:Sparql_Test_Suite" -> {
                                enabledRunFunc = { mainClass == "Sparql_Test_Suite" }
                            }
                            "enabledRun=memoryMode:_Inmemory" -> {
                                enabledRunFunc = { memoryMode == "_Inmemory" }
                            }
                            "enabledRun=memoryMode:_Persistent" -> {
                                enabledRunFunc = { memoryMode == "_Persistent" }
                            }
                            else -> {
                                throw Exception("unknown value '$line'")
                            }
                        }
                    }
                    else -> {
                        throw Exception("unknown value")
                    }
                }
            }
            if (name.length > 0) {
                if (pkg == "") {
                    pkg = name
                }
                modules[name] = (
                    localArgs
                        .ssetModuleName(name, pkg)
                        .ssetArgs2(compileModuleArgs)
                        .ssetEnabledFunc(enabledFunc)
                        .ssetEnabledRunFunc(enabledRunFunc)
                        .ssetCodegenKSP(ksp)
                        .ssetCodegenKAPT(kapt)
                    )
                val dep = mutableSetOf<String>()
                if (!name.startsWith("Luposdate3000_Shared")) {
                    dep.add("Luposdate3000_Shared")
                }
                if (!name.startsWith("Luposdate3000_Shared_")) {
                    dep.add("Luposdate3000_Shared_BrowserJS")
                }
                dependencyMap[name] = dep
                for (t in listOf("js", "jvm", "common", "native")) {
                    val f2 = File(filename + "/${t}Dependencies")
                    if (f2.exists()) {
                        f2.forEachLine { line ->
                            if (line.startsWith("luposdate3000:")) {
                                dep.add(line.substring("luposdate3000:".length, line.lastIndexOf(":")))
                            }
                        }
                    }
                }
            }
        }
    }
    var res = mutableListOf<CreateModuleArgs>()
    val nameSet = mutableSetOf<String>()
    var changed = true
    while (changed) {
        changed = false
        loop@ for ((k, v) in modules) {
            if (!nameSet.contains(k)) {
                for (d in dependencyMap[k]!!) {
                    if (!nameSet.contains(d)) {
                        continue@loop
                    }
                }
                changed = true
                nameSet.add(k)
                res.add(v)
            }
        }
    }
    if (res.size != modules.size || res.size != nameSet.size) {
        throw Exception("something wrong ${modules.keys} ------- $nameSet")
    }
    return res
}

class ParamClass {
    val name: String
    val default: String
    val values: Map<String, () -> Unit>
    val action: () -> Unit
    val action2: (String) -> Unit
    val mode: ParamClassMode
    var additionalHelp: (String) -> Unit = {}

    constructor (name: String, default: String, values: Map<String, () -> Unit>) {
        this.name = name
        this.default = default
        this.values = values
        this.action = {}
        this.action2 = {}
        this.mode = ParamClassMode.VALUES
    }

    constructor(name: String, action: () -> Unit) {
        this.name = name
        this.default = ""
        this.values = mapOf()
        this.action = action
        this.action2 = {}
        this.mode = ParamClassMode.NO_VALUE
    }

    constructor(name: String, default: String, action: (String) -> Unit) {
        this.name = name
        this.default = default
        this.values = mapOf()
        this.action2 = action
        this.action = {}
        this.mode = ParamClassMode.FREE_VALUE
    }

    fun setAdditionalHelp(additionalHelp: (String) -> Unit): ParamClass {
        this.additionalHelp = additionalHelp
        return this
    }

    fun execDefault() {
        if (mode == ParamClassMode.VALUES) {
            values[default]!!()
        }
    }

    fun exec(arg: String) {
        when (mode) {
            ParamClassMode.VALUES -> {
                val value = arg.substring(name.length + 1)
                if (!values.contains(value)) {
                    throw Exception("'$name' does not allow the value '$value'")
                }
                values[value]!!()
            }
            ParamClassMode.NO_VALUE -> {
                if (arg != name) {
                    throw Exception("'$name' does not allow Values")
                }
                action()
            }
            ParamClassMode.FREE_VALUE -> {
                if (arg == name) {
                    throw Exception("'$name' does not allow empty value")
                }
                val value = arg.substring(name.length + 1)
                action2(value)
            }
        }
    }

    fun help(indention: String = "") {
        when (mode) {
            ParamClassMode.VALUES -> {
                println("$indention  $name=${values.keys.map { it }.toString().replace("[", "<").replace("]", ">").replace(", ", "|")}  default=$default")
            }
            ParamClassMode.NO_VALUE -> {
                println("$indention  $name")
            }
            ParamClassMode.FREE_VALUE -> {
                println("$indention  $name=String")
            }
        }
        additionalHelp("$indention  ")
    }
}

fun getAllModuleSpecificParams(): List<ParamClass> {
    val res = mutableListOf<ParamClass>()
    for (module in getAllModuleConfigurations()) {
        for (opt in module.getPossibleOptions()) {
            res.add(
                ParamClass(
                    "--compileArgument_${module.moduleName}:$opt",
                    "",
                    {
                        var t = compileModuleArgs[module.moduleName]
                        if (t == null) {
                            t = mutableMapOf<String, String>()
                            compileModuleArgs[module.moduleName] = t
                        }
                        t[opt] = it
                    }
                )
            )
        }
        if (module.modulePrefix == "Luposdate3000_Main") {
            if (File(File(module.moduleFolder), "runOptions").exists()) {
                File(File(module.moduleFolder), "runOptions").forEachLine { opt ->
                    res.add(
                        ParamClass(
                            "--runArgument_${module.moduleName}:$opt",
                            "",
                            {
                                runArgs.add("--$opt=$it")
                            }
                        )
                    )
                }
            }
        }
    }
    return res
}

val compileParams = mutableListOf<ParamClass>()
var enabledParams = mutableListOf<ParamClass>()
val defaultParams = mutableListOf(
    ParamClass(
        "--dryMode",
        "Disable",
        mapOf(
            "Enable" to { dryMode = "Enable" },
            "Disable" to { dryMode = "Disable" },
        )
    ),
    ParamClass(
        "--intellijMode",
        "Disable",
        mapOf(
            "Enable" to { intellijMode = "Enable" },
            "Disable" to { intellijMode = "Disable" },
        )
    ),
    ParamClass(
        "--target",
        "JVM",
        mapOf(
            "JVM" to { target = "JVM" },
            "JS" to { target = "JS" },
            "Native" to { target = "Native" },
            "All" to { target = "All" },
        )
    ),
    ParamClass(
        "--threadCount",
        "",
        {
            threadCount = it.toInt()
        }
    ),
    ParamClass(
        "--processUrls",
        "",
        {
            processUrls = it
        }
    ),
    ParamClass(
        "--jenaWrapper",
        "Off",
        mapOf(
            "On" to { jenaWrapper = "On" },
            "Off" to { jenaWrapper = "Off" },
        )
    ),
    ParamClass(
        "--releaseMode",
        "Disable",
        mapOf(
            "Enable" to { releaseMode = "Enable" },
            "Disable" to { releaseMode = "Disable" },
        )
    ),
    ParamClass(
        "--suspendMode",
        "Disable",
        mapOf(
            "Enable" to { suspendMode = "Enable" },
            "Disable" to { suspendMode = "Disable" },
        )
    ),
    ParamClass(
        "--inlineMode",
        "Disable",
        mapOf(
            "Enable" to { inlineMode = "Enable" },
            "Disable" to { inlineMode = "Disable" },
        )
    ),
    ParamClass(
        "--partitionMode",
        "Thread",
        mapOf(
            "Thread" to { partitionMode = "Thread" },
            "Process" to { partitionMode = "Process" },
            "None" to { partitionMode = "None" },
        )
    ),
    ParamClass(
        "--memoryMode",
        "inmemory",
        mapOf(
            "persistent" to { memoryMode = "_Persistent" },
            "inmemory" to { memoryMode = "_Inmemory" },
        )
    ),
    ParamClass(
        "--proguardMode",
        "Off",
        mapOf(
            "On" to { proguardMode = "-pro" },
            "Off" to { proguardMode = "" },
        )
    ),
    ParamClass(
        "--endpointMode",
        "None",
        mapOf(
            "None" to { endpointMode = "None" },
            "Java_Sockets" to { endpointMode = "Java_Sockets" },
        )
    ),
    ParamClass(
        "--mainClass",
        "Endpoint",
        mapOf(
            "Binary_Test_Suite" to { mainClass = "Binary_Test_Suite" },
            "Benchmark" to { mainClass = "Benchmark" },
            "Benchmark_Fig5" to { mainClass = "Benchmark_Fig5" },
            "Code_Gen_Example_KAPT" to { mainClass = "Code_Gen_Example_KAPT" },
            "Code_Gen_Example_KSP" to { mainClass = "Code_Gen_Example_KSP" },
            "Endpoint" to { mainClass = "Endpoint" },
            "Generate_Binary_Test_Suite_Single" to { mainClass = "Generate_Binary_Test_Suite_Single" },
            "Generate_Binary_Test_Suite_Multi" to { mainClass = "Generate_Binary_Test_Suite_Multi" },
            "Import" to { mainClass = "Import" },
            "Sparql_Test_Suite" to { mainClass = "Sparql_Test_Suite" },
        )
    ),
    ParamClass(
        "--help",
        {
            execMode = ExecMode.HELP
            skipArgs = true
        }
    ),
    ParamClass(
        "--allTest",
        {
            execMode = ExecMode.ALL_TEST
            skipArgs = true
        }
    ),
    ParamClass(
        "--generateParser",
        {
            execMode = ExecMode.GENERATE_PARSER
            skipArgs = true
        }
    ),
    ParamClass(
        "--generateLauncher",
        {
            execMode = ExecMode.GENERATE_LAUNCHER
            skipArgs = true
        }
    ),
    ParamClass(
        "--generateEnums",
        {
            execMode = ExecMode.GENERATE_ENUMS
            skipArgs = true
        }
    ),
    ParamClass(
        "--run",
        {
            execMode = ExecMode.RUN
        }
    ),
    ParamClass(
        "--compile",
        "",
        {
            enableParams(compileParams)
            compileSpecific = it
            execMode = ExecMode.COMPILE
        }
    ),
    ParamClass(
        "--compileAll",
        {
            enableParams(compileParams)
            execMode = ExecMode.COMPILE
        }
    ).setAdditionalHelp {
        for (param in compileParams) {
            param.help(it)
        }
    },
    ParamClass(
        "--compileAndRun",
        {
            enableParams(compileParams)
            execMode = ExecMode.COMPILE_AND_RUN
        }
    ).setAdditionalHelp {
        for (param in compileParams) {
            param.help(it)
        }
    },
    ParamClass(
        "--clearCaches",
        {
            if (Platform.getOperatingSystem() == EOperatingSystemExt.Windows) {
                File("${System.getenv("LOCALAPPDATA")}${Platform.getPathSeparator()}main.kts.compiled.cache").deleteRecursively()
            } else {
                File("${System.getProperty("user.home")}${Platform.getPathSeparator()}.cache${Platform.getPathSeparator()}main.kts.compiled.cache").deleteRecursively()
            }
            File(Platform.getGradleCache() + "${Platform.getPathSeparator()}modules-2").deleteRecursively()
            File(Platform.getGradleCache() + "${Platform.getPathSeparator()}jars-8").deleteRecursively()
            File(Platform.getGradleCache() + "${Platform.getPathSeparator()}jars-3").deleteRecursively()
            File(Platform.getMavenCache() + "${Platform.getPathSeparator()}luposdate3000").deleteRecursively()
            File("build-cache").deleteRecursively()
            System.exit(0)
        }
    ),
    ParamClass(
        "--setupJS",
        {
            enableParams(compileParams)
            execMode = ExecMode.SETUP_JS
            target = "JS"
        }
    ),
    ParamClass(
        "--setupIntellijIdea",
        {
            enableParams(compileParams)
            execMode = ExecMode.SETUP_INTELLIJ_IDEA
            releaseMode = "Disable"
            suspendMode = "Disable"
            inlineMode = "Disable"
            dryMode = "Enable"
            target = "JVM"
            intellijMode = "Enable"
        }
    ),
)

fun enableParams(params: List<ParamClass>) {
    for (param in params) {
        param.execDefault()
        enabledParams.add(param)
    }
}
enableParams(defaultParams)
enableParams(getAllModuleSpecificParams())
loop@ for (arg in args) {
    for (param in enabledParams) {
        if (arg.startsWith(param.name + "=")) {
            param.exec(arg)
            if (skipArgs) {
                break@loop
            }
            continue@loop
        }
    }
    for (param in enabledParams) {
        if (arg == param.name) {
            param.exec(arg)
            if (skipArgs) {
                break@loop
            }
            continue@loop
        }
    }
    throw Exception("unknown argument $arg")
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
when (execMode) {
    ExecMode.HELP -> onHelp()
    ExecMode.COMPILE -> onCompile()
    ExecMode.RUN -> onRun()
    ExecMode.GENERATE_PARSER -> onGenerateParser()
    ExecMode.GENERATE_LAUNCHER -> onGenerateLauncherMain()
    ExecMode.GENERATE_ENUMS -> onGenerateEnums()
    ExecMode.SETUP_INTELLIJ_IDEA -> onSetupIntellijIdea()
    ExecMode.SETUP_JS -> onSetupJS()
    ExecMode.ALL_TEST -> onAllTest()
    ExecMode.COMPILE_AND_RUN -> {
        onCompile()
        onRun()
    }
    else -> {
        throw Exception("unknown execMode $execMode")
    }
}
fun onHelp() {
    println("Usage ./launcher.main.kts <options>")
    println("where possible options include:")
    for (param in defaultParams) {
        param.help()
    }
    for (param in getAllModuleSpecificParams()) {
        param.help()
    }
}

fun onCompile() {
    println(compileModuleArgs)
    var foundit = false
    for (module in getAllModuleConfigurations()) {
        if (module.enabledFunc()) {
            if (compileSpecific == null || compileSpecific!!.toLowerCase() == module.moduleName.toLowerCase()) {
                createBuildFileForModule(module)
                foundit = true
            }
        }
    }
    if (foundit == false && compileSpecific != null) {
        for (module in getAllModuleConfigurations()) {
            if (module.enabledFunc()) {
                if (compileSpecific == null || module.moduleName.toLowerCase().startsWith(compileSpecific!!.toLowerCase())) {
                    createBuildFileForModule(module)
                }
            }
        }
    }
    if (compileModuleArgs.size > 0) {
        for ((k, v) in compileModuleArgs) {
            println("unknown module argument '$k'")
        }
        throw Exception("there are unkown arguments")
    }
}

fun onSetupIntellijIdea() {
    File(".idea").deleteRecursively()
    File("log").mkdirs()
    onCompile()
    File("build.gradle.kts").printWriter().use { outBuildGradle ->
        outBuildGradle.println("dependencies {")
        outBuildGradle.println("    project(\":src\")")
        outBuildGradle.println("}")
    }
    File("settings.gradle").printWriter().use { outSettingsGradle ->
        File("src${Platform.getPathSeparator()}build.gradle.kts").printWriter().use { outBuildGradle ->
            outSettingsGradle.println("pluginManagement {")
            outSettingsGradle.println("    repositories {")
            outSettingsGradle.println("        mavenLocal()")
            outSettingsGradle.println("        gradlePluginPortal()")
            outSettingsGradle.println("    }")
            outSettingsGradle.println("}")
            outSettingsGradle.println("rootProject.name = \"Luposdate3000\"")
            outSettingsGradle.println("include(\":src\")")
            outBuildGradle.println("dependencies {")
            Files.walk(Paths.get("src"), 1).forEach { it ->
                val name = it.toString()
                println(name)
                if (name.startsWith("src/lupos") || name.startsWith("src\\lupos")) {
                    if (!name.contains("shared_inline")) {
                        outSettingsGradle.println("include(\":src:${name.substring(4)}\")")
                        outBuildGradle.println("    project(\":src:${name.substring(4)}\")")
                    }
                }
            }
            outBuildGradle.println("}")
        }
    }
}

fun onRun() {
    File("log").mkdirs()
    when (target) {
        "JVM", "All" -> {
            val jarsLuposdate3000 = mutableListOf<String>()
            for (module in getAllModuleConfigurations()) {
                if (module.enabledRunFunc()) {
                    jarsLuposdate3000.add("build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}${module.moduleName}-jvm$proguardMode.jar")
                }
            }
            val jars = mutableSetOf<String>()
            for (jar in jarsLuposdate3000) {
                jars.add(jar)
                File("${jar.substring(0, jar.length - 4 - proguardMode.length)}.classpath").forEachLine {
                    jars.add(it)
                }
            }
            var classpath = ""
            for (jar in jars.sorted()) {
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
            cmd.addAll(runArgs)
            if (dryMode == "Enable") {
                println("export LUPOS_PROCESS_URLS=processUrls")
                println("export LUPOS_THREAD_COUNT=$threadCount")
                println("export LUPOS_PARTITION_MODE=$partitionMode")
                println("exec :: " + cmd.joinToString(" "))
            } else {
                val processes = Array(processUrls.count { it == ',' } + 1) {
                    val p = ProcessBuilder(cmd)
                        .redirectOutput(Redirect.INHERIT)
                        .redirectError(Redirect.INHERIT)
                    val env = p.environment()
                    env["LUPOS_PROCESS_ID"] = "$it"
                    env["LUPOS_PROCESS_URLS"] = processUrls
                    env["LUPOS_THREAD_COUNT"] = "$threadCount"
                    env["LUPOS_PARTITION_MODE"] = partitionMode
                    p.start()
                }.forEach {
                    it.waitFor()
                    if (it.exitValue() != 0) {
                        throw Exception("exit-code:: " + it.exitValue())
                    }
                }
            }
        }
        "JS" -> {
            if (memoryMode != "_Inmemory") {
                throw Exception("JS can only use 'Inmemory' as memoryMode")
            }
            if (jenaWrapper != "Off") {
                throw Exception("JS can only use 'Off' as jenaWrapper")
            }
            if (partitionMode != "None") {
                throw Exception("JS can only use 'None' as partitionMode")
            }
            File("build-cache${Platform.getPathSeparator()}node_modules").deleteRecursively()
            File("build-cache${Platform.getPathSeparator()}node_modules").mkdirs()
            class JSHelper(val path: String, val name: String)
            jsBrowserMode = false
            val files = mutableListOf<JSHelper>()
            for (module in getAllModuleConfigurations()) {
                if (module.enabledRunFunc() && module.modulePrefix != "Luposdate3000_Main") {
                    if (module.modulePrefix == module.moduleName) {
                        files.add(JSHelper("build-cache${Platform.getPathSeparator()}bin$appendix", "${module.modulePrefix}.js"))
                    } else {
                        files.add(JSHelper("build-cache${Platform.getPathSeparator()}bin$appendix${Platform.getPathSeparator()}${module.moduleName}", "${module.modulePrefix}.js"))
                    }
                }
            }
            for (f in files) {
                Files.copy(File(f.path + Platform.getPathSeparator() + f.name).toPath(), File("build-cache${Platform.getPathSeparator()}node_modules${Platform.getPathSeparator()}${f.name}").toPath(), REPLACE_EXISTING)
                Files.copy(File(f.path + Platform.getPathSeparator() + f.name + ".map").toPath(), File("build-cache${Platform.getPathSeparator()}node_modules${Platform.getPathSeparator()}${f.name}.map").toPath(), REPLACE_EXISTING)
            }
            copyJSLibsIntoFolder("build-cache${Platform.getPathSeparator()}node_modules")
            File("build-cache${Platform.getPathSeparator()}nodeJsMain.js").printWriter().use { out ->
                out.println("var mainLauncher = require(\"Luposdate3000_Main.js\")")
                out.println("mainLauncher.mainFunc(process.argv.slice(2))")
            }
            val p = ProcessBuilder("node", "build-cache${Platform.getPathSeparator()}nodeJsMain.js")
                .redirectOutput(Redirect.INHERIT)
                .redirectError(Redirect.INHERIT)
                .start()
            p.waitFor()
            if (p.exitValue() != 0) {
                throw Exception("exit-code:: " + p.exitValue())
            }
        }
    }
}

fun onGenerateParser() {
    val turtleGeneratingArgs = arrayOf(
        listOf("PARSER_CONTEXT"),
        listOf("parse_dot", "DOT"),
        listOf("parse_ws", "SKIP_WS"),
        listOf("parse_ws_forced", "SKIP_WS_FORCED"),
        listOf("parse_statement", "BASE", "PREFIX", "BASE2", "PREFIX2", "IRIREF", "PNAME_NS", "BLANK_NODE_LABEL"),
        listOf("parse_base", "IRIREF"),
        listOf("parse_prefix", "PNAME_NS"),
        listOf("parse_prefix2", "IRIREF"),
        listOf("parse_predicate", "VERB1", "IRIREF", "PNAME_NS"),
        listOf("parse_obj", "IRIREF", "PNAME_NS", "BLANK_NODE_LABEL", "STRING_LITERAL_QUOTE", "STRING_LITERAL_SINGLE_QUOTE", "STRING_LITERAL_LONG_SINGLE_QUOTE", "STRING_LITERAL_LONG_QUOTE", "INTEGER", "DECIMAL", "DOUBLE", "BOOLEAN"),
        listOf("parse_triple_end", "PREDICATE_LIST1", "OBJECT_LIST1", "DOT"),
        listOf("parse_triple_end_or_object_iri", "PN_LOCAL", "PREDICATE_LIST1", "OBJECT_LIST1", "DOT", "SKIP_WS_FORCED"),
        listOf("parse_triple_end_or_object_string", "LANGTAG", "IRI1", "PREDICATE_LIST1", "OBJECT_LIST1", "DOT", "SKIP_WS_FORCED"),
        listOf("parse_triple_end_or_object_string_typed", "IRIREF", "PNAME_NS"),
        listOf("parse_triple_end_or_object_string_typed_iri", "PN_LOCAL", "PREDICATE_LIST1", "OBJECT_LIST1", "DOT", "SKIP_WS_FORCED"),
        listOf("parse_subject_iri_or_ws", "PN_LOCAL", "SKIP_WS_FORCED"),
        listOf("parse_predicate_iri_or_ws", "PN_LOCAL", "SKIP_WS_FORCED"),
    )
    val turtleGrammar = mapOf(
        "EXPONENT" to "[eE] [+-]? [0-9]+",
        "DOUBLE" to "[+-]? ([0-9]+ '.' [0-9]* EXPONENT | '.' [0-9]+ EXPONENT | [0-9]+ EXPONENT)",
        "DECIMAL" to "[+-]? [0-9]* '.' [0-9]+",
        "INTEGER" to "[+-]? [0-9]+",
        "PN_LOCAL_ESC" to "'\\\\' ('_' | '~' | '.' | '-' | '!' | '$' | '&' | '\\'' | '(' | ')' | '*' | '+' | ',' | ';' | '=' | '/' | '?' | '#' | '@' | '%')",
        "HEX" to "([0-9] | [A-F] | [a-f])",
        "PERCENT" to "'%' HEX HEX",
        "PLX" to "(PERCENT | PN_LOCAL_ESC)",
        "PN_CHARS_BASE" to "([A-Z] | [a-z] | [#x00C0-#x00D6] | [#x00D8-#x00F6] | [#x00F8-#x02FF] | [#x0370-#x037D] | [#x037F-#x1FFF] | [#x200C-#x200D] | [#x2070-#x218F] | [#x2C00-#x2FEF] | [#x3001-#xD7FF] | [#xF900-#xFDCF] | [#xFDF0-#xFFFD] | [#x10000-#x1fffff])",
        "PN_CHARS_U" to "(PN_CHARS_BASE | '_')",
        "PN_PREFIX" to "PN_CHARS_BASE ([.]* PN_CHARS)*",
        "UCHAR" to "(('\\\\') 'u' HEX HEX HEX HEX | ('\\\\') 'U' HEX HEX HEX HEX HEX HEX HEX HEX)",
        "PN_CHARS" to "(PN_CHARS_U | '-' | [0-9] | #x00B7 | [#x0300-#x036F] | [#x203F-#x2040])",
        "PN_LOCAL" to "(PN_CHARS_U | ':' | [0-9] | PLX) ([.]* (PN_CHARS | ':' | PLX))*", // TODO this includes a trailling dot, which is wrong due to the given grammar
        "ANON" to "'[' [#x20#x9#xD#xA]* ']'",
        "ECHAR" to "('\\\\') ([tbnrf\"'\\])",
        "PNAME_NS" to "(PN_PREFIX)? ':'",
        "PNAME_LN" to "PNAME_NS PN_LOCAL",
        "LANGTAG" to "'@' [a-zA-Z]+ ('-' [a-zA-Z0-9]+)*",
        "STRING_LITERAL_LONG_QUOTE" to "'\"' '\"' '\"' (STRING_LITERAL_LONG_QUOTE_A | ('\"' STRING_LITERAL_LONG_QUOTE_A) | ('\"' '\"' STRING_LITERAL_LONG_QUOTE_A) | ('\"' '\"' '\"' (=)))* (!)",
        "STRING_LITERAL_LONG_QUOTE_A" to "([^\"\\] | ECHAR | UCHAR)",
        "STRING_LITERAL_LONG_SINGLE_QUOTE" to "'\\'' '\\'' '\\'' (STRING_LITERAL_LONG_SINGLE_QUOTE_A | ('\\'' STRING_LITERAL_LONG_SINGLE_QUOTE_A) | ('\\'' '\\'' STRING_LITERAL_LONG_SINGLE_QUOTE_A) | ('\\'' '\\'' '\\'' (=)))* (!)",
        "STRING_LITERAL_LONG_SINGLE_QUOTE_A" to "([^\\'\\] | ECHAR | UCHAR)",
        "STRING_LITERAL_SINGLE_QUOTE" to "((('\\'') ([^#x27#x5C#xA#xD] | ECHAR | UCHAR) ([^#x27#x5C#xA#xD] | ECHAR | UCHAR)* '\\'') | (('\\'') ('\\'')))",
        "STRING_LITERAL_QUOTE" to "((('\"') ([^#x22#x5C#xA#xD] | ECHAR | UCHAR) ([^#x22#x5C#xA#xD] | ECHAR | UCHAR)* '\"') | (('\"') ('\"')))",
        "BLANK_NODE_LABEL" to "'_' ':' (PN_CHARS_U | [0-9]) ([.]* PN_CHARS)*", // TODO this includes a trailling dot, which is wrong due to the given grammar
        "IRIREF" to "'<' (IRIREF_A)* '>'",
        "IRIREF_A" to "IRIREF_B | UCHAR",
        "IRIREF_B" to "[^#x00-#x20<>\"{}|^`\\]",
        "BOOLEAN" to "(('t') ('r') ('u') ('e')) | (('f') ('a') ('l') ('s') ('e'))",
        "PREFIX" to "('P') ('R') ('E') ('F') ('I') ('X')",
        "BASE" to "('B') ('A') ('S') ('E')",
        "PREFIX2" to "('@') ('p') ('r') ('e') ('f') ('i') ('x')",
        "BASE2" to "('@') ('b') ('a') ('s') ('e')",
        "COLLECTION1" to "('(')",
        "COLLECTION2" to "(')')",
        "DOT" to "('.')",
        "PROPERTY_LIST1" to "('[')",
        "PROPERTY_LIST2" to "(']')",
        "OBJECT_LIST1" to "(',')",
        "PREDICATE_LIST1" to "(';')",
        "VERB1" to "('a')",
        "IRI1" to "('^') ('^')",
        "SKIP_WS_FORCED" to "[#x20#x9#xD#xA]+",
        "SKIP_WS" to "[#x20#x9#xD#xA]*",
    )
    val turtleFilename = "src${Platform.getPathSeparator()}luposdate3000_parser${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s02buildSyntaxTree${Platform.getPathSeparator()}turtle${Platform.getPathSeparator()}Turtle2ParserGenerated.kt"
    val turtlePackage = "lupos.s02buildSyntaxTree.turtle"
    val xmlGeneratingArgs = arrayOf(
        listOf("PARSER_CONTEXT"),
        listOf("parse_ws", "SKIP_WS"),
        listOf("parse_ws_forced", "SKIP_WS_FORCED"),
        listOf("parse_element_start", "ELEMENT_START"),
        listOf("parse_element_tag_or_immediate_close_char", "ELEMENT_END_PART", "TAG"),
        listOf("parse_element_tag", "TAG"),
        listOf("parse_element_close", "ELEMENT_CLOSE_LATER"),
        listOf("parse_attribute_or_close_tag", "ATTRIBUTE_NAME", "ELEMENT_CLOSE_IMMEDIATELY", "ELEMENT_CLOSE_LATER"),
        listOf("parse_attribute_assinment", "ATTRIBUTE_ASSIGNMENT"),
        listOf("parse_attribute_value", "ATTRIBUTE_VALUE"),
        listOf("parse_content_or_child", "ELEMENT_CONTENT", "ELEMENT_START"),
    )
    val xmlGrammar = mapOf(
        "ELEMENT_START" to "'<'",
        "ELEMENT_END_PART" to "'/'", // if this is directly after the ELEMENT_START, than the element is finished
        "ELEMENT_CLOSE_IMMEDIATELY" to "'/' '>'",
        "ELEMENT_CLOSE_LATER" to "'>'",
        "TAG" to "[a-zA-Z][a-zA-Z0-9]*",
        "ATTRIBUTE_NAME" to "[a-zA-Z][a-zA-Z0-9]*",
        "ATTRIBUTE_ASSIGNMENT" to "'='",
        "ATTRIBUTE_VALUE" to "'\"' [^\"]* '\"'",
        "ELEMENT_CONTENT" to "[^<]*",
        "SKIP_WS" to "[#x20#x9#xD#xA]*",
    )
    val xmlFilename = "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}xmlParser${Platform.getPathSeparator()}XMLParserGenerated.kt"
    val xmlPackage = "lupos.s00misc.xmlParser"
    ParserGenerator(turtleGeneratingArgs, turtleGrammar, turtleFilename, turtlePackage,)
    ParserGenerator(xmlGeneratingArgs, xmlGrammar, xmlFilename, xmlPackage,)
}

fun onGenerateEnumsHelper(enumName: String, packageName: String, modifier: String, fileName: String) {
    val mapping2 = mutableListOf<String>()
    var id = 0
    File(fileName + ".txt").forEachLine {
        mapping2.add(it)
        id++
    }
    val mapping = mapping2.sorted()
    File(fileName + ".kt").printWriter().use { out ->
        out.println("/*")
        out.println(" * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).")
        out.println(" * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck")
        out.println(" *")
        out.println(" * This program is free software: you can redistribute it and/or modify")
        out.println(" * it under the terms of the GNU General Public License as published by")
        out.println(" * the Free Software Foundation, version 3.")
        out.println(" *")
        out.println(" * This program is distributed in the hope that it will be useful, but")
        out.println(" * WITHOUT ANY WARRANTY; without even the implied warranty of")
        out.println(" * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU")
        out.println(" * General Public License for more details.")
        out.println(" *")
        out.println(" * You should have received a copy of the GNU General Public License")
        out.println(" * along with this program. If not, see <http://www.gnu.org/licenses/>.")
        out.println(" */")
        out.println("package $packageName")
        out.println("$modifier typealias $enumName = Int")
    }
    File(fileName + "Ext.kt").printWriter().use { out ->
        out.println("/*")
        out.println(" * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).")
        out.println(" * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck")
        out.println(" *")
        out.println(" * This program is free software: you can redistribute it and/or modify")
        out.println(" * it under the terms of the GNU General Public License as published by")
        out.println(" * the Free Software Foundation, version 3.")
        out.println(" *")
        out.println(" * This program is distributed in the hope that it will be useful, but")
        out.println(" * WITHOUT ANY WARRANTY; without even the implied warranty of")
        out.println(" * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU")
        out.println(" * General Public License for more details.")
        out.println(" *")
        out.println(" * You should have received a copy of the GNU General Public License")
        out.println(" * along with this program. If not, see <http://www.gnu.org/licenses/>.")
        out.println(" */")
        out.println("package $packageName")
        out.println("import kotlin.jvm.JvmField")
        out.println("$modifier object ${enumName}Ext {")
        for (i in 0 until mapping.size) {
            out.println("    $modifier const val ${mapping[i]}: $enumName = $i")
        }
        out.println("    $modifier const val values_size: Int = ${mapping.size}")
        out.println("    @JvmField $modifier val names: Array<String> = arrayOf(")
        for (i in 0 until mapping.size) {
            out.println("        \"${mapping[i]}\",")
        }
        out.println("    )")
        out.println("}")
    }
}

fun onGenerateEnums() {
    val turtleGeneratingArgs = arrayOf(
        listOf("MyPrintWriterMode", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}MyPrintWriterMode"),
        listOf("BuiltInFunctions", "lupos.s02buildSyntaxTree.sparql1_1", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s02buildSyntaxTree${Platform.getPathSeparator()}sparql1_1${Platform.getPathSeparator()}BuiltInFunctions"),
        listOf("BinaryTestCaseOutputMode", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_test${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}BinaryTestCaseOutputMode"),
        listOf("Aggregation", "lupos.s02buildSyntaxTree.sparql1_1", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s02buildSyntaxTree${Platform.getPathSeparator()}sparql1_1${Platform.getPathSeparator()}Aggregation"),
        listOf("IteratorBundleMode", "lupos.s04logicalOperators.iterator", "internal", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s04logicalOperators${Platform.getPathSeparator()}iterator${Platform.getPathSeparator()}IteratorBundleMode"),
        listOf("ESortPriority", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}ESortPriority"),
        listOf("ETripleIndexType", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}ETripleIndexType"),
        listOf("EGraphRefType", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EGraphRefType"),
        listOf("EOperatorID", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EOperatorID"),
        listOf("EModifyType", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EModifyType"),
        listOf("ETripleComponentType", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}ETripleComponentType"),
        listOf("EGraphOperationType", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EGraphOperationType"),
        listOf("ESortType", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}ESortType"),
        listOf("EGroupMember", "lupos.optimizer.ast", "public", "src${Platform.getPathSeparator()}luposdate3000_optimizer_ast${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}optimizer${Platform.getPathSeparator()}ast${Platform.getPathSeparator()}EGroupMember"),
        listOf("EQueryResultToStream", "lupos.s11outputResult", "public", "src${Platform.getPathSeparator()}luposdate3000_result_format${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s11outputResult${Platform.getPathSeparator()}EQueryResultToStream"),
        listOf("EPOPDebugMode", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EPOPDebugMode"),
        listOf("Turtle2ParserState", "lupos.s02buildSyntaxTree.turtle", "internal", "src${Platform.getPathSeparator()}luposdate3000_parser${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s02buildSyntaxTree${Platform.getPathSeparator()}turtle${Platform.getPathSeparator()}Turtle2ParserState"),
        listOf("EOptimizerID", "lupos.optimizer.logical", "public", "src${Platform.getPathSeparator()}luposdate3000_optimizer_logical${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}optimizer${Platform.getPathSeparator()}logical${Platform.getPathSeparator()}EOptimizerID"),
        listOf("EOperatingSystem", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EOperatingSystem"),
        listOf("EIndexPattern", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EIndexPattern"),
        listOf("EPartitionMode", "lupos.s00misc", "public", "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}s00misc${Platform.getPathSeparator()}EPartitionMode"),
    )
    for (args in turtleGeneratingArgs) {
        onGenerateEnumsHelper(args[0], args[1], args[2], args[3])
    }
}

fun onGenerateLauncherMain() {
    for (module in getAllModuleConfigurations()) {
        if (module.modulePrefix == "Luposdate3000_Main") {
            if (File(File(module.moduleFolder), "runOptions").exists()) {
                var options = mutableListOf<String>()
                File(File(module.moduleFolder), "runOptions").forEachLine {
                    options.add(it)
                }
                for (p in listOf("jvmMain", "jsMain", "nativeMain")) {
                    File(File(File(File(module.moduleFolder), "src"), p), "kotlin").mkdirs()
                    File(File(File(File(File(module.moduleFolder), "src"), p), "kotlin"), "Main.kt").printWriter().use { out ->
                        out.println("/*")
                        out.println(" * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).")
                        out.println(" * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck")
                        out.println(" *")
                        out.println(" * This program is free software: you can redistribute it and/or modify")
                        out.println(" * it under the terms of the GNU General Public License as published by")
                        out.println(" * the Free Software Foundation, version 3.")
                        out.println(" *")
                        out.println(" * This program is distributed in the hope that it will be useful, but")
                        out.println(" * WITHOUT ANY WARRANTY; without even the implied warranty of")
                        out.println(" * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU")
                        out.println(" * General Public License for more details.")
                        out.println(" *")
                        out.println(" * You should have received a copy of the GNU General Public License")
                        out.println(" * along with this program. If not, see <http://www.gnu.org/licenses/>.")
                        out.println(" */")
                        var pkg = module.moduleFolder
                        val idx = pkg.indexOf("_launch_") + "_launch_".length
                        pkg = pkg.substring(idx)
                        out.println("import lupos.launch.$pkg.mainFunc")
                        if (p == "jsMain") {
                            out.println("import kotlin.js.JsName")
                            out.println("")
                            out.println("@JsName(\"main\")")
                        } else {
                            out.println("")
                        }
                        out.println("public fun main(args: Array<String>) {")
                        out.println("    var flag = false")
                        var first = true
                        for (o in options) {
                            if (!first) {
                                out.println("    flag = false")
                            }
                            first = false
                            out.println("    var $o: String = \"\"")
                            out.println("    for (a in args) {")
                            out.println("        if (a.startsWith(\"--$o=\")) {")
                            out.println("            $o = a.substring(${o.length + 3})")
                            out.println("            flag = true")
                            out.println("            break")
                            out.println("        }")
                            out.println("    }")
                            out.println("    if (!flag) {")
                            out.println("        throw Exception(\"the option '--$o' is missing on the arguments list\")")
                            out.println("    }")
                        }
                        var s = "mainFunc("
                        for (i in 0 until options.size - 1) {
                            s += "${options[i]}, "
                        }
                        if (options.size > 0) {
                            s += "${options[options.size - 1]}"
                        }
                        s += ")"
                        out.println("    $s")
                        out.println("}")
                    }
                }
            }
        }
    }
}

fun copyFromJar(source: InputStream, dest: String) {
    val out = FileOutputStream(dest)
    while (source.available() > 0) {
        out.write(source.read())
    }
    out.close()
    source.close()
}

fun copyJSLibsIntoFolder(targetFolder: String) {
    val jsStdlib = JarFile(File("${Platform.getMavenCache()}${Platform.getPathSeparator()}org${Platform.getPathSeparator()}jetbrains${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}kotlin-stdlib-js${Platform.getPathSeparator()}$compilerVersion${Platform.getPathSeparator()}kotlin-stdlib-js-$compilerVersion.jar"))
    copyFromJar(jsStdlib.getInputStream(jsStdlib.getEntry("kotlin.js")), "${targetFolder}${Platform.getPathSeparator()}kotlin.js")
    copyFromJar(jsStdlib.getInputStream(jsStdlib.getEntry("kotlin.js.map")), "${targetFolder}${Platform.getPathSeparator()}kotlin.js.map")
    val kryptoLib = JarFile(find("${Platform.getGradleCache()}${Platform.getPathSeparator()}modules-2${Platform.getPathSeparator()}files-2.1${Platform.getPathSeparator()}com.soywiz.korlibs.krypto${Platform.getPathSeparator()}krypto-js", "krypto-js-1.9.1.jar")!!)
    copyFromJar(kryptoLib.getInputStream(kryptoLib.getEntry("krypto-root-krypto.js")), "${targetFolder}${Platform.getPathSeparator()}krypto-root-krypto.js")
    copyFromJar(kryptoLib.getInputStream(kryptoLib.getEntry("krypto-root-krypto.js.map")), "${targetFolder}${Platform.getPathSeparator()}krypto-root-krypto.js.map")
}

fun onSetupJS() {
    jsBrowserMode = true
    File("build-cache${Platform.getPathSeparator()}index.html").printWriter().use { out ->
        out.println("<!DOCTYPE html>")
        out.println("<html lang=\"en\">")
        out.println("<head>")
        out.println("    <meta charset=\"utf-8\">")
        out.println("    <title>Luposdate3000</title>")
        out.println("    <script src=\"kotlin.js\"></script>")
        out.println("    <script src=\"krypto-root-krypto.js\"></script>")
        for (module in getAllModuleConfigurations()) {
            if (module.enabledRunFunc() && module.modulePrefix != "Luposdate3000_Main") {
                if (module.modulePrefix == module.moduleName) {
                    out.println("    <script src=\"bin$appendix/${module.modulePrefix}.js\"></script>")
                } else {
                    out.println("    <script src=\"bin$appendix/${module.moduleName}/${module.modulePrefix}.js\"></script>")
                }
            }
        }
        out.println("</head>")
        out.println("<body>")
        out.println("<script>")
        out.println("Luposdate3000_Endpoint.lupos.s16network.LuposdateEndpoint.initialize()")
        out.println("Luposdate3000_Endpoint.lupos.s16network.LuposdateEndpoint.evaluate_sparql_to_result_b(\"INSERT DATA { <s> <p> <o> } \")")
        out.println("Luposdate3000_Endpoint.lupos.s16network.LuposdateEndpoint.evaluate_sparql_to_result_b(\"SELECT (5 as ?x) ?s {?s ?p ?o .}\")")
        out.println("</script>")
        out.println("</body>")
        out.println("</html>")
    }
    copyJSLibsIntoFolder("build-cache")
}

fun find(path: String, fName: String): File? {
    val f = File(path)
    if (fName == f.getName()) {
        return f
    }
    if (f.isDirectory()) {
        for (aChild in f.list()) {
            val ff = find(path + File.separator + aChild, fName)
            if (ff != null) {
                return ff
            }
        }
    }
    return null
}

fun onAllTest() {
    for (r in listOf("Enable", "Disable")) {
        for (i in listOf("Enable", "Disable")) {
            for (s in listOf("Disable")) {
                ProcessBuilder("./launcher.main.kts", "--compileAll", "--releaseMode=$r", "--inlineMode=$i", "--suspendMode=$s", "--dryMode=Disable", "--target=All", "--intellijMode=Disable")
                    .redirectOutput(Redirect.appendTo(File("all-test-$r-$i-$s.compile-log")))
                    .redirectError(Redirect.appendTo(File("all-test-$r-$i-$s.compile-err")))
                    .start()
                    .waitFor()
                ProcessBuilder("./launcher.main.kts", "--run", "--runArgument_Luposdate3000_Launch_Binary_Test_Suite:basePath=resources/binary", "--mainClass=Binary_Test_Suite", "--releaseMode=$r", "--inlineMode=$i", "--suspendMode=$s", "--partitionMode=Thread", "--memoryMode=inmemory", "--proguardMode=Off", "--mainClass=Binary_Test_Suite", "--jenaWrapper=On", "--endpointMode=None")
                    .redirectOutput(Redirect.appendTo(File("all-test-$r-$i-$s-WithPartitions.test-log")))
                    .redirectError(Redirect.appendTo(File("all-test-$r-$i-$s-WithPartitions.test-err")))
                    .start()
                    .waitFor()
                File("/tmp/luposdate3000/").deleteRecursively()
                ProcessBuilder("./launcher.main.kts", "--run", "--runArgument_Luposdate3000_Launch_Binary_Test_Suite:basePath=resources/binary", "--mainClass=Binary_Test_Suite", "--releaseMode=$r", "--inlineMode=$i", "--suspendMode=$s", "--partitionMode=None", "--memoryMode=inmemory", "--proguardMode=Off", "--mainClass=Binary_Test_Suite", "--jenaWrapper=On", "--endpointMode=None")
                    .redirectOutput(Redirect.appendTo(File("all-test-$r-$i-$s-NoPartitions.test-log")))
                    .redirectError(Redirect.appendTo(File("all-test-$r-$i-$s-NoPartitions.test-err")))
                    .start()
                    .waitFor()
                ProcessBuilder("./launcher.main.kts", "--run", "--runArgument_Luposdate3000_Launch_Binary_Test_Suite:basePath=resources/binary", "--mainClass=Binary_Test_Suite", "--releaseMode=$r", "--inlineMode=$i", "--suspendMode=$s", "--partitionMode=None", "--memoryMode=persistent", "--proguardMode=Off", "--mainClass=Binary_Test_Suite", "--jenaWrapper=On", "--endpointMode=None")
                    .redirectOutput(Redirect.appendTo(File("all-test-$r-$i-$s-NoPartitions-Persistent.test-log")))
                    .redirectError(Redirect.appendTo(File("all-test-$r-$i-$s-NoPartitions-Persistent.test-err")))
                    .start()
                    .waitFor()
                ProcessBuilder("./launcher.main.kts", "--run", "--runArgument_Luposdate3000_Launch_Binary_Test_Suite:basePath=resources/binary", "--mainClass=Binary_Test_Suite", "--releaseMode=$r", "--inlineMode=$i", "--suspendMode=$s", "--partitionMode=Thread", "--memoryMode=inmemory", "--proguardMode=On", "--mainClass=Binary_Test_Suite", "--jenaWrapper=On", "--endpointMode=None")
                    .redirectOutput(Redirect.appendTo(File("all-test-$r-$i-$s-WithPartitions-Proguard.test-log")))
                    .redirectError(Redirect.appendTo(File("all-test-$r-$i-$s-WithPartitions-Proguard.test-err")))
                    .start()
                    .waitFor()
                ProcessBuilder("./launcher.main.kts", "--run", "--runArgument_Luposdate3000_Launch_Binary_Test_Suite:basePath=resources/binary", "--mainClass=Binary_Test_Suite", "--releaseMode=$r", "--inlineMode=$i", "--suspendMode=$s", "--partitionMode=None", "--memoryMode=inmemory", "--proguardMode=On", "--mainClass=Binary_Test_Suite", "--jenaWrapper=On", "--endpointMode=None")
                    .redirectOutput(Redirect.appendTo(File("all-test-$r-$i-$s-NoPartitions-Proguard.test-log")))
                    .redirectError(Redirect.appendTo(File("all-test-$r-$i-$s-NoPartitions-Proguard.test-err")))
                    .start()
                    .waitFor()
                File("/tmp/luposdate3000/").deleteRecursively()
                ProcessBuilder("./launcher.main.kts", "--run", "--runArgument_Luposdate3000_Launch_Binary_Test_Suite:basePath=resources/binary", "--mainClass=Binary_Test_Suite", "--releaseMode=$r", "--inlineMode=$i", "--suspendMode=$s", "--partitionMode=None", "--memoryMode=persistent", "--proguardMode=On", "--mainClass=Binary_Test_Suite", "--jenaWrapper=On", "--endpointMode=None")
                    .redirectOutput(Redirect.appendTo(File("all-test-$r-$i-$s-NoPartitions-Persistent-Proguard.test-log")))
                    .redirectError(Redirect.appendTo(File("all-test-$r-$i-$s-NoPartitions-Persistent-Proguard.test-err")))
                    .start()
                    .waitFor()
            }
        }
    }
}
