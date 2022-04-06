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
package launcher
import lupos.shared.myPrintStackTrace

import lupos.shared.EOperatingSystemExt
import lupos.shared.inline.Platform
import java.io.File
import java.io.PrintWriter
import java.lang.ProcessBuilder.Redirect
import java.nio.file.Paths

val copySelevtively = false

val validPlatforms = listOf("iosArm32", "iosArm64", "linuxX64", "macosX64", "mingwX64")
private fun printDependencies(dependencies: Set<String>, out: PrintWriter, config: CreateModuleArgs) {
    for (d in dependencies) {
        if (d.startsWith("simora:simora")) {
            if (config.useSimoraDev) {
                out.println("                implementation(\"$d\")")
            } else {
                out.println("                implementation(\"simora:simora\"){version{branch = \"master\"}}")
            }
        } else if (d.startsWith("luposdate3000")) {
            var t = d.substring("luposdate3000:".length, d.lastIndexOf(":")).lowercase()
            if (t.contains("#")) {
                t = t.substring(0, t.indexOf("#"))
            }
            out.println("                implementation(project(\":src:${t}\"))")
        } else if (d.startsWith("npm(")) {
            out.println("                implementation($d)")
        } else {
            out.println("                implementation(\"$d\")")
        }
    }
}

private fun copyFileWithReplacement(src: File, dest: File, replacement: Map<String, String>, sharedInlineReferences: MutableSet<String>) {
    val out = dest.printWriter()
    val effectiveReplacement = mutableMapOf<String, String>()
    effectiveReplacement.putAll(replacement)
    if (src.toString().contains("jsMain")) {
        effectiveReplacement.remove(" public ")
    }
    var line = 0
    src.forEachLine { it ->
        var s = it
        for ((k, v) in effectiveReplacement) {
            if (s.startsWith("import lupos.shared.inline.")) {
                sharedInlineReferences.add(s.substring("import lupos.shared.inline.".length))
            } else if (s.startsWith("// require lupos.shared.inline.")) {
                sharedInlineReferences.add(s.substring("// require lupos.shared.inline.".length))
            }
            s = s.replace(k, v)
            if (k.startsWith(" ")) {
                if (s.startsWith(k.substring(1))) {
                    s = v + s.substring(k.length - 1)
                }
            }
        }
        s = s.replace("SOURCE_FILE_START.*SOURCE_FILE_END".toRegex(), "SOURCE_FILE_START*/\"${fixPathNames(src.absolutePath)}:$line\"/*SOURCE_FILE_END")
        out.println(s)
        line++
    }

    out.close()
}

private fun copyFilesWithReplacement(src: String, dest: String, replacement: Map<String, String>, sharedInlineReferences: MutableSet<String>) {
    for (it in File(src).walk()) {
        val tmp = fixPathNames(it.toString())
        val t = tmp.substring(src.length)
        if (File(tmp).isFile) {
            copyFileWithReplacement(File(src + "/" + t), File(dest + "/" + t), replacement, sharedInlineReferences)
        } else {
            File(dest + "/" + t).mkdirs()
        }
    }
}

class CreateModuleArgs() {
    var compilerVersion: String = ""
    var enabledFunc: () -> Boolean = { true }
    var enabledRunFunc: () -> Boolean = { true }
    var moduleName: String = ""
    var moduleFolder: String = ""
    var modulePrefix: String = ""
    var platform: String = "linuxX64"
    var dictionaryValueMode: EDictionaryValueMode = EDictionaryValueMode.Int
    var releaseMode: ReleaseMode = ReleaseMode.Disable
    var suspendMode: SuspendMode = SuspendMode.Disable
    var inlineMode: InlineMode = InlineMode.Disable
    var intellijMode: IntellijMode = IntellijMode.Disable
    var target: TargetMode2 = TargetMode2.JVM
    var codegenKAPT: Boolean = false
    var codegenKSP: Boolean = false
    var args: MutableMap<String, String> = mutableMapOf()
    var dependenciesCommon: MutableSet<String> = mutableSetOf<String>()
    var dependenciesJvm: MutableSet<String> = mutableSetOf<String>()
    var dependenciesJs: MutableSet<String> = mutableSetOf<String>()
    var dependenciesNative: MutableSet<String> = mutableSetOf<String>()
    var useKTLint = true
    var useKover = false
    var disableJS = false
    var disableJSNode = false
    var disableJSBrowser = false
    var disableJVM = false
    var disableNative = false
    var useSimoraDev = false

    init {
        if (Platform.getOperatingSystem() == EOperatingSystemExt.Windows) {
            platform = "mingw64"
        } else {
            platform = "linuxX64"
        }
    }

    fun clone(): CreateModuleArgs {
        var res = CreateModuleArgs()
        res.dictionaryValueMode = dictionaryValueMode
        res.disableJS = disableJS
        res.disableJSNode = disableJSNode
        res.disableJSBrowser = disableJSBrowser
        res.disableJVM = disableJVM
        res.disableNative = disableNative
        res.dependenciesCommon.addAll(dependenciesCommon)
        res.dependenciesJvm.addAll(dependenciesJvm)
        res.dependenciesJs.addAll(dependenciesJs)
        res.dependenciesNative.addAll(dependenciesNative)
        res.compilerVersion = compilerVersion
        res.enabledFunc = enabledFunc
        res.enabledRunFunc = enabledRunFunc
        res.moduleName = moduleName
        res.moduleFolder = moduleFolder
        res.modulePrefix = modulePrefix
        res.platform = platform
        res.releaseMode = releaseMode
        res.suspendMode = suspendMode
        res.inlineMode = inlineMode
        res.intellijMode = intellijMode
        res.target = target
        res.codegenKAPT = codegenKAPT
        res.codegenKSP = codegenKSP
        res.args = args
        res.useKTLint = useKTLint
        res.useKover = useKover
        res.useSimoraDev = useSimoraDev
        return res
    }

    fun ssetDictionaryValueMode(mode: EDictionaryValueMode): CreateModuleArgs {
        val res = clone()
        res.dictionaryValueMode = mode
        return res
    }

    fun ssetCompilerVersion(compilerVersion: String): CreateModuleArgs {
        val res = clone()
        res.compilerVersion = compilerVersion
        return res
    }

    fun ssetUseKTLint(useKTLint: Boolean): CreateModuleArgs {
        val res = clone()
        res.useKTLint = useKTLint
        return res
    }

    fun ssetUseSimoraDev(useSimoraDev: Boolean): CreateModuleArgs {
        val res = clone()
        res.useSimoraDev = useSimoraDev
        return res
    }

    fun ssetUseKover(useKover: Boolean): CreateModuleArgs {
        val res = clone()
        res.useKover = useKover
        return res
    }

    fun ssetEnabledFunc(enabledFunc: () -> Boolean): CreateModuleArgs {
        val res = clone()
        res.enabledFunc = enabledFunc
        return res
    }

    fun ssetEnabledRunFunc(enabledRunFunc: () -> Boolean): CreateModuleArgs {
        val res = clone()
        res.enabledRunFunc = enabledRunFunc
        return res
    }

    fun ssetArgs(args: MutableMap<String, String>): CreateModuleArgs {
        val res = clone()
        res.args = mutableMapOf()
        res.args.putAll(this.args)
        res.args.putAll(args)
        return res
    }

    fun ssetArgs2(args: MutableMap<String, MutableMap<String, String>>): CreateModuleArgs {
        var arg = args["Luposdate3000_Shared_Inline"]
        if (arg != null) {
            val res = clone()
            res.args = mutableMapOf()
            res.args.putAll(this.args)
            res.args.putAll(arg)
        }
        arg = args[moduleName]
        if (arg != null) {
            val res = clone()
            res.args = mutableMapOf()
            res.args.putAll(this.args)
            res.args.putAll(arg)
            args.remove(moduleName)
            return res
        } else {
            return this
        }
    }

    fun ssetModuleName(moduleName: String): CreateModuleArgs {
        val res = clone()

        res.moduleName = moduleName
        res.moduleFolder = "src/${moduleName.lowercase()}"
        return res
    }

    fun ssetModulePrefix(modulePrefix: String): CreateModuleArgs {
        val res = clone()
        res.modulePrefix = modulePrefix
        return res
    }

    fun ssetReleaseMode(releaseMode: ReleaseMode): CreateModuleArgs {
        val res = clone()
        res.releaseMode = releaseMode
        return res
    }

    fun ssetDisableJS(disableJs: Boolean): CreateModuleArgs {
        val res = clone()
        res.disableJS = disableJs
        return res
    }

    fun ssetDisableJSNode(disableJsNode: Boolean): CreateModuleArgs {
        val res = clone()
        res.disableJSNode = disableJsNode
        return res
    }

    fun ssetDisableJSBrowser(disableJsBrowser: Boolean): CreateModuleArgs {
        val res = clone()
        res.disableJSBrowser = disableJsBrowser
        return res
    }

    fun ssetDisableJVM(disableJVM: Boolean): CreateModuleArgs {
        val res = clone()
        res.disableJVM = disableJVM
        return res
    }

    fun ssetDisableNative(disableNative: Boolean): CreateModuleArgs {
        val res = clone()
        res.disableNative = disableNative
        return res
    }

    fun ssetSuspendMode(suspendMode: SuspendMode): CreateModuleArgs {
        val res = clone()
        res.suspendMode = suspendMode
        return res
    }

    fun ssetInlineMode(inlineMode: InlineMode): CreateModuleArgs {
        val res = clone()
        res.inlineMode = inlineMode
        return res
    }

    fun ssetIntellijMode(intellijMode: IntellijMode): CreateModuleArgs {
        val res = clone()
        res.intellijMode = intellijMode
        return res
    }

    fun ssetTarget(target: TargetMode2): CreateModuleArgs {
        val res = clone()
        res.target = target
        return res
    }

    fun ssetCodegenKSP(codegenKSP: Boolean): CreateModuleArgs {
        val res = clone()
        res.codegenKSP = codegenKSP
        return res
    }

    fun ssetCodegenKAPT(codegenKAPT: Boolean): CreateModuleArgs {
        val res = clone()
        res.codegenKAPT = codegenKAPT
        return res
    }

    fun getPossibleOptions(): List<String> {
        val res = mutableListOf<String>()
        if (File(File(moduleFolder), "configOptions").exists()) {
            File(File(moduleFolder), "configOptions").forEachLine {
                val opt = it.split(",")
                if (opt.size == 4) {
                    res.add(opt[0])
                }
            }
        }
        return res
    }
}

public fun createBuildFileForModule(moduleArgs: CreateModuleArgs) {
    val useKover = moduleArgs.useKover
    var dummy = 0
    val buildLibrary = moduleArgs.modulePrefix != "Luposdate3000_Main"
    moduleArgs.disableJSNode = true // tests and therefore the code wont work there due to Int64Array
    val enableJVM = targetModeCompatible(moduleArgs.target, TargetMode2.JVM) && !moduleArgs.disableJVM
    val enableJS = targetModeCompatible(moduleArgs.target, TargetMode2.JS) && !moduleArgs.disableJS && (!moduleArgs.disableJSNode || !moduleArgs.disableJSBrowser)
    val enableNative = targetModeCompatible(moduleArgs.target, TargetMode2.Native) && !moduleArgs.disableNative
    if (!(enableJVM || enableJS || enableNative)) {
        return
    }
    if (File("${moduleArgs.moduleFolder}/build.template.gradle.kts").exists()) {
        File("${moduleArgs.moduleFolder}/build.gradle.kts").printWriter().use { out ->
            File("${moduleArgs.moduleFolder}/build.template.gradle.kts").forEachLine { line ->
                out.println(line)
            }
        }
        return
    }
    var useKTLint = moduleArgs.useKTLint
    try {
        val replacementsDefault = mutableMapOf<String, String>()
        if (buildLibrary) {
            replacementsDefault[" public "] = " @lupos.ProguardKeepAnnotation public "
        }
        val sharedInlineReferences = mutableSetOf<String>()
        var appendix = ""
        if (moduleArgs.suspendMode == SuspendMode.Enable) {
            appendix += "_Coroutines"
        } else {
            appendix += "_Threads"
        }
        if (moduleArgs.inlineMode == InlineMode.Enable) {
            appendix += "_Inline"
        } else {
            appendix += "_NoInline"
        }
        if (moduleArgs.releaseMode == ReleaseMode.Enable) {
            appendix += "_Release"
        } else {
            appendix += "_Debug"
        }
        val onWindows = System.getProperty("os.name").contains("Windows")
        val enableProguard = !onWindows && enableJVM && !buildLibrary && moduleArgs.compilerVersion == "1.5.0"
        useKTLint = useKTLint && !onWindows
        println("generating buildfile for ${moduleArgs.moduleName}")
        if (!buildLibrary && moduleArgs.codegenKSP) {
            if (moduleArgs.compilerVersion.contains("SNAPSHOT")) {
                return
            }
            if (!moduleArgs.compilerVersion.startsWith("1.4")) {
                return // currently there is no 1.5 plugin from jetbrains
            }
        }
        var shortFolder = "./${moduleArgs.moduleName}"
        shortFolder = shortFolder.substring(shortFolder.lastIndexOf("/") + 1)
        val buildFolder = "build-cache/build_$shortFolder$appendix"
        println("buildFolder :: $buildFolder")
        val srcFolder = "build-cache/src_$shortFolder$appendix"
        val p = Paths.get("${moduleArgs.moduleFolder}/src")
        println("basepath=$p")
        val commonDependencies = mutableSetOf<String>()
        commonDependencies.addAll(moduleArgs.dependenciesCommon)
        val jvmDependencies = mutableSetOf<String>()
        jvmDependencies.addAll(moduleArgs.dependenciesJvm)
        val jsDependencies = mutableSetOf<String>()
        jsDependencies.addAll(moduleArgs.dependenciesJs)
        jsDependencies.removeAll(commonDependencies)
        val nativeDependencies = mutableSetOf<String>()
        nativeDependencies.addAll(moduleArgs.dependenciesNative)
        nativeDependencies.removeAll(commonDependencies)
        var shared_inline_base_folder = fixPathNames("${File(".").absolutePath}/src/")
        var shared_config_base_folder: String
        if (moduleArgs.intellijMode == IntellijMode.Enable) {
            shared_inline_base_folder += "xxx_generated_xxx/${moduleArgs.moduleFolder}"
            shared_config_base_folder = shared_inline_base_folder
            File("$shared_inline_base_folder/src/commonMain/kotlin/lupos/shared").deleteRecursively()
        } else {
            shared_config_base_folder = shared_inline_base_folder + "xxx_generated_xxx/${moduleArgs.moduleFolder}"
            shared_inline_base_folder += "luposdate3000_shared_inline"
            File("$shared_config_base_folder/src/commonMain/kotlin/lupos/shared").deleteRecursively()
        }
        File("$shared_config_base_folder/src/commonMain/kotlin/lupos/shared").mkdirs()
        File("$shared_inline_base_folder/src/commonMain/kotlin/lupos/shared").mkdirs()
        for (filename in listOf("${moduleArgs.moduleFolder}/build.gradle.kts")) {
            File(filename).printWriter().use { out ->
                out.println("import org.gradle.api.tasks.testing.logging.TestExceptionFormat")
                out.println("import org.gradle.api.tasks.testing.logging.TestLogEvent")
                out.println("buildscript {")
                out.println("    repositories {")
                out.println("        mavenLocal()")
                out.println("        google()")
                out.println("        mavenCentral()")
                out.println("        maven { url = uri(\"https://oss.sonatype.org/content/repositories/snapshots/\") }")
                out.println("    }")
                out.println("    dependencies {")
                out.println("        classpath(\"org.jetbrains.kotlin:kotlin-gradle-plugin:${moduleArgs.compilerVersion}\")")
                if (enableProguard) {
                    out.println("        classpath(\"com.guardsquare:proguard-gradle:7.1.0-beta3\")")
                }
                out.println("    }")
                out.println("}")
                val allDep = mutableSetOf<String>()
                allDep.addAll(commonDependencies)
                allDep.addAll(jsDependencies)
                allDep.addAll(jvmDependencies)
                allDep.addAll(nativeDependencies)
                allDep.addAll(moduleArgs.dependenciesJvm)
                for (d in allDep) {
                    if (d.startsWith("luposdate3000")) {
                        var t = d.substring("luposdate3000:".length, d.lastIndexOf(":")).lowercase()
                        if (t.contains("#")) {
                            t = t.substring(0, t.indexOf("#"))
                        }
                        out.println("evaluationDependsOn(\":src:${t}\")")
                    }
                }
                out.println("plugins {")
                if (useKover) {
                    out.println("    id(\"org.jetbrains.kotlinx.kover\") version \"SNAPSHOT-255\"")
                }
                if (useKTLint) {
                    out.println("    id(\"org.jlleitschuh.gradle.ktlint\") version \"10.1.0\"")
                }
                out.println("    id(\"org.jetbrains.kotlin.multiplatform\") version \"${moduleArgs.compilerVersion}\"")
                if (moduleArgs.codegenKAPT) {
                    out.println("    id(\"org.jetbrains.kotlin.kapt\") version \"${moduleArgs.compilerVersion}\"")
                }
                if (!buildLibrary && moduleArgs.codegenKSP) {
                    if (moduleArgs.compilerVersion.startsWith("1.4")) {
                        out.println("    id(\"kotlin-ksp\") version \"1.4.0-dev-experimental-20200914\"")
                    } else {
                        return // currently there is no 1.5 plugin from jetbrains
                    }
                }
                var serializationPluginNeeded = false
                for (d in allDep) {
                    if (d.contains("kotlinx-serialization")) {
                        serializationPluginNeeded = true
                        break
                    }
                }
                if (serializationPluginNeeded) {
                    out.println("    id(\"org.jetbrains.kotlin.plugin.serialization\") version \"${moduleArgs.compilerVersion}\"")
                }
                if (!buildLibrary) {
                    out.println("    application")
                }
                out.println("}")
                out.println("repositories {")
                out.println("    mavenLocal()")
                out.println("    google()")
                out.println("    mavenCentral()")
                out.println("    maven { url = uri(\"https://oss.sonatype.org/content/repositories/snapshots/\") }")
                out.println("}")
                out.println("group = \"luposdate3000\"") // maven-groupID
                out.println("version = \"0.0.1\"") // maven-version
                out.println("apply(plugin = \"maven-publish\")")
                // see /opt/kotlin/compiler/cli/cli-common/src/org/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments.kt
                // or kotlinc -X
                out.println("kotlin {")
                out.println("    explicitApi()") // https://zsmb.co/mastering-api-visibility-in-kotlin/
                out.println("    metadata {")
                out.println("        compilations.forEach {")
                out.println("            it.kotlinOptions {")
                out.println("                freeCompilerArgs += \"-opt-in=kotlin.RequiresOptIn\"")
                out.println("                freeCompilerArgs += \"-Xnew-inference\"")
                out.println("                freeCompilerArgs += \"-Xinline-classes\"")
                out.println("            }")
                out.println("        }")
                out.println("    }")
                if (enableJVM) {
                    out.println("    jvm {")
                    out.println("        compilations.forEach {")
                    out.println("            it.kotlinOptions {")
//                    out.println("                jvmTarget = \"14\"")
                    out.println("                jvmTarget = \"1.8\"")
                    out.println("                freeCompilerArgs += \"-opt-in=kotlin.RequiresOptIn\"")
                    out.println("                freeCompilerArgs += \"-Xno-param-assertions\"")
                    out.println("                freeCompilerArgs += \"-Xnew-inference\"")
                    out.println("                freeCompilerArgs += \"-Xno-receiver-assertions\"")
                    out.println("                freeCompilerArgs += \"-Xno-call-assertions\"")
                    out.println("            }")
                    out.println("        }")
                    out.println("    }")
                }
                if (enableJS) {
                    out.println("    js {")
                    out.println("        moduleName = \"${moduleArgs.modulePrefix}\"")
                    if (!moduleArgs.disableJSBrowser) {
                        out.println("        browser {")
                        out.println("            compilations.forEach{")
                        out.println("                it.kotlinOptions {")
                        out.println("                    freeCompilerArgs += \"-opt-in=kotlin.RequiresOptIn\"")
                        out.println("                    freeCompilerArgs += \"-Xnew-inference\"")
                        out.println("                }")
                        out.println("            }")
                        out.println("            dceTask {")
                        out.println("                keep(\"Luposdate3000_Endpoint.lupos.endpoint.LuposdateEndpoint\")")
                        out.println("                keep(\"Luposdate3000_Endpoint.lupos.endpoint.EndpointExtendedVisualize\")")
                        //                  out.println("                dceOptions.devMode = true")//this disables dce - which than breaks spa-client
                        out.println("            }")
                        out.println("            testTask {")
                        out.println("                useKarma {")
                        out.println("                    useFirefox()")
//                        out.println("                    useChrome()")
//                        out.println("                    useSafari()")
                        out.println("                }")
                        out.println("            }")
                        out.println("        }")
                    }
                    if (!moduleArgs.disableJSNode) {
                        out.println("        nodejs {")
                        out.println("            compilations.forEach{")
                        out.println("                it.kotlinOptions {")
                        out.println("                    freeCompilerArgs += \"-opt-in=kotlin.RequiresOptIn\"")
                        out.println("                    freeCompilerArgs += \"-Xnew-inference\"")
                        out.println("                }")
                        out.println("            }")
                        out.println("            testTask {")
                        out.println("                enabled = false")
                        out.println("            }")
                        out.println("        }")
                    }
                    out.println("        binaries.executable()")
                    out.println("    }")
                }
                if (enableNative) {
                    out.println("    ${moduleArgs.platform}(\"${moduleArgs.platform}\") {")
                    out.println("        compilations.forEach{")
                    out.println("            it.kotlinOptions {")
                    out.println("                freeCompilerArgs += \"-opt-in=kotlin.RequiresOptIn\"")
                    out.println("                freeCompilerArgs += \"-Xnew-inference\"")
                    out.println("            }")
                    out.println("        }")
                    out.println("        binaries {")
                    if (buildLibrary) {
                        if (moduleArgs.releaseMode == ReleaseMode.Enable) {
                            out.println("            sharedLib (listOf(RELEASE)){")
                            out.println("                baseName = \"${moduleArgs.modulePrefix}\"")
                            out.println("            }")
                        } else {
                            out.println("            sharedLib (listOf(DEBUG)){")
                            out.println("                baseName = \"${moduleArgs.modulePrefix}\"")
                            out.println("            }")
                        }
                    } else {
                        if (moduleArgs.releaseMode == ReleaseMode.Enable) {
                            out.println("            executable(listOf(RELEASE)) {")
                            out.println("            }")
                        } else {
                            out.println("            executable(listOf(DEBUG)) {")
                            out.println("            }")
                        }
                    }
                    out.println("        }")
                    out.println("    }")
                }
                out.println("    sourceSets {")
                out.println("        val commonMain by getting {")
                out.println("            dependencies {")
                printDependencies(commonDependencies, out, moduleArgs)
                out.println("            }")
                out.println("        }")
                out.println("        val commonTest by getting {")
                out.println("            dependencies {")
                out.println("                implementation(kotlin(\"test-common\"))")
                out.println("                implementation(kotlin(\"test-annotations-common\"))")
                out.println("            }")
                out.println("        }")

                if (enableJVM) {
                    out.println("        val jvmMain by getting {")
                    out.println("            dependencies {")
                    printDependencies(jvmDependencies, out, moduleArgs)
                    if (moduleArgs.codegenKAPT) {
                        printDependencies(moduleArgs.dependenciesJvm, out, moduleArgs)
                    }
                    if (!buildLibrary && moduleArgs.codegenKSP) {
                        printDependencies(moduleArgs.dependenciesJvm, out, moduleArgs)
                        for (dep in moduleArgs.dependenciesJvm) {
                            if (dep.startsWith("luposdate")) {
                                out.println("                configurations[\"ksp\"].dependencies.add(project.dependencies.create(project(\":src:${dep.lowercase().replace("luposdate3000:", "").replace(":0.0.1", "")}\")))")
                            } else {
                                out.println("                configurations[\"ksp\"].dependencies.add(project.dependencies.create(\"$dep\"))")
                            }
                        }
                    }
                    out.println("            }")
                    out.println("        }")

                    out.println("        val jvmTest by getting {")
                    out.println("            dependencies {")
                    out.println("                implementation(kotlin(\"test\"))")
                    out.println("                implementation(kotlin(\"test-junit\"))")
                    out.println("            }")
                    out.println("        }")
                }
                if (enableJS) {
                    out.println("        val jsMain by getting {")
                    out.println("            dependencies {")
                    printDependencies(jsDependencies, out, moduleArgs)
                    out.println("            }")
                    out.println("        }")
                }
                if (enableNative) {
                    out.println("        val ${moduleArgs.platform}Main by getting {")
                    out.println("            dependencies {")
                    printDependencies(nativeDependencies, out, moduleArgs)
                    out.println("            }")
                    out.println("        }")
                }
                out.println("    }")
                for (bb in setOf(shared_inline_base_folder, shared_config_base_folder)) {
                    if (!moduleArgs.moduleName.startsWith("Luposdate3000_Shared_")) {
                        out.println("    sourceSets[\"commonMain\"].kotlin.srcDir(\"$bb/src/commonMain/kotlin\")")
                        out.println("    sourceSets[\"commonMain\"].resources.srcDir(\"$bb/src/commonMain/resources\")")

                        if (enableJVM) {
                            out.println("    sourceSets[\"jvmMain\"].kotlin.srcDir(\"$bb/src/jvmMain/kotlin\")")
                            out.println("    sourceSets[\"jvmMain\"].resources.srcDir(\"$bb/src/jvmMain/resources\")")
                        }
                        if (enableJS) {
                            out.println("    sourceSets[\"jsMain\"].kotlin.srcDir(\"$bb/src/jsMain/kotlin\")")
                            out.println("    sourceSets[\"jsMain\"].resources.srcDir(\"$bb/src/jsMain/resources\")")
                        }
                        if (enableNative) {
                            out.println("    sourceSets[\"${moduleArgs.platform}Main\"].kotlin.srcDir(\"$bb/src/nativeMain/kotlin\")")
                            out.println("    sourceSets[\"${moduleArgs.platform}Main\"].kotlin.srcDir(\"$bb/src/${moduleArgs.platform}Main/resources\")")
                            out.println("    sourceSets[\"${moduleArgs.platform}Main\"].resources.srcDir(\"$bb/src/nativeMain/kotlin\")")
                            out.println("    sourceSets[\"${moduleArgs.platform}Main\"].resources.srcDir(\"$bb/src/${moduleArgs.platform}Main/resources\")")
                        }
                    }
                }
                if (enableNative) {
                    out.println("    sourceSets[\"${moduleArgs.platform}Main\"].kotlin.srcDir(\"nativeMain/kotlin\")")
                }
                out.println("}")
                if (moduleArgs.codegenKAPT) {
                    out.println("dependencies {")
                    out.println("    \"kapt\"(project(\":src:luposdate3000_code_generator_kapt\")) // attention to the '\"' around kapt - otherwise it resolves to another function")
                    out.println("}")
                }
                if (!buildLibrary) {
                    out.println("application{")
                    out.println("    mainClass.set(\"MainKt\")")
                    out.println("}")
                }
                out.println("tasks.register(\"luposSetup\") {")
                out.println("    fun fixPathNames(s: String): String {")
                out.println("        var res = s.trim()")
                out.println("        var back = \"\"")
                out.println("        while (back != res) {")
                out.println("            back = res")
                out.println("            res = res.replace(\"\\\\\", \"/\").replace(\"/./\", \"/\").replace(\"//\", \"/\")")
                out.println("        }")
                out.println("        return res")
                out.println("    }")
                out.println("    val regexDisableNoInline = \"(^|[^a-zA-Z])noinline \".toRegex()")
                out.println("    val regexDisableInline = \"(^|[^a-zA-Z])inline \".toRegex()")
                out.println("    val regexDisableCrossInline = \"(^|[^a-zA-Z])crossinline \".toRegex()")
                out.println("    for (bp in listOf(File(buildDir.parentFile, \"/src\"), File(rootDir, \"src/luposdate3000_shared_inline/src\"))) {")
                out.println("        for (it in bp.walk()) {")
                out.println("            val tmp = it.toString()")
                out.println("            val ff = File(tmp)")
                out.println("            if (ff.isFile && ff.name.endsWith(\".kt\")) {")
                out.println("                File(ff.absolutePath + \".tmp\").printWriter().use { out ->")
                out.println("                    var line = 0")
                out.println("                    ff.forEachLine { line2 ->")
                out.println("                        var s = line2")
                out.println("                        s = s.replace(\"SOURCE_FILE_START.*SOURCE_FILE_END\".toRegex(), \"SOURCE_FILE_START*/\\\"\${fixPathNames(ff.absolutePath)}:\$line\\\"/*SOURCE_FILE_END\")")
//                out.println("                        s = s.replace(\" public \", \" @lupos.ProguardKeepAnnotation public \")")
                if (moduleArgs.inlineMode == InlineMode.Enable) {
                    out.println("                        s = s.replace(\"/*NOINLINE*/\", \"noinline \")")
                    out.println("                        s = s.replace(\"/*CROSSINLINE*/\", \"crossinline \")")
                    out.println("                        s = s.replace(\"/*INLINE*/\", \"inline \")")
                } else {
                    out.println("                        s = s.replace(\"noinline \", \"/*NOINLINE*/\")")
                    out.println("                        s = s.replace(\"crossinline \", \"/*CROSSINLINE*/\")")
                    out.println("                        s = s.replace(\"inline \", \"/*INLINE*/\")")
                }
                out.println("                        out.println(s)")
                out.println("                        line++")
                out.println("                    }")
                out.println("                }")
                out.println("                File(ff.absolutePath + \".tmp\").copyTo(ff, true)")
                out.println("                File(ff.absolutePath + \".tmp\").delete()")
                out.println("            }")
                out.println("        }")
                out.println("    }")
                out.println("}")
                if (enableJVM) {
                    out.println("tasks.named(\"compileKotlinJvm\") {")
                    out.println("    dependsOn(\"luposSetup\")")
                    out.println("    doLast {")
                    out.println("        File(buildDir, \"external_jvm_dependencies\").printWriter().use { out ->")
                    out.println("            for (f in configurations.getByName(\"jvmRuntimeClasspath\").resolve()) {")
                    out.println("                if (!\"\$f\".contains(\"luposdate3000\")) {")
                    out.println("                    out.println(\"\$f\")")
                    out.println("                }")
                    out.println("            }")
                    out.println("        }")
                    out.println("    }")
                    out.println("}")
                }
                if (enableJS) {
                    out.println("tasks.named(\"compileKotlinJs\") {")
                    out.println("    dependsOn(\"luposSetup\")")
                    out.println("    doLast {")
                    out.println("        File(buildDir,\"external_js_dependencies\").printWriter().use { out ->")
                    out.println("            for (f in configurations.getByName(\"jsRuntimeClasspath\").resolve()) {")
                    out.println("                if (!\"\$f\".contains(\"luposdate3000\")) {")
                    out.println("                    out.println(\"\$f\")")
                    out.println("                }")
                    out.println("            }")
                    out.println("        }")
                    out.println("    }")
                    out.println("}")
                }
                if (enableNative) {
                    out.println("tasks.named(\"compileKotlinNative\") {")
                    out.println("    dependsOn(\"luposSetup\")")
                    out.println("}")
                }
                out.println("tasks.named(\"build\") {")
                out.println("}")
                if (useKTLint) {
                    out.println("configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {")
                    out.println("    enableExperimentalRules.set(true)")
                    out.println("    ignoreFailures.set(true)")
                    out.println("    filter {")
                    out.println("        exclude(\"**/build/**\")")
                    out.println("    }")
                    out.println("}")
                }
                if (enableProguard) {
                    out.println("tasks.register<proguard.gradle.ProGuardTask>(\"proguard\") {")
                    out.println("    dependsOn(\"build\")")
                    out.println("    val javaHome = System.getProperty(\"java.home\")")
                    out.println("    if (System.getProperty(\"java.version\").startsWith(\"1.\")) {")
                    out.println("        libraryjars(\"\$javaHome/lib/rt.jar\")")
                    out.println("    } else {")
                    out.println("        libraryjars(")
                    out.println("            mapOf(")
                    out.println("                \"jarfilter\" to \"!**.jar\",")
                    out.println("                \"filter\" to \"!module-info.class\"")
                    out.println("            ),")
                    out.println("            \"\$javaHome/jmods/java.base.jmod\"")
                    out.println("        )")
                    out.println("    }")
                    out.println("    for (f in configurations.getByName(\"jvmCompileClasspath\").resolve()) {")
                    out.println("            if (\"\$f\".contains(\"luposdate3000\")) {")
                    out.println("                injars(")
                    out.println("                    mapOf(")
                    out.println("                        \"filter\" to \" !META-INF/MANIFEST.MF,!lupos/shared/inline/** \"")
                    out.println("                    ),")
                    out.println("                    files(\"\$f\")")
                    out.println("                )")
                    out.println("            } else {")
                    out.println("                libraryjars(files(\"\$f\"))")
                    out.println("            }")
                    out.println("    }")
                    out.println("    injars(\"build/libs/${moduleArgs.moduleName.lowercase()}-jvm-0.0.1.jar\")")
                    out.println("    printusage(\"usage.pro\")")
                    out.println("    forceprocessing()")
                    out.println("    optimizationpasses(5)")
                    out.println("    allowaccessmodification()")
                    out.println("    dontobfuscate()")
                    out.println("    printconfiguration(\"config.pro.generated\")")
                    out.println("    printmapping(\"build/mapping.txt\")")
                    out.println("    keep(\"public class MainKt { public static void main(java.lang.String[]); }\")")
                    out.println("    outjars(\"build/libs/${moduleArgs.moduleName.lowercase()}-jvm-0.0.1-pro.jar\")")
                    out.println("}")
                }
                out.println("tasks.withType<Test> {")
                out.println("    maxHeapSize = \"2g\"")
                out.println("    maxParallelForks = 20")
                out.println("    testLogging {")
                out.println("        exceptionFormat = TestExceptionFormat.FULL")
                out.println("        showStandardStreams = true")
                out.println("        events.add(TestLogEvent.STARTED)")
                out.println("        events.add(TestLogEvent.FAILED)")
                out.println("        events.add(TestLogEvent.PASSED)")
                out.println("        events.add(TestLogEvent.SKIPPED)")
                out.println("        events.add(TestLogEvent.STANDARD_OUT)")
                out.println("        events.add(TestLogEvent.STANDARD_ERROR)")
                out.println("    }")
                if (useKover) {
                    out.println("        extensions.configure(kotlinx.kover.api.KoverTaskExtension::class) {")
                    out.println("            isEnabled = true")
//out.println("            binaryReportFile.set(file(\"\$buildDir/custom/kover_result.bin\"))")
                    out.println("            includes = listOf(\"lupos\\\\..*\")")
                    out.println("            excludes = listOf(\"java\\\\..*\")")
                    out.println("        }")
                }
                out.println("}")
                if (useKover) {
                    out.println("    tasks.koverHtmlReport {")
                    out.println("        isEnabled = true                        ")
//out.println("        htmlReportDir.set(layout.buildDirectory.dir(\"my-reports/html-result\"))")
                    out.println("    }")
                    out.println("    tasks.koverXmlReport {")
                    out.println("        isEnabled = true                        ")
//out.println("        xmlReportFile.set(layout.buildDirectory.file(\"my-reports/result.xml\"))")
                    out.println("    }")
                    out.println("    tasks.koverCollectReports {")
//out.println("        outputDir.set(layout.buildDirectory.dir(\"my-reports-dir\"))")
                    out.println("    }")
                    out.println("    kover {")
                    out.println("        isEnabled = true                        ")
                    out.println("        coverageEngine.set(kotlinx.kover.api.CoverageEngine.INTELLIJ) ")
                    out.println("        //coverageEngine.set(kotlinx.kover.api.CoverageEngine.JACOCO) ")
                    out.println("        intellijEngineVersion.set(\"1.0.637\")    ")
                    out.println("        jacocoEngineVersion.set(\"0.8.7\")        ")
                    out.println("        generateReportOnCheck.set(true)         ")
                    out.println("    }")
                }
            }
        }
        val typeAliasAll = mutableMapOf<String, Pair<String, String>>()
        val typeAliasUsed = mutableMapOf<String, Pair<String, String>>()
        dummy += when (moduleArgs.dictionaryValueMode) {
            EDictionaryValueMode.Int -> {
                typeAliasAll["DictionaryValueHelper"] = Pair("DictionaryValueHelper", "lupos.shared.inline.DictionaryValueHelperInt")
                typeAliasAll["DictionaryValueType"] = Pair("DictionaryValueType", "Int")
                typeAliasAll["DictionaryValueTypeArray"] = Pair("DictionaryValueTypeArray", "IntArray")
                0
            }
            EDictionaryValueMode.Long -> {
                typeAliasAll["DictionaryValueHelper"] = Pair("DictionaryValueHelper", "lupos.shared.inline.DictionaryValueHelperLong")
                typeAliasAll["DictionaryValueType"] = Pair("DictionaryValueType", "Long")
                typeAliasAll["DictionaryValueTypeArray"] = Pair("DictionaryValueTypeArray", "LongArray")
                0
            }
        }

        if (moduleArgs.releaseMode == ReleaseMode.Enable) {
            typeAliasAll["SanityCheck"] = Pair("SanityCheck", "lupos.shared.inline.SanityCheckOff")
            typeAliasAll["BufferManagerPageWrapper"] = Pair("BufferManagerPageWrapper", "lupos.shared.BufferManagerPageWrapperRelease")
            typeAliasAll["BufferManagerPage"] = Pair("BufferManagerPage", "lupos.shared.inline.BufferManagerPageRelease")
        } else {
            typeAliasAll["SanityCheck"] = Pair("SanityCheck", "lupos.shared.inline.SanityCheckOn")
            typeAliasAll["BufferManagerPageWrapper"] = Pair("BufferManagerPageWrapper", "lupos.shared.BufferManagerPageWrapperDebug")
            typeAliasAll["BufferManagerPage"] = Pair("BufferManagerPage", "lupos.shared.inline.BufferManagerPageDebug")
        }
        if (moduleArgs.suspendMode == SuspendMode.Enable) {
            typeAliasAll["Parallel"] = Pair("Parallel", "lupos.shared.inline.ParallelCoroutine")
            typeAliasAll["ParallelJob"] = Pair("ParallelJob", "lupos.shared.inline.ParallelCoroutineJob")
            typeAliasAll["ParallelCondition"] = Pair("ParallelCondition", "lupos.shared.inline.ParallelCoroutineCondition")
            typeAliasAll["ParallelQueue"] = Pair("ParallelQueue<T>", "lupos.shared.inline.ParallelCoroutineQueue<T>")
            typeAliasAll["MyLock"] = Pair("MyLock", "lupos.shared.inline.MyCoroutineLock")
            typeAliasAll["MyReadWriteLock"] = Pair("MyReadWriteLock", "lupos.shared.inline.MyCoroutineReadWriteLock")
        } else {
            typeAliasAll["Parallel"] = Pair("Parallel", "lupos.shared.inline.ParallelThread")
            typeAliasAll["ParallelJob"] = Pair("ParallelJob", "lupos.shared.ParallelThreadJob")
            typeAliasAll["ParallelCondition"] = Pair("ParallelCondition", "lupos.shared.inline.ParallelThreadCondition")
            typeAliasAll["ParallelQueue"] = Pair("ParallelQueue<T>", "lupos.shared.inline.ParallelThreadQueue<T>")
            typeAliasAll["MyLock"] = Pair("MyLock", "lupos.shared.MyThreadLock")
            typeAliasAll["MyReadWriteLock"] = Pair("MyReadWriteLock", "lupos.shared.inline.MyThreadReadWriteLock")
        }
// selectively copy classes which are inlined from the inline internal module ->
        val classNamesRegex = Regex("\\s*([a-zA-Z0-9_]*)")
        val classNamesFound = mutableMapOf<String, MutableSet<String>>()
        if (!moduleArgs.moduleName.startsWith("Luposdate3000_Shared_")) {
            for (f in File("src/luposdate3000_shared_inline/src").walk()) {
                if (f.isFile) {
                    try {
                        f.forEachLine { it ->
                            var tmp = ""
                            var idxClass = it.indexOf("class")
                            if (idxClass >= 0) {
                                tmp = it.substring(idxClass + 5)
                            } else {
                                var idxObject = it.indexOf("object")
                                if (idxObject >= 0) {
                                    tmp = it.substring(idxObject + 6)
                                } else {
                                    var idxInterface = it.indexOf("interface")
                                    if (idxInterface >= 0) {
                                        tmp = it.substring(idxInterface + 9)
                                    }
                                }
                            }
                            if (tmp.length > 0) {
                                var tmp2 = classNamesRegex.find(tmp)!!.groupValues[1]
                                if (tmp2.startsWith("_")) {
                                    tmp2 = tmp2.substring(1)
                                }
                                if (tmp2.length > 0) {
                                    val tmp3 = classNamesFound[tmp2]
                                    if (tmp3 == null) {
                                        classNamesFound[tmp2] = mutableSetOf(f.toString())
                                    } else {
                                        tmp3.add(f.toString())
                                    }
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        e.myPrintStackTrace(/*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ )
                    }
                }
            }
            typeAliasUsed.putAll(typeAliasAll)
            if (moduleArgs.intellijMode == IntellijMode.Enable) {
                try {
                    copyFilesWithReplacement(("src/luposdate3000_shared_inline/src/commonMain"), ("$shared_inline_base_folder/src/commonMain"), replacementsDefault, sharedInlineReferences)
                } catch (e: Throwable) {
                    e.myPrintStackTrace(/*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ )
                }
                try {
                    copyFilesWithReplacement(("src/luposdate3000_shared_inline/src/jvmMain"), ("$shared_inline_base_folder/src/jvmMain"), replacementsDefault, sharedInlineReferences)
                } catch (e: Throwable) {
                    e.myPrintStackTrace(/*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ )
                }
                try {
                    copyFilesWithReplacement(("src/luposdate3000_shared_inline/src/jsMain"), ("$shared_inline_base_folder/src/jsMain"), replacementsDefault, sharedInlineReferences)
                } catch (e: Throwable) {
                    e.myPrintStackTrace(/*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ )
                }
                try {
                    copyFilesWithReplacement(("src/luposdate3000_shared_inline/src/nativeMain"), ("$shared_inline_base_folder/src/nativeMain"), replacementsDefault, sharedInlineReferences)
                } catch (e: Throwable) {
                    e.myPrintStackTrace(/*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ )
                }
            }
        }
        var configFile: String
        configFile = "$shared_config_base_folder/src/commonMain/kotlin/lupos/shared/Config-${moduleArgs.moduleName}.kt"
        println(typeAliasUsed.keys)
        println()
        // selectively copy classes which are inlined from the inline internal module <-
        val remainingArgs = mutableMapOf<String, String>()
        remainingArgs.putAll(moduleArgs.args)
        File(configFile).printWriter().use { out ->
            out.println("package lupos.shared")
            for (v in typeAliasUsed.values) {
                out.println("internal typealias ${v.first} = ${v.second}")
            }
            for (f in listOf("${moduleArgs.moduleFolder}/configOptions", "src/luposdate3000_shared_inline/configOptions")) {
                if (File(f).exists()) {
                    File(f).forEachLine {
                        val opt = it.split(",")
                        if (opt.size == 4) {
                            var value = opt[3]
                            val v = remainingArgs[opt[0]]
                            if (v != null) {
                                value = v
                                remainingArgs.remove(opt[0])
                            }
                            if (opt[1] == "typealias") {
                                out.println("public ${opt[1]} ${opt[0]} = $value")
                            } else {
                                out.println("${opt[1]} ${opt[0]}: ${opt[2]} = $value")
                            }
                        }
                    }
                }
            }
        }
        if (remainingArgs.size > 0) {
            for ((k, v) in remainingArgs) {
                println("unknown argument '$k' = '$v'")
            }
            throw Exception("unknown arguments")
        }
        try {
            File(srcFolder).deleteRecursively()
        } catch (e: Throwable) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ )
        }
        try {
            File(buildFolder).deleteRecursively()
        } catch (e: Throwable) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ )
        }
        for (f in listOf(File(File(srcFolder), "gradle.log"), File(File(srcFolder), "gradle.err"))) {
            if (f.exists()) {
                f.forEachLine {
                    println(it)
                }
            }
        }
    } catch (e: Throwable) {
        e.myPrintStackTrace(/*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ )
        println("$dummy")
        throw e
    }
}

public fun runCommand(command: List<String>, workingDir: File) {
    val p = ProcessBuilder(command)
        .directory(workingDir)
        .redirectOutput(Redirect.INHERIT)
        .redirectError(Redirect.INHERIT)
        .start()
    p.waitFor()
    if (p.exitValue() != 0) {
        throw Exception("executing '$command' failed")
    }
}
