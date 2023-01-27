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
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/Exceptions.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EOperatingSystemExt.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared/inline/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/shared/inline/Platform.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-inline.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-suspend.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-module.kt")
@file:Import("src/luposdate3000_scripting/generate-buildfile-helper.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/dictionary/EDictionaryTypeExt.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/dictionary/EDictionaryType.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EPartitionModeExt.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EPartitionMode.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EGarbageCollector.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EGarbageCollectorExt.kt")
@file:CompilerOptions("-Xmulti-platform") 

import launcher.CreateModuleArgs  
import launcher.DryMode
import launcher.EDictionaryValueMode
import launcher.ExecMode
import launcher.InlineMode
import launcher.IntellijMode
import launcher.ParamClassMode
import launcher.ReleaseMode
import launcher.SuspendMode
import launcher.TargetMode2
import launcher.createBuildFileForModule
import launcher.fixPathNames 
import launcher.targetModeCompatible
import lupos.shared.EGarbageCollectorExt
import lupos.shared.EOperatingSystemExt
import lupos.shared.EPartitionModeExt
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.inline.Platform
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.ProcessBuilder.Redirect
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption.REPLACE_EXISTING
import java.util.jar.JarFile

object LauncherConfig {
    val configFileName = "build.config"
    val config = readConfig()
    fun readConfig(): MutableMap<String, String> {
        var res = mutableMapOf<String, String>()
        if (File(configFileName).exists()) {
            File(configFileName).forEachLine { line ->
                val a = line.split("=")
                if (a.size == 2) {
                    res[a[0]] = a[1]
                }
            }
        }
        return res
    }

    fun writeConfig(cfg: MutableMap<String, String>) {
        File(configFileName).printWriter().use { out ->
            for ((k, v) in cfg) {
                out.println("$k=$v")
            }
        }
    }

    fun setConfigValue(key: String, value: String) {
        config[key] = value
        writeConfig(config)
    }

    fun getConfigValue(key: String, defaultValue: String): String {
        val res = config[key]
        if (res == null) {
            config[key] = defaultValue
            writeConfig(config)
            return defaultValue
        } else {
            return res
        }
    }

    fun getConfigValue(key: String): String {
        val res = config[key]
        if (res == null) {
            TODO("$key")
        } else {
            return res
        }
    }
}

var compileModuleArgs = mutableMapOf<String, MutableMap<String, String>>()
val jsBrowserMode = true
var skipArgs = false
var runArgs = mutableListOf<String>()
val optionsForPackages = mutableMapOf<String, MutableSet<String>>()
val optionsChoosenForPackages = mutableMapOf<String, String>("Buffer_Manager" to "Inmemory", "Network_Wrapper" to "Java_Sockets", "Jena_Wrapper" to "Off")
var execMode = ExecMode.UNKNOWN

fun makeUppercaseStart(s: String): String {
    val res = StringBuilder()
    var flag = true
    for (c in s) {
        if (flag) {
            res.append(c.uppercaseChar())
        } else {
            res.append(c)
        }
        flag = c == '_'
    }
    return res.toString()
}

fun getAllModuleConfigurations(): List<CreateModuleArgs> {
    val localArgs = CreateModuleArgs()
        .ssetDictionaryValueMode(EDictionaryValueMode.valueOf(LauncherConfig.getConfigValue("--dictionaryValueMode")))
        .ssetReleaseMode(ReleaseMode.valueOf(LauncherConfig.getConfigValue("--releaseMode")))
        .ssetSuspendMode(SuspendMode.valueOf(LauncherConfig.getConfigValue("--suspendMode")))
        .ssetInlineMode(InlineMode.valueOf(LauncherConfig.getConfigValue("--inlineMode")))
        .ssetIntellijMode(IntellijMode.valueOf(LauncherConfig.getConfigValue("--intellijMode", "Disable")))
        .ssetTarget(TargetMode2.valueOf(LauncherConfig.getConfigValue("--target")))
        .ssetCodegenKSP(false)
        .ssetCodegenKAPT(false)
        .ssetCompilerVersion(LauncherConfig.getConfigValue("--compilerVersion"))
        .ssetEnabledFunc { true }
        .ssetEnabledRunFunc { true }
.ssetUseKTLint(LauncherConfig.getConfigValue("--useKtLint").toBoolean())
.ssetUseKover(LauncherConfig.getConfigValue("--useKover").toBoolean())
//.ssetUseSimoraDev(LauncherConfig.getConfigValue("--useSimoraDev").toBoolean()) ... this does not work from github-packages
.ssetUseSimoraDev(true)
    var allpackages = mutableSetOf<String>()
    var modules = mutableMapOf<String, CreateModuleArgs>()
    val dependencyMap = mutableMapOf<String, MutableSet<String>>()
// detect modules and configure their parameters
    Files.walk(Paths.get("src"), 1).forEach { it ->
        val filename = fixPathNames(it.toString())
        val f = File(filename + "/module_config")
        if (f.exists()) {
            var currentArgs = localArgs
                .ssetModuleName(makeUppercaseStart(filename.substring(filename.indexOf("luposdate3000"))))
                .ssetModulePrefix(makeUppercaseStart(filename.substring(filename.indexOf("luposdate3000"))))
            if (filename.endsWith("_js_browser")) {
                currentArgs = currentArgs.ssetEnabledRunFunc { jsBrowserMode && targetModeCompatible(TargetMode2.valueOf(LauncherConfig.getConfigValue("--target")), TargetMode2.JS) }
            } else if (filename.endsWith("_js_node")) {
                currentArgs = currentArgs.ssetEnabledRunFunc { !jsBrowserMode && targetModeCompatible(TargetMode2.valueOf(LauncherConfig.getConfigValue("--target")), TargetMode2.JS) }
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
                    line.startsWith("disableJS=") -> {
                        currentArgs.disableJS = line.substring("disableJS=".length).toBoolean()
                    }
                    line.startsWith("disableJSBrowser=") -> {
                        currentArgs.disableJSBrowser = line.substring("disableJSBrowser=".length).toBoolean()
                    }
                    line.startsWith("disableJSNode=") -> {
                        currentArgs.disableJSNode = line.substring("disableJSNode=".length).toBoolean()
                    }
                    line.startsWith("disableJVM=") -> {
                        currentArgs.disableJVM = line.substring("disableJVM=".length).toBoolean()
                    }
                    line.startsWith("disableNative=") -> {
                        currentArgs.disableNative = line.substring("disableNative=".length).toBoolean()
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
                    line.startsWith("useKTLint=") -> {
                        currentArgs.useKTLint = line.substring("useKTLint=".length).toBoolean()
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
                currentArgs = currentArgs.ssetEnabledRunFunc {
                    "Luposdate3000_" + LauncherConfig.getConfigValue("--mainClass") == currentArgs.moduleName
                }
            }
            currentArgs = currentArgs.ssetArgs2(compileModuleArgs)
            modules[currentArgs.moduleName] = currentArgs
            dependencyMap[currentArgs.moduleName] = dep
            allpackages.add(currentArgs.modulePrefix.lowercase())
        }
    }
// infer the dependencies
    for ((k, v) in modules) {
        val dep = dependencyMap[k]!!
        if (!v.moduleName.startsWith("Luposdate3000_Shared")) {
            dep.add("Luposdate3000_Shared")
        }
        Files.walk(Paths.get(v.moduleFolder)).forEach { it2 ->
            val name = fixPathNames(it2.toString())
            val f = File(name)
            if (f.isFile && name.endsWith(".kt")) {
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
                                    if (y.modulePrefix.lowercase() == s && y.enabledRunFunc()) {
                                        found = true
                                        dep.add(y.moduleName)
                                        break
                                    }
                                }
                                if (!found) {
                                    for (y in modules.values) {
                                        if (y.modulePrefix.lowercase() == s) {
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
// add explicit dependencies of inline module and js-module
    for ((k, v) in modules) {
        val c = modules["Luposdate3000_Shared_Inline"]!!
        v.dependenciesCommon.addAll(c.dependenciesCommon)
        v.dependenciesJvm.addAll(c.dependenciesJvm)
        v.dependenciesJs.addAll(c.dependenciesJs)
        v.dependenciesNative.addAll(c.dependenciesNative)
        if (k != "Luposdate3000_Shared_JS_Browser") {
            dependencyMap[k]!!.add("Luposdate3000_Shared_JS_Browser")
        }
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
                v.dependenciesJvm.add("luposdate3000:Luposdate3000_Code_Generator_KSP:0.0.1")
                v.dependenciesJvm.add("luposdate3000:Luposdate3000_Endpoint:0.0.1")
            }
            if (v.codegenKAPT) {
                depss.add("Luposdate3000_Code_Generator_KAPT")
                v.dependenciesJvm.add("luposdate3000:Luposdate3000_Code_Generator_KAPT:0.0.1")
            }
        }
    }
// calculate recursive dependencies
    var flag = true
    while (flag) {
        flag = false
        for (k in modules.keys) {
            val depss = dependencyMap[k]
            if (depss != null) {
                for (dep in depss.toTypedArray()) {
                    val s = depss.size
                    val deps = dependencyMap[dep]
                    if (deps != null) {
                        depss.addAll(deps)
                        if (s != depss.size) {
                            flag = true
                        }
                    }
                }
            }
        }
    }
// add recursive dependencies
    for ((k, v) in modules) {
        val deps = dependencyMap[k]
        if (deps != null) {
            for (dep in deps) {
                v.dependenciesJvm.addAll(modules[dep]!!.dependenciesJvm)
                v.dependenciesJs.addAll(modules[dep]!!.dependenciesJs)
                v.dependenciesNative.addAll(modules[dep]!!.dependenciesNative)
                v.dependenciesCommon.addAll(modules[dep]!!.dependenciesCommon)
                v.dependenciesCommon.add("luposdate3000:$dep:0.0.1")
            }
        }
    }
// add js module explicitly - because there is NO code in the common module
    for ((k, v) in modules) {
        v.dependenciesCommon.remove("luposdate3000:Luposdate3000_Shared_JS_Browser:0.0.1")
        v.dependenciesJvm.remove("luposdate3000:Luposdate3000_Shared_JS_Browser:0.0.1")
        v.dependenciesNative.remove("luposdate3000:Luposdate3000_Shared_JS_Browser:0.0.1")
        if (k != "Luposdate3000_Shared_JS_Browser") {
            v.dependenciesJs.add("luposdate3000:Luposdate3000_Shared_JS_Browser:0.0.1")
        }
    }
// sort modules by dependency
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
// verify that there are no cyclic dependencies
    if (res.size != modules.size || res.size != nameSet.size) {
        throw Exception("something wrong ${modules.keys} ------- $nameSet")
    }
    for (k in optionsForPackages.keys.toTypedArray()) {
        if (optionsForPackages[k]!!.size == 1) {
            optionsForPackages.remove(k)
        }
    }
    return res
}

class ParamClass : Comparable<ParamClass> {
    val hidden: Boolean
    val name: String
    val default: String
    val values: Map<String, () -> Unit>
    val action: () -> Unit
    val action2: (String) -> Unit
    val mode: ParamClassMode
    var additionalHelp: (String) -> Unit = {}

    constructor (name: String, default: String, values: Map<String, () -> Unit>, hidden: Boolean) {
        this.hidden = hidden
        this.name = name
        this.default = LauncherConfig.getConfigValue(name, default)
        this.values = values
        this.action = {}
        this.action2 = {}
        this.mode = ParamClassMode.VALUES
    }

    constructor (name: String, default: String, values: List<String>, hidden: Boolean) {
        this.hidden = hidden
        this.name = name
        this.default = LauncherConfig.getConfigValue(name, default)
        this.values = values.map { it -> it to { LauncherConfig.setConfigValue(name, it) } }.toMap()
        this.action = {}
        this.action2 = {}
        this.mode = ParamClassMode.VALUES
    }

    constructor(name: String, action: () -> Unit, hidden: Boolean) {
        this.hidden = hidden
        this.name = name
        this.default = ""
        this.values = mapOf()
        this.action = action
        this.action2 = {}
        this.mode = ParamClassMode.NO_VALUE
    }

    constructor(name: String, default: String, action: (String) -> Unit, hidden: Boolean) {
        this.hidden = hidden
        this.name = name
        this.default = LauncherConfig.getConfigValue(name, default)
        this.values = mapOf()
        this.action2 = action
        this.action = {}
        this.mode = ParamClassMode.FREE_VALUE
    }

    constructor(name: String, default: String, hidden: Boolean) {
        this.hidden = hidden
        this.name = name
        this.default = LauncherConfig.getConfigValue(name, default)
        this.values = mapOf()
        this.action2 = { it -> LauncherConfig.setConfigValue(name, it) }
        this.action = {}
        this.mode = ParamClassMode.FREE_VALUE
    }

    override fun compareTo(other: ParamClass): Int {
        return name.compareTo(other.name)
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
                            },
                            false,
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
        TargetMode2.values().map { it -> it.toString() }, false,
    ),
    ParamClass(
        "--dryMode",
        DryMode.Disable.toString(),
        DryMode.values().map { it -> it.toString() }, false,
    ),
    ParamClass(
        "--threadCount",
        "", false,
    ),
    ParamClass(
        "--processUrlsStore",
        "", false,
    ),
    ParamClass(
        "--processUrlsQuery",
        "", false,
    ),
    ParamClass(
        "--processCount",
        "",
        {
            LauncherConfig.setConfigValue("--processUrlsStore", Array(it.toInt()) { "localhost:" + (80 + it) }.joinToString(","))
            LauncherConfig.setConfigValue("--processUrlsQuery", Array(it.toInt()) { "localhost:" + (80 + it) }.joinToString(","))
        },
        false,
    ),
    ParamClass(
        "--dictionaryValueMode",
        EDictionaryValueMode.Long.toString(),
        EDictionaryValueMode.values().map { it -> it.toString() }, false,
    ),
    ParamClass(
        "--releaseMode",
        ReleaseMode.Disable.toString(),
        ReleaseMode.values().map { it -> it.toString() }, false,
    ),
    ParamClass(
        "--suspendMode",
        SuspendMode.Disable.toString(),
        SuspendMode.values().map { it -> it.toString() }, false,
    ),
ParamClass(
        "--useKtLint",
"false",
       listOf("true","false"), false,
    ),
ParamClass(
        "--useKover",
"false",
       listOf("true","false"), false,
    ),
ParamClass(
        "--useSimoraDev",
"false",
       listOf("true","false"), false,
    ),
    ParamClass(
        "--compilerVersion",
        "1.8.0",
        listOf(
            "1.4.0",
            "1.5.0",
            "1.5.21",
            "1.5.30-RC",
            "1.5.255-SNAPSHOT",
            "1.6.0",
            "1.6.10",
            "1.6.20-M1",
            "1.6.255-SNAPSHOT",
            "1.7.255-SNAPSHOT",
            "1.8.0",
// for new versions look at https://oss.sonatype.org/content/repositories/snapshots/org/jetbrains/kotlin/multiplatform/org.jetbrains.kotlin.multiplatform.gradle.plugin/
        ),
        false,
    ),
    ParamClass(
        "--inlineMode",
        InlineMode.Enable.toString(),
        InlineMode.values().map { it -> it.toString() }, false,
    ),
    ParamClass(
        "--partitionMode",
        EPartitionModeExt.names[EPartitionModeExt.None],
        EPartitionModeExt.names.toList(), false,
    ),
    ParamClass(
        "--garbageCollector",
        EGarbageCollectorExt.names[EGarbageCollectorExt.Shenandoah],
        EGarbageCollectorExt.names.toList(), false,
    ),
    ParamClass(
        "--dictionaryMode",
        EDictionaryTypeExt.names[EDictionaryTypeExt.KV],
        EDictionaryTypeExt.names.toList(), false,
    ),
    ParamClass(
        "--help",
        {
            execMode = ExecMode.HELP
            skipArgs = true
        },
        false,
    ),
    ParamClass(
        "--generateLauncher",
        {
            execMode = ExecMode.GENERATE_LAUNCHER
            skipArgs = true
        },
        false,
    ),
    ParamClass(
        "--generateEnums",
        {
            execMode = ExecMode.GENERATE_ENUMS
            skipArgs = true
        },
        false,
    ),
    ParamClass(
        "--run",
        {
            execMode = ExecMode.RUN
        },
        false,
    ),
    ParamClass(
        "--clearCaches",
        {
            if (Platform.getOperatingSystem() == EOperatingSystemExt.Windows) {
                File("${System.getenv("LOCALAPPDATA")}/main.kts.compiled.cache").deleteRecursively()
            } else {
                File("${System.getProperty("user.home")}/.cache/main.kts.compiled.cache").deleteRecursively()
            }
            System.exit(0)
        },
        false,
    ),
    ParamClass(
        "--intellijMode",
        IntellijMode.Enable.toString(),
        IntellijMode.values().map { it -> it.toString() }, false,
    ),
    ParamClass(
        "--copySPAClient",
        {
            enableParams(compileParams)
            execMode = ExecMode.SETUP_SPACLIENT
        },
        true,
    ),
    ParamClass(
        "--setup",
        {
            enableParams(compileParams)
            execMode = ExecMode.SETUP_GRADLE
        },
        false,
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
            values = optionsForPackages[k]!!.map { it.substring(k.length + 1) to { optionsChoosenForPackages[k] = it } }.toMap(), false,
        ),
    )
}
enableParams(defaultParams)
val mainclassParams = listOf(
    ParamClass(
        "--mainClass",
        "Launch_Endpoint",
        optionsForPackages["Main"]!!.sorted(), false,
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
when (execMode) {
    ExecMode.HELP -> onHelp()
    ExecMode.RUN -> onRun()
    ExecMode.GENERATE_LAUNCHER -> onGenerateLauncherMain()
    ExecMode.GENERATE_ENUMS -> onGenerateEnums()
    ExecMode.SETUP_GRADLE -> onSetupGradle()
    ExecMode.SETUP_SPACLIENT -> onSetupSPAClient()
    else -> {
        throw Exception("unknown execMode $execMode")
    }
}
fun onHelp() {
    println("Usage ./launcher.main.kts <options>")
    println("where possible options include:")
    for (param in defaultParams.sorted()) {
        if (!param.hidden) {
            param.help()
        }
    }
    for (param in mainclassParams) {
        if (!param.hidden) {
            param.help()
        }
    }
    for (param in getAllModuleSpecificParams()) {
        if (!param.hidden) {
            param.help()
        }
    }
    System.exit(1)
}

fun onSetupGradle() {
    File(".idea").deleteRecursively()
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
        outBuildGradle.println("if (File(\"src/build.gradle.kts\").exists()) {")
        outBuildGradle.println("    dependencies {")
        outBuildGradle.println("        project(\":src\")")
        outBuildGradle.println("    }")
        outBuildGradle.println("} else {")
        outBuildGradle.println("    task<Exec>(\"build\") {")
        outBuildGradle.println("        commandLine(\"./launcher.main.kts\", \"--help\")")
        outBuildGradle.println("    }")
        outBuildGradle.println("}")
    }
    File("settings.gradle.kts").printWriter().use { outSettingsGradle ->
        File("src/build.gradle.kts").printWriter().use { outBuildGradle ->
//            outSettingsGradle.println("sourceControl {")
//            outSettingsGradle.println("    gitRepository(uri(\"https://github.com/luposdate3000/SIMORA.git\")){")
//            outSettingsGradle.println("        producesModule(\"simora:simora\")")
//            outSettingsGradle.println("    }")
//            outSettingsGradle.println("}")
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
            outSettingsGradle.println("        google()")
            outSettingsGradle.println("        gradlePluginPortal()")
            outSettingsGradle.println("        maven { url = uri(\"https://oss.sonatype.org/content/repositories/snapshots/\") }")
            outSettingsGradle.println("    }")
            outSettingsGradle.println("}")
            outSettingsGradle.println("rootProject.name = \"Luposdate3000\"")
            outSettingsGradle.println("include(\":src\")")
            outBuildGradle.println("dependencies {")
            Files.walk(Paths.get("src"), 1).forEach { it ->
                val name = fixPathNames(it.toString())
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
    when {
        targetModeCompatible(TargetMode2.valueOf(LauncherConfig.getConfigValue("--target")), TargetMode2.JVM) -> {
            val jarsLuposdate3000 = mutableListOf<String>()
            val jars = mutableSetOf<String>()
            for (module in getAllModuleConfigurations()) {
                if (module.enabledRunFunc()) {
                    jarsLuposdate3000.add("${module.moduleFolder}/build/libs/${module.moduleName.lowercase()}-jvm-0.0.1.jar")
                    jars.add("${module.moduleFolder}/build/libs/${module.moduleName.lowercase()}-jvm-0.0.1.jar")
                    if (LauncherConfig.getConfigValue("--dryMode") == "Enable_Test") {
                        val f = File("${module.moduleFolder}/build/external_jvm_test_dependencies")
                        if (f.exists()) {
                            f.forEachLine {
                                jars.add(it)
                            }
                        }
                    }
                    val f = File("${module.moduleFolder}/build/external_jvm_dependencies")
                    if (f.exists()) {
                        f.forEachLine {
                            jars.add(it)
                        }
                    }
                }
            }
            var junitMain = ""
            var classpath = ""
            for (jar in jars.sorted()) {
                if (jar.contains("standalone")) {
                    println(jar)
                }
                if (jar.contains("junit-platform-console-standalone")) {
                    junitMain = jar
                } else if (classpath == "") {
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
                when (EGarbageCollectorExt.names.indexOf(LauncherConfig.getConfigValue("--garbageCollector"))) {
                    EGarbageCollectorExt.Epsilon -> {
                        cmd.add("-Xmx100g")
                        cmd.add("-Xms100g")
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
            val a1 = LauncherConfig.getConfigValue("--processUrlsStore")
            val a2 = LauncherConfig.getConfigValue("--processUrlsQuery")
            val a3 = (a1.split(",").toList() + a2.split(",").toList()).toSet()
            var c = a3.size
            if (c == 0) {
                c = 1
            }
            if (LauncherConfig.getConfigValue("--dryMode") == "Enable_Test") {
                cmd.add("-jar")
                cmd.add(junitMain)
                cmd.add("-cp")
                cmd.add(classpath)

                println("export LUPOS_PROCESS_URLS_STORE=$a1")
                println("export LUPOS_PROCESS_URLS_QUERY=$a2")
                println("export LUPOS_THREAD_COUNT=${LauncherConfig.getConfigValue("--threadCount")}")
                println("export LUPOS_PARTITION_MODE=${LauncherConfig.getConfigValue("--partitionMode")}")
                println("export LUPOS_DICTIONARY_MODE=${LauncherConfig.getConfigValue("--dictionaryMode")}")
                println("exec :: " + cmd.joinToString(" "))
            } else if (LauncherConfig.getConfigValue("--dryMode") == "Enable") {
                cmd.add("-cp")
                cmd.add(classpath)
                cmd.add("MainKt")
                cmd.addAll(runArgs)
                println("export LUPOS_PROCESS_URLS_STORE=$a1")
                println("export LUPOS_PROCESS_URLS_QUERY=$a2")
                println("export LUPOS_THREAD_COUNT=${LauncherConfig.getConfigValue("--threadCount")}")
                println("export LUPOS_PARTITION_MODE=${LauncherConfig.getConfigValue("--partitionMode")}")
                println("export LUPOS_DICTIONARY_MODE=${LauncherConfig.getConfigValue("--dictionaryMode")}")
                println("exec :: " + cmd.joinToString(" "))
            } else {
                cmd.add("-cp")
                cmd.add(classpath)
                cmd.add("MainKt")
                cmd.addAll(runArgs)
                Array(c) {
                    val p = myProcessBuilder(cmd)
                        .redirectOutput(Redirect.INHERIT)
                        .redirectError(Redirect.INHERIT)
                    val env = p.environment()
                    env["LUPOS_PROCESS_ID"] = "$it"
                    env["LUPOS_PROCESS_URLS_STORE"] = "$a1"
                    env["LUPOS_PROCESS_URLS_QUERY"] = "$a2"
                    env["LUPOS_THREAD_COUNT"] = "${LauncherConfig.getConfigValue("--threadCount")}"
                    env["LUPOS_PARTITION_MODE"] = "${LauncherConfig.getConfigValue("--partitionMode")}"
                    env["LUPOS_DICTIONARY_MODE"] = "${LauncherConfig.getConfigValue("--dictionaryMode")}"
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
        var mask = 0
        for (i in 0 until mapping.size) {
            out.println("    $modifier const val ${mapping[i]}: $enumName = $i // 0x${i.toString(16).padStart(8, '0')}")
            mask = mask or i
        }
        out.println("    $modifier const val values_size: Int = ${mapping.size}")
        out.println("    $modifier const val values_mask: Int = $mask // 0x${mask.toString(16).padStart(8, '0')}")
        out.println("    $modifier const val values_mask_inversed: Int = ${mask.toLong().inv() and 0x7FFFFFFF} // 0x${(mask.toLong().inv() and 0x7FFFFFFF).toString(16).padStart(8, '0')}")
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
    val enumArgs = arrayOf(
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
        listOf("EQueryResultToStream", "lupos.result_format", "public", "src/luposdate3000_result_format/src/commonMain/kotlin/lupos/result_format/EQueryResultToStream"),
        listOf("EOptimizerID", "lupos.optimizer.logical", "public", "src/luposdate3000_optimizer_logical/src/commonMain/kotlin/lupos/optimizer/logical/EOptimizerID"),
        listOf("EOptimizer", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EOptimizer"),
        listOf("EVariablePlaceholder", "lupos.code_generator_shared", "internal", "src/luposdate3000_code_generator_shared/src/commonMain/kotlin/lupos/code_generator_shared/EVariablePlaceholder"),
        listOf("EQueryDistributionMode", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EQueryDistributionMode"),
        listOf("EPredefinedPartitionSchemes", "lupos.shared", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/EPredefinedPartitionSchemes"),
    )
    for (args in enumArgs) {
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
                        if (options.size > 0) {
                            out.println("public fun main(args: Array<String>) {")
                            out.println("    var flag = false")
                        } else {
                            out.println("public fun main() {")
                        }
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
    val buf = ByteArray(4096)
    var count = source.read(buf)
    while (count > 0) {
        out.write(buf, 0, count)
        count = source.read(buf)
    }
    out.close()
    source.close()
}

fun onSetupSPAClient() {
    println("onSetupSPAClient")
    val dirname = fixPathNames("${File(".").absolutePath}/src/luposdate3000_spa_client/")
    println("dirname : $dirname")
    val dirluposdatejs = fixPathNames("$dirname/app/scripts/algos/luposdate3000/")
    val relativeUrlJs = fixPathNames("app/scripts/algos/luposdate3000/")
    val dir = File(dirname)
    val scriptFiles = getJSScriptFiles()
    println(File(dirluposdatejs).absolutePath)
    File(dirluposdatejs).mkdirs()
    val imports = mutableListOf<String>()
    println("scriptFiles :: $scriptFiles")
    for (script in scriptFiles) {
        val src = fixPathNames(script)
        val dest = fixPathNames("$dirluposdatejs/${src.substring(src.lastIndexOf("/") + 1)}")
        val dest2 = fixPathNames("$relativeUrlJs/${src.substring(src.lastIndexOf("/") + 1)}")
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
        imports.add("\"$dest2\",")
    }
    val cache = mutableListOf<String>()
    var mode = 0
    File(dir, "gulpfile.js").forEachLine { line ->
        when (mode) {
            0 -> {
                cache.add(line)
                if (line.contains("LUPOSDATE3000 GENERATED CODE START")) {
                    mode = 1
                    cache.addAll(imports)
                }
            }
            1 -> {
                if (line.contains("LUPOSDATE3000 GENERATED CODE END")) {
                    cache.add(line)
                    mode = 0
                }
            }
        }
    }
    File(dir, "gulpfile.js").printWriter().use { out ->
        for (c in cache) {
            out.println(c)
        }
    }
/*
    val bin_gulp = fixPathNames("./node_modules/.bin/gulp")
    println("bin_gulp :" + bin_gulp)
    val commands = mutableListOf<List<String>>()
    commands.add(listOf(bin_gulp))
    for (cmd in commands) {
        println("cmd :: $cmd")
        val p = myProcessBuilder(cmd)
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
*/
}

fun commandToString(pbin: ProcessBuilder): String {
    val bproc = pbin.start()
    val reader = bproc.getInputStream().reader()
    val res = reader.readText()
    reader.close()
    return res
}

fun getJSScriptFiles(): List<String> {
    File("dist-js").deleteRecursively()
    File("dist-js").mkdirs()
    val dependencies = mutableListOf<String>()
    val scripts = mutableListOf<String>()
    val modules = getAllModuleConfigurations()
    val whiteList = mutableSetOf<String>("luposdate3000:Luposdate3000_Endpoint:0.0.1")
    for (module in modules) {
        if (module.modulePrefix == "Luposdate3000_Endpoint") {
            for (w in module.dependenciesJs) {
                whiteList.add(w)
            }
            for (w in module.dependenciesCommon) {
                whiteList.add(w)
            }
            break
        }
    }
    loop@ for (module in modules) {
        if (module.enabledRunFunc() && module.modulePrefix != "Luposdate3000_Main") {
            if (!whiteList.contains("luposdate3000:${module.moduleName}:0.0.1")) {
                println("skipping ${module.moduleName} due to not in whitelist")
                continue@loop
            }
            val f = File("${module.moduleFolder}/build/external_js_dependencies")
            if (f.exists()) {
                f.forEachLine {
                    if (!dependencies.contains(it)) {
                        if (it.contains("kotlin-stdlib")) {
                            dependencies.add(0, it)
                        } else {
                            dependencies.add(it)
                        }
                    }
                }
                var s: String
                if (LauncherConfig.getConfigValue("--releaseMode") == "Enable") {
                    s = "${module.moduleFolder}/build/distributions/${module.moduleName.lowercase()}.js"
                } else {
                    s = "${module.moduleFolder}/build/libs/${module.moduleName.lowercase()}-js-0.0.1.jar"
                }
                if (!dependencies.contains(s)) {
                    dependencies.add(s)
                }
            }
        } else {
            println("skipping ${module.moduleName} due to not enabled")
        }
    }
    for (s in dependencies) {
        if (s.endsWith(".js")) {
            scripts.add(fixPathNames(File(".", s).absolutePath))
        } else if (s.endsWith(".jar")) {
            val f = JarFile(File(s))
            for (e in f.entries()) {
                val name = e.getName()
                if (name.endsWith(".js.map")) {
                    copyFromJar(f.getInputStream(e), "dist-js/$name")
                } else if (name.endsWith(".js") && !name.endsWith("meta.js")) {
                    copyFromJar(f.getInputStream(e), "dist-js/$name")
                    scripts.add(fixPathNames(File(".", "dist-js/$name").absolutePath))
                }
            }
        } else {
            throw Exception("unknown dependency '$s'")
        }
    }
    return scripts
}

fun myProcessBuilder(cmd: List<String>): ProcessBuilder {
    if (Platform.getOperatingSystem() == EOperatingSystemExt.Windows) {
        if (cmd[0].contains("/")) {
            val lastIdx = cmd[0].lastIndexOf("/")
            val path = cmd[0].substring(0, lastIdx)
            val proc = cmd[0].substring(lastIdx + 1)
            val realCmd = mutableListOf<String>("cmd.exe", "/C", proc)
            for (i in 1 until cmd.size) {
                realCmd.add(cmd[i])
            }
            println("realCmd:: " + realCmd)
            val pb = ProcessBuilder(realCmd)
            val env = pb.environment()
            println("oldpath:: " + env["PATH"])
            env["PATH"] = path + ";" + env["PATH"]
            return pb
        } else {
            return ProcessBuilder(cmd)
        }
    } else {
        return ProcessBuilder(cmd)
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
