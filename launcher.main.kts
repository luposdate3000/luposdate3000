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
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EOperatingSystemExt.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared_inline/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/shared_inline/Platform.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-inline.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-suspend.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-module.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-helper.kt")
@file:Import("src/luposdate3000_scripting/parsergenerator.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/dictionary/EDictionaryTypeExt.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/dictionary/EDictionaryType.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EPartitionModeExt.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EPartitionMode.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EGarbageCollector.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EGarbageCollectorExt.kt")
@file:CompilerOptions("-Xmulti-platform")

import launcher.CreateModuleArgs
import launcher.DryMode
import launcher.ExecMode
import launcher.InlineMode
import launcher.IntellijMode
import launcher.ParamClassMode
import launcher.ReleaseMode
import launcher.SuspendMode
import launcher.TargetMode2
import launcher.createBuildFileForModule
import launcher.targetModeCompatible
import lupos.shared.EGarbageCollectorExt
import lupos.shared.EOperatingSystemExt
import lupos.shared.EPartitionModeExt
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared_inline.Platform
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.ProcessBuilder.Redirect
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption.REPLACE_EXISTING
import java.util.jar.JarFile

var compilerVersion = ""
var compileModuleArgs = mutableMapOf<String, MutableMap<String, String>>()
var jsBrowserMode = true
var releaseMode = ReleaseMode.Disable
var dryMode = DryMode.Disable
var suspendMode = SuspendMode.Disable
var inlineMode = InlineMode.Enable
var partitionMode = ""
var dictionaryMode = ""
var mainClass = ""
var target = TargetMode2.JVM
var runArgs = mutableListOf<String>()
var skipArgs = false
var threadCount = 1
var processUrls = ""
var garbageCollector = 0
val optionsForPackages = mutableMapOf<String, MutableSet<String>>()
val optionsChoosenForPackages = mutableMapOf<String, String>("Buffer_Manager" to "Inmemory", "Endpoint_Launcher" to "None", "Jena_Wrapper" to "Off")
var intellijMode = IntellijMode.Enable
var execMode = ExecMode.UNKNOWN

fun makeUppercaseStart(s: String): String {
    val res = StringBuilder()
    var flag = true
    for (c in s) {
        if (flag) {
            res.append(c.toUpperCase())
        } else {
            res.append(c)
        }
        flag = c == '_'
    }
    return res.toString()
}

fun getAllModuleConfigurations(): List<CreateModuleArgs> {
    val localArgs = CreateModuleArgs()
        .ssetReleaseMode(releaseMode)
        .ssetSuspendMode(suspendMode)
        .ssetInlineMode(inlineMode)
        .ssetIntellijMode(intellijMode)
        .ssetTarget(target)
        .ssetCodegenKSP(false)
        .ssetCodegenKAPT(false)
        .ssetCompilerVersion(compilerVersion)
        .ssetEnabledFunc { true }
        .ssetEnabledRunFunc { true }
    var allpackages = mutableSetOf<String>()
    var modules = mutableMapOf<String, CreateModuleArgs>()
    val dependencyMap = mutableMapOf<String, MutableSet<String>>()
    Files.walk(Paths.get("src"), 1).forEach { it ->
        val filename = it.toString()
        val f = File(filename + "/module_config")
        if (f.exists()) {
            var currentArgs = localArgs
                .ssetModuleName(makeUppercaseStart(filename.substring(filename.indexOf("luposdate3000"))))
                .ssetModulePrefix(makeUppercaseStart(filename.substring(filename.indexOf("luposdate3000"))))
            if (filename.endsWith("_browserjs")) {
                currentArgs = currentArgs.ssetEnabledRunFunc { jsBrowserMode && targetModeCompatible(target, TargetMode2.JS) }
            } else if (filename.endsWith("_nodejs")) {
                currentArgs = currentArgs.ssetEnabledRunFunc { !jsBrowserMode && targetModeCompatible(target, TargetMode2.JS) }
            }
            val dep = mutableSetOf<String>()
            f.forEachLine { line ->
                when {
                    line == "codegenKAPT=true" -> {
                        currentArgs = currentArgs.ssetCodegenKAPT(true)
                    }
                    line == "codegenKSP=true" -> {
                        currentArgs = currentArgs.ssetCodegenKSP(true)
                    }
                    line.startsWith("dependencyLuposdate=") -> {
                        dep.add(line.substring("dependencyLuposdate=".length))
                    }
                    line.startsWith("dependencyCommon=") -> {
                        currentArgs.dependenciesCommon.add(line.substring("dependencyCommon=".length))
                    }
                    line.startsWith("dependencyJvm=") -> {
                        currentArgs.dependenciesJvm.add(line.substring("dependencyJvm=".length))
                    }
                    line.startsWith("dependencyJs=") -> {
                        currentArgs.dependenciesJs.add(line.substring("dependencyJs=".length))
                    }
                    line.startsWith("dependencyNative=") -> {
                        currentArgs.dependenciesNative.add(line.substring("dependencyNative=".length))
                    }
                    line.startsWith("name=") -> {
                        currentArgs = currentArgs.ssetModuleName(line.substring("name=".length))
                    }
                    line.startsWith("package=") -> {
                        currentArgs = currentArgs.ssetModulePrefix(line.substring("package=".length))
                    }
                    line.startsWith("enabled=") -> {
                        when (line) {
                            "enabled=never" -> {
                                currentArgs = currentArgs.ssetEnabledRunFunc { false }
                            }
                            else -> {
                                throw Exception("unknown value")
                            }
                        }
                    }
                    line.startsWith("enabledRun=") -> {
                        when (line) {
                            "enabledRun=never" -> {
                                currentArgs = currentArgs.ssetEnabledRunFunc { false }
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
            val name = currentArgs.moduleName.substring("Luposdate3000_".length)
            val pkg = currentArgs.modulePrefix.substring("Luposdate3000_".length)
            var pkgs = optionsForPackages[pkg]
            if (pkgs == null) {
                pkgs = mutableSetOf<String>()
                optionsForPackages[pkg] = pkgs
            }
            if (optionsChoosenForPackages[pkg] != null) {
                currentArgs = currentArgs.ssetEnabledRunFunc {
                    optionsChoosenForPackages[pkg] == name
                }
            }
            pkgs.add(name)
            if (currentArgs.modulePrefix == "Luposdate3000_Main") {
                currentArgs = currentArgs.ssetEnabledRunFunc { mainClass == currentArgs.moduleName }
            }
            currentArgs = currentArgs.ssetArgs2(compileModuleArgs)
            modules[currentArgs.moduleName] = currentArgs
            dependencyMap[currentArgs.moduleName] = dep
            allpackages.add(currentArgs.modulePrefix.toLowerCase())
        }
    }
    for ((k, v) in modules) {
        val dep = dependencyMap[k]!!
        if (!v.moduleName.startsWith("Luposdate3000_Shared")) {
            dep.add("Luposdate3000_Shared")
        }
        Files.walk(Paths.get(v.moduleFolder)).forEach { it2 ->
            val name = it2.toString()
            val f = File(name)
            if (f.isFile() && name.endsWith(".kt")) {
                f.forEachLine { it ->
                    if (it.startsWith("import lupos.")) {
                        val imp = it.split('.')
                        var i = imp.size - 1
                        while (i > 0) {
                            var s = "luposdate3000_" + imp[1]
                            for (j in 2 until i) {
                                s += "_" + imp[j]
                            }
                            if (allpackages.contains(s)) {
                                var found = false
                                for (y in modules.values) {
                                    if (y.modulePrefix.toLowerCase() == s && y.enabledRunFunc()) {
                                        found = true
                                        dep.add(y.moduleName)
                                        break
                                    }
                                }
                                if (!found) {
                                    for (y in modules.values) {
                                        if (y.modulePrefix.toLowerCase() == s) {
                                            found = true
                                            dep.add(y.moduleName)
                                            break
                                        }
                                    }
                                }
                                if (!found) {
                                    throw Exception(s)
                                }
                                i = 0
                            }
                            i--
                        }
                    }
                }
            }
        }
        dep.remove("Luposdate3000_Shared_Inline")
        dep.remove(v.moduleName)
    }
// add explicit dependencies
    for (v in modules.values) {
        val c = modules["Luposdate3000_Shared_Inline"]!!
        v.dependenciesCommon.addAll(c.dependenciesCommon)
        v.dependenciesJvm.addAll(c.dependenciesJvm)
        v.dependenciesJvmRecoursive.addAll(c.dependenciesJvm)
        v.dependenciesJvmRecoursive.addAll(c.dependenciesCommon)
        v.dependenciesJs.addAll(c.dependenciesJs)
        v.dependenciesNative.addAll(c.dependenciesNative)
    }
// add inferred direct module dependencies
    for ((k, v) in modules) {
        val depss = dependencyMap[k]
        if (depss != null) {
            for (w in depss) {
                v.dependenciesCommon.add("luposdate3000:$w:0.0.1")
            }
        }
    }
// add ksp dependency
    for ((k, v) in modules) {
        val depss = dependencyMap[k]
        if (depss != null) {
            if (v.codegenKSP) {
                depss.add("Luposdate3000_Code_Generator_KSP")
                depss.add("Luposdate3000_Endpoint")
                v.dependenciesJvmRecoursive.add("luposdate3000:Luposdate3000_Code_Generator_KSP:0.0.1")
                v.dependenciesJvmRecoursive.add("luposdate3000:Luposdate3000_Endpoint:0.0.1")
            }
            if (v.codegenKAPT) {
                depss.add("Luposdate3000_Code_Generator_KAPT")
                v.dependenciesJvmRecoursive.add("luposdate3000:Luposdate3000_Code_Generator_KAPT:0.0.1")
            }
        }
    }
// add recursive dependencies
    var flag = true
    while (flag) {
        flag = false
        for ((k, v) in modules) {
            val depss = dependencyMap[k]
            if (depss != null) {
                for (dep in depss.toTypedArray()) {
                    val s = depss.size
                    val deps = dependencyMap[dep]
                    if (deps != null) {
                        depss.addAll(deps)
                    }
                    if (s != depss.size) {
                        for (w in deps!!) {
                            v.dependenciesJvmRecoursive.add("luposdate3000:$w:0.0.1")
                            v.dependenciesJs.add("luposdate3000:$w:0.0.1")
                            v.dependenciesNative.add("luposdate3000:$w:0.0.1")
                        }
                        v.dependenciesJvmRecoursive.addAll(modules[dep]!!.dependenciesJvmRecoursive)
                        v.dependenciesJs.addAll(modules[dep]!!.dependenciesJs)
                        v.dependenciesNative.addAll(modules[dep]!!.dependenciesNative)
                        flag = true
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
    for (k in optionsForPackages.keys.toTypedArray()) {
        if (optionsForPackages[k]!!.size == 1) {
            optionsForPackages.remove(k)
        }
    }
    for (v in modules.values) {
        v.dependenciesCommon.remove("luposdate3000:Luposdate3000_Shared_BrowserJS:0.0.1")
        v.dependenciesJvm.remove("luposdate3000:Luposdate3000_Shared_BrowserJS:0.0.1")
        v.dependenciesNative.remove("luposdate3000:Luposdate3000_Shared_BrowserJS:0.0.1")
        v.dependenciesJvmRecoursive.remove("luposdate3000:Luposdate3000_Shared_BrowserJS:0.0.1")
    }
    modules["Luposdate3000_Shared"]!!.dependenciesJs.add("luposdate3000:Luposdate3000_Shared_BrowserJS:0.0.1")
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
        "--target",
        TargetMode2.JVM.toString(),
        TargetMode2.values().map { it -> it.toString() to { target = it } }.toMap()
    ),
    ParamClass(
        "--dryMode",
        DryMode.Disable.toString(),
        DryMode.values().map { it -> it.toString() to { dryMode = it } }.toMap()
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
        "--releaseMode",
        ReleaseMode.Disable.toString(),
        ReleaseMode.values().map { it -> it.toString() to { releaseMode = it } }.toMap()
    ),
    ParamClass(
        "--suspendMode",
        SuspendMode.Disable.toString(),
        SuspendMode.values().map { it -> it.toString() to { suspendMode = it } }.toMap()
    ),
    ParamClass(
        "--compilerVersion",
        "1.4.0",
        mapOf(
            "1.5.0-RC" to { compilerVersion = "1.5.0-RC" },
            "1.4.0" to { compilerVersion = "1.4.0" },
            "1.4.255-SNAPSHOT" to { compilerVersion = "1.4.255-SNAPSHOT" },
            "1.5.255-SNAPSHOT" to { compilerVersion = "1.5.255-SNAPSHOT" },
        )
    ),
    ParamClass(
        "--inlineMode",
        InlineMode.Enable.toString(),
        InlineMode.values().map { it -> it.toString() to { inlineMode = it } }.toMap()
    ),
    ParamClass(
        "--partitionMode",
        EPartitionModeExt.names[EPartitionModeExt.None],
        EPartitionModeExt.names.map { it to { partitionMode = it } }.toMap(),
    ),
    ParamClass(
        "--garbageCollector",
        EGarbageCollectorExt.names[EGarbageCollectorExt.Shenandoah],
        EGarbageCollectorExt.names.mapIndexed { idx, it -> it to { garbageCollector = idx } }.toMap(),
    ),
    ParamClass(
        "--dictionaryMode",
        EDictionaryTypeExt.names[EDictionaryTypeExt.KV],
        EDictionaryTypeExt.names.map { it to { dictionaryMode = it } }.toMap(),
    ),
    ParamClass(
        "--help",
        {
            execMode = ExecMode.HELP
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
            System.exit(0)
        }
    ),
    ParamClass(
        "--setupJS",
        {
            enableParams(compileParams)
            execMode = ExecMode.SETUP_JS
            target = TargetMode2.JS
        }
    ),
    ParamClass(
        "--setupSPAClient",
        {
            enableParams(compileParams)
            execMode = ExecMode.SETUP_SPACLIENT
            target = TargetMode2.JS
        }
    ),
    ParamClass(
        "--setupIntellijIdea",
        {
            enableParams(compileParams)
            intellijMode = IntellijMode.Enable
            execMode = ExecMode.SETUP_GRADLE
        }
    ),
    ParamClass(
        "--setupCommandline",
        {
            enableParams(compileParams)
            intellijMode = IntellijMode.Disable
            execMode = ExecMode.SETUP_GRADLE
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
getAllModuleConfigurations()
for ((k, v) in optionsChoosenForPackages) {
    defaultParams.add(
        ParamClass(
            name = "--$k",
            default = "$v",
            values = optionsForPackages[k]!!.map { it.substring(k.length + 1) to { optionsChoosenForPackages[k] = it } }.toMap(),
        ),
    )
}
enableParams(defaultParams)
val mainclassParams = listOf(
    ParamClass(
        "--mainClass",
        "Endpoint",
        optionsForPackages["Main"]!!.map { it.substring("Launch_".length) to { mainClass = "Luposdate3000_$it" } }.toMap()
    ),
)
enableParams(mainclassParams)
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
when (execMode) {
    ExecMode.HELP -> onHelp()
    ExecMode.RUN -> onRun()
    ExecMode.GENERATE_PARSER -> onGenerateParser()
    ExecMode.GENERATE_LAUNCHER -> onGenerateLauncherMain()
    ExecMode.GENERATE_ENUMS -> onGenerateEnums()
    ExecMode.SETUP_GRADLE -> onSetupGradle()
    ExecMode.SETUP_JS -> onSetupJS()
    ExecMode.SETUP_SPACLIENT -> onSetupSPAClient()
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
    for (param in mainclassParams) {
        param.help()
    }
    for (param in getAllModuleSpecificParams()) {
        param.help()
    }
}

fun onSetupGradle() {
    File(".idea").deleteRecursively()
    File("log").mkdirs()
    println(compileModuleArgs)
    for (module in getAllModuleConfigurations()) {
        if (module.enabledFunc()) {
            createBuildFileForModule(module)
        }
    }
    if (compileModuleArgs.size > 0) {
        for (k in compileModuleArgs.keys) {
            println("unknown module argument '$k'")
        }
        throw Exception("there are unkown arguments")
    }
    File("build.gradle.kts").printWriter().use { outBuildGradle ->
        outBuildGradle.println("dependencies {")
        outBuildGradle.println("    project(\":src\")")
        outBuildGradle.println("}")
    }
    File("settings.gradle.kts").printWriter().use { outSettingsGradle ->
        File("src${Platform.getPathSeparator()}build.gradle.kts").printWriter().use { outBuildGradle ->
            outSettingsGradle.println("pluginManagement {")
            outSettingsGradle.println("    resolutionStrategy {")
            outSettingsGradle.println("        eachPlugin {")
            outSettingsGradle.println("            when (requested.id.id) {")
            outSettingsGradle.println("                \"kotlin-ksp\",")
            outSettingsGradle.println("                \"org.jetbrains.kotlin.kotlin-ksp\",")
            outSettingsGradle.println("                \"org.jetbrains.kotlin.ksp\" -> useModule(\"org.jetbrains.kotlin:kotlin-ksp:\${requested.version}\")")
            outSettingsGradle.println("            }")
            outSettingsGradle.println("        }")
            outSettingsGradle.println("    }")
            outSettingsGradle.println("    repositories {")
            outSettingsGradle.println("        mavenLocal()")
            outSettingsGradle.println("        maven(\"https://dl.bintray.com/kotlin/kotlin-eap\")")
            outSettingsGradle.println("        google()")
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
                    if (!name.contains("shared_inline") && !name.contains("luposdate3000_scripting")) {
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
    when {
        targetModeCompatible(target, TargetMode2.JVM) -> {
            val jarsLuposdate3000 = mutableListOf<String>()
            val jars = mutableSetOf<String>()
            for (module in getAllModuleConfigurations()) {
                if (module.enabledRunFunc()) {
                    jarsLuposdate3000.add("${module.moduleFolder}/build/libs/${module.moduleName.toLowerCase()}-jvm-0.0.1.jar")
                    jars.add("${module.moduleFolder}/build/libs/${module.moduleName.toLowerCase()}-jvm-0.0.1.jar")
                    File("${module.moduleFolder}/build/external_jvm_dependencies").forEachLine {
                        jars.add(it)
                    }
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
            val javaFileName = "/usr/lib/jvm/java-16-openjdk-amd64/bin/java"
            val javaFile = File(javaFileName)
            val cmd = mutableListOf<String>()
            if (javaFile.exists()) {
                cmd.add(javaFileName)
                cmd.add("-server")
                cmd.add("-XX:+UnlockExperimentalVMOptions")
                when (garbageCollector) {
                    EGarbageCollectorExt.Epsilon -> {
                        cmd.add("-Xmx10g")
                        cmd.add("-Xms10g")
                        cmd.add("-XX:+UseEpsilonGC")
                        cmd.add("-XX:+AlwaysPreTouch")
                        cmd.add("-XX:+HeapDumpOnOutOfMemoryError")
                    }
                    EGarbageCollectorExt.Shenandoah -> {
                        cmd.add("-Xmx${Platform.getAvailableRam()}g")
                        cmd.add("-XX:+UseShenandoahGC")
                        cmd.add("-XX:ShenandoahUncommitDelay=1000")
                        cmd.add("-XX:ShenandoahGuaranteedGCInterval=10000")
                    }
                }
            } else {
                cmd.add("java")
                cmd.add("-Xmx${Platform.getAvailableRam()}g")
            }
            cmd.add("-cp")
            cmd.add(classpath)
            cmd.add("MainKt")
            cmd.addAll(runArgs)
            println(cmd)
            if (dryMode == DryMode.Enable) {
                println("export LUPOS_PROCESS_URLS=$processUrls")
                println("export LUPOS_THREAD_COUNT=$threadCount")
                println("export LUPOS_PARTITION_MODE=$partitionMode")
                println("export LUPOS_DICTIONARY_MODE=$dictionaryMode")
                println("exec :: " + cmd.joinToString(" "))
            } else {
                Array(processUrls.count { it == ',' } + 1) {
                    val p = ProcessBuilder(cmd)
                        .redirectOutput(Redirect.INHERIT)
                        .redirectError(Redirect.INHERIT)
                    val env = p.environment()
                    env["LUPOS_PROCESS_ID"] = "$it"
                    env["LUPOS_PROCESS_URLS"] = "$processUrls"
                    env["LUPOS_THREAD_COUNT"] = "$threadCount"
                    env["LUPOS_PARTITION_MODE"] = "$partitionMode"
                    env["LUPOS_DICTIONARY_MODE"] = "$dictionaryMode"
                    p.start()
                }.forEach {
                    it.waitFor()
                    if (it.exitValue() != 0) {
                        throw Exception("exit-code:: " + it.exitValue())
                    }
                }
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
    val turtleFilename = "src${Platform.getPathSeparator()}luposdate3000_parser${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}parser${Platform.getPathSeparator()}turtle${Platform.getPathSeparator()}Turtle2ParserGenerated.kt"
    val turtlePackage = "lupos.parser.turtle"
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
        "SKIP_WS_FORCED" to "[#x20#x9#xD#xA]+",
    )
    val xmlFilename = "src${Platform.getPathSeparator()}luposdate3000_shared${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}shared${Platform.getPathSeparator()}xmlParser${Platform.getPathSeparator()}XMLParserGenerated.kt"
    val xmlPackage = "lupos.shared.xmlParser"
    val nQuadsGeneratingArgs = arrayOf(
        listOf("PARSER_CONTEXT"),
        listOf("parse_dot", "DOT"),
        listOf("parse_ws", "SKIP_WS"),
        listOf("parse_ws_forced", "SKIP_WS_FORCED"),
        listOf("parse_subject", "IRIREF", "BLANK_NODE_LABEL"),
        listOf("parse_predicate", "IRIREF"),
        listOf("parse_object", "IRIREF", "BLANK_NODE_LABEL", "STRING_LITERAL_QUOTE"),
        listOf("parse_object_string", "IRI1", "LANGTAG", "SKIP_WS"),
        listOf("parse_object_typed", "IRIREF"),
        listOf("parse_graph", "IRIREF", "BLANK_NODE_LABEL", "SKIP_WS"),
    )
    val nQuadsGrammar = mapOf(
        "LANGTAG" to "'@' [a-zA-Z0-9_,#x2D]+", // ATTENTION ",", and "_" are not allowed according to the official gramar, and the ordering allows more combinations
        "SKIP_WS_FORCED" to "[#x20#x9#xD#xA]+",
        "SKIP_WS" to "[#x20#x9#xD#xA]*",
        "IRIREF" to "'<' [^>]* '>'", // ATTENTION this is definetly wrong according to official grammar
        "STRING_LITERAL_QUOTE" to "('\"') ([^#x22#x5C#xA#xD] | ECHAR | UCHAR)* ('\"')",
        "ECHAR" to "('\\\\') ([tbnrf\"'\\])",
        "UCHAR" to "(('\\\\') 'u' HEX HEX HEX HEX | ('\\\\') 'U' HEX HEX HEX HEX HEX HEX HEX HEX)",
        "BLANK_NODE_LABEL" to "'_' ':' [^#x20#x9#xD#xA]+", // ATTENTION this is definetly wrong according to official grammar
        "HEX" to "([0-9] | [A-F] | [a-f])",
        "DOT" to "('.')",
        "IRI1" to "('^') ('^')",
    )
    val nQuadsFilename = "src${Platform.getPathSeparator()}luposdate3000_parser${Platform.getPathSeparator()}src${Platform.getPathSeparator()}commonMain${Platform.getPathSeparator()}kotlin${Platform.getPathSeparator()}lupos${Platform.getPathSeparator()}parser${Platform.getPathSeparator()}nQuads${Platform.getPathSeparator()}NQuads2ParserGenerated.kt"
    val nQuadsPackage = "lupos.parser.nQuads"
    ParserGenerator(turtleGeneratingArgs, turtleGrammar, turtleFilename, turtlePackage)
    ParserGenerator(xmlGeneratingArgs, xmlGrammar, xmlFilename, xmlPackage)
    ParserGenerator(nQuadsGeneratingArgs, nQuadsGrammar, nQuadsFilename, nQuadsPackage)
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
        out.println("")
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
        out.println("")
        out.println("import kotlin.jvm.JvmField")
        out.println("")
        out.println("$modifier object ${enumName}Ext {")
        for (i in 0 until mapping.size) {
            out.println("    $modifier const val ${mapping[i]}: $enumName = $i")
        }
        out.println("    $modifier const val values_size: Int = ${mapping.size}")
        out.println("")
        out.println("    @JvmField")
        out.println("    $modifier val names: Array<String> = arrayOf(")
        for (i in 0 until mapping.size) {
            out.println("        \"${mapping[i]}\",")
        }
        out.println("    )")
        out.println("}")
    }
}

fun onGenerateEnums() {
    val turtleGeneratingArgs = arrayOf(
        listOf("ETripleStoreIndexDescriptionPartitionedType", "lupos.triple_store_manager", "public", "src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/ETripleStoreIndexDescriptionPartitionedType"),
        listOf("BinaryTestCaseOutputMode", "lupos.test", "public", "src/luposdate3000_test/src/commonMain/kotlin/lupos/test/BinaryTestCaseOutputMode"),
        listOf("ESortPriority", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ESortPriority"),
        listOf("EIndexPattern", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EIndexPattern"),
        listOf("EOperatingSystem", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EOperatingSystem"),
        listOf("BuiltInFunctions", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/BuiltInFunctions"),
        listOf("ETripleComponentType", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ETripleComponentType"),
        listOf("EModifyType", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EModifyType"),
        listOf("EOperatorID", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EOperatorID"),
        listOf("ETripleIndexType", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ETripleIndexType"),
        listOf("Aggregation", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/Aggregation"),
        listOf("EGraphRefType", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EGraphRefType"),
        listOf("IteratorBundleMode", "lupos.shared.operator.iterator", "internal", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/operator/iterator/IteratorBundleMode"),
        listOf("MyPrintWriterMode", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/MyPrintWriterMode"),
        listOf("EGarbageCollector", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EGarbageCollector"),
        listOf("EPOPDebugMode", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EPOPDebugMode"),
        listOf("EPartitionMode", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EPartitionMode"),
        listOf("ESortType", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ESortType"),
        listOf("EDictionaryType", "lupos.shared.dictionary", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/dictionary/EDictionaryType"),
        listOf("EGraphOperationType", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EGraphOperationType"),
        listOf("EGroupMember", "lupos.optimizer.ast", "public", "src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/EGroupMember"),
        listOf("Turtle2ParserState", "lupos.parser.turtle", "internal", "src/luposdate3000_parser/src/commonMain/kotlin/lupos/parser/turtle/Turtle2ParserState"),
        listOf("EQueryResultToStream", "lupos.result_format", "public", "src/luposdate3000_result_format/src/commonMain/kotlin/lupos/result_format/EQueryResultToStream"),
        listOf("EOptimizerID", "lupos.optimizer.logical", "public", "src/luposdate3000_optimizer_logical/src/commonMain/kotlin/lupos/optimizer/logical/EOptimizerID"),
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

fun onSetupSPAClient() {
// depends on "apt install nodejs npm"
    println("onSetupSPAClient")
    val dirname = "./src/luposdate3000_spa_client"
    val dirluposdatejs = "$dirname/app/scripts/algos/luposdate3000"
    val relativeUrlJs = "scripts/algos/luposdate3000"
    val dir = File(dirname)
    val scriptFiles = getJSScriptFiles()
    println(File(dirluposdatejs).absolutePath)
    File(dirluposdatejs).mkdirs()
    val imports = mutableListOf<String>(
        "<script>Int64Array = BigInt64Array</script>", // fix an error in kotlin compiler ..... .
    )
    println("scriptFiles :: $scriptFiles")
    for (script in scriptFiles) {
        val src = "dist-js/$script"
        val dest = "$dirluposdatejs/${src.substring(src.lastIndexOf("/") + 1)}"
        val dest2 = "$relativeUrlJs/${src.substring(src.lastIndexOf("/") + 1)}"
        try {
            Files.copy(File(src).toPath(), File(dest).toPath(), REPLACE_EXISTING)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        try {
            Files.copy(File("$src.map").toPath(), File("$dest.map").toPath(), REPLACE_EXISTING)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        imports.add("<script src=\"$dest2\" ></script>")
    }
    val cache = mutableListOf<String>()
    var mode = 0
    println("$dirname/app/index.html")
    File("$dirname/app/index.html").forEachLine { line ->
        when (mode) {
            0 -> {
                cache.add(line)
                if (line == "<!-- LUPOSDATE3000 GENERATED CODE START-->") {
                    mode = 1
                    cache.addAll(imports)
                }
            }
            1 -> {
                if (line == "<!-- LUPOSDATE3000 GENERATED CODE END-->") {
                    cache.add(line)
                    mode = 2
                }
            }
            2 -> {
                cache.add(line)
            }
        }
    }
    File("$dirname/app/index.html").printWriter().use { out ->
        for (c in cache) {
            out.println(c)
        }
    }
    val pbin = ProcessBuilder("npm", "bin")
        .directory(dir)
    val bproc = pbin.start()
    val reader = bproc.getInputStream().reader()
    val pwd = reader.readText().trim()
    reader.close()
    val commands = listOf(
        listOf("npm", "install"),
        listOf("$pwd/bower", "install", "--allow-root"),
        listOf("touch", "app/scripts/jst.js"),
        listOf("$pwd/gulp"),
    )
    for (cmd in commands) {
        println("cmd :: $cmd")
        val p = ProcessBuilder(cmd)
            .redirectOutput(Redirect.INHERIT)
            .redirectError(Redirect.INHERIT)
            .directory(dir)
        val env = p.environment()
        env["DISABLE_NOTIFIER"] = "true"
        val p2 = p.start()
        p2.waitFor()
        if (p2.exitValue() != 0) {
            throw Exception("exit-code:: " + p2.exitValue())
        }
    }
}

fun getJSScriptFiles(): List<String> {
    File("dist-js").deleteRecursively()
    File("dist-js").mkdirs()
    val dependencies = mutableListOf<String>()
    val scripts = mutableListOf<String>()
    for (module in getAllModuleConfigurations()) {
        if (module.enabledRunFunc() && module.modulePrefix != "Luposdate3000_Main") {
            var s = "${module.moduleFolder}/build/libs/${module.moduleName.toLowerCase()}-js-0.0.1.jar"
            if (!dependencies.contains(s)) {
                dependencies.add(s)
            }
            File("${module.moduleFolder}/build/external_js_dependencies").forEachLine {
                if (!dependencies.contains(it)) {
                    dependencies.add(it)
                }
            }
        }
    }
    for (s in dependencies) {
        if (s.endsWith(".js")) {
            scripts.add(s)
        } else if (s.endsWith(".jar")) {
            println(s)
            val f = JarFile(File(s))
            for (e in f.entries()) {
                val name = e.getName()
                if (name.endsWith(".js.map")) {
                    copyFromJar(f.getInputStream(e), "dist-js/$name")
                } else if (name.endsWith(".js") && !name.endsWith("meta.js")) {
                    copyFromJar(f.getInputStream(e), "dist-js/$name")
                    scripts.add(name)
                }
            }
        } else {
            throw Exception("unknown dependency '$s'")
        }
    }
    return scripts
}

fun onSetupJS() {
    jsBrowserMode = true
    File("dist-js${Platform.getPathSeparator()}index.html").printWriter().use { out ->
        out.println("<!DOCTYPE html>")
        out.println("<html lang=\"en\">")
        out.println("<head>")
        out.println("    <meta charset=\"utf-8\">")
        out.println("    <title>Luposdate3000</title>")
        for (s in getJSScriptFiles()) {
            out.println("    <script src=\"$s\"></script>")
        }
        out.println("</head>")
        out.println("<body>")
        out.println("<script>")
        out.println("Luposdate3000_Endpoint.lupos.endpoint.LuposdateEndpoint.initialize()")
        out.println("console.log(Luposdate3000_Endpoint.lupos.endpoint.LuposdateEndpoint.evaluate_sparql_to_result_b(\"INSERT DATA { <s> <p> <o> } \"))")
        out.println("console.log(Luposdate3000_Endpoint.lupos.endpoint.LuposdateEndpoint.evaluate_sparql_to_result_b(\"SELECT (5 as ?x) ?s {?s ?p ?o .}\"))")
        out.println("</script>")
        out.println("</body>")
        out.println("</html>")
    }
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
